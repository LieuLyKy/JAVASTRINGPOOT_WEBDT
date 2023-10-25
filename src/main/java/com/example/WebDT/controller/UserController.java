package com.example.WebDT.controller;

import  com.example.WebDT.entity.User;
import  com.example.WebDT.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
   // @Autowired
   // PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository repo;

    @GetMapping("")
    @ResponseBody
    public List<User> all()
    {

        return repo.findAll();
    }
    @GetMapping("/update")
    @ResponseBody
    public String updatePass(int id)
    {
        User findU = repo.findById(id).stream().findFirst().orElse(null);
        if(findU != null)
        {
          //  String newpass = passwordEncoder.encode(findU.getPassword());
          //  findU.setPassword(newpass);
            repo.saveAndFlush(findU);
        }
        return "UPDATE passs";
    }

}
