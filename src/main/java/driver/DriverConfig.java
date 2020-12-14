package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.HashMap;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverConfig {

  private static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

  private static RemoteWebDriver chrome() {
    HashMap<String, Object> chromePrefs = new HashMap<>();
    chromePrefs.put("profile.default_content_settings.popups", 0);
    chromePrefs.put("download.default_directory", System.getProperty("user.dir"));
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--disable-notifications");
    options.setExperimentalOption("prefs", chromePrefs);
    return new ChromeDriver(options);
  }

  public static void setDriver() {
    driver.set(chrome());
  }

  public static RemoteWebDriver getDriver() {
    return driver.get();
  }

  public static void downloadDriverBinary() {
    WebDriverManager.chromedriver().setup();
  }
}
