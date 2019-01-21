package com.java.project.Game.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.project.Game.domain.Partie;
import com.java.project.Game.domain.Utilisateur;

@Repository
public interface IPartieRepository extends JpaRepository<Partie, Long>{}
