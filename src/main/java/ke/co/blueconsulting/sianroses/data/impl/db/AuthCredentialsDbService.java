package ke.co.blueconsulting.sianroses.data.impl.db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import ke.co.blueconsulting.sianroses.model.app.AppAuthCredentials;
import ke.co.blueconsulting.sianroses.util.AppLogger;

import java.io.File;
import java.sql.SQLException;

import static ke.co.blueconsulting.sianroses.util.Constants.APP_DIR_NAME;
import static ke.co.blueconsulting.sianroses.util.Constants.SQLITE_DATABASE_NAME;

/**
 * A class that is used to create a service for storing and retrieving the
 * credentials used to login to the SAP server and credentials used in the
 * Salesforce Client
 */
public class AuthCredentialsDbService {
	
	private Dao<AppAuthCredentials, Integer> credentialsDao;
	
	public AuthCredentialsDbService() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		ConnectionSource connectionSource = new JdbcConnectionSource(getDatabaseUrl());
		credentialsDao = DaoManager.createDao(connectionSource, AppAuthCredentials.class);
		TableUtils.createTableIfNotExists(connectionSource, AppAuthCredentials.class);
	}
	
	private String getDatabaseUrl() {
		String separator = File.separator;
		String directoryName = System.getProperty("user.home") + separator + APP_DIR_NAME + separator;
		File directory = new File(directoryName);
		if (!directory.exists()) {
			directory.mkdirs();
		}
		return "jdbc:sqlite:" + directoryName + SQLITE_DATABASE_NAME;
	}
	
	public AppAuthCredentials save(AppAuthCredentials appAuthCredentials) throws SQLException {
		appAuthCredentials.setId(1);
		credentialsDao.createOrUpdate(appAuthCredentials);
		return appAuthCredentials;
	}
	
	public AppAuthCredentials getAppAuthCredentials() {
		try {
			AppAuthCredentials credentials = credentialsDao.queryForId(1);
			if (credentials != null) {
				return credentials;
			}
		} catch (SQLException e) {
			AppLogger.logError("An error occured when fetching AppAuthCredentials. " + e.getMessage());
		}
		return new AppAuthCredentials();
	}
}
