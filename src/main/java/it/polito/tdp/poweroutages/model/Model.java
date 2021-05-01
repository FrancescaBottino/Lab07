package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	private List<PowerOutage> partenza;
	private List<PowerOutage> soluzioneMigliore;
	private int numeroPersoneMax;
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	
	public List<PowerOutage> calcolaSottoInsiemeBlackout(Nerc nerc, int anni, int ore){
		
		this.partenza=podao.getPowerOutages(nerc); 
		//potrei ordinare partenza per data inizio
		Collections.sort(partenza);
		
		List<PowerOutage> parziale= new ArrayList<PowerOutage>();
		
		this.soluzioneMigliore= new ArrayList<PowerOutage>();
		
		this.numeroPersoneMax=0;
		
		cerca(parziale, anni, ore);
		
		return soluzioneMigliore;
		
	}
	
	public void cerca(List<PowerOutage> parziale, int anni, int ore) {
		
		//Casi terminali
		
		int numeroPersone=calcolaPersone(parziale);
		
		if(numeroPersone> numeroPersoneMax) {
			
			numeroPersoneMax=numeroPersone;
			soluzioneMigliore= new ArrayList<PowerOutage>(parziale);
		}
		
		
		//genero sottoproblemi
		
		for(PowerOutage p: partenza) {
			
			//validità aggiunta? se non l'ho già messo
			//aggiungo
			//ricorsione
			//backtracking
			
			if(!parziale.contains(p)) {
				
				parziale.add(p);
			
				//vincolo su anni e ore
				if(calcolaAnni(parziale, anni) && calcolaOre(parziale, ore))  {
				
					//ricorsione
					cerca(parziale, anni, ore);
				
				
				}
			
				parziale.remove(p);
			}
			
		}
		
		
	}

	
	

	private int calcolaPersone(List<PowerOutage> parziale) {
		
		int num=0;
		
		for(PowerOutage p: parziale) {
			
			num+= p.getPersoneCoinvolte();
		}
		
		return num;
	}
	
	private boolean calcolaOre(List<PowerOutage> parziale, int ore) {
		
		/*Il numero totale di ore di disservizio del sottoinsieme di
		 * eventi selezionati deve essere sempre minore o uguale del
		 * valore Y inserito dall’utente nell’interfaccia grafica
		 */
		
		int minutiSoluzione=0;
		
		for(PowerOutage p: parziale) {
			
			minutiSoluzione+=p.getMinuti();
		}
		
		if((minutiSoluzione/60) > ore)
			return false;
		
		return true;
		
	}

	private boolean calcolaAnni(List<PowerOutage> parziale, int anni) {
		
		/*La differenza tra l’anno dell’evento più recente e l’anno di quello 
		 * più vecchio deve essere sempre minore o uguale del numero di anni X 
		 * inserito dall’utente nell’interfaccia grafica.
		 */
		
		//se ne ho almeno 2
		
		if(parziale.size()>=2) {
		
			int primoanno=parziale.get(0).getDataInizio().getYear(); //il primo della lista
			int ultimoanno=parziale.get(parziale.size()-1).getDataFine().getYear(); //l'ultimo della lista
			
			if((ultimoanno-primoanno)+1 > anni)
				return false;
			
			return true;
			
		}
		
		return true;
	}
	
	

}
