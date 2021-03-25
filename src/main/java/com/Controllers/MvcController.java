package com.Controllers;

import com.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class MvcController {
    @Autowired
    RestTemplate restTemplate;
    @GetMapping("/")
    public String Main()
    {
        return "Main";
    }
    @GetMapping("/find")
    public String FindUser(Model model)
    {
        model.addAttribute("user", new User());
        return "Find";
    }
    @GetMapping("/finding")
    public String User(@RequestParam(name = "username", required = false) String username, Model model)
    {
        User user = restTemplate.getForObject("http://localhost:8090/user?username=" + username + "", User.class);


        model.addAttribute("user", user);
        return "User";
    }
    /*@GetMapping("/users")
    public String Users(Model model)
    {
        HttpEntity<List<User>> request = new HttpEntity<>(new List<User>());
        ResponseEntity<YourResponseClass[]> responses =
                restTemplate.postForEntity("your URL", request , YourResponseClass[].class );
        model.addAttribute("users", new User());
        return "Find";
    }*/
}
