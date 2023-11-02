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
@RequestMapping("/lesson")
@PreAuthorize("hasAnyAuthority('EMPLOYEE')")
public class LessonsController {

    @Autowired
    public LessonsRepo Repo;
    @Autowired
    public CoachesRepo coachesRepo;
    @Autowired
    public CategoryRepo categoryRepo;
    @PersistenceContext
    private EntityManager entityManager;


    @GetMapping()
    public String listLessons(Model model) {
        Iterable<LessonsModel> lessonsModels = Repo.findAll();
        model.addAttribute("lessons", lessonsModels);
        return "lesson/all";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        LessonsModel lessonsModel = new LessonsModel();
        model.addAttribute("lessons", lessonsModel);

        Iterable<CoachesModel> coachesModels = coachesRepo.findAll();
        model.addAttribute("coachess", coachesModels);

        Iterable<CategoryModel> categoryModels = categoryRepo.findAll();
        model.addAttribute("categories", categoryModels);

        return "lesson/add";
    }


    @PostMapping("/add")
    public String add(
            @Valid @ModelAttribute("lesson") LessonsModel lessonsModel,
            BindingResult bindingResult) {
        // Сохранение HeroModel с выбранным оружием
        Repo.save(lessonsModel);

        // После сохранения героя перенаправьтесь на страницу со списком героев
        return "redirect:/lesson";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        LessonsModel lessonsModel = Repo.findById(id).orElse(null);
        if (lessonsModel == null) {
            return "redirect:/lesson";
        }
        Iterable<CoachesModel> coachesModels = coachesRepo.findAll();
        model.addAttribute("coachess", coachesModels);

        Iterable<CategoryModel> categoryModels = categoryRepo.findAll();
        model.addAttribute("categories", categoryModels);

        model.addAttribute("lessons", lessonsModel);
        return "lesson/update";
    }

    @PostMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") long id,
            @Valid @ModelAttribute("lesson") LessonsModel lessonsModel,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            Iterable<CoachesModel> coachesModels = coachesRepo.findAll();
            model.addAttribute("coachess", coachesModels);

            Iterable<CategoryModel> categoryModels = categoryRepo.findAll();
            model.addAttribute("categories", categoryModels);

            return "lesson/update";
        }
        lessonsModel.setId(id);

        Repo.save(lessonsModel);
        return "redirect:/lesson";
    }


    @GetMapping("/delete/{id}")
    public String deleteHero(@PathVariable("id") long id) {
        Repo.deleteById(id);
        return "redirect:/lesson";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam(name = "name") String name, Model model) {
        Iterable<LessonsModel> lessonsModels = Repo.findByNameContainingIgnoreCase(name);
        model.addAttribute("lesson", lessonsModels);
        return "lesson/all";
    }
}
