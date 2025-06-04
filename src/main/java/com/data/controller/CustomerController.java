package com.data.controller;

import com.data.model.Customer;
import com.data.service.CloudinaryService;
import com.data.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.findAll());
        return "customers";
    }

    @GetMapping("/add")
    public String addCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "add-form";
    }

    @PostMapping("/add")
    public String addCustomer(
            @Valid @ModelAttribute("customer") Customer customer,
            BindingResult bindingResult,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(err -> {
                System.out.println("Lỗi tại field: " + err.getField());
                System.out.println("Lý do: " + err.getDefaultMessage());
                System.out.println("Giá trị nhận được: " + err.getRejectedValue());
            });
            return "add-form";
        }

        if (imageFile != null && !imageFile.isEmpty()) {
            String uploadedUrl = cloudinaryService.uploadFile(imageFile);
            customer.setImageUrl(uploadedUrl);
        }

        customerService.save(customer);
        return "redirect:/customers";
    }

    @GetMapping("/update/{id}")
    public String updateCustomerForm(@PathVariable("id") int id, Model model) {
        Customer customer = customerService.findById(id);
        if (customer == null) {
            return "redirect:/customers";
        }
        model.addAttribute("customer", customer);
        return "update-form";
    }

    @PostMapping("/update")
    public String updateCustomer(
            @Valid @ModelAttribute("customer") Customer customer,
            BindingResult bindingResult,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(err -> {
                System.out.println("Lỗi tại field: " + err.getField());
                System.out.println("Lý do: " + err.getDefaultMessage());
                System.out.println("Giá trị nhận được: " + err.getRejectedValue());
            });
            return "update-form";
        }

        if (imageFile != null && !imageFile.isEmpty()) {
            String uploadedUrl = cloudinaryService.uploadFile(imageFile);
            customer.setImageUrl(uploadedUrl);
        }

        customerService.save(customer);
        return "redirect:/customers";
    }
}
