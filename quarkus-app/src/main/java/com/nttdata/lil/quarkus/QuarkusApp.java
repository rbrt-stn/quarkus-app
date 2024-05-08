package com.nttdata.lil.quarkus;

import java.util.List;

import com.nttdata.lil.quarkus.data.entity.Customer;
import com.nttdata.lil.quarkus.data.entity.Service;
import com.nttdata.lil.quarkus.data.entity.Vendor;
import com.nttdata.lil.quarkus.data.repository.CustomerRepository;
import com.nttdata.lil.quarkus.data.repository.ServiceRepository;
import com.nttdata.lil.quarkus.data.repository.VendorRepository;
import com.nttdata.lil.quarkus.util.FizzBuzzExecutor;
import com.nttdata.lil.quarkus.util.GreetingUtil;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.enterprise.context.control.ActivateRequestContext;

@QuarkusMain
public class QuarkusApp implements QuarkusApplication {

	private final ServiceRepository serviceRepository;
	private final CustomerRepository customerRepository;
	private final VendorRepository vendorRepository;

	public QuarkusApp(ServiceRepository serviceRepository, CustomerRepository customerRepository,
			VendorRepository vendorRepository) {
		this.serviceRepository = serviceRepository;
		this.customerRepository = customerRepository;
		this.vendorRepository = vendorRepository;
	}

	@Override
	@ActivateRequestContext
	public int run(String... args) throws Exception {
		System.out.println("**\nServices**");
		List<Service> services = this.serviceRepository.listAll();
		services.forEach(System.out::println);
		Service service = this.serviceRepository.findById(2L);
		System.out.println(service);
		System.out.println("**\nVendors**");
		Vendor vendor = this.vendorRepository.findByName("QUAXO");
		System.out.println("Vendor by name: " + vendor);
		vendor = this.vendorRepository.findByEmail("vharrison1@geocities.com");
		System.out.println("Vendor by email: " + vendor);
		System.out.println("**\nCustomers**");
		Customer customer = this.customerRepository.findByEmail("montes.nascetur@semperrutrum.net");
		System.out.println("Customer by email: " + customer);

		return 0;
	}
}
