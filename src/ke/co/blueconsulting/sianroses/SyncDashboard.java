package ke.co.blueconsulting.sianroses;

import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.model.DbConnection;
import ke.co.blueconsulting.sianroses.presenter.SyncPresenter;
import ke.co.blueconsulting.sianroses.util.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ke.co.blueconsulting.sianroses.SyncDashboard.MESSAGE_CODES.ERROR;

public class SyncDashboard implements SyncContract.View {
  
  public static class CONSTANTS {
    public static final String APP_DIR_NAME = ".sianroses";
  }
  
  static class MESSAGE_CODES {
    static final int ERROR = 1;
  }
  
  private SyncPresenter mPresenter;
  private JTextField serverAddressTextField;
  private JTextField serverPortTextField;
  private JTextField databaseNameTextField;
  private JTextField databaseUsernameTextField;
  private JTextField databasePasswordTextField;
  private JTextField syncPeriodTextField;
  private JProgressBar statusProgressBar;
  private JButton testConnectionButton, saveConnectionButton, syncButton;
  private JComboBox<String> syncPeriodUnitComboBox;
  private String[] syncPeriodUnits = new String[]{"Minute(s)", "Hour(s)", "Day(s)", "Week(s)", "Month(s)", "Year(s)"};
  
  /**
   * @wbp.parser.entryPoint
   */
  private SyncDashboard() throws SQLException, ClassNotFoundException {
    mPresenter = new SyncPresenter(this);
    initViews();
  }
  
  private void initViews() {
    JFrame frmSyncDashboard;
    frmSyncDashboard = new JFrame();
    frmSyncDashboard.setResizable(false);
    frmSyncDashboard.setTitle("Sian Roses: Data Sync Dashboard");
    frmSyncDashboard.setBounds(100, 100, 450, 349);
    frmSyncDashboard.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frmSyncDashboard.getContentPane().setLayout(null);
    
    statusProgressBar = new JProgressBar();
    statusProgressBar.setIndeterminate(true);
    statusProgressBar.setBounds(59, 295, 311, 14);
    statusProgressBar.setVisible(false);
    frmSyncDashboard.getContentPane().add(statusProgressBar);
    
    JLabel serverAddressLabel = new JLabel("Server Address");
    serverAddressLabel.setBounds(10, 11, 250, 15);
    frmSyncDashboard.getContentPane().add(serverAddressLabel);
    
    serverAddressTextField = new JTextField();
    serverAddressTextField.setToolTipText("Enter a server url or IP beginning with the scheme");
    serverAddressTextField.setBounds(10, 36, 255, 20);
    frmSyncDashboard.getContentPane().add(serverAddressTextField);
    serverAddressTextField.setColumns(10);
    
    JLabel serverPortLabel = new JLabel("Port");
    serverPortLabel.setBounds(275, 11, 46, 14);
    frmSyncDashboard.getContentPane().add(serverPortLabel);
    
    serverPortTextField = new JTextField();
    serverPortTextField.setToolTipText("Enter a port number: 1-65535");
    serverPortTextField.setBounds(275, 36, 150, 20);
    frmSyncDashboard.getContentPane().add(serverPortTextField);
    serverPortTextField.setColumns(10);
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
          showError("Validation Error", errMsg);
        }
        return isOkay;
      }
    });
    
    JLabel databaseNameLabel = new JLabel("Database Name");
    databaseNameLabel.setBounds(10, 67, 255, 14);
    frmSyncDashboard.getContentPane().add(databaseNameLabel);
    
    databaseNameTextField = new JTextField();
    databaseNameTextField.setToolTipText("Enter a valid database name");
    databaseNameTextField.setColumns(10);
    databaseNameTextField.setBounds(10, 92, 255, 20);
    frmSyncDashboard.getContentPane().add(databaseNameTextField);
    
    JLabel databaseUsernameLabel = new JLabel("Database Username");
    databaseUsernameLabel.setBounds(10, 123, 255, 14);
    frmSyncDashboard.getContentPane().add(databaseUsernameLabel);
    
    databaseUsernameTextField = new JTextField();
    databaseUsernameTextField.setToolTipText("Enter database username");
    databaseUsernameTextField.setColumns(10);
    databaseUsernameTextField.setBounds(10, 148, 255, 20);
    frmSyncDashboard.getContentPane().add(databaseUsernameTextField);
    
    JLabel databasePasswordLabel = new JLabel("Database Password");
    databasePasswordLabel.setBounds(10, 179, 255, 14);
    frmSyncDashboard.getContentPane().add(databasePasswordLabel);
    
    databasePasswordTextField = new JTextField();
    databasePasswordTextField.setToolTipText("Enter the database password");
    databasePasswordTextField.setColumns(10);
    databasePasswordTextField.setBounds(10, 204, 255, 20);
    frmSyncDashboard.getContentPane().add(databasePasswordTextField);
    
    JLabel syncPeriodLabel = new JLabel("Sync Period");
    syncPeriodLabel.setBounds(10, 235, 255, 14);
    frmSyncDashboard.getContentPane().add(syncPeriodLabel);
    
    syncPeriodTextField = new JTextField();
    syncPeriodTextField.setToolTipText("Enter the duration between synchronisation");
    syncPeriodTextField.setBounds(10, 254, 125, 20);
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
          showError("Validation Error", errMsg);
        }
        return isOkay;
      }
    });
    frmSyncDashboard.getContentPane().add(syncPeriodTextField);
    syncPeriodTextField.setColumns(10);
    
    syncPeriodUnitComboBox = new JComboBox<>();
    syncPeriodUnitComboBox.setModel(new DefaultComboBoxModel<>(syncPeriodUnits));
    syncPeriodUnitComboBox.setBounds(145, 254, 120, 20);
    frmSyncDashboard.getContentPane().add(syncPeriodUnitComboBox);
    
    testConnectionButton = new JButton("Test Connection");
    testConnectionButton.setBounds(287, 185, 138, 23);
    frmSyncDashboard.getContentPane().add(testConnectionButton);
    
    saveConnectionButton = new JButton("Save");
    saveConnectionButton.setBounds(287, 219, 138, 23);
    frmSyncDashboard.getContentPane().add(saveConnectionButton);
    
    syncButton = new JButton("Sync");
    syncButton.setBounds(287, 253, 138, 23);
    frmSyncDashboard.getContentPane().add(syncButton);
    
    testConnectionButton.addActionListener(event -> {
      mPresenter.testConnection();
    });
    
    saveConnectionButton.addActionListener(event -> {
      try {
        if (fieldsAreValid()) {
          mPresenter.saveConnectionDetails(serverAddressTextField.getText(), serverPortTextField.getText(),
              databaseNameTextField.getText(), databaseUsernameTextField.getText(), databasePasswordTextField.getText(),
              syncPeriodTextField.getText(), syncPeriodUnitComboBox.getSelectedItem().toString());
        }
      } catch (Exception e) {
        showError("A fatal app error has occurred.\n" + e.getMessage());
      }
    });
    
    syncButton.addActionListener(event -> {
    
    });
    
    frmSyncDashboard.setVisible(true);
    
    try {
      mPresenter.getDbConnectionData();
    } catch (Exception e) {
      showError("A fatal app error has occurred.\n" + e.getMessage());
    }
  }
  
  @Override
  public void updateUiFields(DbConnection dbConnection) {
    serverAddressTextField.setText(dbConnection.getServerAddress());
    if (dbConnection.getServerPort() != 0) {
      serverPortTextField.setText(String.valueOf(dbConnection.getServerPort()));
    }
    databaseNameTextField.setText(dbConnection.getDatabaseName());
    databaseUsernameTextField.setText(dbConnection.getDatabaseUsername());
    databasePasswordTextField.setText(dbConnection.getDatabasePassword());
    syncPeriodTextField.setText(String.valueOf(dbConnection.getSyncPeriod()));
    syncPeriodUnitComboBox.setSelectedItem(dbConnection.getSyncPeriodUnit());
  }
  
  @Override
  public void showMessage(String message, int error_code) {
    switch (error_code) {
      case ERROR:
        break;
    }
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
  
  private static void showError(String title, String message) {
    JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
  }
  
  private static void showError(String message) {
    JOptionPane.showMessageDialog(null, message, "Fatal Error", JOptionPane.ERROR_MESSAGE);
  }
  
  private boolean fieldsAreValid() {
    boolean isValid = true;
    List<String> messages = new ArrayList<>();
    
    String serverAddress = serverAddressTextField.getText().trim();
    String port = serverPortTextField.getText().trim();
    String dbName = databaseNameTextField.getText().trim();
    String dbUserName = databaseUsernameTextField.getText().trim();
    String dbPassword = databasePasswordTextField.getText().trim();
    String syncPeriod = syncPeriodTextField.getText().trim();
    String syncPeriodUnit = syncPeriodUnitComboBox.getSelectedItem().toString().trim();
    
    if (StringUtils.isBlank(serverAddress) || StringUtils.isNullOrEmpty(serverAddress)) {
      messages.add("Server address is required");
      isValid = false;
    } else if (StringUtils.isBlank(port) || StringUtils.isNullOrEmpty(port)) {
      messages.add("Server port is required");
      isValid = false;
    } else if (!StringUtils.isValidURL(serverAddress.concat(":").concat(port))) {
      messages.add("Use a valid URL for the server address");
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
      showError("Validation Error", "Please correct the following errors:\n" + msgString.toString());
    }
    return isValid;
  }
  
  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      try {
        new SyncDashboard();
      } catch (Exception e) {
        showError("Fatal Error", "A fatal app error has occurred.\n" + e.getMessage());
      }
    });
  }
  
}
