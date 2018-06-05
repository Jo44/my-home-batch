package fr.my.home.batch.task.impl;

import fr.my.home.batch.exception.TechnicalException;

import fr.my.home.batch.exception.FonctionnalException;

/**
 * Interface générale des tâches du batch qui déclare les 3 méthodes init / execute / finish
 * 
 * @author Jonathan
 * @version 1.0
 * @since 15/05/2018
 */
public interface BatchTask {

	/**
	 * Initialise la tâche
	 * 
	 * @throws FonctionnalException
	 * @throws TechnicalException
	 */
	public void init() throws FonctionnalException, TechnicalException;

	/**
	 * Exécute la tâche
	 * 
	 * @return boolean
	 * @throws TechnicalException
	 */
	public boolean execute() throws TechnicalException;

	/**
	 * Finalise la tâche
	 * 
	 * @param status
	 * @throws FonctionnalException
	 * @throws TechnicalException
	 */
	public void finish(boolean status) throws FonctionnalException, TechnicalException;

}
