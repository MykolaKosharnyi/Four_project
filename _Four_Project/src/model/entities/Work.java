package model.entities;

import java.io.Serializable;

public class Work implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private long time;
	private String adress;
	private int idPetition;
	private int idBrigade;
	
	public Work() {}
	
	public Work(int id, long time, String adress, int idPetition,
			int idBrigade) {
		this.id = id;
		this.time = time;
		this.adress = adress;
		this.idPetition = idPetition;
		this.idBrigade = idBrigade;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the time
	 */
	public long getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(long time) {
		this.time = time;
	}
	/**
	 * @return the adress
	 */
	public String getAdress() {
		return adress;
	}
	/**
	 * @param adress the adress to set
	 */
	public void setAdress(String adress) {
		this.adress = adress;
	}
	/**
	 * @return the idPetition
	 */
	public int getIdPetition() {
		return idPetition;
	}
	/**
	 * @param idPetition the idPetition to set
	 */
	public void setIdPetition(int idPetition) {
		this.idPetition = idPetition;
	}
	/**
	 * @return the idBrigade
	 */
	public int getIdBrigade() {
		return idBrigade;
	}
	/**
	 * @param idBrigade the idBrigade to set
	 */
	public void setIdBrigade(int idBrigade) {
		this.idBrigade = idBrigade;
	}
	
}
