import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;

public class ContactList {

	int ptr = -1;
	int contacts = 0;
	Contact[] contactArray = new Contact[20];
	
	// add contact to end of list, move ptr forward
	public boolean add(Contact c) {
			if(ptr < contactArray.length-1) {
				ptr++;
				contacts++;
				contactArray[ptr] = c;
			}
			else {
				return false;
			}
		return true;
	}
	
	// return first contact containing string in name
	public Contact find(String s) {
		// loop through the length of contactArray search for name
		for(int i=0; i<contacts; i++) {
			// if name is in contact return contact
			if(contains(contactArray[i].getName(),(s))) {
				return contactArray[i];
			}
		}
		return null;
	}
	//Takes in name from contact array and another name
	public boolean contains(String s, String search)
	{
		boolean ret = false;
		for(int i =0; i< s.length(); i++)
		{
			//If the first letters are the same, assume it's true
			if(s.charAt(i) == (search.charAt(0)))
			{
				ret = true;
				//Loop through the rest of the letters
				for(int x = 0; x < search.length(); x++)
				{
					//If the letters don't match at every point, return false
					if(s.charAt(x+i) != search.charAt(x))
					{
						ret = false;
					}
				}
			}
		}
		return ret;
	}
	
	// nullify last contact, move pointer back
	public Contact remove() {
		// nullify last contact
		Contact ret = contactArray[ptr];
		contactArray[ptr] = null;
		ptr--;
		contacts--;
		return ret;
	}
	
	public boolean write(String name) {
		PrintWriter p = null;
		try {
			p = new PrintWriter(new File(name));
		}
		catch (Exception e) { return false;}
		
		for(int z = 0; z < contacts; z++){
		
	p.printf("%s%n%d%n%s%n%s%n", 
					contactArray[z].getName(),
					contactArray[z].getPhone(),
					contactArray[z].getAddress(),
					contactArray[z].getComments());
		}
		
		p.close();
		return true;
		
	}
	
	public boolean read(String b) {
		Scanner scan = null;
		try {
			scan = new Scanner(new File(b));
		}
		catch (Exception e) {return false;}
		
		while(scan.hasNext()) {
			Contact newContact = new Contact("Name", 000, "Address", "Comments");
			newContact.setName(scan.nextLine());
			newContact.setPhone(Long.parseLong(scan.nextLine()));
			newContact.setAddress(scan.nextLine());
			newContact.setComments(scan.nextLine());
			add(newContact);
		}
		scan.close();
		return true;
	}
	
	public Contact getCurrent() {
		if(contacts == 0)
		{
			return null;
		}
		return contactArray[ptr+1];
	}
	
	public Contact get(int loc) {
		for(int i = 0; i < loc; i++)
		{
			next();
		}
		return getCurrent();
	}
	
	public Contact next() {
		ptr ++;
		if(ptr >= contacts)
		{
			ptr = 0;
		}
		return getCurrent();
	}
	
	public Contact previous() {
		ptr --;
		if(ptr < -1)
		{
			ptr = contacts -1;
		}
		return getCurrent();
	}
	
	public static void main(String[] args) {
		Contact contactOne = new Contact("Paul", 123456, "123S t", "Random contact 1");
		Contact contactTwo = new Contact("Brooke", 987654, "XYZS t", "Other Rando");
		ContactList testList = new ContactList();
		testList.add(contactOne);
		testList.add(contactTwo);
		testList.add(contactTwo);
		testList.remove();
		
		System.out.println(testList.previous());
		System.out.println(testList.previous());
		
		System.out.println(testList.write("data.txt"));
		ContactList testListCopy = new ContactList();
		testListCopy.read("data.txt");
		System.out.println(testListCopy.find("P"));
	}
}

