package weberhard2740ex3g;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DefaultListModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JOptionPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PayrollForm extends JFrame {

	private JPanel contentPane;
	private JList list;
	private JTextField empIdLabel;
	private JTextField empNameLabel;
	private JTextField payRateLabel;
	private JTextField hoursTextField;
	private JLabel totalHoursLabel;
	private JLabel grossPayLabel;
	private DefaultListModel employeeListModel;
	private JButton addButton;
	private JButton clearHoursButton;
	private JButton btnUpdate;
	private PayrollObjMapper payrollObjMapper;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayrollForm frame = new PayrollForm();
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
	public PayrollForm() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if(payrollObjMapper != null){
					payrollObjMapper.writeAllPayroll(employeeListModel);
				}
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 391);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel selLabel = new JLabel("Select Employee:");
		selLabel.setBounds(35, 11, 99, 14);
		contentPane.add(selLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(35, 36, 369, 126);
		contentPane.add(scrollPane);
		
		// list = new JList();
 //	employeeListModel = new DefaultListModel();
//		employeeListModel.addElement(new Payroll(101, "Will Eberhard", 10.0));
//		employeeListModel.addElement(new Payroll(102, "Mari Kawaii", 20.0));
//		employeeListModel.addElement(new Payroll(103, "Rubix Mann", 30.0));
//		employeeListModel.addElement(new Payroll(104, "Waka Itoy", 40.0));
//		employeeListModel.addElement(new Payroll(105, "Slurmp Nicely", 50.0));
		payrollObjMapper = new PayrollObjMapper("PersonData.txt");
		employeeListModel = payrollObjMapper.getAllPayroll();
		
		list = new JList(employeeListModel);
		
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Payroll emp = (Payroll) list.getSelectedValue();
				empIdLabel.setText(Integer.toString(emp.getId()));
				empNameLabel.setText(emp.getName());
				payRateLabel.setText(Double.toString(emp.getPayrate()));
				addButton.setEnabled(true);
				clearHoursButton.setEnabled(true);
				btnUpdate.setEnabled(true);
			}
		});
		
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);
		
		JLabel lblEmployeeId = new JLabel("Employee ID (> 100):");
		lblEmployeeId.setBounds(10, 189, 116, 14);
		contentPane.add(lblEmployeeId);
		
		empIdLabel = new JTextField("0");
		empIdLabel.setBounds(136, 189, 75, 14);
		contentPane.add(empIdLabel);
		
		JLabel lblEmpoyeeName = new JLabel("Name:");
		lblEmpoyeeName.setBounds(10, 214, 109, 14);
		contentPane.add(lblEmpoyeeName);
		
		empNameLabel = new JTextField(" ");
		empNameLabel.setBounds(136, 214, 230, 20);
		contentPane.add(empNameLabel);
		
		JLabel lblPayrate = new JLabel("Payrate (7.25 - 100):");
		lblPayrate.setBounds(10, 239, 116, 14);
		contentPane.add(lblPayrate);
		
		payRateLabel = new JTextField("$0.00");
		payRateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		payRateLabel.setBounds(136, 239, 46, 14);
		contentPane.add(payRateLabel);
		
		JLabel lblEnterHours = new JLabel("Enter Hours (0.01-20):");
		lblEnterHours.setBounds(10, 264, 145, 14);
		contentPane.add(lblEnterHours);
		
		hoursTextField = new JTextField();
		hoursTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				hoursTextField.selectAll();
			}
		});
		hoursTextField.setText("0.00");
		hoursTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		hoursTextField.setBounds(136, 261, 46, 20);
		contentPane.add(hoursTextField);
		hoursTextField.setColumns(10);
		
		JLabel lblTotalHours = new JLabel("Total Hours:");
		lblTotalHours.setBounds(10, 289, 100, 14);
		contentPane.add(lblTotalHours);
		
		totalHoursLabel = new JLabel("0.00");
		totalHoursLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		totalHoursLabel.setBounds(136, 292, 46, 14);
		contentPane.add(totalHoursLabel);
		
		JLabel lblGrossPay = new JLabel("Gross Pay:");
		lblGrossPay.setBounds(10, 314, 100, 14);
		contentPane.add(lblGrossPay);
		
		grossPayLabel = new JLabel("$0.00");
		grossPayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		grossPayLabel.setBounds(98, 317, 84, 14);
		contentPane.add(grossPayLabel);
		
		addButton = new JButton("+");
		addButton.setEnabled(false);
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Payroll emp = (Payroll) list.getSelectedValue();
				emp.addHours(Double.parseDouble(hoursTextField.getText()));
				DecimalFormat hoursFmt = new DecimalFormat("###.00");
				totalHoursLabel.setText(hoursFmt.format(emp.getHours()));
				DecimalFormat dollarFmt = new DecimalFormat("$#,##0.00");
				grossPayLabel.setText(dollarFmt.format(emp.getGrossPay()));
				hoursTextField.setText("0.00");
				hoursTextField.requestFocus();
			}
		});
		addButton.setBounds(192, 260, 41, 23);
		contentPane.add(addButton);
		
		clearHoursButton = new JButton("Clear");
		clearHoursButton.setEnabled(false);
		clearHoursButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Payroll emp = (Payroll) list.getSelectedValue();
				emp.setHours(0);
				totalHoursLabel.setText("0.00");
				grossPayLabel.setText("0.00");
				hoursTextField.setText("0.00");
				hoursTextField.requestFocus();
			}
		});
		clearHoursButton.setBounds(243, 260, 57, 23);
		contentPane.add(clearHoursButton);
		
		JButton btnClearAll = new JButton("Clear All");
		btnClearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Payroll emp = (Payroll) list.getSelectedValue();
				emp.setHours(0);
				totalHoursLabel.setText("0.00");
				grossPayLabel.setText("0.00");
				hoursTextField.setText("0.00");
				empNameLabel.setText("");
				emp.setId(0);
				hoursTextField.requestFocus();
				list.clearSelection();
				addButton.setEnabled(false);
				clearHoursButton.setEnabled(false);
				btnUpdate.setEnabled(false);
			}
		});
		btnClearAll.setBounds(202, 310, 89, 23);
		contentPane.add(btnClearAll);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setEnabled(false);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				double payrate = Double.parseDouble(payRateLabel.getText());
				double hours = Double.parseDouble(hoursTextField.getText());
				String name = (empNameLabel.getText());
				int id = Integer.parseInt(empIdLabel.getText());
				Payroll emp = (Payroll) list.getSelectedValue();
				if (!emp.setId(id)) {
					JOptionPane.showMessageDialog(null, "Invalid employee ID. \nMust be > 100");
					empIdLabel.setText(Integer.toString(emp.getId()));
					empIdLabel.requestFocus();
				}
				else{
				if (!emp.setName(name)) {
					JOptionPane.showMessageDialog(null, "You must enter a name");
					empNameLabel.setText(name);
					empNameLabel.requestFocus();
				}
				else{
				if (!emp.setPayrate(payrate)) {
					JOptionPane.showMessageDialog(null, "That's absurd! \nMust be > 7.25 or < 100");
					payRateLabel.setText(Double.toString(emp.getPayrate()));
					payRateLabel.requestFocus();
				}
				else{
				if (!emp.setHours(hours)) {
//					JOptionPane.showMessageDialog(null, "That's a crime! \nMust be > .01 or < 20");
					hoursTextField.setText("0.00");
					hoursTextField.requestFocus();
				}
				list.repaint();
			}}}}
		});
		btnUpdate.setBounds(301, 310, 89, 23);
		contentPane.add(btnUpdate);
	}
}
