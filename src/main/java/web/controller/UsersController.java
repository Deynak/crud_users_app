package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.service.UsersService;
import web.entity.Users;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;


    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }


    @GetMapping("/allUsers")
    public String usersPage(Model model) {
        model.addAttribute("users", usersService.readingAllUsers());
        return "allUsers";
    }

    @GetMapping("/new")
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
    public String updateForm(ModelMap model, @RequestParam("id") long id) {
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
