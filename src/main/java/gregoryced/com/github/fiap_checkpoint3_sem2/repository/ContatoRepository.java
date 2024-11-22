package gregoryced.com.github.fiap_checkpoint3_sem2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gregoryced.com.github.fiap_checkpoint3_sem2.model.Contatos;

@Repository
public interface ContatoRepository extends JpaRepository<Contatos, Long>{
	<T> T findByNome(String nome);	
	<T> List<T> findAllByNome(String nome, Class<T> type);
	<T> List<T> findAllByNomeContains(String nome, Class<T> type);
}
