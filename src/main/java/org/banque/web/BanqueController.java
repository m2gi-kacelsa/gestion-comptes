package org.banque.web;

import org.banque.entities.Compte;
import org.banque.entities.Operation;
import org.banque.metier.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BanqueController {

	@Autowired
	private IBanqueMetier iBanqueMetier;

	@RequestMapping("/operations")
	public String index() {
		return "comptes";
	}
	
	@RequestMapping("/consulterCompte")
	public String consulter(Model model, String codeCompte, @RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "size", defaultValue = "5") int size) {
		model.addAttribute("codeCompte", codeCompte);
		try {
			Compte compte = iBanqueMetier.consulterCompte(codeCompte);
			Page<Operation> operations = iBanqueMetier.listOperations(codeCompte, page, size);
			model.addAttribute("listOperations", operations.getContent());
			int[] pages = new int[operations.getTotalPages()];
			model.addAttribute("pages", pages);
			model.addAttribute("compte", compte);
		} catch (Exception e) {
			model.addAttribute("error", e);
		}
		return "comptes";
	}

	@RequestMapping(value = "/saveOperation", method = RequestMethod.POST)
	public String saveOperation(Model model, String typeOperation, String codeCompte, double montant,
			String codeCompte2) {

		try {
			if (typeOperation.equals("VERS")) {
				iBanqueMetier.verser(codeCompte, montant);
			} else if (typeOperation.equals("RETR")) {
				iBanqueMetier.retirer(codeCompte, montant);
			} else if (typeOperation.equals("VIR")) {
				iBanqueMetier.virement(codeCompte, codeCompte2, montant);
			}
		} catch (Exception e) {
			model.addAttribute("errore", e);
			return "redirect:/consulterCompte?codeCompte=" + codeCompte + "&error" + e.getMessage();
		}

		return "redirect:/consulterCompte?codeCompte=" + codeCompte;
	}

}
