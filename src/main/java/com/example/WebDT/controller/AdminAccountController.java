package com.example.WebDT.controller;

import com.example.WebDT.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("admin/users")
@Controller
@Secured("ADMIN")
public class AdminAccountController {
    @Autowired
    UserService userService;
    @GetMapping("/error")
    public String index(){
        return "admin/account/error";
    }

    @GetMapping("")
    public String handelListUser(Model model){
        model.addAttribute("listAdmin", userService.getUsersByRoleId(1));
        model.addAttribute("listSale", userService.getUsersByRoleId(2));
        model.addAttribute("listUser", userService.getUsersByRoleId(3));
        return "admin/account/index";
    }

}
