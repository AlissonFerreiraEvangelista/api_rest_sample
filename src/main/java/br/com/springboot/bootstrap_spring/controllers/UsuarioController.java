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

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.bootstrap_spring.models.Usuario;
import br.com.springboot.bootstrap_spring.repository.UsuarioRepository;
import io.swagger.annotations.*;

@RestController
@CrossOrigin
@Api(value = "API REST CRUD")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@ApiOperation(value = "Retorna uma lista de todos cadastrados")
	@GetMapping("/findall")
	public ResponseEntity<List<Usuario>> listaUsuario() {
		return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findAll());
	}

	@ApiOperation(value = "Cadastra uma pessoa")
	@PostMapping("/save")
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {

		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuario));
	}

	@ApiOperation(value = "Atualiza um cadastrado")
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody Usuario usuario) {
		if (usuario.getId() == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID não informado");
		}
		return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.saveAndFlush(usuario));
	}

	@ApiOperation(value = "Retorna um Cadastrado")
	@GetMapping("/id")
	public ResponseEntity<?> buscaUsuario(@RequestParam(name = "id") UUID id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		if (usuario != null)
			return ResponseEntity.status(HttpStatus.OK).body(usuario);
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
		}
	}

	
	@GetMapping("/findbyname")
	@ApiOperation(value = "Retorna um Cadastro pelo nome")
	public ResponseEntity<?> buscaPorNome(@RequestParam (name = "nome") String nome) {
		if (nome.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body("Digite uma letra");
		}
		return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.findByName(nome.trim().toUpperCase()));
	}

	@ApiOperation(value = "Deleta um cadastrado")
	@DeleteMapping("/delete")
	public ResponseEntity<?> deletePorId(@RequestParam(name = "id") UUID id) {
		Optional<Usuario> user = usuarioRepository.findById(id);
		if (user.isPresent()) {
			usuarioRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body("Deletado com Sucesso");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");

	}
}
