package com.mycompany.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired private UserService service;

    @GetMapping("/users")
    public  String showUserList(Model model){
        List<User> listusers = service.ListAll();
        model.addAttribute("listUsers",listusers);
//        service.listAll();
        return "users";

    }
    @GetMapping("/users/new")
    public String showNewForm(Model model){
    model.addAttribute("user", new User());
    model.addAttribute("pageTitle", "Add New User");
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(@ModelAttribute("user") User user, RedirectAttributes ra){
        service.save(user);
        ra.addFlashAttribute("message","The user has been saved successful");
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            User user =service.get(id);
            model.addAttribute("user",user);
            model.addAttribute("pageTitle", "Edit User (ID: "+ id + ")");
            return "user_form";
        } catch (UserNotFoundException e) {
//            throw new RuntimeException(e);
//            ra.addFlashAttribute("message", "The user has been save successfully.");
                ra.addFlashAttribute("errorMessage", "Could not find any users with ID " + id);

//            e.printStackTrace();
            return "redirect:/users";
        }
    }
}
