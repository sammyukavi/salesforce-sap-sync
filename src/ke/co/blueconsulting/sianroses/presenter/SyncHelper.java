package ke.co.blueconsulting.sianroses.presenter;

import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.data.DataService;
import ke.co.blueconsulting.sianroses.data.db.AuthCredentialsDbService;
import ke.co.blueconsulting.sianroses.data.db.SyncDbService;
import ke.co.blueconsulting.sianroses.data.impl.AuthDataService;
import ke.co.blueconsulting.sianroses.data.impl.SyncDataService;
import ke.co.blueconsulting.sianroses.model.app.*;
import ke.co.blueconsulting.sianroses.model.salesforce.Customer;
import ke.co.blueconsulting.sianroses.model.salesforce.CustomerContacts;
import ke.co.blueconsulting.sianroses.util.AppLogger;
import ke.co.blueconsulting.sianroses.util.Console;
import ke.co.blueconsulting.sianroses.util.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;

class SyncHelper {

    SyncContract.View syncDashboard;
    AuthCredentialsDbService authCredentialsDbService;
    AuthDataService authDataService;
    SyncDataService syncDataService;
    SyncDbService syncDbService;

    void addPreloader() {
        syncDashboard.setIsBusy(true);
    }

    void removePreloader() {
        syncDashboard.setIsBusy(false);
    }

    boolean hasAccessToken() {
        AppAuthCredentials credentials = authCredentialsDbService.getAppAuthCredentials();
        return !StringUtils.isNullOrEmpty(credentials.getSalesforceAccessToken())
                && !StringUtils.isBlank(credentials.getSalesforceAccessToken());
    }

    public boolean hasCredentials() {
        AppAuthCredentials credentials = authCredentialsDbService.getAppAuthCredentials();
        return !StringUtils.isNullOrEmpty(credentials.getServerAddress())
                && !StringUtils.isBlank(credentials.getServerAddress())
                && !StringUtils.isNullOrEmpty(String.valueOf(credentials.getServerPort()))
                && !StringUtils.isBlank(String.valueOf(credentials.getServerPort()))
                && !StringUtils.isNullOrEmpty(credentials.getDatabaseName())
                && !StringUtils.isBlank(credentials.getDatabaseName())
                && !StringUtils.isNullOrEmpty(credentials.getDatabaseUsername())
                && !StringUtils.isBlank(credentials.getDatabaseUsername())
                && !StringUtils.isNullOrEmpty(credentials.getDatabasePassword())
                && !StringUtils.isBlank(credentials.getDatabasePassword())
                && !StringUtils.isNullOrEmpty(String.valueOf(credentials.getSyncPeriod()))
                && !StringUtils.isBlank(String.valueOf(credentials.getSyncPeriod()))
                && !StringUtils.isNullOrEmpty(credentials.getSyncPeriodUnit())
                && !StringUtils.isBlank(credentials.getSyncPeriodUnit())
                && !StringUtils.isNullOrEmpty(credentials.getSalesforceClientId())
                && !StringUtils.isBlank(credentials.getSalesforceClientId())
                && !StringUtils.isNullOrEmpty(credentials.getSalesforceClientSecret())
                && !StringUtils.isBlank(credentials.getSalesforceClientSecret())
                && !StringUtils.isNullOrEmpty(credentials.getSalesforceUsername())
                && !StringUtils.isBlank(credentials.getSalesforceUsername())
                && !StringUtils.isNullOrEmpty(credentials.getSalesforcePassword())
                && !StringUtils.isBlank(credentials.getSalesforcePassword())
                && !StringUtils.isNullOrEmpty(credentials.getSalesforceSecurityToken())
                && !StringUtils.isBlank(credentials.getSalesforceSecurityToken());
    }

    void saveSalesforceCredentials(SalesforceAuthCredentials salesforceAuthCredentials) {
        AppAuthCredentials appAuthCredentials = authCredentialsDbService.getAppAuthCredentials();
        appAuthCredentials.setSalesforceAccessToken(salesforceAuthCredentials.getAccessToken());
        appAuthCredentials.setInstanceUrl(salesforceAuthCredentials.getInstanceUrl());
        appAuthCredentials.setSalesforceId(salesforceAuthCredentials.getId());
        appAuthCredentials.setTokenType(salesforceAuthCredentials.getTokenType());
        appAuthCredentials.setIssuedAt(salesforceAuthCredentials.getIssuedAt());
        appAuthCredentials.setSignature(salesforceAuthCredentials.getSignature());
        try {
            authCredentialsDbService.save(appAuthCredentials);
        } catch (SQLException e) {
            e.printStackTrace();
            AppLogger.logInfo("Failed to store salesforce Credentials. " + e.getMessage());
        }
    }

    void fetchFromTheServer() {
        DataService.GetCallback<Result> getFromTheServerCallback = new DataService.GetCallback<Result>() {
            @Override
            public void onCompleted(Result receivedRecords) {
                Result insertedData = new Result();
                try {
                    /*insertedData.setCustomers(syncDbService.insertRecords(Customer.class, receivedRecords
							.getCustomers()));
					insertedData.setCustomerContacts(syncDbService.insertRecords(CustomerContacts.class, receivedRecords.getCustomerContacts()));
					insertedData.setPriceList(syncDbService.insertRecords(PriceList.class, receivedRecords
					.getPriceList()));*/

                    //TODO This line results into an error Product_Type__c is missing in Salesforce. Check query
                    /*insertedData.setProducts(syncDbService.insertRecords(Product.class, receivedRecords.getProducts
							()));*/
 /*insertedData.setProductsChildren(syncDbService.insertRecords(ProductChild.class, receivedRecords
							.getProductsChildren()));
					insertedData.setWarehouses(syncDbService.insertRecords(Warehouse.class, receivedRecords
							.getWarehouses()));*/
                    insertCustomers(receivedRecords.getCustomers());
                    insertContacts(receivedRecords.getCustomerContacts());
                } catch (Exception e) {
                    e.printStackTrace();
                    AppLogger.logInfo("failed to save to MSSQL server. " + e.getMessage());
                }
            }

            private void insertCustomers(ArrayList<Customer> customers) throws SQLException {
                ArrayList<Customer> insertedCustomers = syncDbService.insertRecords(Customer.class, customers);
                ArrayList<Customer> updatedCustomers = new ArrayList<>();
                for (Customer customer : insertedCustomers) {
                    customer.setPushToSAPC(false);
                    updatedCustomers.add(customer);
                }

                DataService.GetCallback<Result> postToserverCallback = new DataService.GetCallback<Result>() {
                    @Override
                    public void onCompleted(Result serverResponse) {
                        Console.log(serverResponse);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }

                    @Override
                    public void always() {

                    }
                };
                syncDataService.pushCustomersToServer(new PushCustomer(updatedCustomers), postToserverCallback);
            }

            private void insertContacts(ArrayList<CustomerContacts> customerContacts) throws SQLException {
                ArrayList<CustomerContacts> insertedContacts = syncDbService.insertRecords(CustomerContacts.class, customerContacts);

                DataService.GetCallback<Result> postToserverCallback = new DataService.GetCallback<Result>() {
                    @Override
                    public void onCompleted(Result serverResponse) {
                        Console.log(serverResponse);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }

                    @Override
                    public void always() {

                    }
                };
                syncDataService.pushContactsToServer(new PushContacts(insertedContacts), postToserverCallback);
            }

            @Override
            public void onError(Throwable t) {
                AppLogger.logInfo("failed to fetch from the server. " + t.getMessage());
            }

            @Override
            public void always() {

            }
        };
        syncDataService.getFromServer(getFromTheServerCallback);
    }

}
