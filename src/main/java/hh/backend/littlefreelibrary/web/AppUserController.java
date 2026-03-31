package hh.backend.littlefreelibrary.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import hh.backend.littlefreelibrary.domain.AppUser;
import hh.backend.littlefreelibrary.domain.AppUserRepository;

@Controller
public class AppUserController {

    // Database repository
    private final AppUserRepository appUserRepository;

    public AppUserController(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    // CRUD operations
    // display user list
    @GetMapping("/user/list")
    public String listUsers(Model model) {
        model.addAttribute("users", appUserRepository.findAll());
        return "userlist";
    }

    // add a user form
    @GetMapping("/user/new")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new AppUser());
        return "userform";
    }

    // Save new user
    @GetMapping("/user/save")
    public String saveUser(AppUser user) {
        appUserRepository.save(user);
        return "redirect:/user/list";
    }

    // Delete user
    @GetMapping("/user/{id}/delete")
    public String deleteUser(@PathVariable("id") Integer id) {
        appUserRepository.deleteById(id);
        return "redirect:/user/list";
    }

    // Edit user form
    @GetMapping("/user/{id}/edit")
    public String showEditUserForm(@PathVariable("id") Integer id, Model model)
    {
        AppUser user = appUserRepository.findById(id).orElse(null);
        // If user not found, redirect to list
        if (user == null) {
            return "redirect:/user/list";
        }
        model.addAttribute("user", user);
        return "userform";
    }
    
}
