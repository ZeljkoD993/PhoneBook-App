package phonebook.main;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import phonebook.delete.DeleteUser;
import phonebook.login.Register;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.CardLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7234952230714532064L;
	private JPanel contentPane;
	private JTextField phoneNumberField;
	private JTextField addressField;
	private JTextField lastNameField;
	private JTextField emailField;
	private JTextField firstNameField;
	private JTextField countryField;
	public JLabel loggedAs;

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setTitle("Add Contacts Page");
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel menuAdd = new JLabel("");
		menuAdd.setToolTipText("Add contact");
		menuAdd.setIcon(new ImageIcon("res/add.png"));
		menuAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null, "Clicked");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				menuAdd.setIcon(new ImageIcon("res/add_red.png"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuAdd.setIcon(new ImageIcon("res/add.png"));
			}
		});
		menuAdd.setBounds(231, 0, 39, 62);
		contentPane.add(menuAdd);

		JLabel menuEdit = new JLabel("");
		menuEdit.setToolTipText("Edit contact");
		menuEdit.setIcon(new ImageIcon("res/edit.png"));
		menuEdit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null, "Clicked");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				menuEdit.setIcon(new ImageIcon("res/edit_red.png"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuEdit.setIcon(new ImageIcon("res/edit.png"));
			}
		});
		menuEdit.setBounds(304, 0, 39, 62);
		contentPane.add(menuEdit);

		JLabel menuDelete = new JLabel("");
		menuDelete.setToolTipText("Delete contact");
		menuDelete.setIcon(new ImageIcon("res/delete.png"));
		menuDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DeleteUser deleteUser = new DeleteUser();
				deleteUser.setVisible(true);
				dispose();

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				menuDelete.setIcon(new ImageIcon("res/delete_red.png"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuDelete.setIcon(new ImageIcon("res/delete.png"));
			}
		});
		menuDelete.setBounds(377, 0, 39, 62);
		contentPane.add(menuDelete);

		JLabel menuShowAll = new JLabel("");
		menuShowAll.setToolTipText("Show all contacts");
		menuShowAll.setIcon(new ImageIcon("res/showall.png"));
		menuShowAll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null, "Clicked");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				menuShowAll.setIcon(new ImageIcon("res/showall_red.png"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuShowAll.setIcon(new ImageIcon("res/showall.png"));
			}
		});
		menuShowAll.setBounds(450, 0, 39, 62);
		contentPane.add(menuShowAll);

		JLabel menuSearch = new JLabel("");
		menuSearch.setToolTipText("Search contacts");
		menuSearch.setIcon(new ImageIcon("res/search.png"));
		menuSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null, "Clicked");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				menuSearch.setIcon(new ImageIcon("res/search_red.png"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuSearch.setIcon(new ImageIcon("res/search.png"));
			}
		});
		menuSearch.setBounds(523, 0, 39, 62);
		contentPane.add(menuSearch);

		JLabel menuLogout = new JLabel("");
		menuLogout.setToolTipText("Logout");
		menuLogout.setIcon(new ImageIcon("res/logout.png"));
		menuLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null, "Clicked");
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				menuLogout.setIcon(new ImageIcon("res/logout_red.png"));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				menuLogout.setIcon(new ImageIcon("res/logout.png"));
			}
		});
		menuLogout.setBounds(742, 0, 39, 62);
		contentPane.add(menuLogout);

		loggedAs = new JLabel();
		loggedAs.setEnabled(false);
		loggedAs.setForeground(UIManager.getColor("CheckBoxMenuItem.selectionForeground"));
		loggedAs.setFont(new Font("Roboto", Font.PLAIN, 0));
		loggedAs.setBounds(769, 527, 15, 33);
		contentPane.add(loggedAs);

		JLabel lblAddNewPerson = new JLabel("Add new person to phonebook");
		lblAddNewPerson.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddNewPerson.setForeground(new Color(45, 45, 45));
		lblAddNewPerson.setFont(new Font("Roboto", Font.BOLD, 16));
		lblAddNewPerson.setBounds(205, 92, 340, 33);
		contentPane.add(lblAddNewPerson);

		JLabel label = new JLabel("First Name");
		label.setFont(new Font("Roboto", Font.PLAIN, 13));
		label.setBounds(89, 154, 87, 25);
		contentPane.add(label);

		firstNameField = new JTextField("");
		firstNameField.setToolTipText("First Name");
		firstNameField.setHorizontalAlignment(SwingConstants.CENTER);
		firstNameField.setFont(new Font("Roboto", Font.PLAIN, 16));
		firstNameField.setColumns(10);
		firstNameField.setBackground(UIManager.getColor("Button.light"));
		firstNameField.setBounds(89, 185, 267, 50);
		contentPane.add(firstNameField);

		JLabel lblUsername = new JLabel("Email");
		lblUsername.setFont(new Font("Roboto", Font.PLAIN, 13));
		lblUsername.setBounds(89, 339, 87, 25);
		contentPane.add(lblUsername);

		emailField = new JTextField("");
		emailField.setToolTipText("Email");
		emailField.setHorizontalAlignment(SwingConstants.CENTER);
		emailField.setFont(new Font("Roboto", Font.PLAIN, 16));
		emailField.setColumns(10);
		emailField.setBackground(UIManager.getColor("Button.light"));
		emailField.setBounds(89, 374, 267, 50);
		contentPane.add(emailField);

		JLabel lblFirstName = new JLabel("Last Name");
		lblFirstName.setFont(new Font("Roboto", Font.PLAIN, 13));
		lblFirstName.setBounds(458, 154, 87, 25);
		contentPane.add(lblFirstName);

		lastNameField = new JTextField("");
		lastNameField.setToolTipText("Last Name");
		lastNameField.setHorizontalAlignment(SwingConstants.CENTER);
		lastNameField.setFont(new Font("Roboto", Font.PLAIN, 16));
		lastNameField.setColumns(10);
		lastNameField.setBackground(UIManager.getColor("Button.light"));
		lastNameField.setBounds(458, 185, 267, 50);
		contentPane.add(lastNameField);

		JLabel lblLastName = new JLabel("Phone Number");
		lblLastName.setFont(new Font("Roboto", Font.PLAIN, 13));
		lblLastName.setBounds(89, 245, 87, 25);
		contentPane.add(lblLastName);

		phoneNumberField = new JTextField("");
		phoneNumberField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent evt) {
				char vchar = evt.getKeyChar();
				if (!(Character.isDigit(vchar)) || (vchar == KeyEvent.VK_BACK_SPACE) || (vchar == KeyEvent.VK_DELETE)) {
					evt.consume();
				}
			}
		});
		phoneNumberField.setHorizontalAlignment(SwingConstants.CENTER);
		phoneNumberField.setBackground(UIManager.getColor("Button.light"));
		phoneNumberField.setFont(new Font("Roboto", Font.PLAIN, 16));
		phoneNumberField.setToolTipText("Phone Number");
		phoneNumberField.setBounds(89, 278, 267, 50);
		contentPane.add(phoneNumberField);
		phoneNumberField.setColumns(10);

		JLabel lblPassword = new JLabel("Address");
		lblPassword.setFont(new Font("Roboto", Font.PLAIN, 13));
		lblPassword.setBounds(458, 245, 87, 25);
		contentPane.add(lblPassword);

		addressField = new JTextField();
		addressField.setToolTipText("Address");
		addressField.setHorizontalAlignment(SwingConstants.CENTER);
		addressField.setFont(new Font("Roboto", Font.PLAIN, 16));
		addressField.setBackground(UIManager.getColor("Button.light"));
		addressField.setBounds(458, 278, 267, 50);
		contentPane.add(addressField);

		JButton btnSubmit = new JButton("SUBMIT");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String firstName = firstNameField.getText();
				String lastName = lastNameField.getText();
				String phoneNumber = phoneNumberField.getText();
				String address = addressField.getText();
				String email = emailField.getText();
				String country = countryField.getText();

				if (firstNameField.getText().equals("") || lastNameField.getText().equals("")
						|| phoneNumberField.getText().equals("") || addressField.getText().equals("")
						|| emailField.getText().equals("") || countryField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill all fields");
				} else {

					String id = loggedAs.getText();
					Contact contact = new Contact(null, firstName, lastName, phoneNumber, email, address, country, id);
					ContactQuery cq = new ContactQuery();
					cq.insertContact(contact);
					clearFields();
				}

			}
		});

		JLabel lblCountry = new JLabel("Country");
		lblCountry.setFont(new Font("Roboto", Font.PLAIN, 13));
		lblCountry.setBounds(458, 339, 87, 25);
		contentPane.add(lblCountry);

		countryField = new JTextField("");
		countryField.setToolTipText("Country");
		countryField.setHorizontalAlignment(SwingConstants.CENTER);
		countryField.setFont(new Font("Roboto", Font.PLAIN, 16));
		countryField.setColumns(10);
		countryField.setBackground(UIManager.getColor("Button.light"));
		countryField.setBounds(458, 374, 267, 50);
		contentPane.add(countryField);

		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setBackground(new Color(84, 14, 14));
		btnSubmit.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnSubmit.setBounds(256, 487, 124, 50);
		contentPane.add(btnSubmit);

		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearFields();
			}
		});
		btnReset.setForeground(Color.WHITE);
		btnReset.setFont(new Font("Roboto", Font.PLAIN, 18));
		btnReset.setBackground(new Color(84, 14, 14));
		btnReset.setBounds(399, 487, 124, 50);
		contentPane.add(btnReset);

		JLabel bgLabel = new JLabel("");
		bgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bgLabel.setBackground(new Color(255, 255, 255));
		bgLabel.setIcon(new ImageIcon("res/mainBG.png"));
		bgLabel.setBounds(0, 0, 794, 571);
		contentPane.add(bgLabel);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	public void clearFields() {
		firstNameField.setText("");
		lastNameField.setText("");
		phoneNumberField.setText("");
		emailField.setText("");
		addressField.setText("");
		countryField.setText("");
	}

	public JLabel getLoggedAs() {
		return loggedAs;
	}
}
