package com.project.springdemo.dao;

import java.util.List;
import com.project.springdemo.entity.Customer;

//DAO stands for Data Access Object
public interface CustomerDAO {
	
	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);

	public List<Customer> searchCustomers(String theSearchName);
}
