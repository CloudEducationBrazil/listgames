package com.uniruy.listgames.dto;

import com.uniruy.listgames.entities.GameList;

public class GameListDTO {

	private Long id;
	private String name;
	
    // Jackson precisa disso:
    public GameListDTO() {
    }
	
	public GameListDTO(GameList entity) {
		id = entity.getId();
		name = entity.getName();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}