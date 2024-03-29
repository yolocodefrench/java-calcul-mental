package com.java.project.Game.dal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.project.Game.domain.Utilisateur;

@Repository
public interface IUtilisateurRepository extends JpaRepository<Utilisateur, Long>{
	List<Utilisateur> findByName(String name);
}
