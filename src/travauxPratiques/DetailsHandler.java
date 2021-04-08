package travauxPratiques;

import java.util.List;



public class DetailsHandler {
	DetailsCompte detailsCompte = null;
	public DetailsHandler(DetailsCompte detailsCompte) {
		this.detailsCompte = detailsCompte;
	}
	
	public void updateDetailCompte() {
		DetailSDAOIMPL idao = new DetailSDAOIMPL();
		List<Operation> list = idao.findAll();
		System.out.println(list);
		detailsCompte.observableList.addAll(list);
	}
}
