package com.example.banyaadmin.controller;

import com.example.banyaadmin.model.BanyaModel;
import com.example.banyaadmin.model.ZakazModel;
import com.example.banyaadmin.repo.BanyaRepo;
import com.example.banyaadmin.repo.ZakazRepo;
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
@RequestMapping("/zakaz")
@PreAuthorize("hasAnyAuthority('EMPLOYEE')")
public class ZakazController {

    @Autowired
    public ZakazRepo Repo; //hero
    @Autowired
    public BanyaRepo banyaRepo;
    @PersistenceContext
    private EntityManager entityManager;


    @GetMapping()
    public String listZakaz(Model model) {
        Iterable<ZakazModel> zakazModel = Repo.findAll();
        model.addAttribute("zakazs", zakazModel);
        return "zakaz/all";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        ZakazModel zakazModel = new ZakazModel();
        model.addAttribute("zakaz", zakazModel);

        // Получите список классов из репозитория и добавьте его в модель
        Iterable<BanyaModel> banyaModels = banyaRepo.findAll();
        model.addAttribute("banyas", banyaModels);

        return "zakaz/add";
    }



    @PostMapping("/add")
    public String addHero(
            @Valid @ModelAttribute("zakaz") ZakazModel zakazModel,
            BindingResult bindingResult) {
        // Сохранение HeroModel с выбранным оружием
        Repo.save(zakazModel);

        // После сохранения героя перенаправьтесь на страницу со списком героев
        return "redirect:/zakaz";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        ZakazModel zakazModel = Repo.findById(id).orElse(null);
        if (zakazModel == null) {
            return "redirect:/zakaz";
        }
        Iterable<BanyaModel> banyaModels = banyaRepo.findAll();
        model.addAttribute("banyas", banyaModels);

        model.addAttribute("zakaz", zakazModel);
        return "zakaz/update";
    }

    @PostMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") long id,
            @Valid @ModelAttribute("zakaz") ZakazModel zakazModel,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            Iterable<BanyaModel> banyaModels = banyaRepo.findAll();
            model.addAttribute("banyas", banyaModels);

            return "zakaz/update"; // Останется на странице редактирования с отображением ошибок
        }
        zakazModel.setId(id);

        Repo.save(zakazModel);
        return "redirect:/zakaz"; // Перенаправление на страницу со всеми героями
    }


    @GetMapping("/delete/{id}")
    public String deleteHero(@PathVariable("id") long id) {
        Repo.deleteById(id);
        return "redirect:/zakaz";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam(name = "name") String name, Model model) {
        Iterable<ZakazModel> zakazModel = Repo.findByNameContainingIgnoreCase(name);
        model.addAttribute("zakazs", zakazModel);
        return "zakaz/all";
    }

}
