package model.entities;

import java.io.Serializable;

public class Petition implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private ExpressService expressService;//נמה נאבמע
	private ScaleWork scaleWork;//לאסרעאב נאבמע
	private long time;
	private int idUser;
	
	public Petition(int id, ExpressService expressService, ScaleWork scaleWork,
			long time, int idUser) {
		this.id = id;
		this.expressService = expressService;
		this.scaleWork = scaleWork;
		this.time = time;
		this.idUser = idUser;
	}

	public Petition() {}
	
	/**
	 * @return the idUser
	 */
	public int getIdUser() {
		return idUser;
	}
	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(int idUser) {
		this.idUser = idUser;
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
	 * @return the expressService
	 */
	public ExpressService getExpressService() {
		return expressService;
	}
	/**
	 * @param expressService the expressService to set
	 */
	public void setExpressService(ExpressService expressService) {
		this.expressService = expressService;
	}
	/**
	 * @return the scaleWork
	 */
	public ScaleWork getScaleWork() {
		return scaleWork;
	}
	/**
	 * @param scaleWork the scaleWork to set
	 */
	public void setScaleWork(ScaleWork scaleWork) {
		this.scaleWork = scaleWork;
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
	
}
