package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.Service.UsersService;
import web.entity.Users;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;


    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }


    @GetMapping(value = "/allUsers")
    public String usersPage(Model model) {
        model.addAttribute("users", usersService.readingAllUsers());
        return "allUsers";
    }

    @GetMapping(value = "/new")
    public String newUser(Model model) {
        model.addAttribute("user", new Users());
        return "newUser";
    }

    @PostMapping
    public String saveUser(@ModelAttribute("user") Users user) {
        usersService.saveUser(user);
        return "redirect:users/allUsers";
    }


    @GetMapping("/update")
    public String updateForm(ModelMap model,
                             @RequestParam("id") long id) {
        model.addAttribute("user", usersService.readUser(id));
        return "update";
    }

    @PostMapping("/updateauser")
    public String update(@RequestParam("id") long id, @ModelAttribute("user") Users user) {

        usersService.updateUser(id, user);
        return "redirect:users/allUsers";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") long id) {
        usersService.deleteUser(id);
        return "redirect:allUsers";
    }

}
