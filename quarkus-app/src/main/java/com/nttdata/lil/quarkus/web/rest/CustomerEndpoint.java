package com.nttdata.lil.quarkus.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;

import com.nttdata.lil.quarkus.data.entity.Customer;
import com.nttdata.lil.quarkus.data.repository.CustomerRepository;
import com.nttdata.lil.quarkus.service.CustomerService;

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
import jakarta.ws.rs.WebApplicationException;

@Path("/rest/customers")
public class CustomerEndpoint {

	private final CustomerService customerService;

	public CustomerEndpoint(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GET
	public List<Customer> getCustomers(@RestQuery("email") String email) {
		if (StringUtil.isNullOrEmpty(email)) {
			return this.customerService.getAllCustomers();
		} else {
			return this.customerService.getCustomersByEmail(email);
		}
	}

	@POST
	@ResponseStatus(201)
	public Customer addCustomer(Customer customer) {
		return this.customerService.addCustomer(customer);
	}

	@Path("/{id}")
	@GET
	public Customer getCustomer(@RestPath("id") long id) {
		return this.customerService.getCustomer(id);
	}

	@Path("/{id}")
	@PUT
	@ResponseStatus(204)
	public void updateCustomer(@RestPath("id") long id, Customer customer) {
		if (id != customer.getId()) {
			throw new WebApplicationException(400);
		}
		this.customerService.updateCustomer(customer);
	}

	@Path("/{id}")
	@DELETE
	@ResponseStatus(205)
	public void deleteCustomer(@RestPath("id") long id) {
		this.customerService.deleteCustomer(id);
	}
}

