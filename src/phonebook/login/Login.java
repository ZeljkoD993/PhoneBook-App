package phonebook.login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import phonebook.db.ConnectionManager;
import phonebook.main.Main;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6755817528047723671L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setTitle("Login Page");
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnRegister = new JButton("REGISTER");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Register register = new Register();
				register.setVisible(true);
				dispose();

			}
		});
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnRegister.setBackground(new Color(84, 14, 14));
		btnRegister.setBounds(240, 440, 151, 50);
		contentPane.add(btnRegister);

		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				conn = ConnectionManager.getInstance().getConnection();
				String query = "SELECT * FROM users WHERE username = ? AND password = ?";
				try {
					ps = conn.prepareStatement(query);
					ps.setString(1, usernameField.getText());
					ps.setString(2, passwordField.getText());
					rs = ps.executeQuery();

					if (rs.next()) {

						String infoName, infoSurname;
						infoName = rs.getString("firstName");
						infoSurname = rs.getString("lastName");
						
						

						JOptionPane.showMessageDialog(null, "Login Success! Welcome:  " + infoName + " " + infoSurname);
						Main addContact = new Main();
						
						addContact.setVisible(true);
						addContact.loggedAs.setText(rs.getString("id"));
						dispose();

						usernameField.setText("");
						passwordField.setText("");

					} else if (usernameField.getText().equals("") && passwordField.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Username and Password must have some inputs");
					} else {
						JOptionPane.showMessageDialog(null, "Username or Password not correct");
						usernameField.setText("");
						passwordField.setText("");
					}
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e);
				}

			}
		});
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setBackground(new Color(84, 14, 14));
		btnLogin.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnLogin.setBounds(51, 440, 151, 50);
		contentPane.add(btnLogin);

		JLabel txtNoAccount = new JLabel("Dont have an account? Click on REGISTER");
		txtNoAccount.setForeground(new Color(45, 45, 45));
		txtNoAccount.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtNoAccount.setBounds(51, 396, 340, 33);
		contentPane.add(txtNoAccount);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Roboto", Font.PLAIN, 13));
		lblUsername.setBounds(51, 225, 87, 25);
		contentPane.add(lblUsername);

		usernameField = new JTextField("");
		usernameField.setHorizontalAlignment(SwingConstants.CENTER);
		usernameField.setBackground(UIManager.getColor("Button.light"));
		usernameField.setFont(new Font("Roboto", Font.PLAIN, 16));
		usernameField.setToolTipText("Username");
		usernameField.setBounds(51, 250, 340, 50);
		contentPane.add(usernameField);
		usernameField.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Roboto", Font.PLAIN, 13));
		lblPassword.setBounds(51, 312, 87, 25);
		contentPane.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setToolTipText("Password");
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setFont(new Font("Roboto", Font.PLAIN, 16));
		passwordField.setBackground(UIManager.getColor("Button.light"));
		passwordField.setBounds(51, 335, 340, 50);
		contentPane.add(passwordField);

		JLabel textDetails = new JLabel("Enter your details to login");
		textDetails.setForeground(new Color(45, 45, 45, 90));
		textDetails.setFont(new Font("Roboto", Font.PLAIN, 22));
		textDetails.setBounds(51, 167, 296, 45);
		contentPane.add(textDetails);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setForeground(new Color(45, 45, 45));
		lblLogin.setFont(new Font("Roboto", Font.BOLD, 55));
		lblLogin.setBounds(51, 87, 215, 96);
		contentPane.add(lblLogin);

		JLabel bgLabel = new JLabel("");
		bgLabel.setBackground(new Color(255, 255, 255));
		bgLabel.setIcon(new ImageIcon("res/BG_image.png"));
		bgLabel.setBounds(0, 0, 794, 571);
		contentPane.add(bgLabel);
		setLocationRelativeTo(null);
		setResizable(false);
	}

}
