package com.nttdata.lil.quarkus.data.repository;

import java.util.ArrayList;
import java.util.List;

import com.nttdata.lil.quarkus.data.entity.Service;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class ServiceRepository implements PanacheRepository<Service> {

//	private final EntityManager em;
//
//	public ServiceRepository(EntityManager em) {
//		this.em = em;
//	}
//
//	public List<Service> getAllServices(){
//		List<Service> services = this.em.createQuery("select service from Service service", Service.class).getResultList();
//		return services;
//	}
}
