package fr.insa.twf.database.database;

import java.sql.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/database")
public class databaseResources {
	
	//private List<PropertyChangeListener> listener = new ArrayList<PropertyChangeListener>();
	
	protected Connection c = null;
	private Statement stm = null;
	private PreparedStatement pstm = null;
	private ResultSet rs = null;
	@SuppressWarnings("unused")
	private int rowsModified = 0;
	
	public void opendatabase() {
			
		String url = "jdbc:sqlite:src/main/resources/database.db";
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(url);
			System.out.println("[" + this.getClass().toString() + "] Connection OK");

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Integer getSize(String table) {
		this.opendatabase();
		String query = "SELECT COUNT(*) FROM '" + table + "';";
		Integer size = null;
		try {
			this.stm = c.createStatement();
			this.rs = stm.executeQuery(query);
			size = rs.getInt(1);
			stm.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.closeConnection();
		return size;
	}
	
	@GetMapping("lastTemperature")
	public Double getlastTemperature() {
		this.opendatabase();
		String query = "SELECT value FROM temperature ORDER BY rowid DESC LIMIT 1;";
		Double value = null;
		try {
			this.stm = c.createStatement();
			this.rs = stm.executeQuery(query);
			value = rs.getDouble(1);
			stm.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.closeConnection();
		return value;
	}
	
	@GetMapping("lastPresence")
	public Double getlastPresence() {
		this.opendatabase();
		String query = "SELECT value FROM presence ORDER BY rowid DESC LIMIT 1;";
		Double value = null;
		try {
			this.stm = c.createStatement();
			this.rs = stm.executeQuery(query);
			value = rs.getDouble(1);
			stm.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.closeConnection();
		return value;
	}
	
	@GetMapping("lastCO2")
	public Double getlastCO2() {
		this.opendatabase();
		String query = "SELECT CO2Value FROM co2humidity ORDER BY rowid DESC LIMIT 1;";
		Double value = null;
		try {
			this.stm = c.createStatement();
			this.rs = stm.executeQuery(query);
			value = rs.getDouble(1);
			stm.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.closeConnection();
		return value;
	}
	
	@GetMapping("lastHumidity")
	public Double getlastHumidity() {
		this.opendatabase();
		String query = "SELECT humidityValue FROM co2humidity ORDER BY rowid DESC LIMIT 1;";
		Double value = null;
		try {
			this.stm = c.createStatement();
			this.rs = stm.executeQuery(query);
			value = rs.getDouble(1);
			stm.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.closeConnection();
		return value;
	}
	
	@GetMapping("lastLuminosity")
	public Double getlastluminosity() {
		this.opendatabase();
		String query = "SELECT value FROM luminosity ORDER BY rowid DESC LIMIT 1;";
		Double value = null;
		try {
			this.stm = c.createStatement();
			this.rs = stm.executeQuery(query);
			value = rs.getDouble(1);
			stm.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.closeConnection();
		return value;
	}
	
	@GetMapping("randomTemperature")
	public Double getrandomTemperature() {
		this.opendatabase();
		String query = "SELECT value FROM temperature WHERE RANDOM() LIMIT 1";
		Double value = null;
		try {
			this.stm = c.createStatement();
			this.rs = stm.executeQuery(query);
			value = rs.getDouble(1);
			stm.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.closeConnection();
		return value;
	}
	
	@GetMapping("randomPresence")
	public Double getrandomPresence() {
		this.opendatabase();
		String query = "SELECT value FROM presence WHERE RANDOM() LIMIT 1";
		Double value = null;
		try {
			this.stm = c.createStatement();
			this.rs = stm.executeQuery(query);
			value = rs.getDouble(1);
			stm.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.closeConnection();
		return value;
	}
	
	@GetMapping("randomCO2")
	public Double getrandomCO2() {
		this.opendatabase();
		String query = "SELECT CO2Value FROM co2humidity WHERE RANDOM() LIMIT 1";
		Double value = null;
		try {
			this.stm = c.createStatement();
			this.rs = stm.executeQuery(query);
			value = rs.getDouble(1);
			stm.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.closeConnection();
		return value;
	}
	
	@GetMapping("randomHumidity")
	public Double getrandomHumidity() {
		this.opendatabase();
		String query = "SELECT humidityValue FROM co2humidity WHERE RANDOM() LIMIT 1";
		Double value = null;
		try {
			this.stm = c.createStatement();
			this.rs = stm.executeQuery(query);
			value = rs.getDouble(1);
			stm.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.closeConnection();
		return value;
	}

}
