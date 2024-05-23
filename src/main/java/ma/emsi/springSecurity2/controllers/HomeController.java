package ma.emsi.springSecurity2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("")
public class HomeController {
    @GetMapping("/home1")
    public String home() {
        return "home1";
    }


    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String redirectToHome(){ { return "redirect:/home";}

    }




    @ResponseBody
    @RequestMapping(value = "/about",method = RequestMethod.GET)
    public String about(){
        return "about";
    }
}
