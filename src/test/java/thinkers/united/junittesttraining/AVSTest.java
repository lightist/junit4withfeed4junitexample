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
public class AVSTest {
    
    private static final String url = "XXX";
    private static final String userName = "test_api_user";
    private static final String password = "XXX";
    private static final String avsResponseCode00 = "avsResponseCode=00";
    private static final String avsResponseCode46 = "avsResponseCode=46";    
    private static final String avsResponseCode43 = "avsResponseCode=43";
    private static final String avsResponseCode40 = "avsResponseCode=40";
    private static String actualAVSResponseCode = " ";
    private static HttpsURLConnection conn;
    private static String params;    
    private static byte[] buffer;
    
    private OutputStreamWriter osw;
    private InputStream inputStream;
    private ByteArrayOutputStream rsByteArrayOutputStream;
            
    public AVSTest() {
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
    @Source("AVSVerificationAccountNumber00.xls")
    @InvocationCount(1)   // Equals to an amount of rows in the data file, excluding the first (header) row.
    public void testAVSResponseCode00(
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
    ) throws IOException {       
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
        actualAVSResponseCode = new String(rsByteArrayOutputStream.toByteArray());        
        assertEquals(avsResponseCode00, 
                actualAVSResponseCode.substring(actualAVSResponseCode.indexOf("avsResponseCode"), 
                        actualAVSResponseCode.indexOf("avsResponseCode=00") + 18));
    }
    
    @Test
    @Source("AVSVerificationAccountNumber46.xls")
    @InvocationCount(1)   // Equals to an amount of rows in the data file, excluding the first (header) row.
    public void testAVSResponseCode46(
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
    ) throws IOException {        
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
        actualAVSResponseCode = new String(rsByteArrayOutputStream.toByteArray());        
        assertEquals(avsResponseCode46, 
                actualAVSResponseCode.substring(actualAVSResponseCode.indexOf("avsResponseCode"), 
                        actualAVSResponseCode.indexOf("avsResponseCode=46") + 18));
    }
    
    @Test
    @Source("AVSVerification_TrackData40.xls")
    @InvocationCount(1)   // Equals to an amount of rows in the data file, excluding the first (header) row.
    public void testAVSResponseCode40( 
            String requestType,            
            String terminalId,
            String accountId,
            String transactionIndustryType,
            String amount,
            String accountType,
            String accountData,
            String customerAccountCode,
            String transactionCode,
            String zipCode
    ) throws IOException {        
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
                + "&zipCode=" + zipCode;                  
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
        actualAVSResponseCode = new String(rsByteArrayOutputStream.toByteArray());
        assertEquals(avsResponseCode40, 
               actualAVSResponseCode.substring(actualAVSResponseCode.indexOf("avsResponseCode"), 
                        actualAVSResponseCode.indexOf("avsResponseCode=40") + 18));
    }    
    
    @Test
    @Source("AVSVerificationTrackData_43.xls")
    @InvocationCount(1)   // Equals to an amount of rows in the data file, excluding the first (header) row.
    public void testAVSResponseCode43( 
            String requestType,            
            String terminalId,
            String accountId,
            String transactionIndustryType,
            String amount,
            String accountType,
            String accountData,
            String customerAccountCode,
            String transactionCode,
            String zipCode
    ) throws IOException {        
        String params = "requestType=" + requestType
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
                + "&zipCode=" + zipCode;                  
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
        actualAVSResponseCode = new String(rsByteArrayOutputStream.toByteArray());
        assertEquals(avsResponseCode43, 
               actualAVSResponseCode.substring(actualAVSResponseCode.indexOf("avsResponseCode"), 
                        actualAVSResponseCode.indexOf("avsResponseCode=43") + 18));
    }
    
}
