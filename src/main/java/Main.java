import java.io.IOException;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) {
    Contact contact1 =
        new Contact.Builder("Kobe")
            .setLastName("Bryant")
            .setEmailAddress("kobeb@nba.com")
            .setPhoneNumber("7861982745")
            .setPostalAddresss("California")
            .build();

    Contact contact2 =
        new Contact.Builder("Jiacheng")
            .setLastName("Li")
            .setEmailAddress("jcl123@jlu.edu")
            .setNote("Best friend")
            .build();

    System.out.println("Initially, we have 0 contact in our address book");
    AddressBook addressBook = new AddressBook();
    System.out.println(addressBook.getContacts());

    try {
      addressBook.readAddressBookFromFile("/Users/derrickbu/Downloads/addressbooktest.txt");
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("After reading contacts, we have: ");
    System.out.println(addressBook.getContacts());
    System.out.println("Right now the number of contacts is: " + addressBook.getContactNumber());

    System.out.println("After adding contacts, we have: ");
    System.out.println(addressBook.addContacts(Arrays.asList(contact2)));
    System.out.println("Right now the number of contacts is: " + addressBook.getContactNumber());

    System.out.println("After sorting contacts by first name, we have: ");
    System.out.println(addressBook.sortContactsByFirstName());

    System.out.println("After sorting contacts by last name, we have: ");
    System.out.println(addressBook.sortContactsByLastName());

    System.out.println("After searching contacts using phone number, we have: ");
    System.out.println(addressBook.searchContactByPhoneNumber("7861982745"));

    System.out.println("After changing email address of kobe, we have: ");
    System.out.println(
        addressBook.editContact(contact1, null, null, null, null, "kobeb@gmail.com", null));

    System.out.println("After removing contact Jiacheng, we have: ");
    System.out.println(addressBook.removeContacts(contact2));
    System.out.println("Right now the number of contacts is: " + addressBook.getContactNumber());

    try {
      addressBook.saveAddressBookToFile("/Users/derrickbu/Downloads/addressbookoutput.txt");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
