package ke.co.blueconsulting.sianroses.util;

import com.j256.ormlite.logger.LocalLog;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static ke.co.blueconsulting.sianroses.util.Constants.APP_DIR_NAME;
import static ke.co.blueconsulting.sianroses.util.Constants.BundleKeys.LABEL_APP_NAME;
import static ke.co.blueconsulting.sianroses.util.Constants.APP_LOG_FILE_NAME;
import static ke.co.blueconsulting.sianroses.util.Constants.SQL_LOG_FILE_NAME;

/**
 * A class that is used to write logs on the app as events occur
 */
public class AppLogger {
	
	private static Logger logger;
	private static  AppLogger instance;
	
	static {
		instance = new AppLogger();
		logger = Logger.getLogger(StringUtils.getString(LABEL_APP_NAME));
		logger.setUseParentHandlers(false);
		try {
			String separator = File.separator;
			String directoryName = System.getProperty("user.home") + separator + APP_DIR_NAME + separator;
			//System.setProperty(LocalLog.LOCAL_LOG_LEVEL_PROPERTY, "ERROR");
			//System.setProperty(LocalLog.LOCAL_LOG_FILE_PROPERTY, directoryName + separator + SQL_LOG_FILE_NAME);
			File directory = new File(directoryName);
			if (!directory.exists()) {
				directory.mkdirs();
			}
			FileHandler fileHandler = new FileHandler(directoryName + separator + APP_LOG_FILE_NAME,
					true);
			logger.addHandler(fileHandler);
			SimpleFormatter formatter = new SimpleFormatter();
			fileHandler.setFormatter(formatter);
		} catch (SecurityException | IOException e) {
			AppLogger.logError("An error occured while trying to create/access the error log. " + e.getLocalizedMessage());
		}
	}
	
	public static void logInfo(String message) {
		logger.info(message);
	}
	
	public static void logWarning(String message) {
		logger.log(Level.WARNING, message);
	}
	
	public static void logError(String message) {
		logger.log(Level.SEVERE, message);
	}
	
	public static AppLogger getInstance() {
		return instance;
	}
}
