package org.banque;

import java.util.Date;

import org.banque.dao.ClientRepository;
import org.banque.dao.CompteRepository;
import org.banque.dao.OperationRepository;
import org.banque.entities.Client;
import org.banque.entities.CompteEpargne;
import org.banque.entities.Versement;
import org.banque.metier.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionCompteApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private CompteRepository compteRepository;

	@Autowired
	private OperationRepository operationRepository;

	@Autowired
	private IBanqueMetier iBanqueMetier;

	public static void main(String[] args) {
		SpringApplication.run(GestionCompteApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//TESTER LA COUCHE DAO 
//		Client client = clientRepository.save(new Client("sab", "sab@eadn.dz"));
//		Client client2 = clientRepository.save(new Client("lydia", "lydia@eadn.dz"));
//
//		Compte compte = compteRepository.save(new CompteCourant("c1", new Date(), 6000.00, client, 500.50));
//		Compte compte2 = compteRepository.save(new CompteCourant("c2", new Date(), 6000.00, client2, 500.50));

//		CompteEpargne compt3 = compteRepository.save(new CompteEpargne("c3", new Date(), 4523.00, client2, 3434.12));
//
//		operationRepository.save(new Versement(new Date(), 500, compt3));
//		operationRepository.save(new Versement(new Date(), 2500, compte));
//		operationRepository.save(new Retrait(new Date(), 4000, compte));
//
//		operationRepository.save(new Versement(new Date(), 500, compte2));
//		operationRepository.save(new Versement(new Date(), 2500, compte2));
//		operationRepository.save(new Retrait(new Date(), 4000, compte2));

//		iBanqueMetier.verser("c2", 111111111);
	}

}
