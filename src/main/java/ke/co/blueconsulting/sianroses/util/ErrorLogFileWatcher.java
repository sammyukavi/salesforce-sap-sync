package ke.co.blueconsulting.sianroses.util;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import static ke.co.blueconsulting.sianroses.util.Constants.APP_DIR_NAME;
import static ke.co.blueconsulting.sianroses.util.Constants.APP_LOG_FILE_NAME;
import static ke.co.blueconsulting.sianroses.util.Constants.DataTypeKeys.*;

public class ErrorLogFileWatcher extends FileWatcher {
	
	private JTextArea errorLogTextView;
	
	
	public ErrorLogFileWatcher(JTextArea errorLogTextView) {
		try {
			String separator = File.separator;
			String fileName = System.getProperty("user.home") + separator + APP_DIR_NAME + separator + APP_LOG_FILE_NAME;
			FileWatcher fileWatcher = new ErrorLogFileWatcher(errorLogTextView, fileName);
			fileWatcher.watchFile();
		} catch (Exception e) {
			AppLogger.logError("An error occurred while trying to watch the error log. " + e.getMessage());
		}
	}
	
	private ErrorLogFileWatcher(JTextArea errorLogTextView, String fileName) {
		super(fileName);
		this.errorLogTextView = errorLogTextView;
		updateErrorLogview();
	}
	
	@Override
	public void onModified() {
		updateErrorLogview();
	}
	
	private void updateErrorLogview() {
		
		StringBuilder msgStr = new StringBuilder();
		
		HashMap<String, ArrayList<String>> messages = AppLogger.getMessages();
		
		ArrayList<String> infoMessages = messages.get(INFO_MESSAGES_NODE_NAME);
		
		String spacer = "-----------------------------------------------------------------------------" +
				"---------------------------------------------------------";
		if (!infoMessages.isEmpty()) {
			msgStr.append(spacer + "\n");
			msgStr.append("Info Messages" + "\n");
			msgStr.append(spacer + "\n");
			for (String message : infoMessages) {
				msgStr.append(message + "\n\n");
			}
		}
		
		ArrayList<String> warningMessages = messages.get(WARNING_MESSAGES_NODE_NAME);
		
		if (!warningMessages.isEmpty()) {
			msgStr.append(spacer + "\n");
			msgStr.append("\nWarning Messages" + "\n");
			msgStr.append(spacer + "\n");
			for (String message : warningMessages) {
				msgStr.append(message + "\n\n");
			}
		}
		
		ArrayList<String> errorMessages = messages.get(ERROR_MESSAGES_NODE_NAME);
		
		if (!errorMessages.isEmpty()) {
			msgStr.append(spacer + "\n");
			msgStr.append("\nError Messages" + "\n");
			msgStr.append(spacer + "\n");
			for (String message : errorMessages) {
				msgStr.append(message + "\n\n");
			}
		}
		
		errorLogTextView.setText(msgStr.toString());
		
	}
	
	
}
