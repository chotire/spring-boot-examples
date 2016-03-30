package spring.boot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.boot.web.repository.CityMapper;

@Controller
public class CityController {
    @Autowired
    private CityMapper cityMapper;
    
    @RequestMapping("/")
    String home(Model model) {
        model.addAttribute("city", cityMapper.findByState("CA"));
        return "home";
    }
}