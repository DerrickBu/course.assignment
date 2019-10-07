import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class TestAddressBook {

  private static AddressBook ADDRESS_BOOK1;

  private static AddressBook ADDRESS_BOOK2;

  private static AddressBook ADDRESS_BOOK3;

  private static final List<Contact> CONTACT_LIST =
      Collections.singletonList(TestUtil.CONTACT_CASE1);

  @Before
  public void createAddressBooks() {
    ADDRESS_BOOK1 = new AddressBook();
    ADDRESS_BOOK2 = new AddressBook(Collections.singletonList(TestUtil.CONTACT_CASE1));
    ADDRESS_BOOK3 = new AddressBook(Arrays.asList(TestUtil.CONTACT_CASE1, TestUtil.CONTACT_CASE2));
  }

  @Test
  public void testAddContact() {
    List<Contact> contactList = ADDRESS_BOOK1.addContacts(CONTACT_LIST);
    assertEquals(ADDRESS_BOOK1.getContactNumber(), 1);
    assertEquals(contactList, CONTACT_LIST);
  }

  @Test
  public void testRemoveContact() {
    assertEquals(ADDRESS_BOOK2.removeContacts(TestUtil.CONTACT_CASE1), new ArrayList<>());
    assertEquals(ADDRESS_BOOK2.getContactNumber(), 0);
  }

  @Test
  public void testGetContacts() {
    assertEquals(ADDRESS_BOOK2.getContacts(), CONTACT_LIST);
  }

  @Test
  public void testSortContactByFirstName() {
    assertEquals(
        ADDRESS_BOOK3.sortContactsByFirstName(),
        Arrays.asList(TestUtil.CONTACT_CASE2, TestUtil.CONTACT_CASE1));
  }

  @Test
  public void testSortContactByLastName() {
    assertEquals(
        ADDRESS_BOOK3.sortContactsByLastName(),
        Arrays.asList(TestUtil.CONTACT_CASE1, TestUtil.CONTACT_CASE2));
  }

  @Test
  public void testSearchContactByFirstName() {
    assertEquals(ADDRESS_BOOK3.searchContactByFirstName(TestUtil.FIRST_NAME1), CONTACT_LIST);
    assertEquals(ADDRESS_BOOK3.searchContactByLastName(TestUtil.WRONG_NAME), new ArrayList<>());
  }

  @Test
  public void testSearchContactByLastName() {
    assertEquals(ADDRESS_BOOK3.searchContactByLastName(TestUtil.LAST_NAME1), CONTACT_LIST);
    assertEquals(ADDRESS_BOOK3.searchContactByLastName(TestUtil.WRONG_NAME), new ArrayList<>());
  }

  @Test
  public void testEditContact() {
    assertEquals(
        ADDRESS_BOOK2.editContact(
            TestUtil.CONTACT_CASE1,
            TestUtil.FIRST_NAME2,
            TestUtil.LAST_NAME2,
            null,
            null,
            null,
            null),
        TestUtil.CONTACT_CASE2);
    ADDRESS_BOOK2.editContact(
        TestUtil.CONTACT_CASE1, TestUtil.FIRST_NAME1, TestUtil.LAST_NAME1, null, null, null, null);
  }
}
