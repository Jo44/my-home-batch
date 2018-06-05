package fr.my.home.batch.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.query.Query;

import fr.my.home.batch.bean.User;
import fr.my.home.batch.dao.impl.HibernateDAO;
import fr.my.home.batch.exception.FonctionnalException;
import fr.my.home.batch.exception.TechnicalException;
import fr.my.home.batch.tool.DatabaseAccess;
import fr.my.home.batch.tool.Settings;

/**
 * Classe DAO Hibernate qui prends en charge la gestion des utilisateurs
 * 
 * @author Jonathan
 * @version 1.0
 * @since 12/05/2018
 */
public class UserDAO implements HibernateDAO<User> {

	/**
	 * Attributs
	 */
	private static final String USERDAO_GET_BY_INACTIVE = Settings.getStringProperty("user_get_by_inactive");
	private static final String USERDAO_GET_BY_REINIT_TOKEN_OR_DATE = Settings.getStringProperty("user_get_by_reinit_token_or_date");
	private static final String USERDAO_GET_BY_REMEMBER_ME_TOKEN_NOT_NULL = Settings.getStringProperty("user_get_by_remember_me_token_not_null");

	/**
	 * Constructeur
	 */
	public UserDAO() {}

	/**
	 * Méthodes
	 */

	/**
	 * Récupère la liste de tous les utilisateurs inactifs, ou exception fonctionnelle si aucun utilisateur
	 * 
	 * @return List<User>
	 * @throws FonctionnalException
	 * @throws TechnicalException
	 */
	public List<User> getAllInactiveUsers() throws FonctionnalException, TechnicalException {
		List<User> listUser = new ArrayList<User>();
		Session session = DatabaseAccess.getInstance().openSession();
		@SuppressWarnings("unchecked")
		Query<User> query = session.createQuery(USERDAO_GET_BY_INACTIVE);
		try {
			listUser = query.getResultList();
		} catch (NoResultException nre) {
			throw new FonctionnalException("Aucun utilisateur inactif");
		} finally {
			DatabaseAccess.getInstance().validateSession(session);
		}
		return listUser;
	}

	/**
	 * Récupère la liste de tous les utilisateurs ayant un token et/ou une date de ré-initialisation, ou exception fonctionnelle si aucun utilisateur
	 * 
	 * @return List<User>
	 * @throws FonctionnalException
	 * @throws TechnicalException
	 */
	public List<User> getAllUsersWithReinitTokenOrDate() throws FonctionnalException, TechnicalException {
		List<User> listUser = new ArrayList<User>();
		Session session = DatabaseAccess.getInstance().openSession();
		@SuppressWarnings("unchecked")
		Query<User> query = session.createQuery(USERDAO_GET_BY_REINIT_TOKEN_OR_DATE);
		try {
			listUser = query.getResultList();
		} catch (NoResultException nre) {
			throw new FonctionnalException("Aucun utilisateur ayant de token de ré-initialisation");
		} finally {
			DatabaseAccess.getInstance().validateSession(session);
		}
		return listUser;
	}

	/**
	 * Récupère la liste de tous les utilisateurs ayant un token de 'Remember Me' et une date de modification de plus de 1 an, ou exception
	 * fonctionnelle si aucun utilisateur
	 * 
	 * @return List<User>
	 * @throws FonctionnalException
	 * @throws TechnicalException
	 */
	public List<User> getAllUsersWithRememberMeToken() throws FonctionnalException, TechnicalException {
		List<User> listUser = new ArrayList<User>();
		Session session = DatabaseAccess.getInstance().openSession();
		@SuppressWarnings("unchecked")
		Query<User> query = session.createQuery(USERDAO_GET_BY_REMEMBER_ME_TOKEN_NOT_NULL);
		try {
			listUser = query.getResultList();
		} catch (NoResultException nre) {
			throw new FonctionnalException("Aucun utilisateur ayant de token de 'Remember Me'");
		} finally {
			DatabaseAccess.getInstance().validateSession(session);
		}
		return listUser;
	}

	/**
	 * Ajoute un nouvel utilisateur en base, ou exception fonctionnelle si impossible
	 * 
	 * @param user
	 * @param FonctionnalException
	 * @throws TechnicalException
	 */
	@Override
	public void add(User user) throws FonctionnalException, TechnicalException {
		Session session = DatabaseAccess.getInstance().openSession();
		try {
			session.save(user);
			DatabaseAccess.getInstance().validateSession(session);
		} catch (PersistenceException pe) {
			throw new FonctionnalException("Impossible d'ajouter l'utilisateur");
		}
	}

	/**
	 * Met à jour un utilisateur, ou exception fonctionnelle impossible (e-mail indisponible ?)
	 * 
	 * @param user
	 * @throws FonctionnalException
	 * @throws TechnicalException
	 */
	@Override
	public void update(User user) throws FonctionnalException, TechnicalException {
		Session session = DatabaseAccess.getInstance().openSession();
		try {
			session.saveOrUpdate(user);
			DatabaseAccess.getInstance().validateSession(session);
		} catch (PersistenceException pe) {
			throw new FonctionnalException("Impossible de mettre à jour l'utilisateur");
		}
	}

	/**
	 * Supprime un utilisateur, ou exception fonctionnelle si impossible
	 * 
	 * @param user
	 * @throws FonctionnalException
	 * @throws TechnicalException
	 */
	@Override
	public void delete(User user) throws FonctionnalException, TechnicalException {
		Session session = DatabaseAccess.getInstance().openSession();
		try {
			session.delete(user);
			DatabaseAccess.getInstance().validateSession(session);
		} catch (PersistenceException pe) {
			throw new FonctionnalException("Impossible de supprimer l'utilisateur");
		}
	}

}
