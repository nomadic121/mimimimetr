package mimimimetr.controller;

import lombok.RequiredArgsConstructor;
import mimimimetr.entity.Cat;
import mimimimetr.entity.User;
import mimimimetr.service.CatService;
import mimimimetr.service.UserService;
import mimimimetr.service.VotingService;
import mimimimetr.util.CatsFabrik;
import mimimimetr.util.SessionScopedBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final @NonNull
    SessionScopedBean sessionScopedBean;

    private final @NonNull
    VotingService votingService;

    private final @NonNull
    CatService catService;

    private final @NonNull
    UserService userService;

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/vote")
    public String vote(Principal principal,
                       Model model) {
        User user = userService.getByName(principal.getName());
        getVoteList(user, model);
        return "vote";
    }

    @PostMapping("/vote")
    public String vote(@RequestParam Long like,
                       @RequestParam String pair,
                       Principal principal,
                       Model model) {
        User user = userService.getByName(principal.getName());
        try {
            votingService.vote(user, like, pair);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("msg", "Разрешается голосовать только один раз за кота!");
        }
        if (getVoteList(user, model)) {
            return "vote";
        } else {
            return "redirect:/results";
        }
    }

    @GetMapping("/results")
    public String results(Model model) {
        model.addAttribute("cats", votingService.getResults());
        return "results";
    }

    @GetMapping("/init")
    public String initCats(Model model) {
        CatsFabrik.getCatList().stream().forEach(catService::Save);
        userService.Save(User.builder().name("user").build());
        return "redirect:/";
    }

    private boolean getVoteList(final User user, final Model model) {
        List<Cat> catList = votingService.nextCandidates(user);
        if (catList != null && catList.size() >= 2) {
            String pair = String.format("%s:%s", catList.get(0).getId(), catList.get(1).getId());
            model.addAttribute("cats", catList);
            model.addAttribute("pair", pair);
            return true;
        } else {
            model.addAttribute("msg", "Вы проголосовали за всех котов!");
            return false;
        }
    }

}