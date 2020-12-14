package html.elements;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Table extends BaseWebElement<Table> {

  @Getter
  private List<List<String>> previousTableCondition;

  public Table saveCurrentTableCondition() {
    previousTableCondition = getColumnsAsString();
    return this;
  }

  public Table sortByColumnIndex(int index) {
    getHeadings().get(index).click();
    return this;
  }

  public List<WebElement> getHeadings() {
    return this.getWrappedElement().findElements(By.xpath(".//th"));
  }

  public String getColumnName(int index) {
    return getHeadings().get(index).getText();
  }

  public List<List<WebElement>> getRows() {
    return this.findElements(By.xpath(".//tr")).stream()
        .map((rowElement) -> rowElement.findElements(By.xpath(".//td")))
        .filter((row) -> row.size() > 0).collect(Collectors.toList());
  }

  public List<List<String>> getRowsAsString() {
    return this.getRows().stream()
        .map((row) -> row.stream().map(WebElement::getText).collect(Collectors.toList()))
        .collect(Collectors.toList());
  }

  public List<List<WebElement>> getColumns() {
    List<List<WebElement>> columns = new ArrayList<>();
    List<List<WebElement>> rows = this.getRows();
    if (rows.isEmpty()) {
      return columns;
    } else {
      int columnCount = rows.get(0).size();

      for (int i = 0; i < columnCount; i++) {
        List<WebElement> column = new ArrayList<>();

        for (List<WebElement> row : rows) {
          column.add(row.get(i));
        }

        columns.add(column);
      }

      return columns;
    }
  }

  public List<String> getColumnByIndex(int index) {
    return this.getColumns().get(index).stream().map(WebElement::getText)
        .collect(Collectors.toList());
  }

  public List<List<String>> getColumnsAsString() {
    return this.getColumns().stream()
        .map((row) -> row.stream().map(WebElement::getText).collect(Collectors.toList()))
        .collect(Collectors.toList());
  }

}
