package mimimimetr.controller;

import lombok.RequiredArgsConstructor;
import mimimimetr.entity.UserEntity;
import mimimimetr.form.CatVoteForm;
import mimimimetr.service.CatService;
import mimimimetr.service.UserService;
import mimimimetr.service.VotingService;
import mimimimetr.util.CatsFabrik;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final VotingService votingService;

    private final CatService catService;

    private final UserService userService;

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/vote")
    public String vote(Principal principal, Model model) {
        getVoteList(principal, model);
        return "vote";
    }

    @PostMapping("/vote")
    public String vote(@RequestParam Long like, @RequestParam Long unLike, Principal principal, Model model) {
        if (!votingService.vote(principal, like, unLike)) {
            model.addAttribute("msg", "Разрешается голосовать только один раз за кота!");
        }
        if (getVoteList(principal, model)) {
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
        CatsFabrik.getCatList().stream().forEach(catService::save);
        return "redirect:/";
    }

    private boolean getVoteList(final Principal principal, final Model model) {
        UserEntity userEntity = userService.getByName(principal.getName());
        CatVoteForm catVoteForm = votingService.nextCandidates(userEntity);
        if (catVoteForm != null) {
            model.addAttribute("catVoteForm", catVoteForm);
            return true;
        } else {
            model.addAttribute("msg", "Вы проголосовали за всех котов!");
            return false;
        }
    }

}