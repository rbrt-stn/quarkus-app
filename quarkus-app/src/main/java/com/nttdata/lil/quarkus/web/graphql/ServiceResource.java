package com.nttdata.lil.quarkus.web.graphql;

import java.util.List;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;

import com.nttdata.lil.quarkus.data.entity.Service;
import com.nttdata.lil.quarkus.service.ServiceService;
import com.nttdata.lil.quarkus.web.graphql.input.ServiceInput;

@GraphQLApi
public class ServiceResource {

	private final ServiceService serviceService;

	public ServiceResource(ServiceService serviceService) {
		this.serviceService = serviceService;
	}

	@Query("allServices")
	@Description("Gets all services available in the system")
	public List<Service> getAllServices() {
		return this.serviceService.getAllServices();
	}

	@Mutation("addService")
	@Description("Adds a service to system")
	public Service addService(ServiceInput service) {
		return serviceService.addService(service.getEntity());
	}

	@Query("getService")
	@Description("Gets an individual service by ID")
	public Service getService(@Name("id") long id) {
		return serviceService.getService(id);
	}

	@Mutation("updateService")
	@Description("Update an individual service")
	public Service updateService(Service service) {
		return serviceService.updateService(service);
	}

	@Mutation("delete")
	@Description("Deletes an individual service")
	public Service deleteService(@Name("id") long id) {
		Service service = serviceService.getService(id);
		serviceService.deleteService(id);
		return service;
	}
}
