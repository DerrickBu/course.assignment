import com.google.common.base.Preconditions;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AddressBook {

  public static final String COMMA = ",";
  public static final String WHITE_SPACE = " ";
  private final List<Contact> contacts;
  private int contactNumber;

  public AddressBook() {
    contacts = new ArrayList<>();
    contactNumber = 0;
  }

  public AddressBook(List<Contact> contactList) {
    contacts = new ArrayList<>();
    contacts.addAll(contactList);
    contactNumber = contactList.size();
  }

  public List<Contact> getContacts() {
    return contacts;
  }

  public int getContactNumber() {
    return contactNumber;
  }

  /**
   * Add contact(s) to the address book. This method supports you adding one or more contacts once a
   * time.
   *
   * @param contacts one or more contacts you want to add
   * @return a list of contacts existing on the address book right
   */
  public List<Contact> addContacts(List<Contact> contacts) {
    Preconditions.checkNotNull(contacts, "Contact list needs to be added cannot be list");
    Preconditions.checkArgument(
        !contacts.isEmpty(), "Contact list needs to be added cannot be empty");
    this.contacts.addAll(contacts);
    contactNumber += contacts.size();
    return this.contacts;
  }

  /**
   * Remove a contact from the address book
   *
   * @param contact that you want to remove from the address book
   * @return a list of contacts existing on the address book
   */
  public List<Contact> removeContacts(Contact contact) {
    Preconditions.checkNotNull(contact, "Should provide a contact to remove");
    contacts.remove(contact);
    contactNumber -= 1;
    return contacts;
  }

  /**
   * Sort contacts of the address book based on their first names
   *
   * @return list of contacts which have been sort base on their first names
   */
  public List<Contact> sortContactsByFirstName() {
    Collections.sort(contacts);
    return contacts;
  }

  /**
   * Sort contacts of the address book based on their last names
   *
   * @return list of contacts which have been sort base on their last names
   */
  public List<Contact> sortContactsByLastName() {
    Collections.sort(contacts, Contact::compareOnLastName);
    return contacts;
  }

  /**
   * Search a contact by its last name
   *
   * @param lastName which you want to search for a contact
   * @return list of contacts whose last name is the same as given
   */
  public List<Contact> searchContactByLastName(String lastName) {
    Preconditions.checkNotNull(lastName, "First name you want to search cannot be null");
    return contacts.stream()
        .filter(contact -> contact.getLastName() != null)
        .filter(contact -> contact.getLastName().equals(lastName))
        .collect(Collectors.toList());
  }

  /**
   * Search a contact by its last name
   *
   * @param phoneNumber which you want to search for a contact
   * @return list of contacts whose phone number is the same as given
   */
  public List<Contact> searchContactByPhoneNumber(String phoneNumber) {
    Preconditions.checkNotNull(phoneNumber, "First name you want to search cannot be null");
    return contacts.stream()
        .filter(contact -> contact.getPhoneNumber() != null)
        .filter(contact -> contact.getPhoneNumber().equals(phoneNumber))
        .collect(Collectors.toList());
  }

  /**
   * Search a contact by its last name
   *
   * @param firstName which you want to search for a contact
   * @return list of contacts whose first name is the same as given
   */
  public List<Contact> searchContactByFirstName(String firstName) {
    Preconditions.checkNotNull(firstName, "First name you want to search cannot be null");
    return contacts.stream()
        .filter(contact -> contact.getFirstName() != null)
        .filter(contact -> contact.getFirstName().equals(firstName))
        .collect(Collectors.toList());
  }

  /**
   * Save current address book to a file, it includes several contacts
   *
   * @param filename you want to sava address book to
   * @throws IOException
   */
  public void saveAddressBookToFile(String filename) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
      for (Contact contact : contacts) {
        writer.write(contact.toString());
        writer.newLine();
      }
    }
  }

  /**
   * read contacts from a file to address book
   *
   * @param filename contacts you want to read from
   * @throws IOException
   */
  public void readAddressBookFromFile(String filename) throws IOException {
    try (Stream<String> stream = Files.lines(Paths.get(filename))) {
      stream.forEach(
          s -> {
            String[] tmpStr = s.split(COMMA);

            // Parsing line from input file, getting fields of a contact
            List<String> contactInfo =
                Arrays.stream(tmpStr)
                    .map(
                        substr -> {
                          String[] strWithoutWhiteSpace = substr.split(WHITE_SPACE);
                          return strWithoutWhiteSpace[strWithoutWhiteSpace.length - 1];
                        })
                    .collect(Collectors.toList());

            contacts.add(
                new Contact.Builder(contactInfo.get(0))
                    .setLastName(contactInfo.get(1))
                    .setPhoneNumber(contactInfo.get(2))
                    .setPostalAddresss(contactInfo.get(3))
                    .setEmailAddress(contactInfo.get(4))
                    .setNote(contactInfo.get(5))
                    .build());
            contactNumber += 1;
          });
    }
  }

  /**
   * Edit a contact based on parameters you give. if you pass null to one parameter, that means you
   * do NOT want to edit that field
   *
   * @param contact you want to edit
   * @param firstName you want to change to, remain as original if null
   * @param lastName you want to change to, remain as original if null
   * @param phoneNumber you want to change to, remain as original if null
   * @param homeAddress you want to change to, remain as original if null
   * @param emailAddress you want to change to, remain as original if null
   * @return a new contact after editing
   */
  public Contact editContact(
      Contact contact,
      String firstName,
      String lastName,
      String phoneNumber,
      String homeAddress,
      String emailAddress,
      String note) {
    if (!contacts.contains(contact)) {
      throw new IllegalArgumentException("Contact you want to edit does not exist");
    }
    int contactIndex = contacts.indexOf(contact);
    Contact editContact = contacts.get(contactIndex);
    editContact.setFirstName(firstName == null ? editContact.getFirstName() : firstName);
    editContact.setLastName(lastName == null ? editContact.getLastName() : lastName);
    editContact.setPhoneNumber(phoneNumber == null ? editContact.getPhoneNumber() : phoneNumber);
    editContact.setNote(note == null ? editContact.getNote() : note);
    editContact.setPostalAddress(
        homeAddress == null ? editContact.getPostalAddress() : homeAddress);
    editContact.setEmailAddress(
        emailAddress == null ? editContact.getEmailAddress() : emailAddress);
    return editContact;
  }
}
