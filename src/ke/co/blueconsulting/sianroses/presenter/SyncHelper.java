package ke.co.blueconsulting.sianroses.presenter;

import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.data.DataService;
import ke.co.blueconsulting.sianroses.data.RestServiceBuilder;
import ke.co.blueconsulting.sianroses.data.db.AuthCredentialsDbService;
import ke.co.blueconsulting.sianroses.data.db.SAPDbService;
import ke.co.blueconsulting.sianroses.data.impl.AuthDataService;
import ke.co.blueconsulting.sianroses.data.impl.FetchDataService;
import ke.co.blueconsulting.sianroses.data.impl.SyncCustomersDataService;
import ke.co.blueconsulting.sianroses.model.app.*;
import ke.co.blueconsulting.sianroses.model.salesforce.Customer;
import ke.co.blueconsulting.sianroses.model.salesforce.CustomerContacts;
import ke.co.blueconsulting.sianroses.util.AppLogger;
import ke.co.blueconsulting.sianroses.util.Console;
import ke.co.blueconsulting.sianroses.util.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class SyncHelper {
	
	SyncContract.View syncDashboard;
    AuthCredentialsDbService authCredentialsDbService;
	SAPDbService sapDbService;
	
	SyncHelper() throws SQLException, ClassNotFoundException {
		this.authCredentialsDbService = new AuthCredentialsDbService();
		this.sapDbService = new SAPDbService();
	}

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
	
	AuthDataService getAuthService(){
	    RestServiceBuilder.switchToSalesforceAuthUrl();
		return new AuthDataService();
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
                try {
                    insertCustomersToSAP(receivedRecords.getCustomers());
                    //insertContacts(receivedRecords.getCustomerContacts());
                } catch (Exception e) {
                    e.printStackTrace();
                    AppLogger.logInfo("failed to save to MSSQL server. " + e.getMessage());
                }
            }

            private void insertCustomersToSAP(ArrayList<Customer> customers) throws SQLException {
	            ArrayList<Customer> updatedCustomers = new ArrayList<>();
	            
	            for (Customer customer:customers) {
		            customer.setPushToSAPC(false);
		            updatedCustomers.add(customer);
	            }
             
            	ArrayList<Customer> insertedAndUpdatedCustomers = sapDbService.insertRecords(Customer.class, customers);
            	
                updateSalesforceAccounts(insertedAndUpdatedCustomers);
            }

            private void updateSalesforceAccounts(ArrayList<Customer> customers) throws SQLException {
	
	            //get customers that exist in the SAP but not in Salesforce
	            List<Customer> unsyncedContacts = sapDbService.getRecordsWithoutSalesforceId(Customer.class);
	
	            //Add the contacts to the updated contacts
	            customers.addAll(unsyncedContacts);
	            
	            Console.log(customers);

                /*SyncCustomersDataService syncCustomersDataService = new SyncCustomersDataService();

                DataService.GetCallback<PushCustomer> callback = new DataService.GetCallback<PushCustomer>() {
                    @Override
                    public void onCompleted(PushCustomer customers) {
                        Console.log(customers);
                    }

                    @Override
                    public void onError(Throwable t) {
                        Console.log(t);
                    }

                    @Override
                    public void always() {

                    }
                };

                //syncCustomersDataService.pushCustomersToServer(new PushCustomer(customers), callback);*/

            }

            private void insertContacts(ArrayList<CustomerContacts> customerContacts) throws SQLException {
            	
                ArrayList<CustomerContacts> insertedContacts = sapDbService.insertRecords(CustomerContacts.class, customerContacts);

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
            }

            @Override
            public void onError(Throwable t) {
                AppLogger.logInfo("failed to fetch from the server. " + t.getMessage());
            }

            @Override
            public void always() {

            }
        };
        RestServiceBuilder.switchToSalesforceApiBaseUrl();
        FetchDataService fetchDataService = new FetchDataService();
        fetchDataService.getFromServer(getFromTheServerCallback);
    }

}
