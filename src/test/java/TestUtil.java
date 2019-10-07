public class TestUtil {

  public static final String FIRST_NAME1 = "Shuaiyi";
  public static final String LAST_NAME1 = "Bu";
  public static final String PHONE_NUMBER = "9175789012";
  public static final String EMAIL_ADDRESS = "sb666@nyu.edu";
  public static final String FIRST_NAME2 = "Michael";
  public static final String LAST_NAME2 = "Schidlowsky";
  public static final String WRONG_NAME = "foo";

  public static final Contact CONTACT_CASE1 =
      new Contact.Builder(FIRST_NAME1)
          .setLastName(LAST_NAME1)
          .setPhoneNumber(PHONE_NUMBER)
          .setEmailAddress(EMAIL_ADDRESS)
          .build();

  public static final Contact CONTACT_CASE2 =
      new Contact.Builder(FIRST_NAME2)
          .setLastName(LAST_NAME2)
          .setPhoneNumber(PHONE_NUMBER)
          .setEmailAddress(EMAIL_ADDRESS)
          .build();
}
