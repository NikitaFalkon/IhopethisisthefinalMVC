package com.Controllers;

import com.Model.ListOfUsers;
import com.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/users")
public class MvcController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/find")
    public String FindUser(Model model)
    {
        model.addAttribute("user", new User());
        return "Find";
    }
    @GetMapping("/finding")
    public String User(@RequestParam(name = "username", required = false) String username, Model model)
    {
        User user = restTemplate.getForObject("http://localhost:8090/user?username=" + username + "&file=", User.class);
        model.addAttribute("user", user);
        return "User";
    }
    @GetMapping("")
    public String Users(Model model)
    {
        ListOfUsers listOfUsers = restTemplate.getForObject("http://localhost:8090/users", ListOfUsers.class);
        model.addAttribute("users", listOfUsers.getUserList());
        return "Users";
    }
    @GetMapping("/{id}/edit")
    public String FindUser(Model model, @PathVariable Long id)
    {
        User user  = restTemplate.getForObject("http://localhost:8090/user/{id}", User.class, id);
        model.addAttribute("user", user);
        return "User";
    }
    @PostMapping("/{id}/delete")
    public String DeleteUser(Model model, @PathVariable Long id)
    {
        HttpEntity<Long> entity = new HttpEntity<Long>(id);
        ResponseEntity resp = restTemplate.exchange("http://localhost:8090/user/{id}", HttpMethod.DELETE, entity, User.class, id);
        return "redirect:/users";
    }
}
