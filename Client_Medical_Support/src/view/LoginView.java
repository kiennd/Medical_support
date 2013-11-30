package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;

import Model.Message;
import Model.User;
import control.Setting;

public class LoginView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2686488075970560969L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JButton btnLogin;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
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
	public LoginView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(176, 62, 174, 28);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(176, 128, 174, 28);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(54, 186, 94, 29);
		contentPane.add(btnLogin);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(54, 68, 82, 16);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(54, 134, 69, 16);
		contentPane.add(lblPassword);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(6, 6, 438, 266);
		
		BufferedImage img;
		try {
			img = ImageIO.read(new File("medical-funding.jpg"));
			Image image2 = img.getScaledInstance(lblNewLabel.getWidth(),
					lblNewLabel.getHeight(), Image.SCALE_SMOOTH);

			lblNewLabel.setIcon(new ImageIcon(image2));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contentPane.add(lblNewLabel);
		
	}
	public void addActionLogin(ActionListener act){
		this.btnLogin.addActionListener(act);
	}
	
	@SuppressWarnings("deprecation")
	public User getUser(){
		User u = new User();
		u.setUsername(this.txtUsername.getText());
		u.setPassword(this.txtPassword.getText());
		return u;
	}
	
	public Message getMessage(){
		User u = new User();
		u.setUsername(this.txtUsername.getText());
		u.setPassword(this.txtPassword.getText());
		Message mes = new Message(Setting.REQUEST_LOGIN, u);
		String nameTmp = UUID.randomUUID().toString();
		mes.setReceipent(nameTmp);
		mes.setSender(nameTmp);
		return mes;
	}
	
	public void showMessage(String message){
		JOptionPane.showMessageDialog(this, message);
	}
	
	
	
}
