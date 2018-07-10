package ke.co.blueconsulting.sianroses;

import ke.co.blueconsulting.sianroses.util.AppLogger;

import java.awt.*;

import static ke.co.blueconsulting.sianroses.util.Constants.BundleKeys.MESSAGE_FATAL_ERROR;
import static ke.co.blueconsulting.sianroses.util.StringUtils.getString;

public class MainApplication {
	
	/**
	 * The start point of the application
	 *
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(() -> {
			try {
				new SyncDashboard();
				AppLogger.logInfo("Called");
				//new DrawSystemTrayIcon().displayTray();
			} catch (Exception e) {
				e.printStackTrace();
				AppLogger.logInfo(e.getMessage());
				SyncDashboard.getInstance().showErrorMessage(getString(MESSAGE_FATAL_ERROR), getString(MESSAGE_FATAL_ERROR) + e.getMessage());
			}
		});
	}
}
