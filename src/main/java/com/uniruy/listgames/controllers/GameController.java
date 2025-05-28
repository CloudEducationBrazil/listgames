package com.uniruy.listgames.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uniruy.listgames.dto.GameDTO;
import com.uniruy.listgames.dto.GameMinDTO;
import com.uniruy.listgames.services.GameService;

@RestController
@RequestMapping(value = "/games")
public class GameController {
	
    @GetMapping("/")
    public String hello() {
        return "API online!";
    }
	
	// Injeção de dependência do Componente Service
	@Autowired
	public GameService  gameService;
	
	// Endpoint Consultar Todos - protocolo HTTP: Método Get
	@GetMapping
	public List<GameMinDTO> getAllGame(){
		return gameService.getAllGame();
	}

	// Endpoint Consultar por id - protocolo HTTP: Método Get
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getGameById(@PathVariable Long id){
		return gameService.getGameById(id);
	}
	
	// Endpoint Cadastrar - protocolo HTTP: Método Post
	@PostMapping
	public ResponseEntity<?> postGame(@RequestBody GameDTO body){
		return gameService.postGame(body);
	}

	// Endpoint Alterar - protocolo HTTP: Método Put
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> putGame(@PathVariable Long id, @RequestBody GameDTO body){
		return gameService.putGame(id, body);
	}

	// Endpoint Excluir - protocolo HTTP: Método Delete
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteGame(@PathVariable Long id){
		return gameService.deleteGame(id);
	} 
}