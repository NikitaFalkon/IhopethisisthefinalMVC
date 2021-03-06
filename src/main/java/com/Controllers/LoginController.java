package com.Controllers;

import com.Model.User;
import com.dto.AuthenticationRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
        //ResponseEntity resp = restTemplate.getForObject("http://localhost:8090/login?username="+username+"&password="+password+"",  ResponseEntity.class);
        AuthenticationRequestDto user = new AuthenticationRequestDto(username, password);
        //HttpEntity<AuthenticationRequestDto> entity = new HttpEntity<AuthenticationRequestDto>(user);
        ResponseEntity resp = restTemplate.getForObject("http://localhost:8090/login?username="+username+"&password="+password+"", ResponseEntity.class);
        ResponseEntity resp1 = restTemplate.getForObject("http://localhost:8060/login?user="+username+"", ResponseEntity.class);
     //   ResponseEntity resp = restTemplate.exchange("http://localhost:8090/login", HttpMethod.GET, entity, ResponseEntity.class);
        return "redirect:/users";
    }
    @GetMapping("/create")
    public String Create(Model model)
    {
        model.addAttribute("user", new User());
        return "Create";
    }
    @PostMapping("/create")
    public String Creating(@RequestParam(name ="username", required = false) String username, @RequestParam(name ="password", required = false) String password, Model model)
    {
        HttpEntity<String> entity = new HttpEntity<String>(username);
        ResponseEntity resp = restTemplate.exchange("http://localhost:8090/create?username={username}&password="+password+"", HttpMethod.POST, entity,  ResponseEntity.class, username);

        return "redirect:/users";
    }

}
