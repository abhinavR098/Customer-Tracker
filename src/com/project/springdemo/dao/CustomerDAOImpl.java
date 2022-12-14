package com.project.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	//Dependency Injection as bean id of sessionFactory is already defined in xml
	//need to inject session factory
	@Autowired
	private SessionFactory factory;
	
	@Override
	//@Transactional is used in service layer so no need to use here(All transaction is managed by service layer)
	public List<Customer> getCustomers() {
		//get the current Hibernate session
		Session session = factory.getCurrentSession();
		
		//create a query..sort by first name
		Query<Customer> theQuery = session.createQuery("from Customer order by firstName",Customer.class);
		
		//execute the query
		List<Customer> customers = theQuery.getResultList();
		
		//return the results
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		
		//get the current hibernate session
		Session session = factory.getCurrentSession();
		
		//save the customer
		session.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomer(int theId) {
		
		Session session = factory.getCurrentSession();
		
		Customer theCustomer = session.get(Customer.class,theId);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		
		Session session = factory.getCurrentSession();
		
		Query theQuery = session.createQuery("delete from Customer where id=:customerId");
		
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();
		
	}

	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		// get the current hibernate session
		Session session = factory.getCurrentSession();

		Query theQuery = null;

		// only search by name if theSearchName is not empty
		if (theSearchName != null && theSearchName.trim().length() > 0) {
			// search for firstName or lastName ... case insensitive
			theQuery =session.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
			theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
		}
		else {
			// theSearchName is empty ... so just get all customers
			theQuery =session.createQuery("from Customer", Customer.class);            
		}

		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();

		// return the results        
		return customers;
	}

}
