package tests;

import static driver.DriverConfig.downloadDriverBinary;
import static driver.DriverConfig.getDriver;
import static driver.DriverConfig.setDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

  @BeforeSuite
  public void beforeSuite(){
    downloadDriverBinary();
  }

  @BeforeMethod
  public void setUp() {
    setDriver();
    getDriver().manage().window().maximize();
  }

  @AfterMethod
  public void tearDown() {
    getDriver().quit();
  }

}
