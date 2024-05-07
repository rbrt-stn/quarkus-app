package com.nttdata.lil.quarkus;

import com.nttdata.lil.quarkus.util.GreetingUtil;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class QuarkusApp implements QuarkusApplication {

	GreetingUtil greetingUtil;

	public QuarkusApp(GreetingUtil greetingUtil){
		super();
		this.greetingUtil = greetingUtil;
	}

	@Override
	public int run(String... args) throws Exception {
		System.out.println(this.greetingUtil.getGreetingName());
		return 0;
	}
}
