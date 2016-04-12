package fr.utbm.info.vi51.worldswar.gui;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author Leo
 * 
 *         Class that allow the internationnalisation (I18N) of all the GUI
 *         messages
 */
public class Messages {
	private static final String BUNDLE_NAME = "fr.utbm.info.vi51.worldsWar.gui.messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private Messages() {
	}

	/**
	 * @param key
	 *            the unique id of the string
	 * @return the asked string
	 */
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (@SuppressWarnings("unused") final MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
