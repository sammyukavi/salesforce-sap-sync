package ke.co.blueconsulting.sianroses;

import ke.co.blueconsulting.sianroses.dialog.MessageDialog;
import ke.co.blueconsulting.sianroses.util.AppLogger;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

import static ke.co.blueconsulting.sianroses.util.Constants.BundleKeys.LABEL_APP_NAME;
import static ke.co.blueconsulting.sianroses.util.Constants.BundleKeys.TITLE_ABOUT;
import static ke.co.blueconsulting.sianroses.util.StringUtils.breakLongString;
import static ke.co.blueconsulting.sianroses.util.StringUtils.getString;

public class SianRosesApp {
	
	private static SyncDashboard syncDashboard;
	private static TrayIcon trayIcon;
	private static boolean systemTrayIsSupported = false;
	
	/**
	 * The start point of the application
	 */
	public static void main(String[] args) {
		
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(SianRosesApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		
		
		EventQueue.invokeLater(() -> {
			try {
				drawSystemTrayIcon();
				syncDashboard = new SyncDashboard();
			} catch (Exception e) {
				AppLogger.logError("An error occured while trying to start the application. " + e.getMessage());
			}
		});
	}
	
	
	private static void drawSystemTrayIcon() {
		
		systemTrayIsSupported = SystemTray.isSupported();
		
		if (!SystemTray.isSupported()) {
			AppLogger.logInfo("SystemTray is not supported");
			return;
		}
		
		trayIcon = new TrayIcon(createIcon("sian_roses_icon.jpg", "Sian Roses App Tray Icon").getImage());
		trayIcon.setImageAutoSize(true);
		trayIcon.setToolTip(getString(LABEL_APP_NAME));
		
		MenuItem bringToFrontMenuItem = new MenuItem("Show The Dashboard");
		MenuItem showLogsMenuItem = new MenuItem("View Log");
		MenuItem aboutMenuItem = new MenuItem("About");
		MenuItem exitMenuItem = new MenuItem("Exit");
		
		PopupMenu popup = new PopupMenu();
		popup.add(bringToFrontMenuItem);
		popup.addSeparator();
		popup.add(showLogsMenuItem);
		popup.addSeparator();
		popup.add(aboutMenuItem);
		popup.addSeparator();
		popup.add(exitMenuItem);
		
		trayIcon.setPopupMenu(popup);
		
		SystemTray systemTray = SystemTray.getSystemTray();
		try {
			systemTray.add(trayIcon);
		} catch (AWTException e) {
			AppLogger.logInfo("TrayIcon could not be added.");
			return;
		}
		
		trayIcon.addActionListener(e -> {
			SyncDashboard.bringToFront();
		});
		
		bringToFrontMenuItem.addActionListener(e -> {
			SyncDashboard.bringToFront();
		});
		
		aboutMenuItem.addActionListener(e -> {
			//TODO resize and use blue consulting icon
			MessageDialog.showMessageDialog(SyncDashboard.dashboardJFrame, breakLongString("Sian Roses Salesforce Integration App.\n\nv 1.0\n\nPowered by Blue Consulting"), getString(TITLE_ABOUT), JOptionPane.INFORMATION_MESSAGE, getBlueConsultingIcon());
		});
		
		showLogsMenuItem.addActionListener(e -> {
			syncDashboard.switchToLogsTab();
		});
		
		
		exitMenuItem.addActionListener(e -> {
			int dialogResult = JOptionPane.showConfirmDialog(SyncDashboard.dashboardJFrame,
					"Are you sure you want to exit the application?", "Confirm", JOptionPane.YES_NO_OPTION);
			if (dialogResult == JOptionPane.YES_OPTION) {
				systemTray.remove(trayIcon);
				System.exit(0);
			}
		});
	}
	
	static void displayErrorMessage(String message) {
		if (systemTrayIsSupported) {
			trayIcon.displayMessage(getString(LABEL_APP_NAME), message, TrayIcon.MessageType.ERROR);
		}
	}
	
	static void displayWarningMessage(String message) {
		if (systemTrayIsSupported) {
			trayIcon.displayMessage(getString(LABEL_APP_NAME), message, TrayIcon.MessageType.WARNING);
		}
	}
	
	static void displayInfoMessage(String message) {
		if (systemTrayIsSupported) {
			trayIcon.displayMessage(getString(LABEL_APP_NAME), message, TrayIcon.MessageType.INFO);
		}
	}
	
	static void displayOrdinaryMessage(String message) {
		if (systemTrayIsSupported) {
			trayIcon.displayMessage(getString(LABEL_APP_NAME), message, TrayIcon.MessageType.NONE);
		}
	}
	
	private static ImageIcon createIcon(String fileName, String description) {
		URL imageURL = SyncDashboard.class.getResource("/ke/co/blueconsulting/sianroses/resource/" + fileName);
		if (imageURL == null) {
			AppLogger.logError("Resource not found: " + fileName);
			return null;
		} else {
			return (new ImageIcon(imageURL, description));
		}
	}
	
	static ImageIcon getBlueConsultingIcon() {
		return createIcon("blue_consulting_icon.jpg", "Blue Consulting Icon");
	}
	
	static ImageIcon getSianRosesIcon() {
		return createIcon("sian_roses_icon.jpg", "Sian Roses App Tray Icon");
	}
}
