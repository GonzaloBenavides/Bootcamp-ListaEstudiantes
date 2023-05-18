package cl.gonzalobenavides.listaestudiantes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cl.gonzalobenavides.listaestudiantes.controller.service.ApiService;
import cl.gonzalobenavides.listaestudiantes.model.ContactInfo;
import cl.gonzalobenavides.listaestudiantes.model.Student;

@Controller
public class StudentController {

	@Autowired
	ApiService service;
	
	@GetMapping("/")
	public String toIndex() {
		return "index.jsp";
	}
	
	@GetMapping("students")
	public String toStudents(Model model) {
		model.addAttribute("students",this.service.getAllStudentsInfo());
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
	
}
