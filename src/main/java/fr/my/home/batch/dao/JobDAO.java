package fr.my.home.batch.dao;

import javax.persistence.PersistenceException;

import org.hibernate.Session;

import fr.my.home.batch.bean.Job;
import fr.my.home.batch.dao.impl.HibernateDAO;
import fr.my.home.batch.exception.FonctionnalException;
import fr.my.home.batch.exception.TechnicalException;
import fr.my.home.batch.tool.DatabaseAccess;

/**
 * Classe DAO Hibernate qui prends en charge la gestion des jobs (tâches de batch exécutées)
 * 
 * @author Jonathan
 * @version 1.0
 * @since 15/05/2018
 */
public class JobDAO implements HibernateDAO<Job> {

	/**
	 * Constructeur
	 */
	public JobDAO() {}

	/**
	 * Méthodes
	 */

	/**
	 * Ajoute un nouveau job en base, ou exception fonctionnelle si impossible
	 * 
	 * @param job
	 * @param FonctionnalException
	 * @throws TechnicalException
	 */
	@Override
	public void add(Job job) throws FonctionnalException, TechnicalException {
		Session session = DatabaseAccess.getInstance().openSession();
		try {
			session.save(job);
			DatabaseAccess.getInstance().validateSession(session);
		} catch (PersistenceException pe) {
			throw new FonctionnalException("Impossible d'ajouter le job");
		}

	}

	/**
	 * Met à jour un job, ou exception fonctionnelle si impossible
	 * 
	 * @param job
	 * @throws FonctionnalException
	 * @throws TechnicalException
	 */
	@Override
	public void update(Job job) throws FonctionnalException, TechnicalException {
		Session session = DatabaseAccess.getInstance().openSession();
		try {
			session.saveOrUpdate(job);
			DatabaseAccess.getInstance().validateSession(session);
		} catch (PersistenceException pe) {
			throw new FonctionnalException("Impossible de mettre à jour le job");
		}
	}

	/**
	 * Supprime un job, ou exception fonctionnelle si impossible
	 * 
	 * @param job
	 * @throws FonctionnalException
	 * @throws TechnicalException
	 */
	@Override
	public void delete(Job job) throws FonctionnalException, TechnicalException {
		Session session = DatabaseAccess.getInstance().openSession();
		try {
			session.delete(job);
			DatabaseAccess.getInstance().validateSession(session);
		} catch (PersistenceException pe) {
			throw new FonctionnalException("Impossible de supprimer le job");
		}
	}

}
