package org.banque.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CE")
public class CompteEpargne extends Compte {

	private double tauxInteret;

	public CompteEpargne() {
		super();
	}

	public CompteEpargne(String codeCompte, Date dateCreation, double solde, Client client, double tauxInteret) {
		super(codeCompte, dateCreation, solde, client);
		this.tauxInteret = tauxInteret;
	}

	public double getTauxInteret() {
		return tauxInteret;
	}

	public void setTauxInteret(double tauxInteret) {
		this.tauxInteret = tauxInteret;
	}

}
