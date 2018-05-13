package phonebook.main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import phonebook.db.ConnectionManager;

public class ContactQuery {

	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public boolean insertContact(Contact cont) {

		boolean contactIsCreated = true;
		conn = ConnectionManager.getInstance().getConnection();
		String query = "INSERT INTO contacts (id,firstName, lastName, phoneNumber, email, address, country, userid) VALUES (default,?,?,?,?,?,?,?)";

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, cont.getFirstName());
			ps.setString(2, cont.getLastName());
			ps.setString(3, cont.getPhoneNumber());
			ps.setString(4, cont.getEmail());
			ps.setString(5, cont.getAddress());
			ps.setString(6, cont.getCountry());
			ps.setString(7, cont.getUid());

			if (ps.executeUpdate() != 0) {
				JOptionPane.showMessageDialog(null, "Contact successfully added");
				contactIsCreated = false;
			} else {
				JOptionPane.showMessageDialog(null, "Something is wrong");
				contactIsCreated = true;
			}

		} catch (SQLException ex) {
			// TODO: handle exception
			System.out.println(ex);
		}

		return contactIsCreated;
	}
	
	
	
	

}
