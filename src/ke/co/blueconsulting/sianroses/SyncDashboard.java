package ke.co.blueconsulting.sianroses;

import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.model.DbUser;
import ke.co.blueconsulting.sianroses.presenter.SyncPresenter;
import ke.co.blueconsulting.sianroses.util.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SyncDashboard implements SyncContract.View {
  
  private JButton testConnectionButton, saveConnectionButton, syncButton;
  private JProgressBar statusProgressBar;
  private JFrame dashboardJFrame;
  
  public static class CONSTANTS {
    public static final String APP_DIR_NAME = ".sianroses";
  }
  
  private SyncPresenter syncPresenter;
  private String[] syncPeriodUnits = new String[]{"Minute(s)", "Hour(s)", "Day(s)", "Week(s)", "Month(s)", "Year(s)"};
  private JTextField serverAddressTextField, serverPortTextField, databaseNameTextField, databaseUsernameTextField,
      syncPeriodTextField;
  private JPasswordField databasePasswordTextField;
  private JComboBox syncPeriodUnitComboBox;
  
  /**
   * @wbp.parser.entryPoint
   */
  private SyncDashboard() throws SQLException, ClassNotFoundException {
    syncPresenter = new SyncPresenter(this);
    initViews();
  }
  
  private SyncDashboard(boolean blankNewInstance) {
    if (!blankNewInstance) {
      try {
        new SyncDashboard();
      } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
      }
    }
  }
  
  private void initViews() {
    dashboardJFrame = new JFrame();
    dashboardJFrame.setResizable(false);
    dashboardJFrame.setTitle("Sian Roses: Data Sync Dashboard");
    dashboardJFrame.setBounds(100, 100, 485, 330);
    dashboardJFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    //dashboardJFrame.getContentPane().setLayout(null);
    dashboardJFrame.setLocationRelativeTo(null);
    
    JTabbedPane tabbedPane = new JTabbedPane();
    
    JComponent dbServerConfigPanel = makeDbServerConfigPanel();
    tabbedPane.addTab("Database Server Configuration", null, dbServerConfigPanel, "Settings for connecting to the database server");
    
    JComponent salesforceConfigPanel = makeSalesforceConfigPanel();
    tabbedPane.addTab("Salesforce Configuration", null, salesforceConfigPanel, "Settings for connecting to the Salesforce API");
    
    dashboardJFrame.getContentPane().add(tabbedPane);
    dashboardJFrame.setVisible(true);
    
    try {
      syncPresenter.getDbConnectionData();
    } catch (Exception e) {
      showErrorMessage("A fatal app error has occurred.\n" + e.getMessage());
    }
    
  }
  
  private JComponent makeDbServerConfigPanel() {
    JPanel jPanel = new JPanel();
    jPanel.setLayout(null);
    JLabel lblServerAddress = new JLabel("Server Address");
    lblServerAddress.setBounds(10, 10, 215, 14);
    jPanel.add(lblServerAddress);
    
    serverAddressTextField = new JTextField();
    serverAddressTextField.setBounds(10, 30, 215, 20);
    serverAddressTextField.setToolTipText("Enter a server url or IP without the scheme");
    jPanel.add(serverAddressTextField);
    serverAddressTextField.setColumns(10);
    
    JLabel lblServerPort = new JLabel("Server Port");
    lblServerPort.setBounds(250, 10, 80, 14);
    jPanel.add(lblServerPort);
    
    serverPortTextField = new JTextField();
    serverPortTextField.setBounds(250, 30, 178, 20);
    serverPortTextField.setToolTipText("Enter a port number: 1-65535");
    serverPortTextField.setInputVerifier(new InputVerifier() {
      @Override
      public boolean verify(JComponent input) {
        String errMsg = "Port can only be a number between 1 and 65535";
        boolean isOkay = false;
        JTextField jTextField = (JTextField) input;
        try {
          int value = Integer.parseInt(jTextField.getText());
          if (value < 1 || value > 65535) {
            throw new Exception(errMsg);
          }
          isOkay = true;
        } catch (Exception ex) {
          showErrorMessage("Validation Error", errMsg);
        }
        return isOkay;
      }
    });
    jPanel.add(serverPortTextField);
    serverPortTextField.setColumns(10);
    
    JLabel lblDatabaseName = new JLabel("Database Name");
    lblDatabaseName.setBounds(10, 61, 215, 14);
    jPanel.add(lblDatabaseName);
    
    databaseNameTextField = new JTextField();
    databaseNameTextField.setColumns(10);
    databaseNameTextField.setBounds(10, 81, 215, 20);
    databaseNameTextField.setToolTipText("Enter a valid database name");
    jPanel.add(databaseNameTextField);
    
    JLabel lblDatabaseUsername = new JLabel("Database Username");
    lblDatabaseUsername.setBounds(10, 112, 215, 14);
    jPanel.add(lblDatabaseUsername);
    
    databaseUsernameTextField = new JTextField();
    databaseUsernameTextField.setColumns(10);
    databaseUsernameTextField.setBounds(10, 132, 215, 20);
    databaseUsernameTextField.setToolTipText("Enter database username");
    jPanel.add(databaseUsernameTextField);
    
    JLabel lblDatabasePassword = new JLabel("Database Password");
    lblDatabasePassword.setBounds(10, 163, 215, 14);
    jPanel.add(lblDatabasePassword);
    
    databasePasswordTextField = new JPasswordField();
    databasePasswordTextField.setBounds(10, 179, 215, 20);
    databasePasswordTextField.setToolTipText("Enter the database password");
    jPanel.add(databasePasswordTextField);
    
    JLabel lblSyncPeriod = new JLabel("Sync Period");
    lblSyncPeriod.setBounds(10, 210, 215, 14);
    jPanel.add(lblSyncPeriod);
    
    syncPeriodTextField = new JTextField();
    syncPeriodTextField.setBounds(10, 232, 107, 20);
    syncPeriodTextField.setInputVerifier(new InputVerifier() {
      @Override
      public boolean verify(JComponent input) {
        String errMsg = "Sync period can only be a number between 1 and 55";
        boolean isOkay = false;
        JTextField jTextField = (JTextField) input;
        try {
          int value = Integer.parseInt(jTextField.getText());
          if (value < 1 || value > 55) {
            throw new Exception(errMsg);
          }
          isOkay = true;
        } catch (Exception ex) {
          showErrorMessage("Validation Error", errMsg);
        }
        return isOkay;
      }
    });
    jPanel.add(syncPeriodTextField);
    
    syncPeriodUnitComboBox = new JComboBox();
    syncPeriodUnitComboBox.setModel(new DefaultComboBoxModel(syncPeriodUnits));
    syncPeriodUnitComboBox.setBounds(127, 232, 98, 20);
    jPanel.add(syncPeriodUnitComboBox);
    
    testConnectionButton = new JButton("Test Connection");
    testConnectionButton.setBounds(284, 80, 144, 23);
    testConnectionButton.addActionListener(event -> {
      try {
        if (fieldsAreValid()) {
          syncPresenter.testConnection(serverAddressTextField.getText(), serverPortTextField.getText(),
              databaseNameTextField.getText(), databaseUsernameTextField.getText(), new String(databasePasswordTextField.getPassword()));
        }
      } catch (Exception e) {
        showErrorMessage("A fatal app error has occurred.\n" + e.getMessage());
      }
    });
    jPanel.add(testConnectionButton);
    
    saveConnectionButton = new JButton("Save");
    saveConnectionButton.setBounds(284, 131, 144, 23);
    saveConnectionButton.addActionListener(event -> {
      try {
        if (fieldsAreValid()) {
          syncPresenter.saveConnectionDetails(serverAddressTextField.getText(), serverPortTextField.getText(),
              databaseNameTextField.getText(), databaseUsernameTextField.getText(), new String(databasePasswordTextField.getPassword()),
              syncPeriodTextField.getText(), syncPeriodUnitComboBox.getSelectedItem().toString());
        }
      } catch (Exception e) {
        showErrorMessage("A fatal app error has occurred.\n" + e.getMessage());
      }
    });
    jPanel.add(saveConnectionButton);
    
    syncButton = new JButton("Sync");
    syncButton.setBounds(284, 178, 144, 23);
    syncButton.addActionListener(event -> {
      try {
        syncPresenter.performSync();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    });
    jPanel.add(syncButton);
    
    statusProgressBar = new JProgressBar();
    statusProgressBar.setIndeterminate(true);
    statusProgressBar.setBounds(250, 232, 215, 20);
    statusProgressBar.setVisible(false);
    jPanel.add(statusProgressBar);
    
    return jPanel;
  }
  
  private JComponent makeSalesforceConfigPanel() {
    JPanel jPanel = new JPanel(false);
    jPanel.setLayout(null);
    return jPanel;
  }
  
  @Override
  public void updateUiFields(DbUser dbUser) {
    serverAddressTextField.setText(dbUser.getServerAddress());
    if (dbUser.getServerPort() != 0) {
      serverPortTextField.setText(String.valueOf(dbUser.getServerPort()));
    }
    databaseNameTextField.setText(dbUser.getDatabaseName());
    databaseUsernameTextField.setText(dbUser.getDatabaseUsername());
    databasePasswordTextField.setText(dbUser.getDatabasePassword());
    syncPeriodTextField.setText(String.valueOf(dbUser.getSyncPeriod()));
    syncPeriodUnitComboBox.setSelectedItem(dbUser.getSyncPeriodUnit());
  }
  
  @Override
  public void showSuccessMessage(String message) {
    JOptionPane.showMessageDialog(null, message, "Success", JOptionPane.INFORMATION_MESSAGE);
  }
  
  
  @Override
  public void setIsBusy(boolean isBusy) {
    List<Container> containerList = Arrays.asList(serverAddressTextField, serverPortTextField, databaseNameTextField,
        databaseUsernameTextField, databasePasswordTextField, syncPeriodTextField, syncPeriodUnitComboBox, testConnectionButton,
        saveConnectionButton, syncButton);
    if (isBusy) {
      enableContainers(false, containerList);
      statusProgressBar.setVisible(true);
    } else {
      statusProgressBar.setVisible(false);
      enableContainers(true, containerList);
    }
  }
  
  private void enableContainers(boolean enabled, List<Container> containerList) {
    for (Container container : containerList) {
      container.setEnabled(enabled);
    }
  }
  
  @Override
  public void showErrorMessage(String title, String message) {
    JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
  }
  
  @Override
  public void showErrorMessage(String message) {
    JOptionPane.showMessageDialog(null, message, "Fatal Error", JOptionPane.ERROR_MESSAGE);
  }
  
  private boolean fieldsAreValid() {
    boolean isValid = true;
    List<String> messages = new ArrayList<>();
    
    String serverAddress = serverAddressTextField.getText().trim();
    String port = serverPortTextField.getText().trim();
    String dbName = databaseNameTextField.getText().trim();
    String dbUserName = databaseUsernameTextField.getText().trim();
    String dbPassword = new String(databasePasswordTextField.getPassword()).trim();
    String syncPeriod = syncPeriodTextField.getText().trim();
    String syncPeriodUnit = syncPeriodUnitComboBox.getSelectedItem().toString().trim();
    
    if (StringUtils.isBlank(serverAddress) || StringUtils.isNullOrEmpty(serverAddress)) {
      messages.add("Server address is required");
      isValid = false;
    } else if (StringUtils.isBlank(port) || StringUtils.isNullOrEmpty(port)) {
      messages.add("Server port is required");
      isValid = false;
    }
    
    if (StringUtils.isBlank(dbName) || StringUtils.isNullOrEmpty(dbName)) {
      messages.add("Database name is required");
      isValid = false;
    }
    
    if (StringUtils.isBlank(dbUserName) || StringUtils.isNullOrEmpty(dbUserName)) {
      messages.add("Database username is required");
      isValid = false;
    }
    
    if (StringUtils.isBlank(dbPassword) || StringUtils.isNullOrEmpty(dbPassword)) {
      messages.add("Database password is required");
      isValid = false;
    }
    
    if (StringUtils.isBlank(syncPeriod) || StringUtils.isNullOrEmpty(syncPeriod)) {
      messages.add("Sync period is required");
      isValid = false;
    }
    
    if (StringUtils.isBlank(syncPeriodUnit) || StringUtils.isNullOrEmpty(syncPeriodUnit) ||
        !Arrays.asList(syncPeriodUnits).contains(syncPeriodUnit)) {
      messages.add("Select time from that provided");
      isValid = false;
    }
    
    StringBuilder msgString = new StringBuilder();
    for (String message : messages) {
      msgString.append(message).append("\n");
    }
    if (!isValid) {
      showErrorMessage("Validation Error", "Please correct the following errors:\n" + msgString.toString());
    }
    return isValid;
  }
  
  public static void main(String[] args) {
    
    EventQueue.invokeLater(() -> {
      try {
        new SyncDashboard();
      } catch (Exception e) {
        SyncDashboard.getInstance().showErrorMessage("Fatal Error", "A fatal app error has occurred.\n" + e.getMessage());
      }
    });
  }
  
  private static SyncDashboard getInstance() {
    return new SyncDashboard(true);
  }
}
