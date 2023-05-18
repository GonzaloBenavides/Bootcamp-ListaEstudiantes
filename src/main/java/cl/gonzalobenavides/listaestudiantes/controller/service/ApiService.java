package cl.gonzalobenavides.listaestudiantes.controller.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.catalina.valves.StuckThreadDetectionValve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.gonzalobenavides.listaestudiantes.controller.repository.ContactRepository;
import cl.gonzalobenavides.listaestudiantes.controller.repository.StudentRepository;
import cl.gonzalobenavides.listaestudiantes.model.ContactInfo;
import cl.gonzalobenavides.listaestudiantes.model.Student;

@Service
public class ApiService {

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	ContactRepository contactRepository;
	
	
	public void saveStudent(Student s) {
		studentRepository.save(s);
	}
	
	public void saveContactInfo(ContactInfo s) {
		contactRepository.save(s);
		
		s.getStudent().setContact(s);
		studentRepository.save(s.getStudent());
	}
	
	public Student findStudentById(Long id) {
		Optional<Student> opt = studentRepository.findById(id);
		if(opt == null)
			return null;
		return opt.get();
	}
	
	public ContactInfo findContacInfoById(Long id) {
		Optional<ContactInfo> opt = contactRepository.findById(id);
		if(opt == null)
			return null;
		return opt.get();
	}
	
	public List<Student> findAllStudents() {
		return studentRepository.findAll();
	}
	
	public List<ContactInfo> findAllContactInfo() {
		return contactRepository.findAll();
	}
	
	public List<Student> getAllStudentsNoInfo(){
		List<Student> studentsNoInfo = new ArrayList<Student>();
		for(Student s : studentRepository.findAll()) {
			if(s.getContact() == null) {
				studentsNoInfo.add(s);
			}
		}
		return studentsNoInfo;
	}
	
	public List<ContactInfo> getAllStudentsInfo(){
		List<ContactInfo> studentsInfo = new ArrayList<ContactInfo>();
		for(ContactInfo s : contactRepository.findAll()) {
			if(s.getStudent() != null) {
				studentsInfo.add(s);
			}
		}
		return studentsInfo;
	}
}
