package mimimimetr.controller;

import mimimimetr.util.CatsFabrik;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/vote")
    public String vote(Model model) {
        model.addAttribute("cats", CatsFabrik.getCatList());
        return "vote";
    }

    @PostMapping("/vote")
    public String vote(@RequestParam Long like, Model model) {
        model.addAttribute("cats", CatsFabrik.getCatList());
        System.out.println("### : " + like);
        return "vote";
    }

    @GetMapping("/results")
    public String results(Model model) {
        model.addAttribute("cats", CatsFabrik.getCatList());
        return "results";
    }

}