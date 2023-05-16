package sg.nus.iss.visa.ssf.day12workshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import sg.nus.iss.visa.ssf.day12workshop.model.Image;
import sg.nus.iss.visa.ssf.day12workshop.service.RandomNumService;

@Controller
// @RequestMapping(path = "/api")
public class RandomNumber {

    //injecting dependency
    @Autowired
    RandomNumService service;

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

        //calling service method
        List<Integer> randomNum = service.generateRandNumbers(number);

        //populate image objects
        List<Image> imageList = new ArrayList<>();

        for(int randomNo : randomNum){
            imageList.add(new Image(Integer.toString(randomNo), "/images/"+ Integer.toString(randomNo)+".png" ));
        }

        System.out.println("image list: " + imageList);

        model.addAttribute(imageList);

        return "display";

    }

}
