package fr.my.home.batch.tool;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Classe en charge de récupérer en mémoire les paramètres de l'application via un fichier 'settings.properties'
 * 
 * @author Jonathan
 * @version 1.0
 * @since 12/05/2018
 */
public class Settings {
	private static final Logger logger = LogManager.getLogger(Settings.class);

	/**
	 * Attributs
	 */
	private static Properties properties;

	/**
	 * Charge le fichier 'settings.properties'
	 */
	static {
		try {
			properties = new Properties();
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			properties.load(classLoader.getResourceAsStream("settings.properties"));
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Getters
	 */
	public static String getStringProperty(String key) {
		String parametre = properties.getProperty(key, null);
		return parametre;
	}

	public static int getIntProperty(String key) {
		String parametreStr = properties.getProperty(key, null);
		int parametre = Integer.parseInt(parametreStr);
		return parametre;
	}

	public static long getLongProperty(String key) {
		String parametreStr = properties.getProperty(key, null);
		long parametre = Long.parseLong(parametreStr);
		return parametre;
	}

}
