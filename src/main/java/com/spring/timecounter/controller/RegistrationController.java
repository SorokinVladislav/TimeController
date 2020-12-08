package com.spring.timecounter.controller;

import com.spring.timecounter.models.Role;
import com.spring.timecounter.models.User;
import com.spring.timecounter.models.Worker;
import com.spring.timecounter.repos.UserRepo;
import com.spring.timecounter.repos.WorkerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    WorkerRepo workerRepo;


//    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addworker")
    public String addUser( @RequestParam String lastname,
                           @RequestParam String firstname,
                           @RequestParam Long uid, Model model) {
Worker worker = new Worker();
   worker.setFirstname(firstname);
        worker.setLastname(lastname);
        worker.setUid(uid);

        Worker workerFromDb = workerRepo.findByUid(worker.getUid());

        if (workerFromDb != null) {
            model.addAttribute("message", "User exists!");
            return "addworker";
        }

        workerRepo.save(worker);
        return "addworker";
    }
    @GetMapping("/addworker")
    public String addWorker() {
        return "addworker";
    }







    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {

        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }
}
