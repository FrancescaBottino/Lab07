package it.polito.tdp.poweroutages.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class PowerOutage implements Comparable<PowerOutage>{
	
	int id;
	int id_area;
	LocalDateTime dataInizio;
	LocalDateTime dataFine;
	int personeCoinvolte;
	int totMinuti;
	
	public PowerOutage(int id, int id_area, LocalDateTime dataInizio, LocalDateTime dataFine, int personeCoinvolte) {
		
		this.id = id;
		this.id_area=id_area;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.personeCoinvolte = personeCoinvolte;
		
	}
	
	public double getMinuti() {
		
		double seconds= Duration.between(dataInizio, dataFine).getSeconds();
		
		return seconds/60;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutage other = (PowerOutage) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_area() {
		return id_area;
	}

	public void setId_area(int id_area) {
		this.id_area = id_area;
	}

	public LocalDateTime getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(LocalDateTime dataInizio) {
		this.dataInizio = dataInizio;
	}

	public LocalDateTime getDataFine() {
		return dataFine;
	}

	public void setDataFine(LocalDateTime dataFine) {
		this.dataFine = dataFine;
	}

	public int getPersoneCoinvolte() {
		return personeCoinvolte;
	}

	public void setPersoneCoinvolte(int personeCoinvolte) {
		this.personeCoinvolte = personeCoinvolte;
	}

	@Override
	public int compareTo(PowerOutage p1) {
		
		return this.getDataInizio().compareTo(p1.getDataInizio());
	}
	

}
