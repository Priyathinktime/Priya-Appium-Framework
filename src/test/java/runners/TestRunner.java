package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import testcode.BaseTest;

import org.testng.annotations.BeforeClass;


@CucumberOptions(
        features = "src/test/resources/features",
        glue = "stepdefs",
        plugin = {"pretty", "html:target/cucumber-reports.html"},
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @BeforeClass
    public static void globalSetUp() {
        try {
            BaseTest.setUp();  // Initialize Appium driver
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize BaseTest");
        }
    }
}
