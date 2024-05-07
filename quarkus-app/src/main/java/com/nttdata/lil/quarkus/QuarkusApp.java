package com.nttdata.lil.quarkus;

import java.util.List;

import com.nttdata.lil.quarkus.data.entity.Service;
import com.nttdata.lil.quarkus.data.repository.ServiceRepository;
import com.nttdata.lil.quarkus.util.FizzBuzzExecutor;
import com.nttdata.lil.quarkus.util.GreetingUtil;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.enterprise.context.control.ActivateRequestContext;

@QuarkusMain
public class QuarkusApp implements QuarkusApplication {

	private final ServiceRepository serviceRepository;

	public QuarkusApp(ServiceRepository serviceRepository) {
		this.serviceRepository = serviceRepository;
	}

	@Override
	@ActivateRequestContext // or run transaction
	public int run(String... args) throws Exception {
		List<Service> services = this.serviceRepository.getAllServices();
		services.forEach(System.out::println);
		return 0;
	}
}
