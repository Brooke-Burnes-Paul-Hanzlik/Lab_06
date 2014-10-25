import java.io.Serializable;

public class NewContact extends Contact implements Serializable
{
	private String email = "";
	private String group = "";
	private String quickRef = "";
	
	public NewContact(String name, long phone, String address, String comments, String email, String group, String quickRef)
	{
		super(name, phone, address, comments);
		setEmail(email);
		setGroup(group);
		setQuickRef(quickRef);
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public String getGroup()
	{
		return group;
	}
	
	public String getQuickRef()
	{
		return quickRef;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public void setGroup(String group)
	{
		this.group = group;
	}
	
	public void setQuickRef(String quickRef)
	{
		this.quickRef = quickRef;
	}
	
	public String toString()
	{
		String ret = "";
		ret += super.toString();
		ret += ", " + getEmail() + ", " + getGroup() + ", " + getQuickRef();
		
		return ret;
	}
	
	public boolean equals(NewContact contact)
	{
		return super.equals(contact) && 
			getEmail().equals(contact.getEmail()) &&
			getQuickRef().equals(contact.getQuickRef()) &&
			getGroup().equals(contact.getGroup());
	}
	
}

