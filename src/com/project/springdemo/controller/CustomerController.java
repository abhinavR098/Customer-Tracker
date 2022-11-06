package com.project.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.springdemo.entity.Customer;
import com.project.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	//inject customer service as its managing the DAOs
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomer(Model model) {
		//get customer from Service
		List<Customer> theCustomer = customerService.getCustomers();
		
		//add the customer to model
		model.addAttribute("customers",theCustomer);
		
		return "list-customer";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		Customer theCustomer = new Customer();
		
		model.addAttribute("customer", theCustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer")Customer theCustomer) {
		
		customerService.saveCustomer(theCustomer);
		
		return "redirect:/customer/list";
		//redirecting to customer list
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId")int theId, Model model) {
		
		Customer theCustomer = customerService.getCustomers(theId);
		
		model.addAttribute("customer",theCustomer);
		
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("customerId")int theId) {
		
		customerService.deleteCustomers(theId);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/search")
	public String searchCustomers(@RequestParam("theSearchName") String theSearchName,
			Model theModel) {
		// search customers from the service
		List<Customer> theCustomers = customerService.searchCustomers(theSearchName);

		// add the customers to the model
		theModel.addAttribute("customers", theCustomers);
		return "list-customer";        
	}
	

}
