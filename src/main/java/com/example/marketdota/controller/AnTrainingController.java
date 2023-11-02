package com.example.marketdota.controller;

import com.example.marketdota.model.*;
import com.example.marketdota.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@Controller
@RequestMapping("/antraining")
@PreAuthorize("hasAnyAuthority('EMPLOYEE', 'ADMIN')")
public class AnTrainingController {

    @Autowired
    public AnTrainingRepo Repo;
    @Autowired
    public CoachesRepo coachesRepo;
    @Autowired
    public HeroRepo categoryRepo;
    @PersistenceContext // Внедрение EntityManager
    private EntityManager entityManager;


    @GetMapping()
    public String listTrainings(Model model) {
        Iterable<AnTrainingModel> anTrainingModels = Repo.findAll();
        model.addAttribute("antrainings", anTrainingModels);
        return "antraining/all";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        AnTrainingModel anTrainingModel = new AnTrainingModel();
        model.addAttribute("antraining", anTrainingModel);

        // Получите список классов из репозитория и добавьте его в модель
        Iterable<CoachesModel> coachesModels = coachesRepo.findAll();
        model.addAttribute("coachess", coachesModels);

        Iterable<HeroModel> heroModels = categoryRepo.findAll();
        model.addAttribute("heros", heroModels);

        return "antraining/add";
    }


    @PostMapping("/add")
    public String add(
            @Valid @ModelAttribute("antraining") AnTrainingModel anTrainingModel,
            BindingResult bindingResult) {
        Repo.save(anTrainingModel);

        // После сохранения героя перенаправьтесь на страницу со списком героев
        return "redirect:/antraining";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        AnTrainingModel anTrainingModel = Repo.findById(id).orElse(null);
        if (anTrainingModel == null) {
            return "redirect:/antraining";
        }
        Iterable<CoachesModel> coachesModels = coachesRepo.findAll();
        model.addAttribute("coachess", coachesModels);

        Iterable<HeroModel> heroModels = categoryRepo.findAll();
        model.addAttribute("heros", heroModels);

        model.addAttribute("antraining", anTrainingModel);
        return "antraining/update";
    }

    @PostMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") long id,
            @Valid @ModelAttribute("antraining") AnTrainingModel anTrainingModel,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            Iterable<CoachesModel> coachesModels = coachesRepo.findAll();
            model.addAttribute("coachess", coachesModels);

            Iterable<HeroModel> heroModels = categoryRepo.findAll();
            model.addAttribute("hero", heroModels);

            return "antraining/update"; // Останется на странице редактирования с отображением ошибок
        }
        anTrainingModel.setId(id);

        Repo.save(anTrainingModel);
        return "redirect:/antraining"; // Перенаправление на страницу со всеми героями
    }


    @GetMapping("/delete/{id}")
    public String deleteHero(@PathVariable("id") long id) {
        Repo.deleteById(id);
        return "redirect:/antraining";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam(name = "month") String name, Model model) {
        Iterable<AnTrainingModel> anTrainingModels = Repo.findAllByMonthContainsIgnoreCase(name);
        model.addAttribute("antrainings", anTrainingModels);
        return "antraining/all";
    }
}
