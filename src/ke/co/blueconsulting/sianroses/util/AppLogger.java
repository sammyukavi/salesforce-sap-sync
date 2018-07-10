package ke.co.blueconsulting.sianroses.util;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static ke.co.blueconsulting.sianroses.util.Constants.APP_DIR_NAME;
import static ke.co.blueconsulting.sianroses.util.Constants.BundleKeys.LABEL_APP_NAME;

/**
 * A class that is used to write logs on the app as events occur
 */
public class AppLogger {
	
	private static Logger logger;
	
	static {
		logger = Logger.getLogger(StringUtils.getString(LABEL_APP_NAME));
		logger.setUseParentHandlers(false);
		try {
			String separator = File.separator;
			String directoryName = System.getProperty("user.home") + separator + APP_DIR_NAME + separator;
			File directory = new File(directoryName);
			if (!directory.exists()) {
				directory.mkdirs();
			}
			FileHandler fileHandler = new FileHandler(directoryName + separator + "Log.txt",
					true);
			logger.addHandler(fileHandler);
			SimpleFormatter formatter = new SimpleFormatter();
			fileHandler.setFormatter(formatter);
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void logInfo(String message) {
		logger.info(message);
	}
	
	public static void logWarning(String message) {
		logger.warning(message);
	}
}
