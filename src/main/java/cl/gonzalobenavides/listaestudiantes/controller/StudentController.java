package cl.gonzalobenavides.listaestudiantes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cl.gonzalobenavides.listaestudiantes.controller.service.ApiService;
import cl.gonzalobenavides.listaestudiantes.model.ContactInfo;
import cl.gonzalobenavides.listaestudiantes.model.Dorm;
import cl.gonzalobenavides.listaestudiantes.model.Student;

@Controller
public class StudentController {

	@Autowired
	ApiService service;
	
	@GetMapping("/")
	public String toIndex() {
		return "redirect:/students";
	}
	
	@GetMapping("students")
	public String toStudents(Model model) {
		model.addAttribute("students",this.service.getAllStudentsInfo());
		model.addAttribute("dorms",this.service.findAllDorms());
		return "students.jsp";
	}
	
	@GetMapping("students/create")
	public String createStudent(@RequestParam("firstName") String first, @RequestParam("lastName") String last, @RequestParam("age") Integer age) {
		Student newStudent = new Student(first, last, age); 
		service.saveStudent(newStudent);
		return "redirect:/students";
	}
	
	@PostMapping("students/create")
	public String createStudent(@ModelAttribute Student student, BindingResult res) {
		if(res.hasErrors())
			return "students/new";
		service.saveStudent(student);
		return "redirect:/students";
	}

	@GetMapping("students/new")
	public String newStudent(@ModelAttribute Student student) {
		return "newStudent.jsp";
	}
	
	@GetMapping("contact/new")
	public String newContact(@ModelAttribute ContactInfo contact, Model model) {
		model.addAttribute("students", service.getAllStudentsNoInfo());
		model.addAttribute("contact", contact);
		return "newContact.jsp";
	}
	
	@GetMapping("contact/create")
	public String createContactInfo(@RequestParam("student") Long id, @RequestParam("address") String address, @RequestParam("city")String city, @RequestParam("state")String state) {
		ContactInfo newInfo = new ContactInfo(address,city,state,service.findStudentById(id));
		service.saveContactInfo(newInfo);
		return "redirect:/students";
	}
	
	@PostMapping("/contact/create")
	public String createContactInfo(@ModelAttribute ContactInfo contact, BindingResult res) {
		if(res.hasErrors()) {
			return "/contact/new";
		}
		service.saveContactInfo(contact);
		return "redirect:/students";
	}
	
	@GetMapping("dorm/new")
	public String newDorm(@ModelAttribute Dorm dorm) {
		return "newDorm.jsp";
	}

	@GetMapping("dorm/create")
	public String createDorm(@RequestParam("name") String name) {
		Dorm newDorm = new Dorm(name); 
		service.saveDorm(newDorm);
		return "redirect:/students";
	}
	
	@GetMapping("dorm/{id}/add")
	public String addStudentToDorm(@RequestParam("student") Long idStudent, @PathVariable("id") Long idDorm) {
		service.addStudentToDorm(idStudent, idDorm);
		return "redirect:/students";
	}
	
	@GetMapping("dorm/{id}")
	public String goToDorm(@PathVariable("id") Long idDorm, Model model, @ModelAttribute Student freeStudent) {
		model.addAttribute("dorm", this.service.findDormById(idDorm));
		model.addAttribute("students", this.service.getStudentsByDorm(idDorm));
		model.addAttribute("freeStudent", freeStudent);
		model.addAttribute("dormStudents", this.service.getAllStudentsNoDorm());
		return "dorms.jsp";
	}
	
	@PostMapping("dorm/{id}")
	public String addToDorm(@PathVariable("id") Long idDorm, @ModelAttribute Student freeStudent, Model model) {
		service.addStudentToDorm(freeStudent.getId(), idDorm);
		Dorm dorm = this.service.findDormById(idDorm);
		
		model.addAttribute("dorm", dorm);
		model.addAttribute("students", this.service.getStudentsByDorm(idDorm));		
		model.addAttribute("freeStudents", this.service.getAllStudentsNoDorm());
		return "redirect:/dorm/"+idDorm;
	}
	
	@PostMapping("dorm/create")
	public String createDorm(@ModelAttribute Dorm dorm, BindingResult res) {
		if(res.hasErrors())
			return "dorm/new";
		service.saveDorm(dorm);
		return "redirect:/students";
	}
	
	@GetMapping("dorm/{id}/remove")
	public String removeFromDorm(@RequestParam("student") Long studentId,@PathVariable("id") Long idDorm, Model model) {
		model.addAttribute("dorm", this.service.findDormById(idDorm));
		model.addAttribute("students", this.service.getStudentsByDorm(idDorm));
//		model.addAttribute("freeStudent", freeStudent);
		model.addAttribute("dormStudents", this.service.getAllStudentsNoDorm());
		
		service.removeStudentFromDorm(studentId, idDorm);
		return "redirect:/dorm/"+idDorm;
	}
	
	
}
