package org.banque.metier;

import org.banque.entities.Compte;
import org.banque.entities.Operation;
import org.springframework.data.domain.Page;

public interface IBanqueMetier {

	public Compte consulterCompte(String codeCompte);

	public void verser(String codeCompte, double montant);

	public void retirer(String codeCompte, double montant);

	public void virement(String codeCpt1, String codeCpt2, double montant);

	public Page<Operation> listOperations(String codeCompte, int page, int size);

}
