package ke.co.blueconsulting.sianroses.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * This is a dummy model used to create a table on the db for testing purses
 */
@DatabaseTable(tableName = "test_db_connection")
public class TestDbConnection {
  
  @DatabaseField(generatedId = true)
  private int id;
  
  @DatabaseField(columnName = "message", canBeNull = false)
  private String message = "";
  
}
