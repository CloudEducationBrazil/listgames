package com.uniruy.listgames.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uniruy.listgames.entities.Game;
import com.uniruy.listgames.projections.GameMinProjection;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
	@Query(nativeQuery = true, value = """
			SELECT tb_game.id, tb_game.title, tb_game.game_year AS "year", tb_game.img_url AS imgUrl,
			tb_game.short_description AS shortDescription, tb_belonging.position
			FROM tb_game
			INNER JOIN tb_belonging ON tb_game.id = tb_belonging.game_id
			WHERE tb_belonging.game_list_id = :listId
			ORDER BY tb_belonging.position
				""") 
	List<GameMinProjection> searchByCategorie(Long listId);
}
