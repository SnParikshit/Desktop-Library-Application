package lib;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import java.awt.Color;
import java.awt.Button;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Library_App extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	int xx,xy;
	
	
	//creating connecting for database
	public Connection getConnect()
	{
		Connection con = null;
		String username = "root";
		String database = "Registration";
		String connect = "jdbc:mysql://localhost:3306/"+database;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		try {
			con = DriverManager.getConnection(connect, username, null);
			//JOptionPane.showMessageDialog(null, "Connected");
			return con;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Logger.getLogger(Library_App.class.getName()).log(Level.SEVERE, null, e);
			JOptionPane.showMessageDialog(null, " Not Connected");
			return null;
		}
	}
	
	
	
	// check inputs
	public boolean checkInputs()
	{
		if(textField.getText() == null || textField_1.getText()==null || textField_2.getText()==null || textField_3.getText()==null)
		{
			return false;
		}
		else
		{
			
			try {
				Integer.parseInt(textField_1.getText());
				return true;
				
				
			}catch(Exception e)
			{
				System.out.println(e.getMessage());
				return false;
			}
		}
	}
	
	//vallidating email
	public  boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";                      
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
	
	//clearing textfields
	public void clearFields()
	{
		textField.setText(null);
		textField_1.setText(null);
		textField_2.setText(null);
		textField_3.setText(null);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Library_App frame = new Library_App();
					frame.setUndecorated(true);
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
	
	public Library_App() {
		getConnect();
		database();
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 729, 476);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 346, 490);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Library Desktop Applicaiton");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setForeground(new Color(240, 248, 255));
		lblNewLabel.setBounds(55, 367, 257, 27);
		panel.add(lblNewLabel);
		
		JLabel label = new JLabel("");
		
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				 xx = e.getX();
			     xy = e.getY();
			}
		});
		label.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				
				int x = arg0.getXOnScreen();
	            int y = arg0.getYOnScreen();
	            Library_App.this.setLocation(x - xx, y - xy);  
			}
		});
		label.setBounds(-47, 0, 420, 370);
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setIcon(new ImageIcon(Library_App.class.getResource("/image/library.jpg")));
		panel.add(label);
		
		JLabel lblWeGotYou = new JLabel("....Find Yourself....");
		lblWeGotYou.setHorizontalAlignment(SwingConstants.CENTER);
		lblWeGotYou.setForeground(new Color(240, 248, 255));
		lblWeGotYou.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblWeGotYou.setBounds(108, 405, 141, 27);
		panel.add(lblWeGotYou);
		
		Button button = new Button("SignUp");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {    //performing database entry using button action listener
				
				if(checkInputs() && isValid(textField_2.getText())) // calling function for checking and validating inputs
				{
					String full_name = textField.getText();
					int mobile_no = Integer.parseInt(textField_1.getText());
					String email_id = textField_2.getText();
					String place = textField_3.getText();
					
					try {
						Connection con = getConnect();
						PreparedStatement ps = con.prepareStatement("INSERT INTO regis(Name,Mobile_no,Email_id,Place)"+"values(?,?,?,?)");
								ps.setString(1, full_name);
								ps.setInt(2, mobile_no);
								ps.setString(3, email_id);
								ps.setString(4, place);
								ps.executeUpdate();
								JOptionPane.showMessageDialog(null, "Data Inserted");
								clearFields();
					} catch (SQLException e) {
						
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "enter the details properly");
					clearFields();
				}
			}
		});
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(241, 57, 83));
		button.setBounds(395, 363, 283, 36);
		contentPane.add(button);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {           //listenting keypress to check auto-complete
			@Override
			public void keyPressed(KeyEvent evt) {
				switch(evt.getKeyCode())
				{
				case KeyEvent.VK_BACK_SPACE:
					break;
				case KeyEvent.VK_ENTER:
					textField.setText(textField.getText());
					break;
					default:
					 EventQueue.invokeLater(new Runnable() {
						 	public void run() {
								
						 		String txt = textField.getText(); ////calling autocomplete funtion for name
						 		autoComplete(txt);
							}
						});
				}
			}
		});
		
		
		textField.setBounds(395, 83, 283, 36);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("FULL NAME");
		lblUsername.setBounds(395, 58, 114, 14);
		contentPane.add(lblUsername);
		
		JLabel lblEmail = new JLabel("MOBILE NO");
		lblEmail.setBounds(395, 132, 114, 14);
		contentPane.add(lblEmail);
		
		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				switch(evt.getKeyCode())
				{
				case KeyEvent.VK_BACK_SPACE:
					break;
				case KeyEvent.VK_ENTER:
					textField_1.setText(textField_1.getText());
					break;
					default:
					 EventQueue.invokeLater(new Runnable() {
						 	public void run() {
						 		String txt = textField_1.getText();  //calling autocomplete funtion mobile no
						 		autoCompletemob(txt);
							}
						});
				}
			}
		});
		textField_1.setColumns(10);
		textField_1.setBounds(395, 157, 283, 36);
		contentPane.add(textField_1);
		
		JLabel lblPassword = new JLabel("EMAIL ID");
		lblPassword.setBounds(395, 204, 96, 14);
		contentPane.add(lblPassword);
		
		JLabel lblRepeatPassword = new JLabel("PLACE OF RESIDENCE");
		lblRepeatPassword.setBounds(395, 275, 133, 14);
		contentPane.add(lblRepeatPassword);
		
		textField_2 = new JTextField();
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				switch(evt.getKeyCode())
				{
				case KeyEvent.VK_BACK_SPACE:
					break;
				case KeyEvent.VK_ENTER:
					textField_2.setText(textField_2.getText());
					break;
					default:
					 EventQueue.invokeLater(new Runnable() {
						 	public void run() {
						 		String txt = textField_2.getText();    //calling autocomplete funtion for place of email
						 		autoCompleteemail(txt);
							}
						});
				}
			}
		});
		
		textField_2.setBounds(395, 229, 283, 36);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				switch(evt.getKeyCode())
				{
				case KeyEvent.VK_BACK_SPACE:
					break;
				case KeyEvent.VK_ENTER:
					textField_3.setText(textField_3.getText());
					break;
					default:
					 EventQueue.invokeLater(new Runnable() {
						 	public void run() {
						 		String txt = textField_3.getText();
						 		autoCompleteplace(txt);       //calling autocomplete funtion for place of residence
							}
						});
				}
			}
		});
		textField_3.setBounds(395, 293, 283, 36);
		contentPane.add(textField_3);
		
		JLabel lbl_close = new JLabel("X");
		lbl_close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				System.exit(0);
			}
		});
		lbl_close.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_close.setForeground(new Color(241, 57, 83));
		lbl_close.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_close.setBounds(691, 0, 37, 27);
		contentPane.add(lbl_close);
	}
	
	
	
	/*creating functions for auto complete*/
	
	
	
	ArrayList ar = new ArrayList();
	public void database()
	{
		try {
			Connection con = getConnect();
			String sqln = "SELECT * from regis; ";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sqln);
			while(rs.next())
			{
				String name = rs.getString("Name");
				ar.add(name);
				String mob = rs.getString("Mobile_no");
				ar.add(mob);
				String email = rs.getString("Email_id");
				ar.add(email);
				String place = rs.getString("Place");
				ar.add(place);
			}
			rs.close();
			st.close();
			con.close();
		}
		catch(Exception e )
		{
			System.out.println(e.getMessage());
		}
	}
	public void autoComplete(String text)
	{
		String complete = "";
		int start = text.length();
		int last = text.length();
		int a = 0;
		for (a=0;a<ar.size();a++)
		{
			if(ar.get(a).toString().startsWith(text)) {
				complete = ar.get(a).toString();
				last = complete.length();
				break;	
			}			
		}
		if(last>start)
		{
			textField.setText(complete);
			textField.setCaretPosition(last);
			textField.moveCaretPosition(start);
		}
		
	}
	public void autoCompletemob(String text)
	{
		String complete = "";
		int start = text.length();
		int last = text.length();
		int a = 0;
		for (a=0;a<ar.size();a++)
		{
			if(ar.get(a).toString().startsWith(text)) {
				complete = ar.get(a).toString();
				last = complete.length();
				break;	
			}			
		}
		if(last>start)
		{
			textField_1.setText(complete);
			textField_1.setCaretPosition(last);
			textField_1.moveCaretPosition(start);
		}
}
	public void autoCompleteemail(String text)
	{
		String complete = "";
		int start = text.length();
		int last = text.length();
		int a = 0;
		for (a=0;a<ar.size();a++)
		{
				if(ar.get(a).toString().startsWith(text)) {
					complete = ar.get(a).toString();
					last = complete.length();
					break;	
			}			
		}
		if(last>start)
		{
			textField_2.setText(complete);
			textField_2.setCaretPosition(last);
			textField_2.moveCaretPosition(start);
		}
	}
	public void autoCompleteplace(String text)
	{
		String complete = "";
		int start = text.length();
		int last = text.length();
		int a = 0;
		for (a=0;a<ar.size();a++)
		{
				if(ar.get(a).toString().startsWith(text)) {
					complete = ar.get(a).toString();
					last = complete.length();
					break;	
			}			
		}
		if(last>start)
		{
			textField_3.setText(complete);
			textField_3.setCaretPosition(last);
			textField_3.moveCaretPosition(start);
		}
	}
	
	
}
