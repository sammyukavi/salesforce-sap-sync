package ke.co.blueconsulting.sianroses;

import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.model.app.AuthCredentials;
import ke.co.blueconsulting.sianroses.presenter.SyncPresenter;
import ke.co.blueconsulting.sianroses.util.StringUtils;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.MissingResourceException;

import static ke.co.blueconsulting.sianroses.util.Constants.BundleKeys.*;
import static ke.co.blueconsulting.sianroses.util.Constants.DATABASE_SERVER_CONFIGURATION;
import static ke.co.blueconsulting.sianroses.util.Constants.SALESFORCE_CONFIGURATION;

public class SyncDashboard implements SyncContract.View {
  
  private JButton testConnectionButton, saveConnectionButton, syncButton;
  private JProgressBar statusProgressBar;
  private String tabOnView = DATABASE_SERVER_CONFIGURATION;
  private SyncPresenter syncPresenter;
  private String[] syncPeriodUnits;
  private JTextField serverAddressTextField, serverPortTextField, databaseNameTextField, databaseUsernameTextField,
      syncPeriodTextField, salesforceClientIdTextField, salesforceClientSecretTextField,
      salesforceUsernameTextField, salesforceSecurityTokenTextField;
  private JPasswordField databasePasswordTextField, salesforcePasswordTextField;
  private JComboBox syncPeriodUnitComboBox;
  private JFrame dashboardJFrame;
  
  /**
   * @wbp.parser.entryPoint
   */
  private SyncDashboard() throws SQLException, ClassNotFoundException {
    this.syncPeriodUnits = getString(SYNC_PERIOD_UNITS).split(",");
    this.syncPresenter = new SyncPresenter(this);
    initViews();
  }
  
  private SyncDashboard(boolean blankNewInstance) {
    if (!blankNewInstance) {
      try {
        new SyncDashboard();
      } catch (SQLException | ClassNotFoundException ignored) {
      
      }
    }
  }
  
  public static String getString(String key) {
    String string = null;
    try {
      string = java.util.ResourceBundle.getBundle("ke/co/blueconsulting/sianroses/resources/strings").getString(key);
    } catch (MissingResourceException ignored) {
    
    }
    return string;
  }
  
  private void initViews() {
    dashboardJFrame = new JFrame();
    dashboardJFrame.setResizable(false);
    dashboardJFrame.setTitle(getString(LABEL_APP_NAME));
    dashboardJFrame.setBounds(100, 100, 530, 410);
    dashboardJFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    dashboardJFrame.getContentPane().setLayout(null);
    dashboardJFrame.setLocationRelativeTo(null);
    dashboardJFrame.getContentPane().setLayout(null);
    
    JTabbedPane tabbedPane = new JTabbedPane();
    tabbedPane.setUI(new BasicTabbedPaneUI() {
      private final Insets borderInsets = new Insets(0, 0, 0, 0);
      
      @Override
      protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
      }
      
      @Override
      protected Insets getContentBorderInsets(int tabPlacement) {
        return borderInsets;
      }
    });
    tabbedPane.setBounds(0, 0, 524, 320);
    tabbedPane.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    tabbedPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
    
    JComponent dbServerConfigPanel = makeDbServerConfigPanel();
    tabbedPane.addTab(getString(TAB_DATABASE_SERVER_CONFIG), null, dbServerConfigPanel);
    
    JComponent salesforceConfigPanel = makeSalesforceConfigPanel();
    tabbedPane.addTab(getString(TAB_SALESFORCE_CONFIG), null, salesforceConfigPanel);
    
    tabbedPane.addChangeListener(e -> {
      updateUiButtons(tabbedPane.getSelectedIndex());
    });
    
    dashboardJFrame.getContentPane().add(tabbedPane);
    
    statusProgressBar = new JProgressBar();
    statusProgressBar.setBounds(12, 324, 500, 20);
    statusProgressBar.setIndeterminate(true);
    statusProgressBar.setVisible(false);
    dashboardJFrame.getContentPane().add(statusProgressBar);
    
    testConnectionButton = new JButton(getString(BTN_TEST_DB_CONNECTION));
    testConnectionButton.setBounds(12, 350, 160, 23);
    testConnectionButton.addActionListener(event -> {
      try {
        if (tabOnView.equals(DATABASE_SERVER_CONFIGURATION)) {
          if (serverConfigFieldsAreValid()) {
            syncPresenter.testDbConnection(serverAddressTextField.getText(), serverPortTextField.getText(),
                databaseNameTextField.getText(), databaseUsernameTextField.getText(), new String(databasePasswordTextField.getPassword()));
          }
        } else {
          if (salesforceConfigFieldsAreValid()) {
            syncPresenter.testSalesforceAuth(salesforceClientIdTextField.getText(),
                salesforceClientSecretTextField.getText(), salesforceUsernameTextField.getText(),
                salesforcePasswordTextField.getPassword().toString(), salesforceSecurityTokenTextField.getText());
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
        showErrorMessage(getString(MESSAGE_ERROR_OCCURRED) + e.getMessage());
      }
    });
    
    dashboardJFrame.getContentPane().add(testConnectionButton);
    
    saveConnectionButton = new JButton(getString(BTN_SAVE));
    saveConnectionButton.setBounds(183, 350, 160, 23);
    saveConnectionButton.addActionListener(event -> {
      try {
        if (tabOnView.equals(DATABASE_SERVER_CONFIGURATION)) {
          if (serverConfigFieldsAreValid()) {
            syncPresenter.saveDbAuth(serverAddressTextField.getText(), serverPortTextField.getText(),
                databaseNameTextField.getText(), databaseUsernameTextField.getText(), new String(databasePasswordTextField.getPassword()),
                syncPeriodTextField.getText(), syncPeriodUnitComboBox.getSelectedItem().toString());
          }
        } else {
          if (salesforceConfigFieldsAreValid()) {
            syncPresenter.saveSalesforceAuth(salesforceClientIdTextField.getText(),
                salesforceClientSecretTextField.getText(), salesforceUsernameTextField.getText(),
                salesforcePasswordTextField.getPassword().toString(), salesforceSecurityTokenTextField.getText());
          }
        }
        
        
      } catch (Exception e) {
        showErrorMessage(getString(MESSAGE_ERROR_OCCURRED) + e.getMessage());
      }
    });
    
    dashboardJFrame.getContentPane().add(saveConnectionButton);
    
    syncButton = new JButton(getString(BTN_SYNC));
    syncButton.setBounds(355, 350, 160, 23);
    dashboardJFrame.getContentPane().add(syncButton);
    
    syncButton.addActionListener(event -> {
      try {
        syncPresenter.performSync();
      } catch (SQLException e) {
        showErrorMessage(getString(MESSAGE_ERROR_OCCURRED) + e.getMessage());
      }
    });
    
    dashboardJFrame.setVisible(true);
    
    try {
      syncPresenter.getCredentials();
    } catch (Exception e) {
      showErrorMessage(getString(MESSAGE_FATAL_ERROR) + e.getMessage());
    }
    
  }
  
  private void updateUiButtons(int selectedIndex) {
    if (selectedIndex == 0) {
      tabOnView = DATABASE_SERVER_CONFIGURATION;
      testConnectionButton.setText(getString(BTN_TEST_DB_CONNECTION));
    } else {
      tabOnView = SALESFORCE_CONFIGURATION;
      testConnectionButton.setText(getString(BTN_TEST_SALESFORCE_AUTH));
    }
  }
  
  /**
   * Build the MSSQL Server Config Panel UI
   *
   * @return JPanel
   */
  private JComponent makeDbServerConfigPanel() {
    JPanel jPanel = new JPanel();
    jPanel.setBorder(null);
    jPanel.setLayout(null);
    JLabel lblServerAddress = new JLabel(getString(LABEL_SERVER_ADDRESS));
    lblServerAddress.setBounds(10, 10, 497, 15);
    jPanel.add(lblServerAddress);
    
    serverAddressTextField = new JTextField();
    serverAddressTextField.setBounds(10, 30, 497, 20);
    jPanel.add(serverAddressTextField);
    serverAddressTextField.setColumns(10);
    
    JLabel lblServerPort = new JLabel(getString(LABEL_SERVER_PORT));
    lblServerPort.setBounds(10, 55, 497, 15);
    jPanel.add(lblServerPort);
    
    serverPortTextField = new JTextField();
    serverPortTextField.setBounds(10, 75, 497, 20);
    serverPortTextField.setInputVerifier(new InputVerifier() {
      @Override
      public boolean verify(JComponent input) {
        String errMsg = getString(MESSAGE_PORT_OUT_OF_RANGE);
        boolean isOkay = false;
        JTextField jTextField = (JTextField) input;
        try {
          int value = Integer.parseInt(jTextField.getText());
          if (value < 1 || value > 65535) {
            throw new Exception(errMsg);
          }
          isOkay = true;
        } catch (Exception ex) {
          showErrorMessage(getString(MESSAGE_VALIDATION_ERROR), errMsg);
        }
        return isOkay;
      }
    });
    jPanel.add(serverPortTextField);
    serverPortTextField.setColumns(10);
    
    JLabel lblDatabaseName = new JLabel(getString(LABEL_DATABASE_NAME));
    lblDatabaseName.setBounds(10, 100, 497, 15);
    jPanel.add(lblDatabaseName);
    
    databaseNameTextField = new JTextField();
    databaseNameTextField.setColumns(10);
    databaseNameTextField.setBounds(10, 120, 497, 20);
    jPanel.add(databaseNameTextField);
    
    JLabel lblDatabaseUsername = new JLabel(getString(LABEL_DATABASE_USERNAME));
    lblDatabaseUsername.setBounds(10, 150, 497, 15);
    jPanel.add(lblDatabaseUsername);
    
    databaseUsernameTextField = new JTextField();
    databaseUsernameTextField.setColumns(10);
    databaseUsernameTextField.setBounds(10, 170, 497, 20);
    jPanel.add(databaseUsernameTextField);
    
    JLabel lblDatabasePassword = new JLabel(getString(LABEL_DATABASE_PASSWORD));
    lblDatabasePassword.setBounds(10, 200, 497, 15);
    jPanel.add(lblDatabasePassword);
    
    databasePasswordTextField = new JPasswordField();
    databasePasswordTextField.setBounds(10, 220, 497, 20);
    jPanel.add(databasePasswordTextField);
    
    JLabel lblSyncPeriod = new JLabel(getString(LABEL_SYNC_PERIOD));
    lblSyncPeriod.setBounds(10, 250, 497, 15);
    jPanel.add(lblSyncPeriod);
    
    syncPeriodTextField = new JTextField();
    syncPeriodTextField.setBounds(10, 270, 107, 20);
    syncPeriodTextField.setInputVerifier(new InputVerifier() {
      @Override
      public boolean verify(JComponent input) {
        String errMsg = getString(MESSAGE_SYNC_UNIT_OUT_OF_RANGE);
        boolean isOkay = false;
        JTextField jTextField = (JTextField) input;
        try {
          int value = Integer.parseInt(jTextField.getText());
          if (value < 1 || value > 55) {
            throw new Exception(errMsg);
          }
          isOkay = true;
        } catch (Exception ex) {
          showErrorMessage(getString(MESSAGE_VALIDATION_ERROR), errMsg);
        }
        return isOkay;
      }
    });
    jPanel.add(syncPeriodTextField);
    
    syncPeriodUnitComboBox = new JComboBox();
    syncPeriodUnitComboBox.setModel(new DefaultComboBoxModel(syncPeriodUnits));
    syncPeriodUnitComboBox.setBounds(125, 270, 98, 20);
    jPanel.add(syncPeriodUnitComboBox);
    
    return jPanel;
  }
  
  /**
   * Build the Salesforce Config Panel UI
   *
   * @return JPanel
   */
  private JComponent makeSalesforceConfigPanel() {
    JPanel jPanel = new JPanel(false);
    jPanel.setBorder(null);
    jPanel.setLayout(null);
    
    JLabel clientId = new JLabel(getString(LABEL_SALESFORCE_CLIENT_ID));
    clientId.setBounds(10, 10, 497, 15);
    jPanel.add(clientId);
    
    salesforceClientIdTextField = new JTextField();
    salesforceClientIdTextField.setColumns(10);
    salesforceClientIdTextField.setBounds(10, 30, 497, 20);
    jPanel.add(salesforceClientIdTextField);
    
    JLabel lblClientSecret = new JLabel(getString(LABEL_SALESFORCE_CLIENT_SECRET));
    lblClientSecret.setBounds(10, 55, 497, 15);
    jPanel.add(lblClientSecret);
    
    salesforceClientSecretTextField = new JTextField();
    salesforceClientSecretTextField.setColumns(10);
    salesforceClientSecretTextField.setBounds(10, 75, 497, 20);
    jPanel.add(salesforceClientSecretTextField);
    
    JLabel lblUsername = new JLabel(getString(LABEL_SALESFORCE_USERNAME));
    lblUsername.setBounds(10, 100, 497, 15);
    jPanel.add(lblUsername);
    
    salesforceUsernameTextField = new JTextField();
    salesforceUsernameTextField.setColumns(10);
    salesforceUsernameTextField.setBounds(10, 120, 497, 20);
    jPanel.add(salesforceUsernameTextField);
    
    JLabel lblSalesforcePassword = new JLabel(getString(LABEL_SALESFORCE_PASSWORD));
    lblSalesforcePassword.setBounds(10, 150, 497, 15);
    jPanel.add(lblSalesforcePassword);
    
    salesforcePasswordTextField = new JPasswordField();
    salesforcePasswordTextField.setColumns(10);
    salesforcePasswordTextField.setBounds(10, 170, 497, 20);
    jPanel.add(salesforcePasswordTextField);
    
    JLabel lblSecurityToken = new JLabel(getString(LABEL_SALESFORCE_SECURITY_TOKEN));
    lblSecurityToken.setBounds(10, 200, 497, 15);
    jPanel.add(lblSecurityToken);
    
    salesforceSecurityTokenTextField = new JTextField();
    salesforceSecurityTokenTextField.setColumns(10);
    salesforceSecurityTokenTextField.setBounds(10, 220, 497, 20);
    jPanel.add(salesforceSecurityTokenTextField);
    
    return jPanel;
  }
  
  @Override
  public void updateUiFields(AuthCredentials authCredentials) {
    serverAddressTextField.setText(authCredentials.getServerAddress());
    if (authCredentials.getServerPort() != 0) {
      serverPortTextField.setText(String.valueOf(authCredentials.getServerPort()));
    }
    databaseNameTextField.setText(authCredentials.getDatabaseName());
    databaseUsernameTextField.setText(authCredentials.getDatabaseUsername());
    databasePasswordTextField.setText(authCredentials.getDatabasePassword());
    syncPeriodTextField.setText(String.valueOf(authCredentials.getSyncPeriod()));
    syncPeriodUnitComboBox.setSelectedItem(authCredentials.getSyncPeriodUnit());
    salesforceClientIdTextField.setText(authCredentials.getSalesforceClientId());
    salesforceClientSecretTextField.setText(authCredentials.getSalesforceClientSecret());
    salesforceUsernameTextField.setText(authCredentials.getSalesforceUsername());
    salesforcePasswordTextField.setText(authCredentials.getSalesforcePassword());
    salesforceSecurityTokenTextField.setText(authCredentials.getSalesforceSecurityToken());
  }
  
  @Override
  public void setIsBusy(boolean isBusy) {
    List<Container> containerList = Arrays.asList(serverAddressTextField, serverPortTextField, databaseNameTextField,
        databaseUsernameTextField, databasePasswordTextField, syncPeriodTextField, syncPeriodUnitComboBox,
        salesforceClientIdTextField, salesforceClientSecretTextField, salesforceUsernameTextField,
        salesforcePasswordTextField, salesforceSecurityTokenTextField, testConnectionButton,
        saveConnectionButton, syncButton);
    enableContainers(!isBusy, containerList);
    statusProgressBar.setVisible(isBusy);
  }
  
  private void enableContainers(boolean enabled, List<Container> containerList) {
    for (Container container : containerList) {
      container.setEnabled(enabled);
    }
  }
  
  @Override
  public void showSuccessMessage(String message) {
    message = StringUtils.breakLongString(message);
    JOptionPane.showMessageDialog(dashboardJFrame, message, getString(MESSAGE_SUCCESS), JOptionPane.INFORMATION_MESSAGE);
  }
  
  @Override
  public void showErrorMessage(String title, String message) {
    message = StringUtils.breakLongString(message);
    JOptionPane.showMessageDialog(dashboardJFrame, message, title, JOptionPane.ERROR_MESSAGE);
  }
  
  @Override
  public void showErrorMessage(String message) {
    message = StringUtils.breakLongString(message);
    JOptionPane.showMessageDialog(dashboardJFrame, message, getString(MESSAGE_ERROR_OCCURRED), JOptionPane.ERROR_MESSAGE);
  }
  
  private boolean serverConfigFieldsAreValid() {
    boolean isValid = true;
    List<String> messages = new ArrayList<>();
    
    String serverAddress = serverAddressTextField.getText().trim();
    String port = serverPortTextField.getText().trim();
    String dbName = databaseNameTextField.getText().trim();
    String dbUserName = databaseUsernameTextField.getText().trim();
    String dbPassword = String.valueOf(databasePasswordTextField.getPassword()).trim();
    String syncPeriod = syncPeriodTextField.getText().trim();
    String syncPeriodUnit = "";
    if (syncPeriodUnitComboBox.getSelectedItem() != null) {
      syncPeriodUnit = syncPeriodUnitComboBox.getSelectedItem().toString().trim();
    }
    
    if (StringUtils.isBlank(serverAddress) || StringUtils.isNullOrEmpty(serverAddress)) {
      messages.add(getString(MESSAGE_SERVER_ADDRESS_REQUIRED));
      isValid = false;
    } else if (StringUtils.isBlank(port) || StringUtils.isNullOrEmpty(port)) {
      messages.add(getString(MESSAGE_SERVER_PORT_REQUIRED));
      isValid = false;
    }
    
    if (StringUtils.isBlank(dbName) || StringUtils.isNullOrEmpty(dbName)) {
      messages.add(getString(MESSAGE_DATABASE_NAME_REQUIRED));
      isValid = false;
    }
    
    if (StringUtils.isBlank(dbUserName) || StringUtils.isNullOrEmpty(dbUserName)) {
      messages.add(getString(MESSAGE_DATABASE_USERNAME_REQUIRED));
      isValid = false;
    }
    
    if (StringUtils.isBlank(dbPassword) || StringUtils.isNullOrEmpty(dbPassword)) {
      messages.add(getString(MESSAGE_DATABASE_PASSWORD_REQUIRED));
      isValid = false;
    }
    
    if (StringUtils.isBlank(syncPeriod) || StringUtils.isNullOrEmpty(syncPeriod)) {
      messages.add(getString(MESSAGE_SYNC_PERIOD_REQUIRED));
      isValid = false;
    }
    
    if (StringUtils.isBlank(syncPeriodUnit) || StringUtils.isNullOrEmpty(syncPeriodUnit) ||
        !Arrays.asList(syncPeriodUnits).contains(syncPeriodUnit)) {
      messages.add(getString(MESSAGE_SELECT_PROVIDED_TIME));
      isValid = false;
    }
    
    StringBuilder msgString = new StringBuilder();
    for (String message : messages) {
      msgString.append(message).append("\n");
    }
    if (!isValid) {
      showErrorMessage(getString(MESSAGE_VALIDATION_ERROR), getString(MESSAGE_CORRECT_ERRORS) + "\n" + msgString.toString());
    }
    return isValid;
  }
  
  /**
   * Check if the Salesforce Configuration UI fields have been filled as required
   *
   * @return boolean True if fields are properly filled in False if a field has not been properly filled
   */
  private boolean salesforceConfigFieldsAreValid() {
    boolean isValid = true;
    List<String> messages = new ArrayList<>();
    
    String salesforceClientId = salesforceClientIdTextField.getText().trim();
    String salesforceClientSecret = salesforceClientSecretTextField.getText().trim();
    String salesforceUsername = salesforceUsernameTextField.getText().trim();
    String salesforcePassword = String.valueOf(salesforcePasswordTextField.getPassword()).trim();
    String salesforceSecurityToken = salesforceSecurityTokenTextField.getText();
    
    if (StringUtils.isBlank(salesforceClientId) || StringUtils.isNullOrEmpty(salesforceClientId)) {
      messages.add(getString(MESSAGE_SALESFORCE_CLIENT_ID_REQUIRED));
      isValid = false;
    }
    
    if (StringUtils.isBlank(salesforceClientSecret) || StringUtils.isNullOrEmpty(salesforceClientSecret)) {
      messages.add(getString(MESSAGE_SALESFORCE_CLIENT_SECRET_REQUIRED));
      isValid = false;
    }
    
    if (StringUtils.isBlank(salesforceUsername) || StringUtils.isNullOrEmpty(salesforceUsername)) {
      messages.add(getString(MESSAGE_SALESFORCE_ACCOUNT_USERNAME_REQUIRED));
      isValid = false;
    }
    
    if (StringUtils.isBlank(salesforcePassword) || StringUtils.isNullOrEmpty(salesforcePassword)) {
      messages.add(getString(MESSAGE_SALESFORCE_ACCOUNT_PASSWORD_REQUIRED));
      isValid = false;
    }
    
    if (StringUtils.isBlank(salesforceSecurityToken) || StringUtils.isNullOrEmpty(salesforceSecurityToken)) {
      messages.add(getString(MESSAGE_SALESFORCE_SECURITY_TOKEN_REQUIRED));
      isValid = false;
    }
    
    StringBuilder msgString = new StringBuilder();
    for (String message : messages) {
      msgString.append(message).append("\n");
    }
    if (!isValid) {
      showErrorMessage(getString(MESSAGE_VALIDATION_ERROR), getString(MESSAGE_CORRECT_ERRORS) + "\n" + msgString.toString());
    }
    
    return isValid;
  }
  
  /**
   * The start point of the application
   *
   * @param args
   */
  public static void main(String[] args) {
    
    EventQueue.invokeLater(() -> {
      try {
        new SyncDashboard();
      } catch (Exception e) {
        e.printStackTrace();
        SyncDashboard.getInstance().showErrorMessage(getString(MESSAGE_FATAL_ERROR), getString(MESSAGE_FATAL_ERROR) + e.getMessage());
      }
    });
  }
  
  private static SyncDashboard getInstance() {
    return new SyncDashboard(true);
  }
}
