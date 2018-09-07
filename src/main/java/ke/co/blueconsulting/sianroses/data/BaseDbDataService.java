package ke.co.blueconsulting.sianroses.data;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import ke.co.blueconsulting.sianroses.data.impl.db.AuthCredentialsDbService;
import ke.co.blueconsulting.sianroses.model.app.AppAuthCredentials;
import ke.co.blueconsulting.sianroses.util.AppLogger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class BaseDbDataService<C> implements DataService {
	
	protected Dao<C, Integer> dao;
	protected JdbcConnectionSource connectionSource;
	
	public BaseDbDataService() {
		try {
			connectionSource = new JdbcConnectionSource(getSAPConnectionUrl());
			this.dao = DaoManager.createDao(connectionSource, getDaoServiceClass());
		} catch (SQLException | ClassNotFoundException e) {
			AppLogger.logError("An error occured in the BaseDbDataService Constructor " + e.getMessage());
		}
	}
	
	public static boolean testSAPConnection(String serverAddress, String serverPort, String databaseName,
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
	
	/**
	 * Get an instance of the Class used to make REST calls
	 *
	 * @return Class Instance
	 */
	protected abstract Class<C> getDaoServiceClass();
	
	private String getSAPConnectionUrl() throws SQLException, ClassNotFoundException {
		AuthCredentialsDbService authCredentialsDbService = new AuthCredentialsDbService();
		AppAuthCredentials connectionData = authCredentialsDbService.getAppAuthCredentials();
		return "jdbc:sqlserver://" + connectionData.getServerAddress() + ":" + connectionData.getServerPort()
				+ ";" + "databaseName=" + connectionData.getDatabaseName() + ";user=" + connectionData.getDatabaseUsername()
				+ ";password=" + connectionData.getDatabasePassword();
	}
	
	public C insertRecord(C record) throws SQLException {
		dao.createOrUpdate(record);
		return record;
	}
	
	public ArrayList<C> insertRecords(ArrayList<C> records) throws SQLException {
		ArrayList<C> insertedRecords = new ArrayList<>();
		for (C record : records) {
			dao.createOrUpdate(record);
			insertedRecords.add(record);
		}
		return insertedRecords;
	}
	
	public ArrayList<C> getRecordsWithoutSalesforceId() throws SQLException {
		String columnName = "SalesForceId";
		return getRecordsWithNullOrEmptyColumn(columnName);
	}
	
	
	private ArrayList<C> getRecordsWithNullOrEmptyColumn(String columnName) throws SQLException {
		QueryBuilder<C, Integer> queryBuilder = dao.queryBuilder();
		Where<C, Integer> where = queryBuilder.where();
		queryBuilder.orderBy("AUTOID", true);
		queryBuilder.setWhere(where.or(where.isNull(columnName), where.eq(columnName, "")));
		return (ArrayList<C>) dao.query(queryBuilder.prepare());
	}
	
	
	public void getRecordsWithPullFromSapCheckedTrue(GetCallback<ArrayList<C>> callback) {
		getRecordsWithPullFromSapCheckedTrue(callback, 0L);
	}
	
	private void getRecordsWithPullFromSapCheckedTrue(GetCallback<ArrayList<C>> callback, long limit) {
		try {
			QueryBuilder<C, Integer> queryBuilder = dao.queryBuilder();
			Where<C, Integer> where = queryBuilder.where();
			queryBuilder.setWhere(where.or(where.isNull("Pull_from_SAP__c"), where.eq("Pull_from_SAP__c", true)));
			queryBuilder.orderBy("AUTOID", true);
			if (limit != 0) {
				queryBuilder.limit(limit);
			}
			if (callback != null) {
				callback.onCompleted((ArrayList<C>) dao.query(queryBuilder.prepare()));
			}
		} catch (SQLException e) {
			if (callback != null) {
				callback.onError(e);
			}
		} finally {
			if (callback != null) {
				callback.always();
			}
		}
		
	}
	
	public void getUnsynced(GetCallback<ArrayList<C>> callback) {
		try {
			QueryBuilder<C, Integer> queryBuilder = dao.queryBuilder();
			Where<C, Integer> where = queryBuilder.where();
			where = where.or(where.isNull("SalesForceId"), where.eq("SalesForceId", ""),
					where.eq("Pull_from_SAP__c", true));
			queryBuilder.setWhere(where);
			queryBuilder.orderBy("AUTOID", true);
			if (callback != null) {
				callback.onCompleted((ArrayList<C>) dao.query(queryBuilder.prepare()));
			}
		} catch (SQLException e) {
			if (callback != null) {
				callback.onError(e);
			}
		} finally {
			if (callback != null) {
				callback.always();
			}
		}
	}
	
	public void getUnsyncedCount(GetCallback<Long> callback) {
		QueryBuilder<C, Integer> queryBuilder = dao.queryBuilder();
		try {
			Where<C, Integer> where = queryBuilder.where();
			where = where.or(where.isNull("SalesForceId"), where.eq("SalesForceId", ""),
					where.eq("Pull_from_SAP__c", true));
			queryBuilder.setWhere(where);
			if (callback != null) {
				callback.onCompleted(queryBuilder.countOf());
			}
		} catch (SQLException e) {
			if (callback != null) {
				callback.onError(e);
			}
		} finally {
			if (callback != null) {
				callback.always();
			}
		}
		
	}
	
	
}
