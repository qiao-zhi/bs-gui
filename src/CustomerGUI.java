import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class CustomerGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtGender;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerGUI frame = new CustomerGUI();
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
	public CustomerGUI() {
		setTitle("CustomerInfo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, -21, 913, 555);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblId = new JLabel("Id");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblId.setBounds(31, 24, 65, 39);
		contentPane.add(lblId);
		
		JTextPane txtId = new JTextPane();
		txtId.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtId.setBounds(120, 37, 138, 26);
		contentPane.add(txtId);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblName.setBounds(28, 98, 80, 32);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtName.setBounds(120, 98, 138, 31);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblAddress.setBounds(24, 170, 102, 21);
		contentPane.add(lblAddress);
		
		txtAddress = new JTextField();
		txtAddress.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtAddress.setBounds(120, 164, 297, 32);
		contentPane.add(txtAddress);
		txtAddress.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Gender");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(28, 234, 80, 39);
		contentPane.add(lblNewLabel);
		
		txtGender = new JTextField();
		txtGender.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtGender.setBounds(120, 246, 186, 27);
		contentPane.add(txtGender);
		txtGender.setColumns(10);

		JCheckBox chkCat = new JCheckBox("Cat");
		chkCat.setBackground(Color.CYAN);
		chkCat.setFont(new Font("Tahoma", Font.PLAIN, 22));
		chkCat.setBounds(533, 35, 155, 28);
		contentPane.add(chkCat);
		
		JCheckBox chkDog = new JCheckBox("Dog");
		chkDog.setFont(new Font("Tahoma", Font.PLAIN, 22));
		chkDog.setBackground(Color.CYAN);
		chkDog.setBounds(533, 88, 155, 28);
		contentPane.add(chkDog);
		
		JCheckBox chkHorse = new JCheckBox("Horse");
		chkHorse.setFont(new Font("Tahoma", Font.PLAIN, 22));
		chkHorse.setBackground(Color.CYAN);
		chkHorse.setBounds(533, 139, 155, 28);
		contentPane.add(chkHorse);
		
		JRadioButton rdRural = new JRadioButton("Rural");
		buttonGroup.add(rdRural);
		rdRural.setBackground(Color.CYAN);
		rdRural.setFont(new Font("Tahoma", Font.PLAIN, 22));
		rdRural.setBounds(533, 205, 127, 25);
		contentPane.add(rdRural);
		
		JRadioButton rdResidential = new JRadioButton("Residential");
		buttonGroup.add(rdResidential);
		rdResidential.setFont(new Font("Tahoma", Font.PLAIN, 22));
		rdResidential.setBackground(Color.CYAN);
		rdResidential.setBounds(533, 248, 155, 25);
		contentPane.add(rdResidential);
		
		JComboBox cboxCountry = new JComboBox();
		cboxCountry.setEditable(true);
		cboxCountry.setBackground(Color.CYAN);
		cboxCountry.setFont(new Font("Tahoma", Font.PLAIN, 22));
		cboxCountry.setBounds(497, 284, 303, 53);
		contentPane.add(cboxCountry);
		cboxCountry.addItem("Australia");
		cboxCountry.addItem("China");
		cboxCountry.addItem("France");
		cboxCountry.addItem("India");
		cboxCountry.addItem("Pakistan");
		
		
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*System.out.println("Customer created with " + "\n" +
			                       "Id: " + txtId.getText() + "\n" + 
			                       "Name: " + txtName.getText() + "\n" + 
			                       "Address: " + txtAddress.getText() + "\n" + 
			                       "Gender: " + txtGender.getText());*/
				/*if(chkCat.isSelected()) {
					System.out.println("Customer has a cat");
				}
				if(chkDog.isSelected()) {
					System.out.println("Customer has a dog");
				}
				if(chkHorse.isSelected()) {
					System.out.println("Customer has a horse");
				}
				
				if(rdRural.isSelected()) {
					System.out.print("Rural customer from ");
				} else {
					System.out.print("Residential customer from ");
				}
				System.out.println(cboxCountry.getSelectedItem());*/
			}
		});
		btnOK.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnOK.setBounds(31, 316, 113, 39);
		contentPane.add(btnOK);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Customer not created");
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnCancel.setBounds(179, 316, 113, 39);
		contentPane.add(btnCancel);
		
	}
}
