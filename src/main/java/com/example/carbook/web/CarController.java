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
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/car")
    public String cars(Model model, @PageableDefault(
            size = 3,
            sort = "id"
    )Pageable pageable) {
        Page<CarSummaryDTO> allCars = carService.getAllCars(pageable);

        model.addAttribute("cars", allCars);
        return "car";
    }

}
