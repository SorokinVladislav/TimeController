package com.spring.timecounter.controller;

import com.spring.timecounter.models.Role;
import com.spring.timecounter.models.User;
import com.spring.timecounter.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('USER')")
public class UserController {
    @Autowired
    private UserRepo userRepo;
    @GetMapping
    public String userList(Model model){
        model.addAttribute("users", userRepo.findAll());
        return "userList";
    }

    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping
    public String userSave(
            @RequestParam String username,
//            @RequestParam String firstname,
//            @RequestParam String lastname,
//            @RequestParam String position,
            @RequestParam Map <String, String> form,
            @RequestParam ("userId") User user){
        user.setUsername(username);
//        user.setUsername(firstname);
//        user.setUsername(lastname);
//        user.setUsername(position);


        Set<String> roles = Arrays.stream(Role.values())
                .map(Role :: name)
                .collect(Collectors.toSet());
        user.getRoles().clear();

        for (String key : form.keySet()){
            if (roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userRepo.save(user);
return "redirect:/user";
    }

}
