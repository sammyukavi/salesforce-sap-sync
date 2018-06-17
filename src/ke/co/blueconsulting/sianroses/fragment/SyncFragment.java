package ke.co.blueconsulting.sianroses.fragment;

import ke.co.blueconsulting.sianroses.BaseFragment;
import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.model.DbConnection;
import ke.co.blueconsulting.sianroses.presenter.SyncPresenter;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static ke.co.blueconsulting.sianroses.MainApplication.MESSAGE_CODES.ERROR;

public class SyncFragment extends BaseFragment<SyncContract.Presenter> implements SyncContract.View {
  
  
  private JTextField serverAddressTextField;
  private JTextField serverPortTextField;
  private JTextField databaseNameTextField;
  private JTextField databaseUsernameTextField;
  private JTextField databasePasswordTextField;
  private JTextField syncPeriodTextField;
  private JProgressBar statusProgressBar;
  private JButton testConnectionButton, saveConnectionButton, syncButton;
  private JComboBox<String> syncPeriodComboBox;
  
  public SyncFragment() {
    try {
      mPresenter = new SyncPresenter(this);
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
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
    serverAddressTextField.setBounds(10, 36, 255, 20);
    frmSyncDashboard.getContentPane().add(serverAddressTextField);
    serverAddressTextField.setColumns(10);
    
    JLabel serverPortLabel = new JLabel("Port");
    serverPortLabel.setBounds(275, 11, 46, 14);
    frmSyncDashboard.getContentPane().add(serverPortLabel);
    
    serverPortTextField = new JTextField();
    serverPortTextField.setBounds(275, 36, 150, 20);
    frmSyncDashboard.getContentPane().add(serverPortTextField);
    serverPortTextField.setColumns(10);
    
    JLabel databaseNameLabel = new JLabel("Database Name");
    databaseNameLabel.setBounds(10, 67, 255, 14);
    frmSyncDashboard.getContentPane().add(databaseNameLabel);
    
    databaseNameTextField = new JTextField();
    databaseNameTextField.setColumns(10);
    databaseNameTextField.setBounds(10, 92, 255, 20);
    frmSyncDashboard.getContentPane().add(databaseNameTextField);
    
    JLabel databaseUsernameLabel = new JLabel("Database Username");
    databaseUsernameLabel.setBounds(10, 123, 255, 14);
    frmSyncDashboard.getContentPane().add(databaseUsernameLabel);
    
    databaseUsernameTextField = new JTextField();
    databaseUsernameTextField.setColumns(10);
    databaseUsernameTextField.setBounds(10, 148, 255, 20);
    frmSyncDashboard.getContentPane().add(databaseUsernameTextField);
    
    JLabel databasePasswordLabel = new JLabel("Database Password");
    databasePasswordLabel.setBounds(10, 179, 255, 14);
    frmSyncDashboard.getContentPane().add(databasePasswordLabel);
    
    databasePasswordTextField = new JTextField();
    databasePasswordTextField.setColumns(10);
    databasePasswordTextField.setBounds(10, 204, 255, 20);
    frmSyncDashboard.getContentPane().add(databasePasswordTextField);
    
    JLabel syncPeriodLabel = new JLabel("Sync Period");
    syncPeriodLabel.setBounds(10, 235, 255, 14);
    frmSyncDashboard.getContentPane().add(syncPeriodLabel);
    
    syncPeriodTextField = new JTextField();
    syncPeriodTextField.setBounds(10, 254, 125, 20);
    frmSyncDashboard.getContentPane().add(syncPeriodTextField);
    syncPeriodTextField.setColumns(10);
    
    syncPeriodComboBox = new JComboBox<>();
    syncPeriodComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Minute(s)", "Hour(s)", "Day(s)", "Week(s)",
        "Month(s)", "Year(s)"}));
    syncPeriodComboBox.setBounds(145, 254, 120, 20);
    frmSyncDashboard.getContentPane().add(syncPeriodComboBox);
    
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
        mPresenter.saveConnectionDetails(serverAddressTextField.getText(), serverPortTextField.getText(),
            databaseNameTextField.getText(), databaseUsernameTextField.getText(), databasePasswordTextField.getText(),
            syncPeriodTextField.getText(), syncPeriodComboBox.getSelectedItem().toString());
      } catch (SQLException e) {
        e.printStackTrace();
      }
    });
    
    syncButton.addActionListener(event -> {
    
    });
    frmSyncDashboard.setVisible(true);
    try {
      mPresenter.getDbConnectionData();
    } catch (SQLException e) {
      e.printStackTrace();
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
    syncPeriodComboBox.setSelectedItem(dbConnection.getSyncPeriodUnit());
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
        databaseUsernameTextField, databasePasswordTextField, syncPeriodTextField, syncPeriodComboBox, testConnectionButton,
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
  
}
