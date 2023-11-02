package com.example.marketdota.controller;

import com.example.marketdota.model.HeroModel;
import com.example.marketdota.model.RaitModel;
import com.example.marketdota.repo.HeroRepo;
import com.example.marketdota.repo.RaitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/hero")
@PreAuthorize("hasAnyAuthority('EMPLOYEE', 'ADMIN')")
public class HeroController {
    @Autowired
    private HeroRepo Repo;
    @Autowired
    private RaitRepo raitRepo;

    @Autowired
    public HeroController(HeroRepo Repo) {
        this.Repo = Repo;
    }

    @GetMapping()
    public String listHero(Model model) {
        Iterable<HeroModel> heroModels = Repo.findAll();
        model.addAttribute("heros", heroModels);
        return "hero/all";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        Iterable<RaitModel> raitModels = raitRepo.findAll();
        model.addAttribute("rait", raitModels);
        return "hero/add";
    }

    @PostMapping("/add")
    public String add(@RequestParam long id, @RequestParam String name, @RequestParam String attrib) {
        HeroModel heroModel = new HeroModel(id, name,attrib);
        Repo.save(heroModel);
        return "redirect:/hero";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        HeroModel heroModel = Repo.findById(id).orElse(null);
        if (heroModel == null) {
            return "redirect:/hero";
        }
        model.addAttribute("hero", heroModel);
        return "hero/update";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, @Valid @ModelAttribute("hero") HeroModel heroModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "hero/update"; // Останется на странице редактирования с отображением ошибок
        }
        heroModel.setId(id);
        Repo.save(heroModel);
        return "redirect:/hero"; // Перенаправление на страницу со всеми героями
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        Repo.deleteById(id);
        return "redirect:/hero";
    }

    @GetMapping("/search")
    public String search(@RequestParam(name = "name") String name, Model model) {
        Iterable<HeroModel> heroModels = Repo.findByNameContainingIgnoreCase(name);
        model.addAttribute("heros", heroModels);
        return "hero/all";
    }
}
