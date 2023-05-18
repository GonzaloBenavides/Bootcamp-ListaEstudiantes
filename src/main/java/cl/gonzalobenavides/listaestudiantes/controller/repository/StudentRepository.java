package cl.gonzalobenavides.listaestudiantes.controller.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.gonzalobenavides.listaestudiantes.model.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
	
	List<Student> findAll();
	List<Student> findByDormId(Long id);
	
	Student findStudentById(Long id);
}
