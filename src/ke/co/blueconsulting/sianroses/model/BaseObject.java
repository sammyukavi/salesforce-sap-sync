package ke.co.blueconsulting.sianroses.model;

import com.j256.ormlite.field.DatabaseField;

public class BaseObject {

    @DatabaseField(columnName = "Push_to_SAP__c")
    private boolean pushToSap;

    @DatabaseField(columnName = "Pull_from_SAP__c")
    private boolean pullFromSap;
}
