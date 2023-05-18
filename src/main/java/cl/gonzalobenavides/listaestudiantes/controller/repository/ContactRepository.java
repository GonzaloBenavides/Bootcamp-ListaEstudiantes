package cl.gonzalobenavides.listaestudiantes.controller.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.gonzalobenavides.listaestudiantes.model.ContactInfo;
import cl.gonzalobenavides.listaestudiantes.model.Student;

@Repository
public interface ContactRepository extends CrudRepository<ContactInfo, Long> {
	
	List<ContactInfo> findAll();
}


