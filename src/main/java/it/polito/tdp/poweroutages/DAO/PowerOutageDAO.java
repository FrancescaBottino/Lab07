package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutage;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	
	public List<PowerOutage> getPowerOutages(Nerc nerc){
		
		String sql="SELECT id, date_event_began, date_event_finished, customers_affected "
				+ "FROM PowerOutages "
				+ "WHERE nerc_id=? ";
		
		List<PowerOutage> result=new ArrayList<PowerOutage>();
		
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, nerc.getId());
			
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				
				LocalDateTime di= res.getTimestamp("date_event_began").toLocalDateTime();
				LocalDateTime df= res.getTimestamp("date_event_finished").toLocalDateTime();
				
				PowerOutage po= new PowerOutage(res.getInt("id"), nerc.getId(), di, df, res.getInt("customers_affected"));
				result.add(po);
				
			}
			
			conn.close();
			

		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return result;
	}
	

}
