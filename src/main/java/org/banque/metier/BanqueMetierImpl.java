package org.banque.metier;

import java.util.Date;

import org.banque.dao.CompteRepository;
import org.banque.dao.OperationRepository;
import org.banque.entities.Compte;
import org.banque.entities.CompteCourant;
import org.banque.entities.Operation;
import org.banque.entities.Retrait;
import org.banque.entities.Versement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // pour que Spring puisse instancier cette classe au demarrage
@Transactional
public class BanqueMetierImpl implements IBanqueMetier {

	@Autowired
	private CompteRepository compteRepository;

	@Autowired
	private OperationRepository operationRepository;

	@Override
	public Compte consulterCompte(String codeCompte) {
		Compte compte = compteRepository.findByCodeCompte(codeCompte);
		if (compte == null)
			throw new RuntimeException("Compte introuvable");
		return compte;
	}

	@Override
	public void verser(String codeCompte, double montant) {
		Compte compte = consulterCompte(codeCompte);
		Versement versement = new Versement(new Date(), montant, compte);
		operationRepository.save(versement);
		compte.setSolde(compte.getSolde() + montant);
		compteRepository.save(compte);
	}

	@Override
	public void retirer(String codeCompte, double montant) {
		Compte compte = consulterCompte(codeCompte);
		double facilitesCaisse = 0;
		if (compte instanceof CompteCourant)
			facilitesCaisse = ((CompteCourant) compte).getDecouvert();

		if (compte.getSolde() + facilitesCaisse < montant)
			throw new RuntimeException("Solde Insuffisant!");
		Retrait retrait = new Retrait(new Date(), montant, compte);
		operationRepository.save(retrait);
		compte.setSolde(compte.getSolde() - montant);
		compteRepository.save(compte);
	}

	@Override
	public void virement(String codeCpt1, String codeCpt2, double montant) {
		if (codeCpt1.equals(codeCpt2)) throw new RuntimeException("Opération impossible!");
		retirer(codeCpt1, montant);
		verser(codeCpt2, montant);
	}

	@Override
	public Page<Operation> listOperations(String codeCompte, int page, int size) {
		return operationRepository.listOperations(codeCompte, PageRequest.of(page, size));
	}

}
