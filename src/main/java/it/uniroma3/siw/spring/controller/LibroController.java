package it.uniroma3.siw.spring.controller;

import javax.transaction.Transactional;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.controller.validator.LibroValidator;
import it.uniroma3.siw.spring.model.Libro;
import it.uniroma3.siw.spring.service.LibroService;

@Controller
public class LibroController {
	
	@Autowired
	private LibroService libroService;
	
    @Autowired
    private LibroValidator libroValidator;
        
    @RequestMapping(value="/libro", method = RequestMethod.GET)
    public String addLibro(Model model) {
    	model.addAttribute("libro", new Libro());
        return "libroForm.html";
    }

    @RequestMapping(value = "/libro/{id}", method = RequestMethod.GET)
    public String getLibro(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("libro", this.libroService.libroPerId(id));
    	return "libro.html";
    }

    @RequestMapping(value = "/libri", method = RequestMethod.GET)
    public String getLibri(Model model) {
    		model.addAttribute("libri", this.libroService.tutti());
    		return "libri.html";
    }
    
    //@RequestMapping(value = "/admin/libro", method = RequestMethod.POST)
    @PostMapping("/libro")
    public String addlibro(@ModelAttribute("libro") Libro libro, 
    									Model model, BindingResult bindingResult) {
    	this.libroValidator.validate(libro, bindingResult);
        if (!bindingResult.hasErrors()) {
        	this.libroService.inserisci(libro);
            model.addAttribute("libri", this.libroService.tutti());
            return "libri.html";
        }
        return "libroForm.html";
    }
    
	@Transactional
	@GetMapping("deleteLibro/{id}")
	public String deleteLibro(@PathVariable ("id") Long id,Model model) {
//		if(action.equals("Elimina")) {
//			this.personaService.deleteById(id);
//		}
		libroService.deleteById(id);
		model.addAttribute("libri",libroService.findAll());
		return "libri.html";
	}
}
