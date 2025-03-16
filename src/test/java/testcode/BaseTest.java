package testcode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {
    protected static AndroidDriver driver;  // Make driver static
    private static AppiumDriverLocalService service;
    protected static Properties properties;
    
  
    @BeforeClass
    public static void setUp() throws IOException {
    	
    	properties = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");
        properties.load(fis);
    	
        if (driver == null) {  // Ensure driver is initialized once
            // Start Appium Service
//            service = new AppiumServiceBuilder()
//                   .withAppiumJS(new File("C:\\Users\\rithi\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
//            	      .usingAnyFreePort()
//            		.withIPAddress("127.0.0.1")
//                    .usingPort(4723)
//                    .withTimeout(Duration.ofSeconds(10))
//                    .build();
//            service.start();
        	service = new AppiumServiceBuilder()
        	        .withIPAddress("127.0.0.1")
        	        .usingAnyFreePort()
        	        .withTimeout(Duration.ofSeconds(10))
        	        .build();
        	service.start();


            // Set Capabilities
            UiAutomator2Options options = new UiAutomator2Options()
                    .setPlatformName("Android")
                    .setDeviceName("emulator-5554")
                    .setApp(System.getProperty("user.dir") + "/src/test/resources/resources/Android-MyDemoAppRN.1.3.0.build-244 (1).apk")
                  
                    .setAutomationName("UiAutomator2");

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        }
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (service != null) {
            service.stop();
        }
    }
}
