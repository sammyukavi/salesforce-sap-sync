package ke.co.blueconsulting.sianroses.util;

public class Constants {

    public static final String APP_DIR_NAME = ".sianroses";
    public static final String DATABASE_SERVER_CONFIGURATION_TAB = "databaseServerConfiguration";
    public static final String SALESFORCE_CONFIGURATION_TAB = "salesforceConfiguration";
    public static final String SQLITE_DATABASE_NAME = "production.db";
    public static final String SALESFORCE_API_BASE_URL = "https://sianroses--Developer.cs89.my.salesforce.com/services/apexrest/";//With a forwardSlash. Very important!!!
    public static final String SALESFORCE_AUTH_BASE_URL = "https://test.salesforce.com/";//With a forwardSlash. Very important!!!

    public static class Units {

        public static final int READ_TIME_OUT_SECONDS = 10;
        public static final int CONNECT_TIME_OUT_SECONDS = 10;
    }

    public static class RequestKeys {

        public static final String CLIENT_ID = "client_id";
        public static final String CLIENT_SECRET = "client_secret";
        public static final String GRANT_TYPE = "grant_type";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String AUTHORIZATION = "Authorization";
        public static final String GRANT_TYPE_PASSWORD = "password";
        public static final String DATA_TYPE = "dataType";
    }

    public static class DataTypes {

        public static final String ACCOUNTS = "accounts";
        public static final String CONTACTS = "contacts";
        public static final String GRANT_TYPE = "grant_type";
        public static final String USERNAME = "username";
        public static final String PASSWORD = "password";
        public static final String AUTHORIZATION = "Authorization";
        public static final String GRANT_TYPE_PASSWORD = "password";
    }

    public static class BundleKeys {

        public static final String TAB_DATABASE_SERVER_CONFIG = "tab.databaseServerConfiguration";
        public static final String TAB_SALESFORCE_CONFIG = "tab.salesforceConfiguration";
        public static final String LABEL_APP_NAME = "labels.appName";
        public static final String LABEL_SERVER_ADDRESS = "labels.serverAddress";
        public static final String LABEL_SERVER_PORT = "labels.serverPort";
        public static final String LABEL_DATABASE_NAME = "labels.databaseName";
        public static final String LABEL_DATABASE_USERNAME = "labels.databaseUsername";
        public static final String LABEL_DATABASE_PASSWORD = "labels.databasePassword";
        public static final String LABEL_SYNC_PERIOD = "labels.syncPeriod";
        public static final String LABEL_SALESFORCE_CLIENT_ID = "labels.salesforceClientId";
        public static final String LABEL_SALESFORCE_CLIENT_SECRET = "labels.salesforceClientSecret";
        public static final String LABEL_SALESFORCE_USERNAME = "labels.salesforceUsername";
        public static final String LABEL_SALESFORCE_PASSWORD = "labels.salesforcePassword";
        public static final String LABEL_SALESFORCE_SECURITY_TOKEN = "labels.salesforceSecurityToken";
        public static final String MESSAGE_PORT_OUT_OF_RANGE = "messages.portOutOfRange";
        public static final String MESSAGE_VALIDATION_ERROR = "messages.validationError";
        //public static final String MESSAGE_CORRECT_ERRORS = "messages.correctErrors";
        public static final String MESSAGE_FATAL_ERROR = "messages.fatalError";
        public static final String MESSAGE_ERROR_OCCURRED = "messages.errorOccured";
        public static final String MESSAGE = "messages.message";
        public static final String MESSAGE_SYNC_UNIT_OUT_OF_RANGE = "messages.syncUnitOutOfRange";
        public static final String MESSAGE_SERVER_ADDRESS_REQUIRED = "messages.serverAddressRequired";
        public static final String MESSAGE_SERVER_PORT_REQUIRED = "messages.serverPortRequired";
        public static final String MESSAGE_SELECT_PROVIDED_TIME = "messages.selectProvidedTime";
        public static final String MESSAGE_SYNC_PERIOD_REQUIRED = "messages.syncPeriodRequired";
        public static final String MESSAGE_DATABASE_PASSWORD_REQUIRED = "messages.databasePasswordRequired";
        public static final String MESSAGE_DATABASE_USERNAME_REQUIRED = "messages.databaseUsernameRequired";
        public static final String MESSAGE_DATABASE_NAME_REQUIRED = "messages.databaseNameRequired";
        public static final String MESSAGE_SALESFORCE_CLIENT_ID_REQUIRED = "messages.salesforceClientIdRequired";
        public static final String MESSAGE_SALESFORCE_CLIENT_SECRET_REQUIRED = "messages.salesforceClientSecretRequired";
        public static final String MESSAGE_SALESFORCE_ACCOUNT_USERNAME_REQUIRED = "messages.salesforceAccountUsernameRequired";
        public static final String MESSAGE_SALESFORCE_ACCOUNT_PASSWORD_REQUIRED = "messages.salesforceAccountPasswordRequired";
        public static final String MESSAGE_SALESFORCE_SECURITY_TOKEN_REQUIRED = "messages.salesforceSecurityTokenRequired";
        public static final String MESSAGE_CONNECTION_SUCCESSFUL = "messages.connectionSuccessful";
        public static final String MESSAGE_LOGIN_FAILED = "messages.loginFailed";
        public static final String MESSAGE_INVALID_SALESFORCE_CREDENTIALS = "messages.invalidSalesforceCredentials";
        public static final String MESSAGE_UNSUPPORTED_ELEMENT = "messages.unsupportedElements";
        public static final String BTN_TEST_DB_CONNECTION = "buttons.testDbConnection";
        public static final String BTN_TEST_SALESFORCE_AUTH = "buttons.testSalesforceAuth";
        public static final String BTN_SAVE = "buttons.save";
        public static final String BTN_SYNC = "button.sync";
        public static final String SYNC_PERIOD_UNITS = "units.sync_period";
    }

}
