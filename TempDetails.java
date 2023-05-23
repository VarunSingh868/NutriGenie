
public class TempDetails {
public static String name, age, height, weight, email, str, goal;
public void setDetails(String name, String age, String height, String weight, String email,String goal){
	this.name = name;
	this.age = age;
	this.height = height;
	this.weight = weight;
	this.email = email;
	this.goal = goal;
}
public String sendDetails() {
	String str = "Name: " + name + "\nAge: " + age + "\nHeight: " + height + "\nWeight: " + weight +  "\nEmail: " + email;
	return str;
}
public String getEmail() {
	return email;
}

public String getGoal() {
	return goal;
}
public String getName() {
	return name;
}
public int getBMI() {
	int w = Integer.parseInt(weight);
	double h = Double.parseDouble(height);
	h = h/100;
	int bmi = (int)(w/(h*h));
	return bmi;
	}
public int getAge() {
	return Integer.parseInt(age);
}
}
