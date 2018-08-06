package ke.co.blueconsulting.sianroses.util;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static ke.co.blueconsulting.sianroses.util.Constants.APP_DIR_NAME;
import static ke.co.blueconsulting.sianroses.util.Constants.APP_LOG_FILE_NAME;

public class ErrorLogFileWatcher extends FileWatcher {
	
	private String separator = File.separator;
	private String fileName = System.getProperty("user.home") + separator + APP_DIR_NAME + separator + APP_LOG_FILE_NAME;
	private JTextArea errorLogTextView;
	
	
	public ErrorLogFileWatcher(JTextArea errorLogTextView) {
		try {
			FileWatcher fileWatcher = new ErrorLogFileWatcher(errorLogTextView, fileName);
			fileWatcher.watchFile();
		} catch (Exception e) {
			AppLogger.logError("An error occured while trying to watch the error log. " + e.getLocalizedMessage());
		}
	}
	
	public ErrorLogFileWatcher(JTextArea errorLogTextView, String fileName) {
		super(fileName);
		this.errorLogTextView = errorLogTextView;
		updateErrorLogview();
	}
	
	@Override
	public void onModified() {
		updateErrorLogview();
	}
	
	private void updateErrorLogview() {
		errorLogTextView.setText("");
		try {
			File file = new File(fileName);
			byte[] fileBytes = Files.readAllBytes(file.toPath());
			for (byte b : fileBytes) {
				errorLogTextView.append(String.valueOf((char) b));
			}
		} catch (IOException e) {
			AppLogger.logError("An error occured when trying to update the error log view. " + e.getLocalizedMessage());
		}
	}
	
	
}
