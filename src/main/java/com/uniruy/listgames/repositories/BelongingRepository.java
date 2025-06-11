package com.uniruy.listgames.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;

import com.uniruy.listgames.entities.Belonging;
import com.uniruy.listgames.entities.BelongingPK;
import com.uniruy.listgames.projections.BelongingProjection;

//@Repository
public interface BelongingRepository extends JpaRepository<Belonging, BelongingPK>{
  @Query(nativeQuery = true, value = """
  			SELECT game_id AS GameId, game_list_id AS GameListId
  			  FROM tb_belonging WHERE game_id = :id
  			 LIMIT 1
  		""")
  BelongingProjection foundGameBelongin(Long id);
}