package it.uniroma3.siw;



import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import it.uniroma3.siw.spring.model.Libro;
import it.uniroma3.siw.spring.repository.LibroRepository;

//@SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(connection=EmbeddedDatabaseConnection.H2)
class SiwSpringSecurityApplicationTests {
	
	@Autowired
	LibroRepository libroRepository;

	@Test
	void contextLoads() {
		Libro libro=new Libro();
		libro.setAutore("Autore");
		libro.setDescrizione("Descrizione");
		libro.setTitolo("Titolo");
		libro.setId((long) 1);
		
		Libro libroSalvato=libroRepository.save(libro);
		
		Assertions.assertThat(libroSalvato).isNotNull();
		Assertions.assertThat(libroSalvato.getId()).isGreaterThan(0);
		Assertions.assertThat(libroSalvato.getId()).isEqualTo(1);
	}

}
