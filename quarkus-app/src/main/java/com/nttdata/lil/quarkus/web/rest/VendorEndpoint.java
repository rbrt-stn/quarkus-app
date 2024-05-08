package com.nttdata.lil.quarkus.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;

import com.nttdata.lil.quarkus.data.entity.Customer;
import com.nttdata.lil.quarkus.data.entity.Service;
import com.nttdata.lil.quarkus.data.entity.Vendor;
import com.nttdata.lil.quarkus.data.repository.CustomerRepository;
import com.nttdata.lil.quarkus.data.repository.ServiceRepository;
import com.nttdata.lil.quarkus.data.repository.VendorRepository;
import com.nttdata.lil.quarkus.service.VendorService;

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
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.WebApplicationException;

@Path("/rest/vendors")
public class VendorEndpoint {

	private final VendorService vendorService;

	public VendorEndpoint(VendorService vendorService) {
		this.vendorService = vendorService;
	}

	@GET
	public List<Vendor> getVendors(@QueryParam("email")String email, @QueryParam("name")String name){
		if(StringUtil.isNullOrEmpty(email) && StringUtil.isNullOrEmpty(name)){
			return this.vendorService.getAllVendors();
		}else{
			if(!StringUtil.isNullOrEmpty(email) && !StringUtil.isNullOrEmpty(name)){
				return this.vendorService.getVendorsByEmailAndName(email, name);
			}else if(!StringUtil.isNullOrEmpty(email)){
				return this.vendorService.getVendorsByEmail(email);
			}else{
				return this.vendorService.getVendorsByName(name);
			}
		}
	}

	@POST
	@ResponseStatus(201)
	public Vendor addVendor(Vendor vendor){
		return this.vendorService.addVendor(vendor);
	}

	@GET
	@Path("/{id}")
	public Vendor getVendor(@RestPath("id")long id){
		return this.vendorService.getVendor(id);
	}

	@PUT
	@Path("/{id}")
	@ResponseStatus(204)
	public void updateVendor(@RestPath("id")long id, Vendor vendor){
		if (id != vendor.getId()){
			throw new WebApplicationException(400);
		}
		this.vendorService.updateVendor(vendor);
	}

	@DELETE
	@Path("/{id}")
	@ResponseStatus(205)
	public void deleteVendor(@RestPath("id")long id){
		this.vendorService.deleteVendor(id);
	}
}