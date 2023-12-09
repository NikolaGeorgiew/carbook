package com.example.carbook.web;

import com.example.carbook.model.dto.MessageDTO;
import com.example.carbook.model.entity.MessageEntity;
import com.example.carbook.service.MessageService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.Map;

@Controller
public class ContactController {
    private final MessageService messageService;

    public ContactController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        if (!model.containsAttribute("messageDTO")) {
            model.addAttribute("messageDTO", MessageDTO.empty());
        }
        return "contact";
    }

    @PostMapping("/contact")
    public String add(@Valid MessageDTO messageDTO, BindingResult bindingResult, RedirectAttributes rAtt) {

        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("messageDTO", messageDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.messageDTO", bindingResult);
            return "redirect:/contact";
        }
        messageService.save(messageDTO);
        rAtt.addFlashAttribute("successMessage", "Message successfully sent !");

        return "redirect:/contact";
    }
}
