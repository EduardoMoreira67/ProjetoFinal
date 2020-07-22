package br.com.isidrocorp.projetofinal.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.isidrocorp.projetofinal.dao.UsuarioDAO;
import br.com.isidrocorp.projetofinal.model.Usuario;

@RestController
@CrossOrigin("*")
public class UsuarioController {
	
	@Autowired
	private UsuarioDAO dao;
	
	@GetMapping("/usuarios")
	public ArrayList<Usuario> recuperarTodos(){
		ArrayList<Usuario> lista;
		lista = (ArrayList<Usuario>)dao.findAll();
		for (Usuario u: lista) {
			u.setSenha("******");
		}
		return lista;
	}
	/* idéia do login:
	 *  recupero o usuário pelo email
	 *  	- se não existir, retorno um código de erro 404 - NOT FOUND (depois a gente trata isso)
	 *      - se existir, aí eu faço uma 2a conferência: se a senha coincide 
	 *          - se a senha não confere, retorno um erro 401 - UNAUTHORIZED (usuário existe, mas a senha não bate)
	 *          - se a senha conferir, retorno um OK!
	 */
	@PostMapping("/login")
	public ResponseEntity<Usuario> logarUsuario(@RequestBody Usuario userEmailSenha) {
		// busco o usuário apenas pelo email
		Usuario res = dao.findByEmail(userEmailSenha.getEmail());
		if (res != null) {
			if (res.getSenha().equals(userEmailSenha.getSenha())) { // a senha do usuário q recuperei do banco e que eu passei na requisição conferem?
				res.setSenha("**************");
				return ResponseEntity.ok(res);
			}
			else {
				return ResponseEntity.status(401).build(); // nao autorizado
			}
		}
		else {  // usuario nao existe
			return ResponseEntity.status(404).build();
		}
	}
	
	
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<Usuario> buscarPeloId(@PathVariable int id) {
		Usuario user = dao.findById(id).orElse(null);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok(user);
		}
	}
	
}