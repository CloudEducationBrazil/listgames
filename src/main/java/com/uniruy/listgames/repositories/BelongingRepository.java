package com.uniruy.listgames.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uniruy.listgames.entities.Belonging;
import com.uniruy.listgames.entities.BelongingPK;
import com.uniruy.listgames.projections.BelongingProjection;

@Repository
public interface BelongingRepository extends JpaRepository<Belonging, BelongingPK>{
  @Query(nativeQuery = true, value = """
  			select top 1 game_id as GameId, game_list_id as GameListId
  			  from tb_belonging where game_id = :id
  		""")
  BelongingProjection foundGameBelongin(Long id);
}