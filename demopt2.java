import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.*;

class JDBC{
	public String goal;
	public static String  table = "";
	public static String ageGr = "";
	public String carbs = "";
	public String proveg = "";
	public String pronveg = "";
	public String fats = "";
	int age,bmi;
	public JDBC(int bmi, int age, String goal) {
		this.age = age;
		this.bmi = bmi;
		this.goal = goal;
	}
	public boolean setValues() {
		boolean flag = true;
		if(bmi < 19 && goal.equals("Weight Loss")) {
			return false;
		}
		else if(bmi > 26 && goal.equals("Weight Gain")) {
			return false;
		}
		else if(goal.equals("Muscle Gain")) {
			System.out.println(goal);
			table += "muscleGain";
			if(age < 26) {
				ageGr += "15-25";				
			}
			else if(age < 51) {
				ageGr += "26-50";
			}
			else {
				ageGr += "51 and above";
			}
		}
		else if(goal.equals("Weight Gain")) {
			System.out.println(goal);
			table += "weightGain";
			if(age < 26) {
				ageGr += "15-25";				
			}
			else if(age < 51) {
				ageGr += "26-50";
			}
			else {
				ageGr += "51 and above";
			}
		}
		else if(goal.equals("Weight Loss")) {
			System.out.println(goal);
			table += "weightLoss";
			if(age < 26) {
				ageGr += "15-25";				
			}
			else if(age < 51) {
				ageGr += "26-50";
			}
			else {
				ageGr += "51 and above";
			}
		}
		System.out.println(table + "  " + ageGr);
		return flag;
	}
	public String getcarbs() {
		 try {
	            Class.forName("com.mysql.jdbc.Driver");
	            String url = "jdbc:mysql://localhost/nutrigenie";
	            String username = "root";
	            String password = "Varun54";
	            Connection connection = DriverManager.getConnection(url, username, password);
	            Statement st = connection.createStatement();
	            ResultSet rs = st.executeQuery("Select * from " + table + " where type='carbohydrates' and agegroup='" + ageGr + "'");
	            while(rs.next()) {
	            	carbs += rs.getString(3) + "\n";	            	
	            }
	            connection.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("In catch block");
	        }
		 return carbs;
	}
	public String getProveg() {
		try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/nutrigenie";
            String username = "root";
            String password = "Varun54";
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select * from " + table + " where type='proteins(veg)' and agegroup='" + ageGr + "'");
            while(rs.next()) {
            	proveg += rs.getString(3) + "\n";
            	
            }
            connection.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("In catch block");
        }
	 return proveg;
	}
	public String getPronveg() {
		try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/nutrigenie";
            String username = "root";
            String password = "Varun54";
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select * from " + table + " where type='proteins(non-veg)' and agegroup='" + ageGr + "'");
            while(rs.next()) {
            	pronveg += rs.getString(3) + "\n";
            	
            }
            connection.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("In catch block");
        }
	 return pronveg;
	}
	public String getFats() {
		try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost/nutrigenie";
            String username = "root";
            String password = "Varun54";
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select * from " + table + " where type='proteins(veg)' and agegroup='" + ageGr + "'");
            while(rs.next()) {
            	fats += rs.getString(3) + "\n";
            	
            }
            connection.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("In catch block");
        }
	 return fats;
	}
}

@WebServlet("/demopt2")
public class demopt2 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public demopt2() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();
        String OTP = request.getParameter("otp");
        String check = new SendTempEmail().retOTP();
        String details = new TempDetails().sendDetails();
        String to = new TempDetails().getEmail();
        int bmi = new TempDetails().getBMI();
        String goal = new TempDetails().getGoal();
        int age = new TempDetails().getAge();
        String name = new TempDetails().getName();
        JDBC temp = new JDBC(bmi,age,goal);
        boolean res = temp.setValues();
        if (OTP.equals(check)) {
        	pw.println(details);
        	pw.println("\nYou Current BMI is: " + bmi);
            if(res) {
            	String carbs = temp.getcarbs();
            	String proveg = temp.getProveg();
            	String pronveg = temp.getPronveg();
            	String fats = temp.getFats();
            	pw.println("\nDiet plan based on your current BMI for " + goal);
            	pw.println("\n*****CARBOHYDRATES*****");
            	pw.println(carbs);
            	pw.println("\n*****PROTEINS(for Veg)*****");
            	pw.println(proveg);
            	pw.println("\n*****PROTEINS(for Non-veg)*****");
            	pw.println(pronveg);
            	pw.println("\n*****FATS*****");
            	pw.println(fats);
            	pw.println();
            	String send = "Dear " + name + ",\nYour Diet Plan for " + goal + ":\n\nCARBOHYDRATES:\n" + carbs +"\nPROTEINS(for Veg):\n" + proveg + "\nPROTEINS(for Non-Veg):\n" + pronveg + "\nFATS:\n" + fats + "\nThank You"; 
            	new SenEmailDetails().sendUserDetails(to, send);
            	pw.println("\n\n\n\n\nNote: Details have been sent to mailbox also");
            }
            else {
            	pw.println("Based on your BMI " + goal + "isn't recommended for you");
            	pw.println(res);
            }
        } else {
            response.sendRedirect("NewFile.jsp");
        }
        pw.close();
    }
}
