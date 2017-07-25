package thinkers.united.junittesttraining;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import javax.net.ssl.HttpsURLConnection;
import org.databene.benerator.anno.InvocationCount;
import org.databene.benerator.anno.Source;
import org.databene.feed4junit.Feeder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.runner.RunWith;


@RunWith(Feeder.class)   //Use Feed4JUnit lib
public class CSCTest {
    
    private static final String url = "XXX";
    private static final String userName = "test_api_user";
    private static final String password = "XXX";
    private static final String cscResponseCodeM = "cscResponseCode=M";
    private static final String cscResponseCodeN = "cscResponseCode=N";    
    private static final String cscResponseCodeP = "cscResponseCode=P";
    private static final String cscResponseCodeS = "cscResponseCode=S";
    private static String actualCSCResponseCode = " ";
    private static HttpsURLConnection conn;
    private static String params;    
    private static byte[] buffer;
    
    private OutputStreamWriter osw;
    private InputStream inputStream;
    private ByteArrayOutputStream rsByteArrayOutputStream;
    
    public CSCTest() {
    }   
   
    @BeforeClass
    public static void setUpClass() {        
        buffer = new byte[4096];   // allocate some memory for the static array
    }
        
    @Before
    public void setUp() throws Exception {
        conn = (HttpsURLConnection) new URL(url) // connecting to the host
                        .openConnection();
        conn.setDoInput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setDoOutput(true);
        conn.setUseCaches(false);                
    }
    
    @After
    public void endUp() throws IOException {        
       conn.disconnect(); //closing the connection and the I/O streams to free the memory
       inputStream.close();  
       rsByteArrayOutputStream.flush();
       rsByteArrayOutputStream.close();
    }
    
    @AfterClass
    public static void endUpClass() {
        buffer = null;  //free the memory
    }
        
    @Test
    @Source("CSCVerificationAccountNumberM.xls")
    @InvocationCount(1)   // Equals to an amount of rows in the data file, excluding the first (header) row.
    public void testCSCResponseCodeM(
            String requestType,            
            String accountId,
            String amount,
            String accountType,
            String transactionIndustryType,
            String holderType,
            String holderName,
            String accountNumber,
            String accountAccessory,
            String street,
            String city,
            String state,
            String zipCode,
            String customerAccountCode,
            String transactionCode,
            String csc 
    ) throws  IOException {       
        params = "requestType=" + requestType
                + "&userName=" + userName
                + "&password=" + password
                + "&accountId=" + accountId
                + "&amount=" + amount
                + "&accountType=" + accountType
                + "&transactionIndustryType=" + transactionIndustryType
                + "&holderType=" + holderType
                + "&holderName=" + holderName
                + "&accountNumber=" + accountNumber
                + "&accountAccessory=" + accountAccessory
                + "&street=" + street
                + "&city=" + city
                + "&state=" + state
                + "&zipCode=" + zipCode
                + "&customerAccountCode=" + customerAccountCode
                + "&transactionCode=" + transactionCode
                + "&csc=" + csc;                  
        conn.setRequestProperty("Content-Length", "" + Integer.toString(params.getBytes().length));
        osw = new OutputStreamWriter(conn.getOutputStream());        
        osw.write(params);
        osw.flush();
        osw.close();
        inputStream = conn.getInputStream(); 
        rsByteArrayOutputStream = new ByteArrayOutputStream();        
        int length = 0;
        while ((length = inputStream.read(buffer)) != -1) { 
            rsByteArrayOutputStream.write(buffer, 0, length);
        }
        actualCSCResponseCode = new String(rsByteArrayOutputStream.toByteArray());        
        assertEquals(cscResponseCodeM, 
                actualCSCResponseCode.substring(actualCSCResponseCode.indexOf("cscResponseCode"), 
                        actualCSCResponseCode.indexOf("cscResponseCode=M") + 17));
    }
    
    @Test
    @Source("CSCVerificationAccountNumberN.xls")
    @InvocationCount(1)   // Equals to an amount of rows in the data file, excluding the first (header) row.
    public void testCSCResponseCodeN(
            String requestType,            
            String accountId,
            String amount,
            String accountType,
            String transactionIndustryType,
            String holderType,
            String holderName,
            String accountNumber,
            String accountAccessory,
            String street,
            String city,
            String state,
            String zipCode,
            String customerAccountCode,
            String transactionCode,
            String csc 
    ) throws MalformedURLException, IOException {       
        params = "requestType=" + requestType
                + "&userName=" + userName
                + "&password=" + password
                + "&accountId=" + accountId
                + "&amount=" + amount
                + "&accountType=" + accountType
                + "&transactionIndustryType=" + transactionIndustryType
                + "&holderType=" + holderType
                + "&holderName=" + holderName
                + "&accountNumber=" + accountNumber
                + "&accountAccessory=" + accountAccessory
                + "&street=" + street
                + "&city=" + city
                + "&state=" + state
                + "&zipCode=" + zipCode
                + "&customerAccountCode=" + customerAccountCode
                + "&transactionCode=" + transactionCode
                + "&csc=" + csc;                  
        conn.setRequestProperty("Content-Length", "" + Integer.toString(params.getBytes().length));
        osw = new OutputStreamWriter(conn.getOutputStream());        
        osw.write(params);
        osw.flush();
        osw.close();
        inputStream = conn.getInputStream(); 
        rsByteArrayOutputStream = new ByteArrayOutputStream();        
        int length = 0;
        while ((length = inputStream.read(buffer)) != -1) { 
            rsByteArrayOutputStream.write(buffer, 0, length);
        }
        actualCSCResponseCode = new String(rsByteArrayOutputStream.toByteArray());        
        assertEquals(cscResponseCodeN, 
                actualCSCResponseCode.substring(actualCSCResponseCode.indexOf("cscResponseCode"), 
                        actualCSCResponseCode.indexOf("cscResponseCode=N") + 17));
    }
    
    @Test
    @Source("CSCVerification_TrackData_P.xls")
    @InvocationCount(1)   // Equals to an amount of rows in the data file, excluding the first (header) row.
    public void testCSCResponseCodeP( 
            String requestType,            
            String terminalId,
            String accountId,
            String transactionIndustryType,
            String amount,
            String accountType,
            String accountData,
            String customerAccountCode,
            String transactionCode,
            String holderName,
            String csc
    ) throws MalformedURLException, IOException {        
        params = "requestType=" + requestType
                + "&userName=" + userName
                + "&password=" + password
                + "&terminalId=" + terminalId
                + "&accountId=" + accountId
                + "&transactionIndustryType=" + transactionIndustryType
                + "&amount=" + amount
                + "&accountType=" + accountType
                + "&accountData=" + URLEncoder.encode(accountData, java.nio.charset.StandardCharsets.UTF_8.toString())
                + "&customerAccountCode=" + customerAccountCode
                + "&transactionCode=" + transactionCode
                + "&holderName=" + holderName
                + "&csc=" + csc;                   
        conn.setRequestProperty("Content-Length", "" + Integer.toString(params.getBytes().length));
        osw = new OutputStreamWriter(conn.getOutputStream());        
        osw.write(params);
        osw.flush();
        osw.close();
        inputStream = conn.getInputStream(); 
        rsByteArrayOutputStream = new ByteArrayOutputStream();       
        int length = 0;
        while ((length = inputStream.read(buffer)) != -1) { 
            rsByteArrayOutputStream.write(buffer, 0, length);
        }
        actualCSCResponseCode = new String(rsByteArrayOutputStream.toByteArray());        
        assertEquals(cscResponseCodeP, 
                actualCSCResponseCode.substring(actualCSCResponseCode.indexOf("cscResponseCode"), 
                        actualCSCResponseCode.indexOf("cscResponseCode=P") + 17));
    }
    
    
    @Test
    @Source("CSCVerificationTrackDataS.xls")
    @InvocationCount(1)   // Equals to an amount of rows in the data file, excluding the first (header) row.
    public void testCSCResponseCodeS( 
            String requestType,            
            String terminalId,
            String accountId,
            String transactionIndustryType,
            String amount,
            String accountType,
            String accountData,
            String customerAccountCode,
            String transactionCode,
            String holderName,
            String csc
    ) throws MalformedURLException, IOException {        
        params = "requestType=" + requestType
                + "&userName=" + userName
                + "&password=" + password
                + "&terminalId=" + terminalId
                + "&accountId=" + accountId
                + "&transactionIndustryType=" + transactionIndustryType
                + "&amount=" + amount
                + "&accountType=" + accountType
                + "&accountData=" + URLEncoder.encode(accountData, java.nio.charset.StandardCharsets.UTF_8.toString())
                + "&customerAccountCode=" + customerAccountCode
                + "&transactionCode=" + transactionCode
                + "&holderName=" + holderName
                + "&csc=" + csc;                  
        conn.setRequestProperty("Content-Length", "" + Integer.toString(params.getBytes().length));
        osw = new OutputStreamWriter(conn.getOutputStream());        
        osw.write(params);
        osw.flush();
        osw.close();
        inputStream = conn.getInputStream(); 
        rsByteArrayOutputStream = new ByteArrayOutputStream();        
        int length = 0;
        while ((length = inputStream.read(buffer)) != -1) { 
            rsByteArrayOutputStream.write(buffer, 0, length);
        }
        actualCSCResponseCode = new String(rsByteArrayOutputStream.toByteArray());        
        assertEquals(cscResponseCodeS, 
                actualCSCResponseCode.substring(actualCSCResponseCode.indexOf("cscResponseCode"), 
                        actualCSCResponseCode.indexOf("cscResponseCode=S") + 17));
    }
    
}
