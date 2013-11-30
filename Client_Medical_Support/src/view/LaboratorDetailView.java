package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class LaboratorDetailView extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

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
			JLabel lblPatientId_ = new JLabel("Patient ID:");
			lblPatientId_.setBounds(24, 26, 85, 16);
			contentPanel.add(lblPatientId_);
		}
		{
			JLabel lblPatientID = new JLabel("");
			lblPatientID.setBounds(170, 26, 212, 16);
			contentPanel.add(lblPatientID);
		}
		{
			JLabel lblPatientName = new JLabel("Patient Name:");
			lblPatientName.setBounds(24, 54, 99, 16);
			contentPanel.add(lblPatientName);
		}
		{
			JLabel lblBornYear = new JLabel("Born year:");
			lblBornYear.setBounds(24, 82, 91, 16);
			contentPanel.add(lblBornYear);
		}
		{
			JLabel label = new JLabel("");
			label.setBounds(170, 82, 212, 16);
			contentPanel.add(label);
		}
		{
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setBounds(170, 54, 212, 16);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblSex = new JLabel("Gender:");
			lblSex.setBounds(24, 110, 61, 16);
			contentPanel.add(lblSex);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setBounds(170, 110, 212, 16);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(24, 151, 413, 249);
			contentPanel.add(scrollPane);
			{
				table = new JTable();
				scrollPane.setViewportView(table);
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

}
