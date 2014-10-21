import java.io.Serializable;

public class Contact implements Serializable {

	private String name = "";
	private long phone = 0;
	private String address = "";
	private String comments = "";
	
	//Constructor that takes in all four variable of the Contact class
	public Contact(String n, long p, String a, String c) {
		name = n;
		phone = p;
		address = a;
		comments = c;
		
	}
	
	public String getName() {
		return name;
	}
	
	public long getPhone() {
		return phone;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getComments() {
		return comments;
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	public void setPhone(long newPhone) {
		phone = newPhone;
	}
	
	public void setAddress(String newAddress) {
		address = newAddress;
	}
	
	public void setComments(String newComments) {
		comments = newComments;
	}
	
	public String toString() {
		return getName() + ", " + getPhone() + ", " + getAddress() + ", " + getComments();
	}
	//Compares two instances of the Contact class
	//returns true is those instances contain the same variables
	//returns false if any of the variable are not the same
	
	public boolean equals(Contact c) {

		if(c.getName().equals(getName()) 
				&& c.getPhone() == getPhone() 
				&& c.getAddress().equals(getAddress())
				&& c.getComments().equals(getComments())) {
			return true;
		}
		return false;
		
	}
	
	public static void main(String[] args) {
		
		Contact newContact = new Contact("",0L,"","");
		newContact.setName("Bob");
		newContact.setPhone(1234567654L);
		newContact.setAddress("123 ABC Street");
		newContact.setComments("test contact");
		
		System.out.println("The contact's information:");
		System.out.println(newContact.toString());
	}
}
