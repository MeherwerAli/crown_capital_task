package org.crown.capital.simpletask.controller;

import org.crown.capital.simpletask.dto.UserDTO;
import org.crown.capital.simpletask.model.User;
import org.crown.capital.simpletask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/signup")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserDTO());
        return "signup";
    }

    @PostMapping("/signup")
    public String registerUserAccount(@Valid @ModelAttribute("user") UserDTO userDto,
                                      BindingResult result,
                                      Model model){
        User existingUser = userService.checkExistingUsername(userDto.getUserName());

        if(existingUser != null && existingUser.getUserName() != null && !existingUser.getUserName().isEmpty()){
            result.rejectValue("user", null,
                    "There is already an account registered with the same username");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/signup";
        }

        userService.saveUser(userDto);
        return "redirect:/user/signin";
    }

    @GetMapping("/signin")
    public String showLoginForm() {
        return "signin";
    }

}
