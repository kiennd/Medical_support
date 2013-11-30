package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.ConstantMedical;
import Model.Laborator;
import Model.LaboratorForm;

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
	private JButton btnSubmit;
	private JLabel lblDiseaseName;
	private DefaultTableModel abnormalModel;
	private DefaultTableModel nearLaboratorFormModel;

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

		btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(38, 443, 117, 29);
		contentPane.add(btnSubmit);

		JLabel lblNewLabel = new JLabel("Medical support system");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel.setBounds(565, 15, 284, 41);
		contentPane.add(lblNewLabel);

		detectForm = new JPanel();
		detectForm.setLayout(new GridLayout(
				ConstantMedical.LABORATOR_CATEGORY.length, 2));

		JScrollPane scrollPane = new JScrollPane(detectForm);
		scrollPane.setBounds(41, 75, 395, 357);
		contentPane.add(scrollPane);
		scrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		JPanel resultPanel = new JPanel();
		resultPanel.setBorder(new TitledBorder(null, "Detected result",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		resultPanel.setBounds(465, 68, 384, 369);
		contentPane.add(resultPanel);
		resultPanel.setLayout(null);

		JLabel lbl121212 = new JLabel("Disease name:");
		lbl121212.setBounds(20, 25, 118, 16);
		resultPanel.add(lbl121212);

		lblDiseaseName = new JLabel("");
		lblDiseaseName.setBounds(150, 25, 200, 16);
		resultPanel.add(lblDiseaseName);

		JLabel lbl323 = new JLabel("Abnormal value:");
		lbl323.setBounds(20, 53, 118, 16);
		resultPanel.add(lbl323);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 81, 346, 110);
		resultPanel.add(scrollPane_1);

		tblAbnormal = new JTable();
		scrollPane_1.setViewportView(tblAbnormal);

		Vector<String> columnNames = new Vector<>();
		columnNames.add("Name");
		columnNames.add("Value");
		columnNames.add("Normal Value Range");
		abnormalModel = new DefaultTableModel(columnNames, 0);
		tblAbnormal.setModel(abnormalModel);

		JLabel lblNewLabel_1 = new JLabel(" Similar laborator:");
		lblNewLabel_1.setBounds(20, 203, 118, 16);
		resultPanel.add(lblNewLabel_1);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(20, 231, 342, 109);
		resultPanel.add(scrollPane_2);

		tblSimilarLaborator = new JTable() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -3970231752020540692L;

			public boolean isCellEditable(int row, int column) {
				return true;
			}
		};
		scrollPane_2.setViewportView(tblSimilarLaborator);

		Vector<String> columnNames2 = new Vector<>();
		columnNames2.add("ID");
		columnNames2.add("Patient id");
		columnNames2.add("Time");
		columnNames2.add("Patient name");
		columnNames2.add("Result");
		nearLaboratorFormModel = new DefaultTableModel(columnNames2, 0);
		tblSimilarLaborator.setModel(nearLaboratorFormModel);

		JLabel label = new JLabel("");
		label.setBounds(6, 6, 869, 495);
		Image image2 = img.getScaledInstance(label.getWidth(),
				label.getHeight(), Image.SCALE_SMOOTH);
		label.setIcon(new ImageIcon(image2));

		contentPane.add(label);
		initForm();
	}

	public void initForm() {
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

	public Vector<Laborator> getLaborators() {
		Vector<Laborator> laborators = new Vector<>();
		for (int i = 0; i < txtList.size(); i++) {
			Laborator la = new Laborator();
			la.setName(txtList.elementAt(i).getName());
			if (txtList.elementAt(i).getText().length() > 0) {
				la.setResult(Float.parseFloat(txtList.elementAt(i).getText()));
			} else {
				la.setResult(Float.NaN);
			}
			laborators.add(la);
		}
		return laborators;
	}

	public void addButtonActionListener(ActionListener act) {
		this.btnSubmit.addActionListener(act);
	}

	public void setDiseaseName(String name) {
		this.lblDiseaseName.setText(name);
	}

	public void setAbNormalTable(Vector<Laborator> abNormals) {
		clearAbNormalTable();
		for (Laborator laborator : abNormals) {
			Vector<String> row = new Vector<>();
			row.add(laborator.getName());
			row.add(laborator.getResult() + "");
			row.add(laborator.getNormalValue());

			abnormalModel.addRow(row);
		}
	}

	public void setSimilarTable(Vector<LaboratorForm> laboratorForms) {
		clearSimmilarTable();
		for (LaboratorForm laboratorForm : laboratorForms) {
			Vector<String> row = new Vector<>();
			row.add(laboratorForm.getId() + "");
			row.add(laboratorForm.getPantient().getId());
			row.add(laboratorForm.getCount() + "");
			row.add(laboratorForm.getPantient().getName());
			row.add(laboratorForm.getResult());

			nearLaboratorFormModel.addRow(row);

		}
	}

	public void clearAbNormalTable() {
		while (abnormalModel.getRowCount() > 0) {
			abnormalModel.removeRow(0);
		}
	}

	public void clearSimmilarTable() {
		while (nearLaboratorFormModel.getRowCount() > 0) {
			nearLaboratorFormModel.removeRow(0);
		}
	}

	public void AddTableMouseListener(MouseListener ms) {
		this.tblSimilarLaborator.addMouseListener(ms);
	}

}
