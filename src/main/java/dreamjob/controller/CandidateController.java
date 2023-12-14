package dreamjob.controller;

import dreamjob.repository.MemoryCandidateRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/candidates")
public class CandidateController {

    private final MemoryCandidateRepository memoryCandidateRepository = MemoryCandidateRepository.getInstance();

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("candidates", memoryCandidateRepository.findAll());
        return "/candidates/list";
    }
}
