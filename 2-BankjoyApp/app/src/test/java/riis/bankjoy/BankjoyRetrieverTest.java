package riis.bankjoy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class BankjoyRetrieverTest
{
    private static final String TEST_USERNAME = "roger";
    private static final String TEST_PASSWORD = "gostate";

    private static final String INVALID_LOGIN_RESPONSE = "token and things";
    private static final String VALID_LOGIN_RESPONSE = "{ \"dataValue\": { \"token\": \"abc123\" } }";

    private static final String LIST_RESPONSE = "{ \"dataValue\": [ { \"id\": \"1\", \"name\": \"John Doe\" } ] }";

    private BankjoyRetriever mBankJoyRetriever;

    @Before
    public void setUp()
    {
        mBankJoyRetriever = Mockito.mock(BankjoyRetriever.class);
    }

    @Test
    public void InvalidLoginTest()
    {
        Mockito.when(mBankJoyRetriever.login(TEST_USERNAME, TEST_PASSWORD)).thenReturn(INVALID_LOGIN_RESPONSE);

        String response = mBankJoyRetriever.login(TEST_USERNAME, TEST_PASSWORD);
        String token = Util.parseToken(response);
        assertTrue(token.isEmpty());
    }

    @Test
    public void ValidLoginTest()
    {
        Mockito.when(mBankJoyRetriever.login(TEST_USERNAME, TEST_PASSWORD)).thenReturn(VALID_LOGIN_RESPONSE);

        String response = mBankJoyRetriever.login(TEST_USERNAME, TEST_PASSWORD);
        String token = Util.parseToken(response);
        assertFalse(token.isEmpty());
    }

    @Test
    public void GetPayeeListTest()
    {
        Mockito.when(mBankJoyRetriever.login(TEST_USERNAME, TEST_PASSWORD)).thenReturn(VALID_LOGIN_RESPONSE);
        String response = mBankJoyRetriever.login(TEST_USERNAME, TEST_PASSWORD);
        String token = Util.parseToken(response);

        Mockito.when(mBankJoyRetriever.getPayeeList(token)).thenReturn(LIST_RESPONSE);

        String listResponse = mBankJoyRetriever.getPayeeList(token);
        ArrayList<Payee> payeeList = Util.parsePayeeList(listResponse);
        assertFalse(payeeList.isEmpty());

        assertEquals(1L, payeeList.get(0).getId());
        assertEquals("John Doe", payeeList.get(0).getName());
    }
}