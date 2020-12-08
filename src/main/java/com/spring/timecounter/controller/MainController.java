package com.spring.timecounter.controller;

import com.spring.timecounter.models.Message;
import com.spring.timecounter.models.User;
import com.spring.timecounter.repos.MessageRepo;
import com.spring.timecounter.repos.WorkerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Date;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    WorkerRepo workerRepo;

    @GetMapping("/")
    public String greeting(Model model) {
        return "redirect:/main";
    }

    @GetMapping("style.css")
    public String style(Model model) {
        return "redirect:/main";
    }



    //отчёт по смене
    @GetMapping("report")
    public String report(Model model) {
        return "report";
    }


    //отчёт по смене
    @PostMapping("report")
    public String addUser( @RequestParam String lastname,
                           @RequestParam String firstname,
                           @RequestParam Long uid, Model model) {
        return "report";
    }










    @GetMapping("/main")
    public String main(  @AuthenticationPrincipal User user,Model model) {
        String shift="";
        LocalDateTime local  = LocalDateTime.now();
        int hourOfDay = local.getHour();

        if (hourOfDay >=7  && hourOfDay < 19) {
            shift="день";
        }
        else
            shift="ночь";

        model.addAttribute("user", user.getFirstname()+" "+user.getLastname()+" - "+shift);

        try {
            Iterable<Message> messages = messageRepo.findByUserandSh(user.getId(), shift);
            model.addAttribute("messages", messages);

        } catch (Exception e){e.printStackTrace();}

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam Long worker
            , Model model
    ) {


String shift="";
        LocalDateTime local  = LocalDateTime.now();
        int hourOfDay = local.getHour();

        if (hourOfDay >=7  && hourOfDay < 19) {
            shift="день";
            System.out.println("Сейчас день");
        }
        else
            shift="ночь";

        System.out.println(worker);
        if (workerRepo.findByUid(worker)==null) {

            System.out.println(workerRepo.findByUid(worker));
            model.addAttribute("empty", "Данный работник не зарегистрирован");
                  }
        else {
            Message message = new Message(workerRepo.findByUid(worker), new java.sql.Timestamp(new Date().getTime()), user, shift);
            messageRepo.save(message);
        }

        model.addAttribute("user", user.getFirstname()+" "+user.getLastname()+" - "+shift);

        try {
            Iterable<Message> messages = messageRepo.findByUserandSh(user.getId(), shift);
            model.addAttribute("messages", messages);

        } catch (Exception e){e.printStackTrace();}

        return "main";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable(value="id") String id, Model model) {
        System.out.println(id);
try {
    messageRepo.delete(messageRepo.findAllById(Integer.parseInt(id)));
}catch (Exception e ){}


        return "redirect:/main";
    }
}
