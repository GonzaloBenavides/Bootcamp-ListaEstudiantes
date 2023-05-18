package cl.gonzalobenavides.listaestudiantes.controller.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.gonzalobenavides.listaestudiantes.model.Dorm;

@Repository
public interface DormRepository extends CrudRepository<Dorm, Long> {
	
	List<Dorm> findAll();
}


