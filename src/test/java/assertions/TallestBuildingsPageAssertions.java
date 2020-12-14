package assertions;

import html.pages.TallestBuildingsPage;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.testng.Assert;

public class TallestBuildingsPageAssertions {

  public static void assertTallestBuildingsTableSortingFunctionality(
      TallestBuildingsPage tallestBuildingsPage, Integer columnIndex) {
    List<String> expectedColumnSorting = tallestBuildingsPage.getWorldTallestBuildings()
        .getPreviousTableCondition().get(columnIndex);

    Collections.reverse(expectedColumnSorting);

    List<String> actualColumnSorting = tallestBuildingsPage.getWorldTallestBuildings()
        .getColumnByIndex(columnIndex);

    Assert
        .assertEquals(actualColumnSorting, expectedColumnSorting,
            "Wrong sorting. Column - " + tallestBuildingsPage.getWorldTallestBuildings()
                .getColumnName(columnIndex));
  }

  public static void assertOldiestBuildingInTable(TallestBuildingsPage tallestBuildingsPage,
      String expectedBuildingName) {
    List<Integer> yearList = tallestBuildingsPage.getWorldTallestBuildings().getColumnByIndex(8)
        .stream()
        .map(Integer::valueOf).collect(Collectors.toList());

    int minYearIndex = IntStream.range(0, yearList.size())
        .reduce((i, j) -> yearList.get(i) > yearList.get(j) ? j : i).getAsInt();

    String actualOldestBuilding = tallestBuildingsPage.getWorldTallestBuildings()
        .getColumnByIndex(1).get(minYearIndex);

    Assert
        .assertEquals(actualOldestBuilding, expectedBuildingName, "Wrong building name.");
  }

  public static void assertCountryWithTheBiggestNumberOfTallBuildings(
      TallestBuildingsPage tallestBuildingsPage, String expectedCountry) {
    List<String> countries = tallestBuildingsPage.getWorldTallestBuildings().getColumnByIndex(4);

    Map<String, Long> map = countries.stream()
        .collect(Collectors.groupingBy(String::trim, Collectors.counting()));

    String country = Collections.max(map.entrySet(), (entry1, entry2) -> Math
        .toIntExact(entry1.getValue() - entry2.getValue())).getKey();

    Assert.assertEquals(country, expectedCountry, "Wrong country.");
  }

}
