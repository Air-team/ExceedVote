package exceedvote.air.ui;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Messages {
	
	private static Locale LOCALE = new Locale("");
	
	private static final String BUNDLE_NAME = "exceedvote.air.ui.messages"; //$NON-NLS-1$

	private static ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME , LOCALE);

	private Messages() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
	
	public static void setLocale(String language) {
		RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME , new Locale(language));
	}
}
