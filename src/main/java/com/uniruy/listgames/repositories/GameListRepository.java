package com.uniruy.listgames.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uniruy.listgames.entities.GameList;

@Repository
public interface GameListRepository extends JpaRepository<GameList, Long> {

}