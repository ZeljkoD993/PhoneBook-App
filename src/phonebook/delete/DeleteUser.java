package phonebook.delete;

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

import phonebook.db.ConnectionManager;
import phonebook.main.Main;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DeleteUser extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7234952230714532064L;
	private JPanel contentPane;
	private JTextField userPhoneField;
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
	public DeleteUser() {
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
				Main main = new Main();
				main.setVisible(true);
				dispose();
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

		JLabel lblEnterUserPhone = new JLabel("Enter user phone number");
		lblEnterUserPhone.setBounds(229, 136, 278, 25);
		contentPane.add(lblEnterUserPhone);
		lblEnterUserPhone.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterUserPhone.setFont(new Font("Roboto", Font.PLAIN, 13));

		userPhoneField = new JTextField("");
		userPhoneField.setBounds(197, 178, 348, 50);
		contentPane.add(userPhoneField);
		userPhoneField.setToolTipText("First Name");
		userPhoneField.setHorizontalAlignment(SwingConstants.CENTER);
		userPhoneField.setFont(new Font("Roboto", Font.PLAIN, 16));
		userPhoneField.setColumns(10);
		userPhoneField.setBackground(UIManager.getColor("Button.light"));

		JButton btnSubmit = new JButton("SUBMIT");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				conn = ConnectionManager.getInstance().getConnection();
				String querySelect = "SELECT * FROM contacts WHERE phoneNumber = ?";
				try {
					ps = conn.prepareStatement(querySelect);
					ps.setString(1, userPhoneField.getText());

					rs = ps.executeQuery();

					if (rs.next()) {

						String infoName, infoSurname, infoPhoneNumber;
						infoName = rs.getString("firstName");
						infoSurname = rs.getString("lastName");
						infoPhoneNumber = rs.getString("phoneNumber");

						int reply = JOptionPane.showConfirmDialog(
								null, "Do you want delete contact:  " + infoName + " " + infoSurname
										+ " with phonenumber: " + infoPhoneNumber,
								"Delete phone number " + infoPhoneNumber, JOptionPane.YES_NO_OPTION);
						if (reply == JOptionPane.YES_NO_OPTION) {
							String query = "DELETE FROM contacts WHERE phoneNumber = ? ";
							try {
								ps = conn.prepareStatement(query);

								ps.setString(1, userPhoneField.getText());
								ps.execute();
								JOptionPane.showMessageDialog(null, "Deleted");
								userPhoneField.setText(" ");

							} catch (Exception e) {
								// TODO: handle exception
								System.out.println(e);
							}
						} else
							JOptionPane.showMessageDialog(null, "Canceled");

					} else {
						JOptionPane.showMessageDialog(null, "This phone number not in contacts");

					}
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e);
				}

			}
		});
		btnSubmit.setBounds(284, 252, 205, 50);
		contentPane.add(btnSubmit);

		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setBackground(new Color(84, 14, 14));
		btnSubmit.setFont(new Font("Roboto", Font.PLAIN, 18));
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
				JOptionPane.showMessageDialog(null, "Clicked");
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

		JLabel lblAddNewPerson = new JLabel("Delete user from phonebook");
		lblAddNewPerson.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddNewPerson.setForeground(new Color(45, 45, 45));
		lblAddNewPerson.setFont(new Font("Roboto", Font.BOLD, 16));
		lblAddNewPerson.setBounds(205, 92, 340, 33);
		contentPane.add(lblAddNewPerson);

		JLabel bgLabel = new JLabel("");
		bgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bgLabel.setBackground(new Color(255, 255, 255));
		bgLabel.setIcon(new ImageIcon("res/mainBG.png"));
		bgLabel.setBounds(0, 0, 794, 571);
		contentPane.add(bgLabel);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	public JLabel getLoggedAs() {
		return loggedAs;
	}
}
