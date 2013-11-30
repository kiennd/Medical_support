package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import Model.ConstantMedical;
import Model.Laborator;

public class DetectView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1033550751623850582L;
	private JPanel contentPane;
	private JPanel detectForm;
	private Vector<JTextField> txtList;
	private JTable tblAbnormal;
	private JTable tblSimilarLaborator;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetectView frame = new DetectView();
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
	public DetectView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 881, 524);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("detect_bg.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(38, 443, 117, 29);
		contentPane.add(btnSubmit);
					
		JLabel lblNewLabel = new JLabel("Medical support system");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel.setBounds(565, 15, 284, 41);
		contentPane.add(lblNewLabel);
		
		
		detectForm = new JPanel();
		detectForm.setLayout(new GridLayout(ConstantMedical.LABORATOR_CATEGORY.length, 2));
		
		JScrollPane scrollPane = new JScrollPane(detectForm);
		scrollPane.setBounds(41, 75, 395, 357);
		contentPane.add(scrollPane);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		JPanel resultPanel = new JPanel();
		resultPanel.setBorder(new TitledBorder(null, "Detected result", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		resultPanel.setBounds(465, 68, 384, 369);
		contentPane.add(resultPanel);
		resultPanel.setLayout(null);
		
		JLabel lblDisease = new JLabel("Disease name:");
		lblDisease.setBounds(20, 25, 118, 16);
		resultPanel.add(lblDisease);
		
		JLabel lblDiseaseName = new JLabel("");
		lblDiseaseName.setBounds(150, 25, 200, 16);
		resultPanel.add(lblDiseaseName);
		
		JLabel lblAbnormalValue = new JLabel("Abnormal value:");
		lblAbnormalValue.setBounds(20, 53, 118, 16);
		resultPanel.add(lblAbnormalValue);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 81, 346, 110);
		resultPanel.add(scrollPane_1);
		
		tblAbnormal = new JTable();
		scrollPane_1.setViewportView(tblAbnormal);
		
		JLabel lblNewLabel_1 = new JLabel(" Similar laborator:");
		lblNewLabel_1.setBounds(20, 203, 118, 16);
		resultPanel.add(lblNewLabel_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(20, 231, 342, 109);
		resultPanel.add(scrollPane_2);
		
		tblSimilarLaborator = new JTable();
		scrollPane_2.setViewportView(tblSimilarLaborator);
		
		JLabel label = new JLabel("");
		label.setBounds(6, 6, 869, 495);
		Image image2 = img.getScaledInstance(label.getWidth(),
				label.getHeight(), Image.SCALE_SMOOTH);
		label.setIcon(new ImageIcon(image2));
		
		contentPane.add(label);
		initForm();
	}
	
	public void initForm(){
		txtList = new Vector<>();
		for (int i = 0; i < ConstantMedical.LABORATOR_CATEGORY.length; i++) {
			String laboratorName = ConstantMedical.LABORATOR_CATEGORY[i];
			JLabel lb = new JLabel(laboratorName);
			detectForm.add(lb);
			JTextField tfTmp = new JTextField(20);
			tfTmp.setName(laboratorName);
			detectForm.add(tfTmp);
			txtList.addElement(tfTmp);
		}
	}
	
	public Vector<Laborator> getLaborators(){
		return null;
	}
	
}
