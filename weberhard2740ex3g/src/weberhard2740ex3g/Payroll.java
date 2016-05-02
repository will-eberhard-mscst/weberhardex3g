package weberhard2740ex3g;
import java.text.DecimalFormat;

public class Payroll {
	private int id;
	private String name;
	private double payrate;
	private double hours;
	
	public Payroll(int id, String name, double payrate) {
		super();
		this.id = id;
		this.name = name;
		this.payrate = payrate;
		
		
	}
	public Payroll(int id, String name, double payrate, double hours) {
		super();
		this.id = id;
		this.name = name;
		this.payrate = payrate;
		this.hours = hours;
		
	}

	public int getId() {
		return id;
	}

	public boolean setId(int id) {
		if (id > 100) {
			this.id = id;
			return true;
		}
		else {
			return false;
		}
	}
	public String getName() {
		return name;
	}

	public boolean setName(String name) {
		if(name.isEmpty())
			return false;
		else{
		this.name = name;
		return true;
		}
	}

	public double getPayrate() {
		return payrate;
	}

	public boolean setPayrate(double payrate) {
		if(payrate > 7.25 && payrate < 100){
		this.payrate = payrate;
		return true;
		}
		else{
			return false;
		}
	}

	public double getHours() {
		return hours;
	}

	public boolean setHours(double hours) {
		if(hours > .01 && hours < 20){
		this.hours = 0;
		return true;
	}
		else{
			return false;
		}
	}

	@Override
	public String toString() {
		return id + " " + name + ", payrate: " + payrate;
	}
	
	public double getGrossPay()
	{
		double grossPay;
		double overtimePay;
		
		if (hours > 40)
		{
			grossPay = 40 * payrate;
			overtimePay = (hours - 40) * (payrate *1.5);
			grossPay += overtimePay;
		}
		else
		{
			grossPay = payrate * hours;
		}
		return grossPay;
	}
	public void addHours(double hours){
		DecimalFormat hoursFmt = new DecimalFormat("#.00");
		hoursFmt.format(this.hours = hours + this.hours);
	}
	
/*
	public boolean setId(int id) {
		if (id > 100) {
			this.id = id;
			return true;
		}
		else {
			return false;
		}
	}
 */

}
