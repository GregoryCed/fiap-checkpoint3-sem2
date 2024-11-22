package gregoryced.com.github.fiap_checkpoint3_sem2.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gregoryced.com.github.fiap_checkpoint3_sem2.model.Contatos;
import gregoryced.com.github.fiap_checkpoint3_sem2.repository.ContatoRepository;

@Service
public class ContatoService {

	@Autowired
    private ContatoRepository contatoRepository;
	
	public List<Contatos> list() {
        return contatoRepository.findAll();
    }
	
	 public Optional<Contatos> findById(Long id) {
	     return contatoRepository.findById(id);
	 }
	 
	 public Optional<Contatos> findByNome(String nome) {
	     return contatoRepository.findByNome(nome);
	 }
	 
	 public Contatos save(Contatos contato) {
		 return contatoRepository.save(contato);
	 }
	 
	 public void delete(Long id) {
        contatoRepository.deleteById(id);
    }
	
	public boolean existsById(Long id) {        
        return contatoRepository.existsById(id);
    }
	
}
