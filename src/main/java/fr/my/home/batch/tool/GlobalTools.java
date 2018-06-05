package fr.my.home.batch.tool;

import java.util.Calendar;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.my.home.batch.tool.Settings;

/**
 * Classe regroupant différents outils (send Email / stock launchDate)
 * 
 * @author Jonathan
 * @version 1.1
 * @since 03/06/2018
 */
public class GlobalTools {
	private static final Logger logger = LogManager.getLogger(GlobalTools.class);

	/**
	 * Attributs
	 */
	private static final String SMTP_HOSTNAME = Settings.getStringProperty("smtp_hostname");
	private static final int SMTP_PORT = Settings.getIntProperty("smtp_port");
	private static final String SMTP_USER = Settings.getStringProperty("smtp_user");
	private static final String SMTP_PASS = Settings.getStringProperty("smtp_pass");
	private static final String EMAIL_TARGET = Settings.getStringProperty("email_target");
	private static Calendar launchDate;

	/**
	 * Envoi un mail en utilisant Apache Commons Email
	 * 
	 * @param subject
	 * @param message
	 */
	public static void sendEmail(String subject, String message) {
		try {
			Email email = new SimpleEmail();
			email.setHostName(SMTP_HOSTNAME);
			email.setSmtpPort(SMTP_PORT);
			email.setAuthenticator(new DefaultAuthenticator(SMTP_USER, SMTP_PASS));
			email.setSSLOnConnect(true);
			email.setFrom(SMTP_USER);
			email.setSubject(subject);
			email.setMsg(message);
			email.addTo(EMAIL_TARGET);
			email.send();
			logger.info("Email correctement envoyé à " + EMAIL_TARGET);
		} catch (EmailException ee) {
			ee.printStackTrace();
			logger.error("Erreur lors de l'envoi de l'email à " + EMAIL_TARGET);
		}
	}

	/**
	 * Getter / Setter
	 */
	public static Calendar getLaunchDate() {
		return launchDate;
	}

	public static void setLaunchDate(Calendar calendar) {
		launchDate = calendar;
	}

}
