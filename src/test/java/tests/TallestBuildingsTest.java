package tests;

import static assertions.TallestBuildingsPageAssertions.assertCountryWithTheBiggestNumberOfTallBuildings;
import static assertions.TallestBuildingsPageAssertions.assertOldiestBuildingInTable;
import static assertions.TallestBuildingsPageAssertions.assertTallestBuildingsTableSortingFunctionality;

import dataproviders.WorldTallestBuildingsTableColumnsIndexes;
import html.pages.TallestBuildingsPage;
import org.testng.annotations.Test;

public class TallestBuildingsTest extends BaseTest {

  @Test(dataProviderClass = WorldTallestBuildingsTableColumnsIndexes.class, dataProvider = "columnIndexes")
  public void verifySortingFunctionality(Integer columnIndex) {

    TallestBuildingsPage tallestBuildingsPage = new TallestBuildingsPage();

    tallestBuildingsPage
        .openPage()
        .showBuildingsTable()
        .sortByColumnIndex(columnIndex)
        .saveCurrentTableCondition()
        .sortByColumnIndex(columnIndex);

    assertTallestBuildingsTableSortingFunctionality(tallestBuildingsPage, columnIndex);
  }

  @Test
  public void verifyThatOldestBuildingIsEmpireStateBuilding() {

    TallestBuildingsPage tallestBuildingsPage = new TallestBuildingsPage();

    tallestBuildingsPage
        .openPage()
        .showBuildingsTable()
        .sortByColumnIndex(0);

    assertOldiestBuildingInTable(tallestBuildingsPage, "Empire State Building");
  }

  @Test
  public void verifyCountryWithTheBiggestNumberOfTallBuildings() {

    TallestBuildingsPage tallestBuildingsPage = new TallestBuildingsPage();

    tallestBuildingsPage
        .openPage()
        .showBuildingsTable()
        .sortByColumnIndex(4);

    assertCountryWithTheBiggestNumberOfTallBuildings(tallestBuildingsPage, "China");
  }
}
