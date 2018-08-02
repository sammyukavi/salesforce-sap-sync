package ke.co.blueconsulting.sianroses;

import ke.co.blueconsulting.sianroses.contract.SyncContract;
import ke.co.blueconsulting.sianroses.dialogs.MessageDialogInFrame;
import ke.co.blueconsulting.sianroses.model.app.AppAuthCredentials;
import ke.co.blueconsulting.sianroses.presenter.SyncPresenter;
import ke.co.blueconsulting.sianroses.util.ErrorLogFileWatcher;
import ke.co.blueconsulting.sianroses.util.StringUtils;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ke.co.blueconsulting.sianroses.util.Constants.BundleKeys.*;
import static ke.co.blueconsulting.sianroses.util.Constants.*;
import static ke.co.blueconsulting.sianroses.util.StringUtils.getString;

public class SyncDashboard implements SyncContract.View {
	
	private JButton testConnectionButton, saveConnectionButton, syncButton;
	private JProgressBar statusProgressBar;
	private String tabOnView = DATABASE_SERVER_CONFIGURATION_TAB;
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
	SyncDashboard() throws Exception, ClassNotFoundException {
		this.syncPeriodUnits = getString(SYNC_PERIOD_UNITS).split(",");
		this.syncPresenter = new SyncPresenter(this);
		initViews();
	}
	
	private SyncDashboard(boolean blankNewInstance) {
		if (!blankNewInstance) {
			try {
				new SyncDashboard();
			} catch (Exception ignored) {
			
			}
		}
	}
	
	static SyncDashboard getInstance() {
		return new SyncDashboard(true);
	}
	
	private void initViews() {
		dashboardJFrame = new JFrame();
		dashboardJFrame.setResizable(false);
		dashboardJFrame.setTitle(getString(LABEL_APP_NAME));
		dashboardJFrame.setBounds(100, 100, 600, 410);
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
		tabbedPane.setBounds(0, 0, 598, 320);
		tabbedPane.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		tabbedPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
		
		tabbedPane.addTab(getString(TAB_DATABASE_SERVER_CONFIG), null, makeDbServerConfigPanel());
		
		tabbedPane.addTab(getString(TAB_SALESFORCE_CONFIG), null, makeSalesforceConfigPanel());
		
		tabbedPane.addTab(getString(TAB_APP_LOGVIEW), null, makeAppLogViewPanel());
		
		tabbedPane.addChangeListener(e -> {
			updateTabButtons(tabbedPane.getSelectedIndex());
		});
		
		dashboardJFrame.getContentPane().add(tabbedPane);
		
		statusProgressBar = new JProgressBar();
		statusProgressBar.setBounds(12, 324, 574, 20);
		statusProgressBar.setIndeterminate(true);
		statusProgressBar.setVisible(false);
		dashboardJFrame.getContentPane().add(statusProgressBar);
		
		testConnectionButton = new JButton(getString(BTN_TEST_DB_CONNECTION));
		testConnectionButton.setBounds(12, 350, 183, 23);
		testConnectionButton.addActionListener(event -> {
			try {
				if (tabOnView.equals(DATABASE_SERVER_CONFIGURATION_TAB) && serverConfigFieldsAreValid()) {
					syncPresenter.testDbConnection(serverAddressTextField.getText(), serverPortTextField.getText(),
							databaseNameTextField.getText(), databaseUsernameTextField.getText(),
							String.valueOf(databasePasswordTextField.getPassword()));
				} else if (tabOnView.equals(SALESFORCE_CONFIGURATION_TAB) && salesforceConfigFieldsAreValid()) {
					syncPresenter.testSalesforceAuth(salesforceClientIdTextField.getText(),
							salesforceClientSecretTextField.getText(), salesforceUsernameTextField.getText(),
							String.valueOf(salesforcePasswordTextField.getPassword()), salesforceSecurityTokenTextField.getText());
				}
			} catch (Exception e) {
				e.printStackTrace();
				showErrorMessage(getString(MESSAGE_ERROR_OCCURRED) + e.getMessage());
			}
		});
		
		dashboardJFrame.getContentPane().add(testConnectionButton);
		
		saveConnectionButton = new JButton(getString(BTN_SAVE));
		saveConnectionButton.setBounds(207, 350, 183, 23);
		saveConnectionButton.addActionListener(event -> {
			try {
				if (tabOnView.equals(DATABASE_SERVER_CONFIGURATION_TAB)) {
					if (serverConfigFieldsAreValid()) {
						syncPresenter.saveDbAuth(serverAddressTextField.getText(), serverPortTextField.getText(),
								databaseNameTextField.getText(), databaseUsernameTextField.getText(), String.valueOf(databasePasswordTextField.getPassword()),
								syncPeriodTextField.getText(), syncPeriodUnitComboBox.getSelectedItem().toString());
					}
				} else {
					if (salesforceConfigFieldsAreValid()) {
						syncPresenter.saveSalesforceAuth(salesforceClientIdTextField.getText(),
								salesforceClientSecretTextField.getText(), salesforceUsernameTextField.getText(),
								String.valueOf(salesforcePasswordTextField.getPassword()), salesforceSecurityTokenTextField.getText());
					}
				}
				
			} catch (Exception e) {
				showErrorMessage(getString(MESSAGE_ERROR_OCCURRED) + e.getMessage());
			}
		});
		
		dashboardJFrame.getContentPane().add(saveConnectionButton);
		
		syncButton = new JButton(getString(BTN_SYNC));
		syncButton.setBounds(403, 350, 183, 23);
		dashboardJFrame.getContentPane().add(syncButton);
		
		syncButton.addActionListener(event -> {
			syncPresenter.performSync();
		});
		
		dashboardJFrame.setVisible(true);
		
		try {
			syncPresenter.getCredentials();
		} catch (Exception e) {
			showErrorMessage(getString(MESSAGE_FATAL_ERROR) + e.getMessage());
		}
		
	}
	
	private void updateTabButtons(int selectedIndex) {
		testConnectionButton.setVisible(true);
		saveConnectionButton.setVisible(true);
		if (selectedIndex == 0) {
			tabOnView = DATABASE_SERVER_CONFIGURATION_TAB;
			testConnectionButton.setText(getString(BTN_TEST_DB_CONNECTION));
		} else if (selectedIndex == 1) {
			tabOnView = SALESFORCE_CONFIGURATION_TAB;
			testConnectionButton.setText(getString(BTN_TEST_SALESFORCE_AUTH));
		} else {
			tabOnView = LOG_VIEW_TAB;
			testConnectionButton.setVisible(false);
			saveConnectionButton.setVisible(false);
		}
	}
	
	/**
	 * Build the MSSQL Server Settings Panel UI
	 *
	 * @return JPanel
	 */
	private JComponent makeDbServerConfigPanel() {
		JPanel jPanel = new JPanel();
		jPanel.setBorder(null);
		jPanel.setLayout(null);
		JLabel lblServerAddress = new JLabel(getString(LABEL_SERVER_ADDRESS));
		lblServerAddress.setBounds(10, 10, 559, 15);
		jPanel.add(lblServerAddress);
		
		serverAddressTextField = new JTextField();
		serverAddressTextField.setBounds(10, 30, 559, 20);
		jPanel.add(serverAddressTextField);
		serverAddressTextField.setColumns(10);
		
		JLabel lblServerPort = new JLabel(getString(LABEL_SERVER_PORT));
		lblServerPort.setBounds(10, 55, 559, 15);
		jPanel.add(lblServerPort);
		
		serverPortTextField = new JTextField();
		serverPortTextField.setBounds(10, 75, 559, 20);
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
		lblDatabaseName.setBounds(10, 100, 559, 15);
		jPanel.add(lblDatabaseName);
		
		databaseNameTextField = new JTextField();
		databaseNameTextField.setColumns(10);
		databaseNameTextField.setBounds(10, 120, 559, 20);
		jPanel.add(databaseNameTextField);
		
		JLabel lblDatabaseUsername = new JLabel(getString(LABEL_DATABASE_USERNAME));
		lblDatabaseUsername.setBounds(10, 150, 559, 15);
		jPanel.add(lblDatabaseUsername);
		
		databaseUsernameTextField = new JTextField();
		databaseUsernameTextField.setColumns(10);
		databaseUsernameTextField.setBounds(10, 170, 559, 20);
		jPanel.add(databaseUsernameTextField);
		
		JLabel lblDatabasePassword = new JLabel(getString(LABEL_DATABASE_PASSWORD));
		lblDatabasePassword.setBounds(10, 200, 559, 15);
		jPanel.add(lblDatabasePassword);
		
		databasePasswordTextField = new JPasswordField();
		databasePasswordTextField.setBounds(10, 220, 559, 20);
		jPanel.add(databasePasswordTextField);
		
		JLabel lblSyncPeriod = new JLabel(getString(LABEL_SYNC_PERIOD));
		lblSyncPeriod.setBounds(10, 250, 559, 15);
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
		
		syncPeriodUnitComboBox = new JComboBox<>(syncPeriodUnits);
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
		JPanel jPanel = new JPanel();
		jPanel.setBorder(null);
		jPanel.setLayout(null);
		
		JLabel clientId = new JLabel(getString(LABEL_SALESFORCE_CLIENT_ID));
		clientId.setBounds(10, 10, 559, 15);
		jPanel.add(clientId);
		
		salesforceClientIdTextField = new JTextField();
		salesforceClientIdTextField.setColumns(10);
		salesforceClientIdTextField.setBounds(10, 30, 559, 20);
		jPanel.add(salesforceClientIdTextField);
		
		JLabel lblClientSecret = new JLabel(getString(LABEL_SALESFORCE_CLIENT_SECRET));
		lblClientSecret.setBounds(10, 55, 559, 15);
		jPanel.add(lblClientSecret);
		
		salesforceClientSecretTextField = new JTextField();
		salesforceClientSecretTextField.setColumns(10);
		salesforceClientSecretTextField.setBounds(10, 75, 559, 20);
		jPanel.add(salesforceClientSecretTextField);
		
		JLabel lblUsername = new JLabel(getString(LABEL_SALESFORCE_USERNAME));
		lblUsername.setBounds(10, 100, 559, 15);
		jPanel.add(lblUsername);
		
		salesforceUsernameTextField = new JTextField();
		salesforceUsernameTextField.setColumns(10);
		salesforceUsernameTextField.setBounds(10, 120, 559, 20);
		jPanel.add(salesforceUsernameTextField);
		
		JLabel lblSalesforcePassword = new JLabel(getString(LABEL_SALESFORCE_PASSWORD));
		lblSalesforcePassword.setBounds(10, 150, 559, 15);
		jPanel.add(lblSalesforcePassword);
		
		salesforcePasswordTextField = new JPasswordField();
		salesforcePasswordTextField.setColumns(10);
		salesforcePasswordTextField.setBounds(10, 170, 559, 20);
		jPanel.add(salesforcePasswordTextField);
		
		JLabel lblSecurityToken = new JLabel(getString(LABEL_SALESFORCE_SECURITY_TOKEN));
		lblSecurityToken.setBounds(10, 200, 559, 15);
		jPanel.add(lblSecurityToken);
		
		salesforceSecurityTokenTextField = new JTextField();
		salesforceSecurityTokenTextField.setColumns(10);
		salesforceSecurityTokenTextField.setBounds(10, 220, 559, 20);
		jPanel.add(salesforceSecurityTokenTextField);
		
		return jPanel;
	}
	
	/**
	 * Build the PA
	 *
	 * @return JPanel
	 */
	private JComponent makeAppLogViewPanel() {
		JPanel jPanel = new JPanel();
		jPanel.setBorder(null);
		jPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 12, 569, 269);
		jPanel.add(scrollPane);
		
		JTextArea errorLogTextView = new JTextArea();
		errorLogTextView.setLineWrap(true);
		errorLogTextView.setWrapStyleWord(true);
		errorLogTextView.setEditable(false);
		
		DefaultCaret caret = (DefaultCaret) errorLogTextView.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		scrollPane.setViewportView(errorLogTextView);
		
		Thread watchErrorLogFileThread = new Thread(() -> {
			new ErrorLogFileWatcher(errorLogTextView);
		});
		
		watchErrorLogFileThread.start();
		
		return jPanel;
	}
	
	@Override
	public void updateUiFields(AppAuthCredentials appAuthCredentials) {
		serverAddressTextField.setText(appAuthCredentials.getServerAddress());
		if (appAuthCredentials.getServerPort() != 0) {
			serverPortTextField.setText(String.valueOf(appAuthCredentials.getServerPort()));
		}
		databaseNameTextField.setText(appAuthCredentials.getDatabaseName());
		databaseUsernameTextField.setText(appAuthCredentials.getDatabaseUsername());
		databasePasswordTextField.setText(appAuthCredentials.getDatabasePassword());
		syncPeriodTextField.setText(String.valueOf(appAuthCredentials.getSyncPeriod()));
		syncPeriodUnitComboBox.setSelectedItem(appAuthCredentials.getSyncPeriodUnit());
		salesforceClientIdTextField.setText(appAuthCredentials.getSalesforceClientId());
		salesforceClientSecretTextField.setText(appAuthCredentials.getSalesforceClientSecret());
		salesforceUsernameTextField.setText(appAuthCredentials.getSalesforceUsername());
		salesforcePasswordTextField.setText(appAuthCredentials.getSalesforcePassword());
		salesforceSecurityTokenTextField.setText(appAuthCredentials.getSalesforceSecurityToken());
	}
	
	@Override
	public void setIsBusy(boolean isBusy) {
		List<Container> containerList = Arrays.asList(serverAddressTextField, serverPortTextField, databaseNameTextField,
				databaseUsernameTextField, databasePasswordTextField, syncPeriodTextField, syncPeriodUnitComboBox,
				salesforceClientIdTextField, salesforceClientSecretTextField, salesforceUsernameTextField,
				salesforcePasswordTextField, salesforceSecurityTokenTextField, testConnectionButton,
				saveConnectionButton, syncButton);
		enableContainers(!isBusy, containerList);
		syncButton.setEnabled(syncPresenter.hasCredentials());
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
		MessageDialogInFrame.showMessageDialog(dashboardJFrame, message, getString(MESSAGE), JOptionPane.INFORMATION_MESSAGE);
	}
	
	@Override
	public void showErrorMessage(String title, String message) {
		message = StringUtils.breakLongString(message);
		MessageDialogInFrame.showMessageDialog(dashboardJFrame, message, title, JOptionPane.ERROR_MESSAGE);
	}
	
	@Override
	public void showErrorMessage(String message) {
		message = StringUtils.breakLongString(message);
		MessageDialogInFrame.showMessageDialog(dashboardJFrame, message, getString(MESSAGE_ERROR_OCCURRED), JOptionPane.ERROR_MESSAGE);
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
		
		if (StringUtils.isBlank(syncPeriodUnit) || StringUtils.isNullOrEmpty(syncPeriodUnit)
				|| !Arrays.asList(syncPeriodUnits).contains(syncPeriodUnit)) {
			messages.add(getString(MESSAGE_SELECT_PROVIDED_TIME));
			isValid = false;
		}
		
		StringBuilder msgString = new StringBuilder();
		for (String message : messages) {
			msgString.append(message).append("\n");
		}
		if (!isValid) {
			showErrorMessage(getString(MESSAGE_VALIDATION_ERROR), /*getString(MESSAGE_CORRECT_ERRORS) + "\n" + */ msgString.toString());
		}
		return isValid;
	}
	
	/**
	 * Check if the Salesforce Configuration UI fields have been filled as
	 * required
	 *
	 * @return boolean True if fields are properly filled in False if a field
	 * has not been properly filled
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
			showErrorMessage(getString(MESSAGE_VALIDATION_ERROR), /*getString(MESSAGE_CORRECT_ERRORS) + "\n" +*/ msgString.toString());
		}
		return isValid;
	}
}
