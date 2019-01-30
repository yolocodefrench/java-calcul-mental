package com.java.project.Game.dal;

import com.java.project.Game.domain.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java.project.Game.domain.Partie;

import java.util.List;

@Repository
public interface IPartieRepository extends JpaRepository<Partie, Long>{
    List<Partie> findTop10ByOrderByScoreDesc();

    Partie findFirstByUtilisateurOrderByScoreDesc(Utilisateur utilisateur);
}
