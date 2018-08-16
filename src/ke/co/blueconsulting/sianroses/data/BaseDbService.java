package ke.co.blueconsulting.sianroses.data;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import ke.co.blueconsulting.sianroses.data.db.AuthCredentialsDbService;
import ke.co.blueconsulting.sianroses.model.app.AppAuthCredentials;
import ke.co.blueconsulting.sianroses.util.AppLogger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class BaseDbService {
	
	protected JdbcConnectionSource connectionSource;
	
	public BaseDbService() {
		try {
			connectionSource = new JdbcConnectionSource(getConnectionUrl());
		} catch (SQLException | ClassNotFoundException e) {
			AppLogger.logError("An error occured in the BaseDbService Constructor " + e.getMessage());
		}
	}
	
	private String getConnectionUrl() throws SQLException, ClassNotFoundException {
		AuthCredentialsDbService authCredentialsDbService = new AuthCredentialsDbService();
		AppAuthCredentials connectionData = authCredentialsDbService.getAppAuthCredentials();
		return "jdbc:sqlserver://" + connectionData.getServerAddress() + ":" + connectionData.getServerPort()
				+ ";" + "databaseName=" + connectionData.getDatabaseName() + ";user=" + connectionData.getDatabaseUsername()
				+ ";password=" + connectionData.getDatabasePassword();
	}
	
	protected <S> Dao<S, Integer> createDao(Class<S> sClass) throws SQLException {
		return DaoManager.createDao(connectionSource, sClass);
	}
	
	public boolean testServerConnection(String serverAddress, String serverPort, String databaseName,
	                                    String databaseUsername, String databasePassword) throws ClassNotFoundException, SQLException {
		String connectionUrl = "jdbc:sqlserver://" + serverAddress + ":" + serverPort + ";" + "databaseName="
				+ databaseName + ";user=" + databaseUsername + ";password=" + databasePassword;
		Connection connection = null;
		boolean status;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(connectionUrl);
			status = true;
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (Exception ignored) {
				}
			}
		}
		return status;
	}
	
	public <S> ArrayList<S> insertRecords(Class<S> sClass, ArrayList<S> records) throws SQLException {
		ArrayList<S> insertedRecords = new ArrayList<>();
		Dao<S, Integer> dao = createDao(sClass);
		for (S record : records) {
			dao.createOrUpdate(record);
			insertedRecords.add(record);
		}
		return insertedRecords;
	}
	
	public <S> ArrayList<S> getRecordsWithoutSalesforceId(Class<S> sClass) throws SQLException {
		String columnName = "SalesForceId";
		return getRecordsWithNullOrEmptyColumn(sClass, columnName);
	}
	
	
	private <S> ArrayList<S> getRecordsWithNullOrEmptyColumn(Class<S> sClass, String columnName) throws SQLException {
		Dao<S, Integer> dao = createDao(sClass);
		QueryBuilder<S, Integer> queryBuilder = dao.queryBuilder();
		Where<S, Integer> where = queryBuilder.where();
		return (ArrayList<S>) dao.query(where.or(where.isNull(columnName), where.eq(columnName, "")).prepare());
	}
	
	
	public <S> ArrayList<S> getRecordsWithAFieldCheckedTrue(Class<S> sClass) throws SQLException {
		String columnName = "Pull_from_SAP__c";
		return getRecordsWithAFieldCheckedTrue(sClass, columnName);
	}
	
	public <S> ArrayList<S> getRecordsWithAFieldCheckedTrue(Class<S> sClass, String columnName) throws SQLException {
		Dao<S, Integer> dao = createDao(sClass);
		QueryBuilder<S, Integer> queryBuilder = dao.queryBuilder();
		Where<S, Integer> where = queryBuilder.where();
		return (ArrayList<S>) dao.query(where.or(where.isNull(columnName), where.eq(columnName, true)).prepare());
	}
	
	
}
