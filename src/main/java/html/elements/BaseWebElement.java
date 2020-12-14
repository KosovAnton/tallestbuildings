package html.elements;

import static driver.DriverConfig.getDriver;
import static org.junit.Assert.assertTrue;

import java.util.Objects;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public abstract class BaseWebElement<T> extends HtmlElement {

  private WebDriverWait wait = new WebDriverWait(getDriver(), 10);

  private boolean isVisible() {
    try {
      wait.until(Function -> this.isDisplayed());
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public T waitElementAppears() {
    assertTrue("Unable to locate element: " + this.getName(), isVisible());
    scrollToElement(this);
    return (T) this;
  }

  public void waitElementDisappear() {
    assertTrue("Element " + this.getName()
            + "is expected to disappear, but was visible during default waiting timeout.",
        wait.until(Function -> Objects.equals(isVisible(), false)));
  }

  protected void scrollToElement(HtmlElement element) {
    ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
  }
}
