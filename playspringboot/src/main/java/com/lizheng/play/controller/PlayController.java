package com.lizheng.play.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lizheng.play.model.Person;
import com.lizheng.play.respository.PersonRepository;


@Controller
@RequestMapping(value="/")
public class PlayController {
	
	@Autowired
	public PersonRepository  personRepository;

	
	@GetMapping(value="/")
	public String getPersons(Model model){
		Person empty = new Person();
		List<Person> persons= personRepository.findAll();
		model.addAttribute("random", Math.random());
		model.addAttribute("persons", persons);
		model.addAttribute("emptyPerson", empty);
		return "MyHtml";
		
	}
	
	@PostMapping(value="/add")
	public String addPerson(HttpServletResponse response,Person emptyPerson){
		emptyPerson.setAge((int)(Math.random()*100));
		emptyPerson.setLength((int)(Math.random()*10+10));
		personRepository.save(emptyPerson);
		/*try {
			response.sendRedirect("/");
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		return "redirect:/";
	}
	
	@GetMapping(value="/delete/{id}")
	public void deletePerson(HttpServletResponse response,@PathVariable("id") String id){
		personRepository.delete(Integer.parseInt(id));
		try {
			response.sendRedirect("/");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
