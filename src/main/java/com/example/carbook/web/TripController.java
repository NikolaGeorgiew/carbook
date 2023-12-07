package com.example.carbook.web;

import com.example.carbook.model.dto.AddTripDTO;
import com.example.carbook.service.TripService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TripController {
    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

//    @GetMapping("/add-trip")
//    public String addTrip() {
//        return "/add-trip";
//    }
//
//    @PostMapping("/add-trip/{id}")
//    public String addTrip(@PathVariable("id") Long id, @Valid AddTripDTO addTripDTO, BindingResult bindingResult, RedirectAttributes rAtt) {
//        if (bindingResult.hasErrors()){
//            rAtt.addFlashAttribute("addTripDTO", addTripDTO);
//            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.addTripDTO", bindingResult);
//            return "redirect:/car/" + id;
//        }
//        tripService.createTrip(addTripDTO);
//        return "redirect:/";
//    }
@GetMapping("/add-trip")
public String showAddTripForm(Model model) {
    // Populate the model with necessary data (if needed)
    model.addAttribute("addTripDTO", AddTripDTO.empty()); // Assuming you have a form backing object

    return "add-trip"; // Assuming "add-trip" is the Thymeleaf template for the add trip form
}

//    @PostMapping("/add-trip/{id}")
//    public String addTrip(@PathVariable("id") Long id, @Valid AddTripDTO addTripDTO, BindingResult bindingResult, RedirectAttributes rAtt) {
//        if (bindingResult.hasErrors()) {
//            rAtt.addFlashAttribute("addTripDTO", addTripDTO);
//            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.addTripDTO", bindingResult);
//            return "redirect:/add-trip/{id}"; // Redirect back to the form with errors
//        }
//
//        tripService.createTrip(addTripDTO);
//
//        return "redirect:/"; // Redirect to the home page or wherever you want to go after a successful trip creation
//    }
@PostMapping("/add-trip/")
public String addTrip(@Valid AddTripDTO addTripDTO, BindingResult bindingResult, RedirectAttributes rAtt) {
    if (bindingResult.hasErrors()) {
        rAtt.addFlashAttribute("addTripDTO", addTripDTO);
        rAtt.addFlashAttribute("org.springframework.validation.BindingResult.addTripDTO", bindingResult);
        return "redirect:/add-trip"; // Redirect back to the form with errors
    }

    tripService.createTrip(addTripDTO);

    return "redirect:/"; // Redirect to the home page or wherever you want to go after a successful trip creation
}
}
