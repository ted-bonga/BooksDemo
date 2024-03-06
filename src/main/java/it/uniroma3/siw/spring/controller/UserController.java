package it.uniroma3.siw.spring.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.spring.model.User;
import it.uniroma3.siw.spring.service.CredentialsService;
import it.uniroma3.siw.spring.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	CredentialsService credentialService;
	
	@Transactional
	@GetMapping("/admin/deleteUser/{id}")
	public String deleteUsers(@PathVariable ("id") Long id,Model model) {
//		if(action.equals("Elimina")) {
//			this.personaService.deleteById(id);
//		}
//		User user=userService.getUser(id);
		credentialService.deleteByUser(this.userService.getUser(id));
//		userService.deleteById(id);
//		model.addAttribute("users",userService.getAllUsers());
		return "admin/users";
	}

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public String getUsers(Model model) {
    		model.addAttribute("users", this.userService.getAllUsers());
    		return "admin/users";
    }
	
    @RequestMapping(value = "/admin/user/{id}", method = RequestMethod.GET)
	public String getUser(@PathVariable("id") Long id, Model model) {
	    	model.addAttribute("user", this.userService.getUser(id));
	    	return "admin/user";
	    }

}
