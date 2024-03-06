package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.spring.model.Libro;
import it.uniroma3.siw.spring.repository.LibroRepository;

@Service
public class LibroService {
	
	@Autowired
	private LibroRepository libroRepository; 
	
	@Transactional
	public Libro inserisci(Libro libro) {
		return libroRepository.save(libro);
	}

	@Transactional
	public List<Libro> tutti() {
		return (List<Libro>) libroRepository.findAll();
	}

	@Transactional
	public Libro libroPerId(Long id) {
		Optional<Libro> optional = libroRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Libro libro) {
		List<Libro> libri = this.libroRepository.findByTitolo(libro.getTitolo());
		if (libri.size() > 0)
			return true;
		else 
			return false;
	}
	
	public void deleteById(Long id) {
		libroRepository.deleteById(id);
	}

	public List<Libro> findAll() {
		List<Libro> libri=new ArrayList<Libro>();
		for(Libro l:libroRepository.findAll()) {
			libri.add(l);
		}
		return libri;
	}
}
