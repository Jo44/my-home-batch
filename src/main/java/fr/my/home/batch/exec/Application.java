package fr.my.home.batch.exec;

import java.io.File;
import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.my.home.batch.manager.TaskManager;
import fr.my.home.batch.tool.DatabaseAccess;
import fr.my.home.batch.tool.Settings;
import fr.my.home.batch.tool.GlobalTools;

/**
 * Batch de traitement du projet 'My Home' qui effectue diverses tâches plânifiées de nettoyage de la base de données
 * 
 * @author Jonathan
 * @version 1.0
 * @since 15/05/2018
 */
public class Application {
	private static final Logger logger = LogManager.getLogger(Application.class);

	// Ré-initialise les variables TaskToday (pour exécution 1 fois / jour max)
	// -> Tous les jours entre 00h05 et 00h07

	// Batch de suppression utilisateur non activé après 72h
	// -> Tous les jours entre 6h et 6h30

	// Batch de suppression des infos de ré-initialisation après 24h
	// -> Tous les jours entre 6h30 et 7h

	// Batch de suppression de l'info du 'Remember Me' après 1an
	// -> Tous les Lundi entre 7h et 7h30

	/**
	 * Attributs
	 */
	private static final String MYHOME_PATH = System.getenv("MYHOME");
	private static final String DB_ERROR = Settings.getStringProperty("error_db");

	/**
	 * Lancement du batch
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {

		// Lancement du batch
		logger.info("### Lancement du batch ###");
		boolean run = true;
		TaskManager taskMgr = new TaskManager();

		// Supprime le fichier 'file.stop' pour le lancement
		File batchStopFile = new File(MYHOME_PATH + "/batch/file.stop");
		if (batchStopFile.exists() && !batchStopFile.isDirectory()) {
			batchStopFile.delete();
			logger.info("Suppression du fichier 'file.stop' pour lancement");
		}

		// Met à jour la date/heure de lancement de l'application
		GlobalTools.setLaunchDate(Calendar.getInstance());

		// Lance la configuration d'Hibernate et test la connexion
		boolean dbOk = DatabaseAccess.getInstance().testConnection();

		// Si base de données OK
		if (dbOk) {
			// Boucle d'exécution
			while (run) {
				// Vérifie si le fichier 'file.stop' est toujours absent
				batchStopFile = new File(MYHOME_PATH + "/batch/file.stop");
				if (batchStopFile.exists() && !batchStopFile.isDirectory()) {
					run = false;
					logger.info("Fichier 'file.stop' trouvé");
					logger.info("### Fin du batch ###");
				}
				// Si toujours présent
				if (run) {
					// Vérifie si une tâche doit être lancée
					taskMgr.checkSchedule();
					// ZzZzZz 1sec ZzZzZz
					Thread.sleep(1000);
				}
			}
		} else {
			// Si base de données KO
			logger.error(DB_ERROR);

			// Renvoi une RunTime Exception
			throw new RuntimeException();
		}
	}

}
