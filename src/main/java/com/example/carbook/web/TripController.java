package com.example.carbook.web;

import com.example.carbook.model.dto.AddTripDTO;
import com.example.carbook.service.TripService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    @GetMapping("/add-trip/{id}")
    public String showAddTripForm(@PathVariable("id") Long id, AddTripDTO addTripDTO, Model model) {
        // Populate the model with necessary data (if needed)
        addTripDTO.setCarId(id);
        model.addAttribute("addTripDTO", addTripDTO);// Assuming you have a form backing object


        return "/add-trip";// Assuming "add-trip" is the Thymeleaf template for the add trip form
    }
//@GetMapping("/add-trip")
//public String showAddTripForm(Model model) {
//    // Populate the model with necessary data (if needed)
//    model.addAttribute("addTripDTO", AddTripDTO.empty()); // Assuming you have a form backing object
//
//    return "add-trip"; // Assuming "add-trip" is the Thymeleaf template for the add trip form
//}

    //@PostMapping("/add-trip/")
//public String addTrip(@Valid AddTripDTO addTripDTO, BindingResult bindingResult, RedirectAttributes rAtt) {
//    if (bindingResult.hasErrors()) {
//        rAtt.addFlashAttribute("addTripDTO", addTripDTO);
//        rAtt.addFlashAttribute("org.springframework.validation.BindingResult.addTripDTO", bindingResult);
//        return "redirect:/add-trip"; // Redirect back to the form with errors
//    }
//
//    tripService.createTrip(addTripDTO);
//
//    return "redirect:/"; // Redirect to the home page or wherever you want to go after a successful trip creation
//}
    @PostMapping("/add-trip/{carId}")
    public String processAddTripForm(@PathVariable("carId") Long carId,
                                     @ModelAttribute("addTripDTO") @Valid AddTripDTO addTripDTO,
                                     BindingResult bindingResult,
                                     RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            // If there are validation errors, redirect back to the form with the errors
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addTripDTO", bindingResult);
            redirectAttributes.addFlashAttribute("addTripDTO", addTripDTO);
            return "redirect:/add-trip/" + carId;
        }

        // Set the carId in AddTripDTO before saving
        addTripDTO.setCarId(carId);

        try {
            // Save the trip with the provided carId
            tripService.createTrip(addTripDTO);

            // Redirect to a success page or another appropriate page
            redirectAttributes.addFlashAttribute("successMessage", "Trip successfully created!");
            return "redirect:/";
        } catch (Exception e) {
            // Handle any exceptions (e.g., database errors)
            redirectAttributes.addFlashAttribute("errorMessage", "An error occurred while processing the request.");
            return "redirect:/add-trip/" + carId;
        }
    }
}