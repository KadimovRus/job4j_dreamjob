package dreamjob.controller;


import dreamjob.model.Vacancy;
import dreamjob.repository.MemoryVacancyRepository;
import dreamjob.repository.VacancyRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/vacancies")
public class VacancyController {
    private final VacancyRepository vacancyRepository = MemoryVacancyRepository.getInstance();

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("vacancies", vacancyRepository.findAll());
        return "vacancies/list";
    }

    @GetMapping("/create")
    public String getCreationPage() {
        return "vacancies/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Vacancy vacancy) {
        vacancy.setCreationDate(LocalDateTime.now());
        vacancyRepository.save(vacancy);
        return "redirect:/vacancies";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id) {
        var vacancyOptional = vacancyRepository.findById(id);
        if (vacancyOptional.isEmpty()) {
            model.addAttribute("message", "Вакансия с указанным идентификатором не найдена");
            return "errors/404";
        }
        model.addAttribute("vacancy", vacancyOptional.get());
        return "vacancies/one";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Vacancy vacancy, Model model) {
        var isUpdated = vacancyRepository.update(vacancy);
        if (!isUpdated) {
            model.addAttribute("message", "Вакансия с указанным идентификатором не найдена");
            return "errors/404";
        }
        return "redirect:/vacancies";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        var isDeleted = vacancyRepository.deleteById(id);
        if (!isDeleted) {
            model.addAttribute("message", "Вакансия с указанным идентификатором не найдена");
            return "errors/404";
        }
        return "redirect:/vacancies";
    }
}
