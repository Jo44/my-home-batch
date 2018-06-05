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
 * Classe modèle des utilisateurs de l'application
 * 
 * @author Jonathan
 * @version 1.0
 * @since 12/05/2018
 */
@Entity
@Table(name = "user")
public class User implements Serializable {
	private static final long serialVersionUID = 930448801449184468L;

	/**
	 * Attributs
	 */
	private int id;
	private String name;
	private String pass;
	private String email;
	private String rememberMeToken;
	private String validationToken;
	private boolean active;
	private String reInitToken;
	private Timestamp reInitDate;
	private Timestamp inscriptionDate;
	private Timestamp update;

	/**
	 * Constructeur par défaut
	 */
	public User() {
		super();
	}

	/**
	 * Constructeur
	 * 
	 * @param name
	 * @param pass
	 * @param email
	 * @param rememberMeToken
	 * @param validationToken
	 * @param active
	 * @param reInitToken
	 * @param reInitDate
	 * @param inscriptionDate
	 * @param update
	 */
	public User(String name, String pass, String email, String rememberMeToken, String validationToken, boolean active, String reInitToken,
			Timestamp reInitDate, Timestamp inscriptionDate, Timestamp update) {
		this();
		this.name = name;
		this.pass = pass;
		this.email = email;
		this.rememberMeToken = rememberMeToken;
		this.validationToken = validationToken;
		this.active = active;
		this.reInitToken = reInitToken;
		this.reInitDate = reInitDate;
		this.inscriptionDate = inscriptionDate;
		this.update = update;
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
		sb.append(" , Email: ");
		sb.append(email);
		sb.append(" , Remember Me Token: ");
		sb.append(rememberMeToken);
		sb.append(" , Validation Token: ");
		sb.append(validationToken);
		sb.append(" , Active: ");
		sb.append(String.valueOf(active));
		sb.append(" , ReInit Token: ");
		sb.append(reInitToken);
		sb.append(" , ReInit Date: ");
		if (reInitDate != null) {
			sb.append(reInitDate.toString());
		} else {
			sb.append("null");
		}
		sb.append(" , Inscription Date: ");
		if (inscriptionDate != null) {
			sb.append(inscriptionDate.toString());
		} else {
			sb.append("null");
		}
		sb.append(" , Update: ");
		if (update != null) {
			sb.append(update.toString());
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
	@Column(name = "user_id")
	public int getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(int id) {
		this.id = id;
	}

	@Column(name = "user_name")
	public String getName() {
		return name;
	}

	@SuppressWarnings("unused")
	private void setName(String name) {
		this.name = name;
	}

	@Column(name = "user_pass")
	public String getPass() {
		return pass;
	}

	@SuppressWarnings("unused")
	private void setPass(String pass) {
		this.pass = pass;
	}

	@Column(name = "user_email")
	public String getEmail() {
		return email;
	}

	@SuppressWarnings("unused")
	private void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "user_remember_me_token")
	public String getRememberMeToken() {
		return rememberMeToken;
	}

	public void setRememberMeToken(String rememberMeToken) {
		this.rememberMeToken = rememberMeToken;
	}

	@Column(name = "user_validation_token")
	public String getValidationToken() {
		return validationToken;
	}

	@SuppressWarnings("unused")
	private void setValidationToken(String validationToken) {
		this.validationToken = validationToken;
	}

	@Column(name = "user_active")
	public boolean isActive() {
		return active;
	}

	@SuppressWarnings("unused")
	private void setActive(boolean active) {
		this.active = active;
	}

	@Column(name = "user_reinit_token")
	public String getReInitToken() {
		return reInitToken;
	}

	public void setReInitToken(String reInitToken) {
		this.reInitToken = reInitToken;
	}

	@Column(name = "user_reinit_date")
	public Timestamp getReInitDate() {
		return reInitDate;
	}

	public void setReInitDate(Timestamp reInitDate) {
		this.reInitDate = reInitDate;
	}

	@Column(name = "user_inscription_date")
	public Timestamp getInscriptionDate() {
		return inscriptionDate;
	}

	@SuppressWarnings("unused")
	private void setInscriptionDate(Timestamp inscriptionDate) {
		this.inscriptionDate = inscriptionDate;
	}

	@Column(name = "user_update")
	public Timestamp getUpdate() {
		return update;
	}

	public void setUpdate(Timestamp update) {
		this.update = update;
	}

}
