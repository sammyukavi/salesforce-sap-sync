package ke.co.blueconsulting.sianroses.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "ARCREDIT")
public class Arcredit {
  @DatabaseField(generatedId = true, columnName = "AUTOID")
  private int id;
}
