package com.devix.number;

import com.devix.number.controller.MessageController;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@AutoConfigureMockMvc
@WebMvcTest
public class NumberApplicationTest {

    private MessageController messageController = new MessageController();

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Start NumberApplicationTest.class");
    }

    @AfterClass
    public  static void afterClass() {
        System.out.println("Finish NumberApplicationTest.class");
    }

    @Test
    public void testNumberNotNull() {
        String number = messageController.random();
        System.out.println("number: " + number);
        assertNotNull(number);
    }

    @Test
    public void testNumberSize() {
        String number = messageController.random();
        System.out.println("number: " + number);
        assertEquals(12, number.length());
    }

    @Test
    @Ignore
    public void testRestApi() {
        int responseCode = 0;
        String responseMessage = null;
        try {
            URL url = new URL("http://localhost:8080/number/random");
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setConnectTimeout(5000);
            httpCon.setReadTimeout(5000);
            responseCode = httpCon.getResponseCode();

            String line;
            BufferedReader br = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
            while ((line = br.readLine()) != null) {
                responseMessage = "";
                responseMessage += line;
            }

            System.out.println("responseCode: " + responseCode);
            System.out.println("responseMessage: " + responseMessage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        assertEquals(200, responseCode);
        assertNotNull(responseMessage);
        assertEquals(12, responseMessage.length());
    }
}