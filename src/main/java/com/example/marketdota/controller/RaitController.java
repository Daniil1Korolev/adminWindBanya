package com.example.marketdota.controller;

import com.example.marketdota.model.RaitModel;
import com.example.marketdota.repo.RaitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/rait")
@PreAuthorize("hasAnyAuthority('EMPLOYEE', 'ADMIN')")
public class RaitController {
    private final RaitRepo Repo;

    @Autowired
    public RaitController(RaitRepo Repo) {
        this.Repo = Repo;
    }

    @GetMapping()
    public String listRait(Model model) {
        Iterable<RaitModel> rait = Repo.findAll();
        model.addAttribute("rait", rait);
        return "rait/all";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        RaitModel raitModel = new RaitModel();
        //clas.setPublicationYear(0);
        model.addAttribute("rait", raitModel);
        return "rait/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("rait") RaitModel raitModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "rait/add";
        }
        System.out.println("Name: " + raitModel.getName());
        Repo.save(raitModel);
        return "redirect:/rait";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        RaitModel raitModel = Repo.findById(id).orElse(null);
        if (raitModel == null) {
            return "redirect:/rait";
        }
        model.addAttribute("rait", raitModel);
        return "rait/update";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, @Valid @ModelAttribute("rait") RaitModel raitModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "rait/update"; // Останется на странице редактирования с отображением ошибок
        }
        raitModel.setId(id);
        Repo.save(raitModel);
        return "redirect:/rait"; // Перенаправление на страницу со всеми героями
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        Repo.deleteById(id);
        return "redirect:/rait";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam(name = "code") String code, Model model) {
        Iterable<RaitModel> raitModels = Repo.findByNameContainingIgnoreCase(code);
        model.addAttribute("rait", raitModels);
        return "rait/all";
    }
}
