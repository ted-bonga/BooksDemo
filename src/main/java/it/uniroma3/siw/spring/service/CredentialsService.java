package it.uniroma3.siw.spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.spring.model.Credentials;
import it.uniroma3.siw.spring.model.Libro;
import it.uniroma3.siw.spring.model.User;
import it.uniroma3.siw.spring.repository.CredentialsRepository;

@Service
public class CredentialsService {
	
    @Autowired
    protected PasswordEncoder passwordEncoder;

	@Autowired
	protected CredentialsRepository credentialsRepository;
	
	@Transactional
	public Credentials getCredentials(Long id) {
		Optional<Credentials> result = this.credentialsRepository.findById(id);
		return result.orElse(null);
	}

	@Transactional
	public Credentials getCredentials(String username) {
		Optional<Credentials> result = this.credentialsRepository.findByUsername(username);
		return result.orElse(null);
	}
		
    @Transactional
    public Credentials saveCredentials(Credentials credentials) {
        credentials.setRole(Credentials.DEFAULT_ROLE);
        credentials.setPassword(this.passwordEncoder.encode(credentials.getPassword()));
        return this.credentialsRepository.save(credentials);
    }
    
	public List<Credentials> findAll() {
		List<Credentials> credentials=new ArrayList<Credentials>();
		for(Credentials c:credentialsRepository.findAll()) {
			credentials.add(c);
		}
		return credentials;
	}

	public void deleteByUser(User user) {
		credentialsRepository.deleteByUser(user);
	}

	public void deleteById(Long id) {
		credentialsRepository.deleteById(id);
	}
}
