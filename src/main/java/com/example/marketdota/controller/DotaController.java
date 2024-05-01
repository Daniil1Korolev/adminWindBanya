package com.example.marketdota.controller;

import com.example.marketdota.model.CoachesModel;
import com.example.marketdota.model.DotaModel;
import com.example.marketdota.model.UserModel;
import com.example.marketdota.repo.CoachesRepo;
import com.example.marketdota.repo.DotaRepo;
import com.example.marketdota.repo.UserRepo;
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
@RequestMapping("/dota")
@PreAuthorize("hasAnyAuthority('EMPLOYEE')")
public class DotaController {

    @Autowired
    public DotaRepo Repo; //hero
    @Autowired
    public CoachesRepo coachesRepo;
    @PersistenceContext
    private EntityManager entityManager;


    @GetMapping()
    public String listDota(Model model) {
        Iterable<DotaModel> dotaModel = Repo.findAll();
        model.addAttribute("dotas", dotaModel);
        return "dota/all";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        DotaModel dotaModel = new DotaModel();
        model.addAttribute("dota", dotaModel);

        // Получите список классов из репозитория и добавьте его в модель
        Iterable<CoachesModel> coachesModels = coachesRepo.findAll();
        model.addAttribute("coachess", coachesModels);

        return "dota/add";
    }



    @PostMapping("/add")
    public String addHero(
            @Valid @ModelAttribute("dota") DotaModel dotaModel,
            BindingResult bindingResult) {
        // Сохранение HeroModel с выбранным оружием
        Repo.save(dotaModel);

        // После сохранения героя перенаправьтесь на страницу со списком героев
        return "redirect:/dota";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        DotaModel dotaModel = Repo.findById(id).orElse(null);
        if (dotaModel == null) {
            return "redirect:/dota";
        }
        Iterable<CoachesModel> coachesModels = coachesRepo.findAll();
        model.addAttribute("coachess", coachesModels);

        model.addAttribute("dota", dotaModel);
        return "dota/update";
    }

    @PostMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") long id,
            @Valid @ModelAttribute("dota") DotaModel dotaModel,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            Iterable<CoachesModel> coachesModels = coachesRepo.findAll();
            model.addAttribute("coachess", coachesModels);

            return "dota/update"; // Останется на странице редактирования с отображением ошибок
        }
        dotaModel.setId(id);

        Repo.save(dotaModel);
        return "redirect:/dota"; // Перенаправление на страницу со всеми героями
    }


    @GetMapping("/delete/{id}")
    public String deleteHero(@PathVariable("id") long id) {
        Repo.deleteById(id);
        return "redirect:/dota";
    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam(name = "name") String name, Model model) {
        Iterable<DotaModel> dotaModel = Repo.findByNameContainingIgnoreCase(name);
        model.addAttribute("dota", dotaModel);
        return "dota/all";
    }

}
