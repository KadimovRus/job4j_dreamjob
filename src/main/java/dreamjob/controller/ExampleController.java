package dreamjob.controller;

import dreamjob.model.InformationOfController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/examples")
public class ExampleController {

    @GetMapping
    public String examples(Model model) {
        List<InformationOfController> controllerList = new ArrayList<>();
        controllerList.add(new InformationOfController(
                "http//:localhost:8080/examples",
                "ExampleController.examples(Model model)"));
        controllerList.add(new InformationOfController(
                "http//:localhost:8080/candidates",
                "CandidateController.getAll(Model model)"));
        controllerList.add(new InformationOfController(
                "http//:localhost:8080/candidates/create",
                "CandidateController.create()"));
        controllerList.add(new InformationOfController(
                "http//:localhost:8080/vacancies",
                "VacancyController.getAll(Model model)"));
        controllerList.add(new InformationOfController(
                "http//:localhost:8080/vacancies/create",
                "VacancyController.create()"));
        model.addAttribute("list", controllerList);
        return "examples/list";
    }
}
