package ke.co.blueconsulting.sianroses.model.salesforce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * This model maps Products to Flower_Variety__c in Salesforce Syncing for
 * Products is one way from the SAP server to Salesforce.
 */
@DatabaseTable(tableName = "PRODUCTSCHILD")
public class ProductChild {

    @DatabaseField(generatedId = true, columnName = "AUTOID")
    private int autoId;

    @DatabaseField(columnName = "Breeder__c")
    @SerializedName("Breeder__c")
    @Expose
    private String breederC;

    @DatabaseField(columnName = "Classification__c")
    @SerializedName("Classification__c")
    @Expose
    private String classificationC;

    @DatabaseField(columnName = "Color__c")
    @SerializedName("Color__c")
    @Expose
    private String colorC;

    @DatabaseField(columnName = "Consumable_Stock__c")
    @SerializedName("Consumable_Stock__c")
    @Expose
    private double consumableStockC;

    @DatabaseField(columnName = "Flower_Code__c")
    @SerializedName("Flower_Code__c")
    @Expose
    private String flowerCodeC;

    @DatabaseField(columnName = "Product_Family__c")
    @SerializedName("Family__c")
    @Expose
    private String productFamilyC;

    @DatabaseField(columnName = "Farm__c")
    @SerializedName("Farms__c")
    @Expose
    private String farmC;

    @DatabaseField(columnName = "Headsize__c")
    @SerializedName("Head_size__c")
    @Expose
    private double headSizeC;

    @DatabaseField(columnName = "Is_Active__c")
    @SerializedName("Is_Active__c")
    @Expose
    private boolean isActiveC;

    @DatabaseField(columnName = "Length__c")
    @SerializedName("Length__c")
    @Expose
    private double lengthC;

    @DatabaseField(columnName = "Name")
    @SerializedName("Name")
    @Expose
    private String name;

    @DatabaseField(columnName = "Pull_from_SAP__c")
    @SerializedName("Pull_from_SAP__c")
    @Expose
    private boolean pullFromSAPC;

    @DatabaseField(columnName = "Push_to_SAP__c")
    @SerializedName("Push_to_SAP__c")
    @Expose
    private boolean pushToSAPC;

}
