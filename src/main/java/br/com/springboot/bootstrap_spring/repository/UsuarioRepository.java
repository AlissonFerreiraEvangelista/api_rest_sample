package br.com.springboot.bootstrap_spring.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springboot.bootstrap_spring.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

	@Query(value =" SELECT u FROM Usuario u WHERE upper(trim(u.nome)) like %?1%")
	public List<Usuario> findByName(String nome);
}
