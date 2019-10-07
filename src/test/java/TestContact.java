import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestContact {

  @Test
  public void testCompareOnLastName() {
    int actualResult = Contact.compareOnLastName(TestUtil.CONTACT_CASE2, TestUtil.CONTACT_CASE1);
    assertTrue(actualResult > 0);
  }
}
