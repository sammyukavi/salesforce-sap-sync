package ke.co.blueconsulting.sianroses.data.db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import ke.co.blueconsulting.sianroses.model.app.AppAuthCredentials;

import java.io.File;
import java.sql.SQLException;

import static ke.co.blueconsulting.sianroses.util.Constants.APP_DIR_NAME;
import static ke.co.blueconsulting.sianroses.util.Constants.SQLITE_DATABASE_NAME;

public class AuthCredentialsDbService {
	
	private Dao<AppAuthCredentials, Integer> credentialsDao;
	private AppAuthCredentials appAuthCredentials;
	
	
	public AuthCredentialsDbService() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		ConnectionSource connectionSource = new JdbcConnectionSource(getDatabaseUrl());
		credentialsDao = DaoManager.createDao(connectionSource, AppAuthCredentials.class);
		TableUtils.createTableIfNotExists(connectionSource, AppAuthCredentials.class);
		this.appAuthCredentials = credentialsDao.queryForId(1);
		if (appAuthCredentials == null) {
			appAuthCredentials = new AppAuthCredentials();
		}
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
	
	public void save(AppAuthCredentials appAuthCredentials) throws SQLException {
		appAuthCredentials.setId(1);
		credentialsDao.createOrUpdate(appAuthCredentials);
	}
	
	public AppAuthCredentials getAppAuthCredentials() {
		return appAuthCredentials;
	}
	
	public void setAppAuthCredentials(AppAuthCredentials appAuthCredentials) {
		this.appAuthCredentials = appAuthCredentials;
	}
}
