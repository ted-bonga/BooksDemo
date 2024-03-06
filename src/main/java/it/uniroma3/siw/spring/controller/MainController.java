package it.uniroma3.siw.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.service.LibroService;

@Controller
public class MainController {
	
	@Autowired
	LibroService libroService;
//	   @RequestMapping(value = "/libri", method = RequestMethod.GET)
//	    public String getLibri(Model model) {
//	    		model.addAttribute("libri", this.libroService.tutti());
//	    		return "libri.html";
//	    }
	
	@RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("libri", this.libroService.tutti());
		return "index";
	}
}