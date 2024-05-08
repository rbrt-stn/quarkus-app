package com.nttdata.lil.quarkus.data.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;

import com.nttdata.lil.quarkus.data.entity.Customer;
import com.nttdata.lil.quarkus.data.repository.CustomerRepository;

import io.netty.util.internal.StringUtil;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/rest/customers")
@Produces("application/json")
@Consumes("application/json")
public class CustomerEndpoint {

	private final CustomerRepository customerRepository;

	public CustomerEndpoint(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	// curl http://localhost:8080/rest/customers
	// $ curl -X GET 'http://localhost:8080/rest/customers?email=enim.Mauris.quis@Vivamusnibh.net'
	@GET
	public List<Customer> getCustomers(@RestQuery("email") String email) {
		if (StringUtil.isNullOrEmpty(email)) {
			return this.customerRepository.listAll();
		}

		List<Customer> customers = new ArrayList<>();
		Customer customer = this.customerRepository.findByEmail(email);
		customers.add(customer);
		return customers;
	}

	@POST
	@ResponseStatus(201)
	@Transactional // because we are adding sth to database
	public Customer addCustomer(Customer customer) {
		this.customerRepository.persist(customer);
		return customer;
	}

	@GET
	@Path("/{id}")
	public Customer getCustomer(@RestPath("id") long id) {
		Customer customer = this.customerRepository.findById(id);
		if (customer == null) {
			throw new NotFoundException();
		}

		return customer;
	}

	@PUT
	@Path("/{id}")
	@Transactional
	@ResponseStatus(204)
	public void updateCustomer(@RestPath("id") long id, Customer customer) {
		if (id != customer.getId()) {
			throw new BadRequestException();
		}

		Customer entity = customerRepository.findById(id);
		if (entity == null) {
			throw new NotFoundException();
		}

		entity.setAddress(customer.getAddress());
		entity.setEmail(customer.getEmail());
		entity.setFirstName(customer.getFirstName());
		entity.setLastName(customer.getLastName());
		entity.setPhone(customer.getPhone());
		this.customerRepository.persist(entity);
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	@ResponseStatus(205)
	public void deleteCustomer(@RestPath("id") long id) {
		this.customerRepository.deleteById(id);
	}
}
