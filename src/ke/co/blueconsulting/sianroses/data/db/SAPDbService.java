package ke.co.blueconsulting.sianroses.data.db;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import ke.co.blueconsulting.sianroses.data.BaseDbService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * A class that is used to create a service for storing and retrieving the data
 * in the SAP server
 */
public class SAPDbService extends BaseDbService {
	
	public SAPDbService() {
		super();
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
		String columnName = "SalesforceId";
		return getRecordsWithoutSalesforceId(sClass, columnName);
	}
	
	@SuppressWarnings("unchecked")
	public <S> ArrayList<S> getRecordsWithoutSalesforceId(Class<S> sClass, String columnName) throws SQLException {
		Dao<S, Integer> dao = createDao(sClass);
		QueryBuilder<S, Integer> queryBuilder = dao.queryBuilder();
		Where<S, Integer> where = queryBuilder.where();
		return (ArrayList<S>) dao.query(where.or(where.isNull(columnName), where.eq(columnName, "")).prepare());
	}
	
	public <S> ArrayList<S> getRecordsWithAFieldCheckedTrue(Class<S> sClass) throws SQLException {
		String columnName = "Pull_from_SAP__c";
		return getRecordsWithAFieldCheckedTrue(sClass, columnName);
	}
	
	@SuppressWarnings("unchecked")
	public <S> ArrayList<S> getRecordsWithAFieldCheckedTrue(Class<S> sClass, String columnName) throws SQLException {
		Dao<S, Integer> dao = createDao(sClass);
		QueryBuilder<S, Integer> queryBuilder = dao.queryBuilder();
		Where<S, Integer> where = queryBuilder.where();
		return (ArrayList<S>) dao.query(where.or(where.isNull(columnName), where.eq(columnName, true)).prepare());
	}
}
