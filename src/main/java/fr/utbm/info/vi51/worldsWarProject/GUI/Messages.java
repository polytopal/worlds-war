package fr.utbm.info.vi51.worldsWarProject.GUI;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author Leo
 *
 *         Classe permettant l'internationnalisation (I18N) des messages de
 *         l'interface graphique
 */
public class Messages {
	private static final String BUNDLE_NAME = "fr.utbm.info.vi51.worldsWarProject.GUI.messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private Messages() {
	}

	/**
	 * @param key
	 *            la clé de la chaine
	 * @return la chaine demandé
	 */
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (@SuppressWarnings("unused") final MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
