package phonebook.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import phonebook.db.ConnectionManager;

public class Register extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7234952230714532064L;
	private JPanel contentPane;
	private JTextField lastNameField;
	private JPasswordField passwordField;
	private JTextField firstNameField;
	private JTextField usernameField;

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setTitle("Register Page");
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Roboto", Font.PLAIN, 13));
		lblUsername.setBounds(51, 287, 87, 25);
		contentPane.add(lblUsername);

		usernameField = new JTextField("");
		usernameField.setToolTipText("Username");
		usernameField.setHorizontalAlignment(SwingConstants.CENTER);
		usernameField.setFont(new Font("Roboto", Font.PLAIN, 16));
		usernameField.setColumns(10);
		usernameField.setBackground(UIManager.getColor("Button.light"));
		usernameField.setBounds(51, 315, 340, 50);
		contentPane.add(usernameField);

		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Roboto", Font.PLAIN, 13));
		lblFirstName.setBounds(51, 112, 87, 25);
		contentPane.add(lblFirstName);

		firstNameField = new JTextField("");
		firstNameField.setToolTipText("First Name");
		firstNameField.setHorizontalAlignment(SwingConstants.CENTER);
		firstNameField.setFont(new Font("Roboto", Font.PLAIN, 16));
		firstNameField.setColumns(10);
		firstNameField.setBackground(UIManager.getColor("Button.light"));
		firstNameField.setBounds(51, 140, 340, 50);
		contentPane.add(firstNameField);

		JLabel txtNoAccount = new JLabel("If you have an account? Click on LOGIN");
		txtNoAccount.setForeground(new Color(45, 45, 45));
		txtNoAccount.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtNoAccount.setBounds(51, 458, 340, 33);
		contentPane.add(txtNoAccount);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Roboto", Font.PLAIN, 13));
		lblLastName.setBounds(51, 201, 87, 25);
		contentPane.add(lblLastName);

		lastNameField = new JTextField("");
		lastNameField.setHorizontalAlignment(SwingConstants.CENTER);
		lastNameField.setBackground(UIManager.getColor("Button.light"));
		lastNameField.setFont(new Font("Roboto", Font.PLAIN, 16));
		lastNameField.setToolTipText("Last Name");
		lastNameField.setBounds(51, 226, 340, 50);
		contentPane.add(lastNameField);
		lastNameField.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Roboto", Font.PLAIN, 13));
		lblPassword.setBounds(51, 376, 87, 25);
		contentPane.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setToolTipText("Password");
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setFont(new Font("Roboto", Font.PLAIN, 16));
		passwordField.setBackground(UIManager.getColor("Button.light"));
		passwordField.setBounds(51, 399, 340, 50);
		contentPane.add(passwordField);

		JLabel textDetails = new JLabel("Enter your details to register.");
		textDetails.setForeground(new Color(45, 45, 45, 90));
		textDetails.setFont(new Font("Roboto", Font.PLAIN, 16));
		textDetails.setBounds(51, 74, 361, 50);
		contentPane.add(textDetails);

		JLabel lblRegister = new JLabel("Register");
		lblRegister.setForeground(new Color(45, 45, 45));
		lblRegister.setFont(new Font("Roboto", Font.BOLD, 55));
		lblRegister.setBounds(48, 3, 215, 96);
		contentPane.add(lblRegister);

		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				dispose();
			}
		});
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnLogin.setBackground(new Color(84, 14, 14));
		btnLogin.setBounds(240, 495, 151, 50);
		contentPane.add(btnLogin);

		JButton btnSubmit = new JButton("SUBMIT");
		btnSubmit.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				conn = ConnectionManager.getInstance().getConnection();
				String query = "INSERT INTO users (id,firstName, lastName, username, password) VALUES (default,?,?,?,?)";

				if (!(Pattern.matches("^[a-zA-Z]+$", firstNameField.getText() + lastNameField.getText()))) {
					JOptionPane.showMessageDialog(null, "First and Last name can be only alphabet", "Error",
							JOptionPane.ERROR_MESSAGE);
					clearFields();
				}

				else if (isUsernameExists(usernameField.getText())) {
					JOptionPane.showMessageDialog(null, "User already exist");
					clearFields();
				} else if (firstNameField.getText().equals("") || lastNameField.getText().equals("")
						|| usernameField.getText().equals("") || passwordField.getText().equals("")) {

					JOptionPane.showMessageDialog(null, "All fields must have inputs");
					clearFields();

				} else if (firstNameField.getText().length() > 255 || lastNameField.getText().length() > 255
						|| usernameField.getText().length() > 255 || passwordField.getText().length() > 255) {

					JOptionPane.showMessageDialog(null, "Your input reach maximum words");
					clearFields();

				} else {
					try {
						ps = conn.prepareStatement(query);
						ps.setString(1, firstNameField.getText());
						ps.setString(2, lastNameField.getText());
						ps.setString(3, usernameField.getText());
						ps.setString(4, passwordField.getText());
						ps.executeUpdate();

						JOptionPane.showMessageDialog(null, "User added");
						clearFields();

						Login login = new Login();
						login.setVisible(true);
						dispose();

					} catch (Exception ex) {
						// TODO: handle exception
						System.out.println(ex);
					}
				}

			}
		});
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setBackground(new Color(84, 14, 14));
		btnSubmit.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnSubmit.setBounds(51, 495, 151, 50);
		contentPane.add(btnSubmit);

		JLabel bgLabel = new JLabel("");
		bgLabel.setBackground(new Color(255, 255, 255));
		bgLabel.setIcon(new ImageIcon("res/BG_image.png"));
		bgLabel.setBounds(0, 0, 794, 571);
		contentPane.add(bgLabel);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	public boolean isUsernameExists(String un) {

		boolean uExist = false;

		conn = ConnectionManager.getInstance().getConnection();
		try {
			ps = conn.prepareStatement("SELECT * FROM users WHERE username = ?");
			ps.setString(1, usernameField.getText());

			rs = ps.executeQuery();

			if (rs.next()) {
				uExist = true;
			}
		} catch (SQLException ex) {
			System.out.println(ex);
		}

		return uExist;
	}

	public void clearFields() {
		firstNameField.setText("");
		lastNameField.setText("");
		usernameField.setText("");
		passwordField.setText("");
	}

}
