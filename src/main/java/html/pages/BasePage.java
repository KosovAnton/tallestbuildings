package html.pages;

import static driver.DriverConfig.getDriver;

import lombok.SneakyThrows;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public abstract class BasePage<T> extends HtmlElement {

  protected final String BASE_PAGE_URL = "https://en.wikipedia.org/";

  public BasePage() {
    PageFactory
        .initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(getDriver())),
            this);
  }

  @SneakyThrows
  public T openPage() {
    getDriver().get(BASE_PAGE_URL + this.getClass().getDeclaredField("PAGE_URL").get(this));
    return (T) this;
  }
}
