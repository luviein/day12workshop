package sg.nus.iss.visa.ssf.day12workshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
// @RequestMapping(path = "/api")
public class RandomNumber {

    @GetMapping("/home")
    public String landingPage() {
        return "home";
    }

    @GetMapping("/get")
    public String generateRandNumbers(Model model, HttpServletRequest request) {

        //fetch input parameter
        int number = Integer.parseInt(request.getParameter("number").trim());
        System.out.println("input no is: " + number);

        //check condition if no is 1-30
        if(number < 1 || number > 30){

            String errorMessage = "invalid number: " + number;

            model.addAttribute("errorMessage", errorMessage);
            return "home";
        }

        return "display";

    }

}
