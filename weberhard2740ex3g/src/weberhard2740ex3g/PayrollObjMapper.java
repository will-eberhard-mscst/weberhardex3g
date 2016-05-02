package weberhard2740ex3g;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.DefaultListModel;



public class PayrollObjMapper {
	private String fileName;
	private PrintWriter outputFile;
	private Scanner inputFile;
	private File file;
	
	public PayrollObjMapper(String fileName) {
		super();
		this.fileName = fileName;
	}
	
	public boolean openInputFile(){
		boolean fileOpened = false;
		
		 try {
		    	File file = new File(this.fileName);
				fileOpened = file.exists();
					
				if(fileOpened) {
					// Open the file.
				    this.inputFile = new Scanner(file);
				    
				}
		    }
		    catch (IOException e) {}

		
		return fileOpened;
	}
	public boolean openOutputFile(){
		boolean fileOpened = false;
		try{
			this.outputFile = new PrintWriter(fileName);
			fileOpened = true;
		}
		catch (IOException e){}
		
		return fileOpened;
	}
	public void closeInputFile(){
		if(this.inputFile != null) inputFile.close();
	}
	public void closeOutputFile(){
		
	}
	public Payroll getNextPayroll(){
		Payroll p = null;
		
//		String textLine = inputFile.nextLine();
//		int id = Integer.parseInt(textLine);
//		String name = inputFile.nextLine();
//		textLine = inputFile.nextLine();
//		double payrate = Double.parseDouble(textLine);
//		textLine = inputFile.nextLine();
//		double hours = Double.parseDouble(textLine);
//		p = new Payroll(id, name, payrate, hours);
		
		int id = 0;
		String name = "";
		double payrate = 0.0;
		double hours = 0.0;
		
		try{
			String textLine = inputFile.nextLine();
			id = Integer.parseInt(textLine);
			name = inputFile.nextLine();
			textLine = inputFile.nextLine();
			payrate = Double.parseDouble(textLine);
			textLine = inputFile.nextLine();
			hours = Double.parseDouble(textLine);
			p = new Payroll(id, name, payrate, hours);
		}
		catch(NoSuchElementException e){}
		catch(NumberFormatException e){}
		
		return p;
	}
	public void writePayroll(Payroll payroll){
		if(this.outputFile != null && payroll != null){
		outputFile.println(payroll.getId());
		outputFile.println(payroll.getName());
		outputFile.println(payroll.getPayrate());
		outputFile.println(payroll.getHours());
		}
	}
	public DefaultListModel getAllPayroll(){
		DefaultListModel payrollCollection = new DefaultListModel();
		if(this.openInputFile()){
			while(this.inputFile.hasNext()){
				Payroll p = this.getNextPayroll();
				if(p != null){
					payrollCollection.addElement(p);
				}
		
			}
		}
		this.closeInputFile();
		return payrollCollection;
		
	}
	
	public void writeAllPayroll(DefaultListModel payrollCollection){
		if(this.openOutputFile()){
			payrollCollection.getSize();
			for(int i = 0; i < getSize(); i += 1){
			Payroll p = (Payroll) payrollCollection.get(i);
			this.writePayroll(p);
			}
			
		}
		this.closeOutputFile();
	}

	private int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

}
