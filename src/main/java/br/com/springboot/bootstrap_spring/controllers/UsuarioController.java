package br.com.springboot.bootstrap_spring.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.bootstrap_spring.models.Usuario;
import br.com.springboot.bootstrap_spring.repository.UsuarioRepository;

@RestController
@CrossOrigin
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/findall")
	public ResponseEntity<List<Usuario>> listaUsuario(){
		return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findAll());
	}
	
	@PostMapping("/save")
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){

		 return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));
	}
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody Usuario usuario){
		if(usuario.getId() == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID não informado");	
		}
		 return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.saveAndFlush(usuario));
	}
	
	@GetMapping("/id")
	public ResponseEntity<?> buscaUsuario(@RequestParam(name="id") UUID id){
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if(usuario != null)
			return ResponseEntity.status(HttpStatus.OK).body(usuario);
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
		}
	}
	@GetMapping("/findbyname")
	public ResponseEntity<?> buscaPorNome(@RequestParam (name="nome") String nome){
			if(nome.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body("Digite uma letra");				
			}
		return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findByName(nome.trim().toUpperCase()));
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> deletePorId(@RequestParam (name="id")UUID id){
		Optional<Usuario> user = usuarioRepository.findById(id);
		if(user.isPresent()){
			usuarioRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Deletado com Sucesso");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
		

	}
}
