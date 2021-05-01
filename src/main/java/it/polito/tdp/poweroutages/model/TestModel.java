package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		System.out.println(model.getNercList());
		Nerc nerc=null;
		
		for(Nerc n: model.getNercList()) {
			if(n.getId()==3)
				nerc=n;
				
		}
				
		List<PowerOutage> result = new ArrayList<PowerOutage>();
		
		result=model.calcolaSottoInsiemeBlackout(nerc, 2, 30);
		
		for(PowerOutage p: result) {
			System.out.println(p.getPersoneCoinvolte());
		}

	}

}
