package com.example.banyaadmin.controller;

import com.example.banyaadmin.model.BanyaModel;
import com.example.banyaadmin.repo.BanyaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/banya")
@PreAuthorize("hasAnyAuthority('EMPLOYEE')")
public class BanyaController {
    private final BanyaRepo Repo;

    @Autowired
    public BanyaController(BanyaRepo Repo) {
        this.Repo = Repo;
    }

    @GetMapping()
    public String listBanya(Model model) {
        Iterable<BanyaModel> banyaModels = Repo.findAll();
        model.addAttribute("banyas", banyaModels);
        return "banya/all";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        BanyaModel banyaModel = new BanyaModel();
        //clas.setPublicationYear(0);
        model.addAttribute("banya", banyaModel);
        return "banya/add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute("banya") BanyaModel banyaModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "banya/add";
        }
        Repo.save(banyaModel);
        return "redirect:/banya";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        BanyaModel banyaModel = Repo.findById(id).orElse(null);
        if (banyaModel == null) {
            return "redirect:/banya";
        }
        model.addAttribute("banya", banyaModel);
        return "banya/update";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, @Valid @ModelAttribute("banya") BanyaModel banyaModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "banya/update"; // Останется на странице редактирования с отображением ошибок
        }
        banyaModel.setId(id);
        Repo.save(banyaModel);
        return "redirect:/banya"; // Перенаправление на страницу со всеми героями
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        Repo.deleteById(id);
        return "redirect:/banya";
    }

    @GetMapping("/search")
    public String search(@RequestParam(name = "email") String email, Model model) {
        Iterable<BanyaModel> banyaModels = Repo.findByNameContainingIgnoreCase(email);
        model.addAttribute("banyas", banyaModels);
        return "banya/all";
    }


}
