package it.uniroma3.siw.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.spring.model.Libro;

public interface LibroRepository extends CrudRepository<Libro, Long> {

	public List<Libro> findByTitolo(String titolo);
}