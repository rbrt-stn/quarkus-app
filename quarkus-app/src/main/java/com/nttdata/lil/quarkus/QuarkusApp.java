package com.nttdata.lil.quarkus;

import java.util.List;

import com.nttdata.lil.quarkus.data.entity.Service;
import com.nttdata.lil.quarkus.data.repository.ServiceRepository;
import com.nttdata.lil.quarkus.util.FizzBuzzExecutor;
import com.nttdata.lil.quarkus.util.GreetingUtil;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class QuarkusApp implements QuarkusApplication {

//	GreetingUtil greetingUtil;
//
//	FizzBuzzExecutor fizzBuzzExecutor;
//
//	public QuarkusApp(GreetingUtil greetingUtil, FizzBuzzExecutor fizzBuzzExecutor){
//		super();
//		this.greetingUtil = greetingUtil;
//		this.fizzBuzzExecutor = fizzBuzzExecutor;
//	}

	private final ServiceRepository serviceRepository;

	public QuarkusApp(ServiceRepository serviceRepository) {
		this.serviceRepository = serviceRepository;
	}

	@Override
	public int run(String... args) throws Exception {
//		System.out.println(this.greetingUtil.getGreetingName());
//		fizzBuzzExecutor.execute();
		List<Service> services = this.serviceRepository.getAllServices();
		services.forEach(System.out::println);
		return 0;
	}
}
