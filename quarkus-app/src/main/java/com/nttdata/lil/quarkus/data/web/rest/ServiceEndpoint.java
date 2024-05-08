package com.nttdata.lil.quarkus.data.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;

import com.nttdata.lil.quarkus.data.entity.Customer;
import com.nttdata.lil.quarkus.data.entity.Service;
import com.nttdata.lil.quarkus.data.repository.CustomerRepository;
import com.nttdata.lil.quarkus.data.repository.ServiceRepository;

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

@Path("/rest/services")
public class ServiceEndpoint {

	private final ServiceRepository serviceRepository;

	public ServiceEndpoint(ServiceRepository serviceRepository) {
		this.serviceRepository = serviceRepository;
	}

	@GET
	public List<Service> getAllServices() {
		return this.serviceRepository.listAll();
	}

	@POST
	@ResponseStatus(201)
	@Transactional
	public Service addService(Service service) {
		this.serviceRepository.persist(service);
		return service;
	}

	@GET
	@Path("/{id}")
	public Service getService(@RestPath("id") long id) {
		Service service = this.serviceRepository.findById(id);
		if (service == null) {
			throw new WebApplicationException(404);
		}
		return service;
	}

	@PUT
	@Path("/{id}")
	@Transactional
	@ResponseStatus(204)
	public void updateService(@RestPath("id") long id, Service service) {
		if (id != service.getId()) {
			throw new WebApplicationException(400);
		}
		this.serviceRepository.persist(service);
	}

	@DELETE
	@Path("/{id}")
	@Transactional
	@ResponseStatus(205)
	public void deleteService(@RestPath("id") long id) {
		this.serviceRepository.deleteById(id);
	}
}
