package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Laborator;
import Model.LaboratorForm;

public class LaboratorDetailView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tblLaborators;
	private JLabel lblPatientID,lblPatientName,lblBornYear,lblGender;
	private DefaultTableModel laboratorsModel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LaboratorDetailView dialog = new LaboratorDetailView();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LaboratorDetailView() {
		setBounds(100, 100, 464, 479);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblPati1212 = new JLabel("Patient ID:");
			lblPati1212.setBounds(24, 26, 85, 16);
			contentPanel.add(lblPati1212);
		}
		{
			lblPatientID = new JLabel("");
			lblPatientID.setBounds(170, 26, 212, 16);
			contentPanel.add(lblPatientID);
		}
		{
			JLabel lblPatie1212 = new JLabel("Patient Name:");
			lblPatie1212.setBounds(24, 54, 99, 16);
			contentPanel.add(lblPatie1212);
		}
		{
			JLabel lbl128912 = new JLabel("Born year:");
			lbl128912.setBounds(24, 82, 91, 16);
			contentPanel.add(lbl128912);
		}
		{
			lblBornYear = new JLabel("");
			lblBornYear.setBounds(170, 82, 212, 16);
			contentPanel.add(lblBornYear);
		}
		{
			lblPatientName = new JLabel("");
			lblPatientName.setBounds(170, 54, 212, 16);
			contentPanel.add(lblPatientName);
		}
		{
			JLabel lblh821 = new JLabel("Gender:");
			lblh821.setBounds(24, 110, 61, 16);
			contentPanel.add(lblh821);
		}
		{
			lblGender = new JLabel("");
			lblGender.setBounds(170, 110, 212, 16);
			contentPanel.add(lblGender);
		}
		{
			JScrollPane scroll = new JScrollPane();
			scroll.setBounds(24, 151, 413, 249);
			contentPanel.add(scroll);
			{
				tblLaborators = new JTable();
				scroll.setViewportView(tblLaborators);
				Vector<String> columnNames = new Vector<>();
				columnNames.add("Name");
				columnNames.add("Result");
				laboratorsModel = new DefaultTableModel(columnNames, 0);
				tblLaborators.setModel(laboratorsModel);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
						
					}
				});
			}
		}
	}
	
	public void initData(LaboratorForm lf){
		this.lblPatientName.setText(lf.getPantient().getName());
		this.lblPatientID.setText(lf.getPantient().getId());
		switch (lf.getPantient().getSex()) {
		case 0:
			this.lblGender.setText("Nữ");
			
			break;
		case 1:
			
			this.lblGender.setText("Nam");
			break;

		}
		this.lblBornYear.setText(lf.getPantient().getBornYear()+"");
		
		
		
		Vector<Laborator>laborators = lf.getLaborators();
		for (Laborator laborator : laborators) {
			Vector<String> row = new Vector<>();
			row.add(laborator.getName()+ "");
			row.add(laborator.getResult()+ "");
			laboratorsModel.addRow(row);

		}
		
	}

}
