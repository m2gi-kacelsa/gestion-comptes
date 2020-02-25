package org.banque.dao;

import org.banque.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompteRepository extends JpaRepository<Compte, String> {
	
	@Query("select c from Compte c where c.codeCompte = ?1")
	Compte findByCodeCompte(String codeCompte);

}
