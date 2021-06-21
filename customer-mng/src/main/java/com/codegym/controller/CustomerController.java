package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/customer/list")
    public ModelAndView showCustomerList(){
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customerList", customerService.findAll());
        return modelAndView;
    }

    @GetMapping("/create-customer")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/customer/save")
    public ModelAndView save(@ModelAttribute(name="customer") Customer customer){
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        customerService.save(customer);
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @GetMapping("/customer/{id}/edit")
    public ModelAndView edit(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        Customer customer = customerService.findById(id);
        modelAndView.addObject("customer",customer);
        return modelAndView;
    }

    @PostMapping("/customer/update")
    public ModelAndView edit(@ModelAttribute("customer") Customer customer){
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        modelAndView.addObject("customer",customer);
        modelAndView.addObject("message","Customer updated success");
        return modelAndView;
    }

    @GetMapping("/customer/{id}/delete")
    public ModelAndView delete(@PathVariable Long id){
        customerService.remove(id);
        return showCustomerList();
    }
}
