package com.luv2code.springdemo.service;

import java.util.List;

import com.luv2code.springdemo.entity.Customer;

public interface CustomerService {

	public List<Customer> getCustomers(int theSortField);
	
	public void saveCustomer(Customer customer);
	
	public Customer getCustomer(int customerId);

	public void deleteCustomer(int customerId);
	
    public List<Customer> searchCustomers(String theSearchName);

}
