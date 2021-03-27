package com.Controllers;

import com.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;
@Controller
public class LoginController {
    @Autowired
    RestTemplate restTemplate;
    @GetMapping("/login")
    public String Login(Model model)
    {
        model.addAttribute("user", new User());
        return "Login";
    }
    @PostMapping("/login")
    public String LoginG(@RequestParam(name ="username", required = false) String username, @RequestParam(name ="password", required = false) String password, Model model)
    {
        RedirectView resp = restTemplate.getForObject("http://localhost:8090/login?username="+username+"&password="+password+"",  RedirectView.class);
        return "redirect:/"+resp+"";
    }
}
