package com.nttdata.lil.quarkus.util;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GreetingUtil {

	@ConfigProperty(name="greeting.name", defaultValue = "Students")
	String greetingName;

	public String getGreetingName(){
		return "Hello " + greetingName;
	}
}
