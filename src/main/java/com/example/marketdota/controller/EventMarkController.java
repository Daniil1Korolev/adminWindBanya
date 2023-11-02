package com.example.marketdota.controller;

import com.example.marketdota.model.*;
import com.example.marketdota.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Controller
@RequestMapping("/eventmark")
//@PreAuthorize("hasAnyAuthority('USER', 'EMPLOYEE', 'ADMIN')")
public class EventMarkController {

    @Autowired
    public LessonsRepo lessonsRepo;
    @Autowired
    public UserRepo userRepo;

    @PersistenceContext
    private EntityManager entityManager;


    @GetMapping()
    public String listEventMark(Model model) {
        Iterable<LessonsModel> lessonsModels = lessonsRepo.findAll();
        model.addAttribute("lessons", lessonsModels);
        Iterable<UserModel> userModels = userRepo.findAll();
        model.addAttribute("users", userModels);
        return "eventmark/all";
    }

    @GetMapping("/add")
    private String MainEventMark(Model model){
        Iterable<LessonsModel> lessonsModels = lessonsRepo.findAll();
        model.addAttribute("lessons", lessonsModels);
        Iterable<UserModel> userModels = userRepo.findAll();
        model.addAttribute("users", userModels);

        return "eventmark/add";
    }

    @PostMapping("/add")
    public String blogPostAdd(@RequestParam String lesson, @RequestParam String user, RedirectAttributes redirectAttributes)
    {
        LessonsModel lessonsmodel = lessonsRepo.findByName(lesson);
        UserModel userModel = userRepo.findByEmail(user);
        userModel.getLesson().add(lessonsmodel);
        lessonsmodel.getUser().add(userModel);
        lessonsRepo.save(lessonsmodel);
        userRepo.save(userModel);

        // Добавьте атрибут "success" для сообщения об успешном добавлении
        redirectAttributes.addFlashAttribute("success", "Заклинание успешно добавлено в класс");

        // Перенаправьте пользователя на страницу "allSC"
        return "redirect:/eventmark";
    }
}
