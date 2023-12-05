package com.example.carbook.web;

import com.example.carbook.model.dto.CarDetailDTO;
import com.example.carbook.model.dto.CarSummaryDTO;
import com.example.carbook.service.CarService;
import com.example.carbook.service.exception.ObjectNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/car")
    public String cars(Model model, @PageableDefault(
            sort = "id"
    )Pageable pageable) {
        Page<CarSummaryDTO> allCars = carService.getAllCars(pageable);

        model.addAttribute("cars", allCars);
        return "car";
    }

    @GetMapping("/car/{id}")
    public String details(@PathVariable("id") Long id, Model model) {
        CarDetailDTO carDetailDTO = carService
                .getCarDetail(id)
                .orElseThrow(() -> new ObjectNotFoundException("Object with id " + id + " was not found!"));
        model.addAttribute("car", carDetailDTO);
        return "car-single";
    }
}
