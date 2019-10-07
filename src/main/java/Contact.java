import com.google.common.base.Preconditions;
import java.util.Comparator;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact implements Comparable<Contact> {

  private String firstName;
  private String lastName;
  private String phoneNumber;
  private String postalAddress;
  private String emailAddress;
  private String note;

  private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
      Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

  private static final Comparator<String> strcmp = Comparator.nullsFirst(Comparator.naturalOrder());

  private static final Comparator<Contact> FIRST_NAME_COMPARATOR =
      Comparator.comparing(Contact::getFirstName)
          .thenComparing(Contact::getLastName, strcmp)
          .thenComparing(Contact::getPhoneNumber, strcmp)
          .thenComparing(Contact::getPostalAddress, strcmp)
          .thenComparing(Contact::getEmailAddress, strcmp)
          .thenComparing(Contact::getNote, strcmp);

  private static final Comparator<Contact> LAST_NAME_COMPARATOR =
      Comparator.comparing(Contact::getLastName, strcmp)
          .thenComparing(c -> c.firstName)
          .thenComparing(Contact::getPhoneNumber, strcmp)
          .thenComparing(Contact::getPostalAddress, strcmp)
          .thenComparing(Contact::getEmailAddress, strcmp)
          .thenComparing(Contact::getNote, strcmp);

  public static class Builder {

    public static final String NULL = "null";
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String postalAddress;
    private String emailAddress;
    private String note;

    public Builder(String firstName) {
      this.firstName = firstName;
    }

    public Builder setLastName(String lastName) {
      this.lastName = lastName.equals(NULL) ? null : lastName;
      return this;
    }

    public Builder setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber.equals(NULL) ? null : phoneNumber;
      return this;
    }

    public Builder setPostalAddresss(String postalAddress) {
      this.postalAddress = postalAddress.equals(NULL) ? null : postalAddress;
      return this;
    }

    public Builder setEmailAddress(String emailAddress) {
      this.emailAddress = emailAddress.equals(NULL) ? null : emailAddress;
      return this;
    }

    public Builder setNote(String note) {
      this.note = note.equals(NULL) ? null : note;
      return this;
    }

    public Contact build() {
      return new Contact(this);
    }
  }

  private Contact(Builder builder) {
    this.firstName = builder.firstName;
    this.lastName = builder.lastName;
    this.phoneNumber = builder.phoneNumber;
    this.postalAddress = builder.postalAddress;
    this.emailAddress = builder.emailAddress;
    this.note = builder.note;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  public void setPostalAddress(String postalAddress) {
    this.postalAddress = postalAddress;
  }

  public String getPostalAddress() {
    return this.postalAddress;
  }

  public String getNote() {
    return this.note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public void setEmailAddress(String emailAddress) {
    Preconditions.checkArgument(
        isValidEmailAddress(emailAddress), "Should give a valid email address");
    this.emailAddress = emailAddress;
  }

  public String getEmailAddress() {
    return this.emailAddress;
  }

  private static boolean isValidEmailAddress(String emailAddress) {
    Preconditions.checkNotNull(emailAddress, "Email address cannot be null");
    Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailAddress);
    return matcher.find();
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Contact)) {
      return false;
    }
    Contact c = (Contact) o;
    return Objects.equals(c.firstName, firstName)
        && Objects.equals(c.lastName, lastName)
        && Objects.equals(c.phoneNumber, phoneNumber)
        && Objects.equals(c.postalAddress, postalAddress)
        && Objects.equals(c.emailAddress, emailAddress)
        && Objects.equals(c.note, note);
  }

  @Override
  public String toString() {
    return String.format(
        "Contact: firstName: %s, lastName: %s, "
            + "phoneNumber: %s, postalAddress: %s, emailAddress: %s, "
            + "note: %s",
        firstName, lastName, phoneNumber, postalAddress, emailAddress, note);
  }

  @Override
  public int compareTo(Contact contact) {
    return FIRST_NAME_COMPARATOR.compare(this, contact);
  }

  /**
   * Compare function which compare contacts by their last name
   *
   * @param contact1 to compare
   * @param contact2 to compare
   * @return 1 means contact1 is greater than contact2. 0 means equal and -1 means contact1 is
   *     smaller
   */
  public static int compareOnLastName(Contact contact1, Contact contact2) {
    return LAST_NAME_COMPARATOR.compare(contact1, contact2);
  }
}
