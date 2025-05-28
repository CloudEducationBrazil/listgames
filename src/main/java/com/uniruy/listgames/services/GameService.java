package com.uniruy.listgames.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uniruy.listgames.dto.GameDTO;
import com.uniruy.listgames.dto.GameMinDTO;
import com.uniruy.listgames.entities.Game;
import com.uniruy.listgames.projections.BelongingProjection;
import com.uniruy.listgames.repositories.BelongingRepository;
import com.uniruy.listgames.repositories.GameRepository;
import com.uniruy.listgames.responses.ResponseMessage;

@Service
public class GameService {
	// Injeção de dependência do Componente Repository
	@Autowired
	private GameRepository  gameRepository;
	
	// Injeção de dependência do Componente Repository
	@Autowired
	private BelongingRepository belongingRepository;

	// Função que atende ao Endpoint Consultar Todos - protocolo HTTP: Método Get
	@Transactional(readOnly = true)
	public List<GameMinDTO> getAllGame(){
		List<Game> listGame = gameRepository.findAll();
		//List<GameMinDTO> dto = listGame.stream().map(x -> new GameMinDTO(x)).toList();
	    List<GameMinDTO> dto = listGame.stream().map(GameMinDTO::new).toList();
	
		return dto;
	}
	
	// Função que atende ao Endpoint Consultar por id - protocolo HTTP: Método Get
	@Transactional(readOnly = true)
	public ResponseEntity<?> getGameById(Long id){
		Optional<Game> gameOptional = gameRepository.findById(id);
		
		if ( !gameOptional.isPresent() ) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Game não encontrado!"));
		}
		
		GameDTO dto = new GameDTO(gameOptional.get());
		
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}
	
	// Função que atende ao Endpoint Cadastrar - protocolo HTTP: Método Post
	@Transactional
	public ResponseEntity<?> postGame(GameDTO body){
		gameRepository.save(new Game(body));
		
		return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseMessage("Game criado com sucesso."));		
	}
	
	// Endpoint Alterar - protocolo HTTP: Método Put
	@Transactional
	public ResponseEntity<?> putGame(Long id, GameDTO body){
		Optional<Game> gameOptional = gameRepository.findById(id);
		
		if ( !gameOptional.isPresent() )
		{  ResponseMessage responseMessage = new ResponseMessage("Game não encontrado ...");
		   //return ResponseEntity.notFound().build();
		   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
		}   

		Game game = gameOptional.get();
		
		game.setTitle(body.getTitle());
		game.setImgUrl(body.getImgUrl());
		game.setPlatforms(body.getPlatforms());
		game.setScore(body.getScore());
		game.setYear(body.getYear());
		game.setGenre(body.getGenre());
		game.setShortDescription(body.getShortDescription());
		game.setLongDescription(body.getLongDescription());
		
		gameRepository.save(game);
		
		ResponseMessage responseMessage = new ResponseMessage("Game atualizado com sucesso ...");
		   
//		return ResponseEntity.ok().build();
		return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
	}
	
	// Função que atende ao Endpoint Excluir - protocolo HTTP: Método Delete
	@Transactional
	public ResponseEntity<?> deleteGame(Long id){
		Optional<Game> gameOptional = gameRepository.findById(id);

		if ( !gameOptional.isPresent() )
		{  ResponseMessage responseMessage = new ResponseMessage("Game não encontrado ...");
		   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
		}
		
		BelongingProjection belongingProjection = belongingRepository.foundGameBelongin(id);
	
		if ( belongingProjection != null ){
			ResponseMessage responseMessage = new ResponseMessage("Game encontrado no GameList: " + belongingProjection.getGameListId());
		   return ResponseEntity.status(HttpStatus.FOUND).body(responseMessage);
		}
		
		gameRepository.deleteById(id);

		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Game excluído com sucesso ..."));
	} 
}