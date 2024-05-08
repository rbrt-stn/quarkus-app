package com.nttdata.lil.quarkus.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;

import com.nttdata.lil.quarkus.data.entity.Customer;
import com.nttdata.lil.quarkus.data.entity.Service;
import com.nttdata.lil.quarkus.data.repository.CustomerRepository;
import com.nttdata.lil.quarkus.data.repository.ServiceRepository;
import com.nttdata.lil.quarkus.service.ServiceService;

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

	private final ServiceService serviceService;

	public ServiceEndpoint(ServiceService serviceService) {
		this.serviceService = serviceService;
	}

	@GET
	public List<Service> getAllServices(){
		return this.serviceService.getAllServices();
	}

	@POST
	@ResponseStatus(201)
	public Service addService(Service service){
		return this.serviceService.addService(service);
	}

	@GET
	@Path("/{id}")
	public Service getService(@RestPath("id")long id){
		return this.serviceService.getService(id);
	}

	@PUT
	@Path("/{id}")
	@ResponseStatus(204)
	public void updateService(@RestPath("id")long id, Service service){
		if (id != service.getId()){
			throw new WebApplicationException(400);
		}
		this.serviceService.updateService(service);
	}

	@DELETE
	@Path("/{id}")
	@ResponseStatus(205)
	public void deleteService(@RestPath("id")long id){
		this.serviceService.deleteService(id);
	}
}
