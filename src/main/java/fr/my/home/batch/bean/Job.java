package fr.my.home.batch.bean;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * Classe modèle des jobs du batch (tâches exécutées)
 * 
 * @author Jonathan
 * @version 1.0
 * @since 15/05/2018
 */
@Entity
@Table(name = "job")
public class Job implements Serializable {
	private static final long serialVersionUID = 930448801449184468L;

	/**
	 * Attributs
	 */
	private int id;
	private String name;
	private int processed;
	private boolean finished;
	private boolean status;
	private Timestamp startTime;
	private Timestamp finishTime;

	/**
	 * Constructeur par défaut
	 */
	public Job() {
		super();
	}

	/**
	 * Constructeur
	 * 
	 * @param name
	 * @param processed
	 * @param finished
	 * @param status
	 * @param startTime
	 * @param finishTime
	 */
	public Job(String name, int processed, boolean finished, boolean status, Timestamp startTime, Timestamp finishTime) {
		this();
		this.name = name;
		this.processed = processed;
		this.finished = finished;
		this.status = status;
		this.startTime = startTime;
		this.finishTime = finishTime;
	}

	/**
	 * To String
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{ ID: ");
		sb.append(String.valueOf(id));
		sb.append(" , Name: ");
		sb.append(name);
		sb.append(" , Processed: ");
		sb.append(String.valueOf(processed));
		sb.append(" , Finished: ");
		sb.append(String.valueOf(finished));
		sb.append(" , Status: ");
		sb.append(String.valueOf(status));
		sb.append(" , Start Time: ");
		if (startTime != null) {
			sb.append(startTime.toString());
		} else {
			sb.append("null");
		}
		sb.append(" , Finish Time: ");
		if (finishTime != null) {
			sb.append(finishTime.toString());
		} else {
			sb.append("null");
		}
		sb.append(" }");
		return sb.toString();
	}

	/**
	 * Getters / Setters (les setters privés sont seulement utilisés par Hibernate)
	 */
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "job_id")
	public int getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(int id) {
		this.id = id;
	}

	@Column(name = "job_name")
	public String getName() {
		return name;
	}

	@SuppressWarnings("unused")
	private void setName(String name) {
		this.name = name;
	}

	@Column(name = "job_processed")
	public int getProcessed() {
		return processed;
	}

	public void setProcessed(int processed) {
		this.processed = processed;
	}

	@Column(name = "job_finished")
	public boolean getFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	@Column(name = "job_status")
	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Column(name = "job_start_time")
	public Timestamp getStartTime() {
		return startTime;
	}

	@SuppressWarnings("unused")
	private void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	@Column(name = "job_finish_time")
	public Timestamp getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Timestamp finishTime) {
		this.finishTime = finishTime;
	}

}
