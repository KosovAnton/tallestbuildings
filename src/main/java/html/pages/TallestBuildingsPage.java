package html.pages;

import html.elements.Table;
import lombok.Getter;
import org.openqa.selenium.support.FindBy;

public class TallestBuildingsPage extends BasePage<TallestBuildingsPage> {

  String PAGE_URL = "/wiki/List_of_tallest_buildings";

  @Getter
  @FindBy(xpath = ".//table[@class = 'wikitable sortable jquery-tablesorter'][1]")
  private Table worldTallestBuildings;


  public Table showBuildingsTable() {
    return worldTallestBuildings.waitElementAppears();
  }

}
