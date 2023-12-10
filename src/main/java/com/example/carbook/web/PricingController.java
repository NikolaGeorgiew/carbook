package com.example.carbook.web;

import com.example.carbook.model.dto.CarSummaryDTO;
import com.example.carbook.service.CarService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PricingController {
    private final CarService carService;

    public PricingController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/pricing")
    public String cars(Model model, @PageableDefault(
            sort = "id"
    ) Pageable pageable) {
        Page<CarSummaryDTO> allCars = carService.getAllCars(pageable);

        model.addAttribute("cars", allCars);
        return "pricing";
    }
}
