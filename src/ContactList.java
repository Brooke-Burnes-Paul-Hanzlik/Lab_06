import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.*;

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
		ObjectOutputStream o;
		
		try {
			o = new ObjectOutputStream(new FileOutputStream(name));
			for(int i = 0; i < contacts; i++)
			{
				o.writeObject(get(i));
			}
			
			o.close();
		}
		catch(IOException e) {
			System.out.println("File write problem to fix");
			return false;
		}
		
		return true;
	}
	
	public boolean read(String name) {
		ObjectInputStream i;
		
		boolean next = true;
		
		try {
			i = new ObjectInputStream(new FileInputStream(name));
			// keep reading files till EOFException
			while(next)
			{
				add((Contact) i.readObject());
			}
			
			i.close();
		}
		catch (EOFException e)
		{
			System.out.println("End of File read");
			next = false;
		}
		catch (IOException e)
		{
			System.out.println("IO problem to fix");
			return false;
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Class not found");
			return false;
		}
		return true;
	}
	
	public boolean addInOrder(Contact contact)
	{
		// If no room for another contact, return false
		if(contacts >= 20)
		{
			return false;
		}
		
		// Find index where it needs to be added
		int i = 0;
		while(contact.toString().compareTo(get(i).toString()) == 1
			&& i < contacts)
		{
			i++;
		}
		
		
		//move contacts ahead of the new contact into an array
		Contact tmp[] = new Contact[contacts - i];
		
		for(int x = i; x < contacts; x++)
		{
			tmp[x] = get(x + i);
		}
		
		// remove contacts ahead of new contact
		while(contacts >= i)
		{
			remove();
		}
		
		// add new contact and stored contacts
		add(contact);
		
		for(Contact con : tmp)
		{
			add(con);
		}
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

