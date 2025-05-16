	package com.uniruy.listgames.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_belonging")
public class Belonging implements Serializable{
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BelongingPK id = new BelongingPK();
	
	private Integer position;
	
	public Belonging() {
	}

	public Belonging(Game game_id, GameList game_list_id, Integer position) {
		id.setIdGame(game_id);
		id.setIdGameList(game_list_id);
		this.position = position;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Belonging [id=" + id + ", position=" + position + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Belonging other = (Belonging) obj;
		return Objects.equals(id, other.id);
	}
}
