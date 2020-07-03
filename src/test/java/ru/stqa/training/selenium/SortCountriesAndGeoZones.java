package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SortCountriesAndGeoZones extends TestBase {

    //1.Проверка для пункта меню Countries
    @Test
    public void sortCountriesAndZones() {
        //авторизовываемся в админке
        loginInAdmin();
        //переходим на страницу со странами
        driver.get("http://localhost/litecart/public_html/admin/?app=countries&doc=countries");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertTrue(isElementPresent(By.tagName("h1")));

        //получаем таблицу со странами и геозонами
        List<WebElement> tableCountries = driver.findElements(By.xpath(".//table[@class='dataTable']//tr[@class='row']"));

        List<String> nameOfCountries = new ArrayList<>();
        for (int i = 0; i < tableCountries.size(); i++) {
            //получим таблицу повторно, т.к. локатор может в дальнейшем испортиться
            List<WebElement> tableCountries2 = driver.findElements(By.xpath(".//table[@class='dataTable']//tr[@class='row']"));
            String countryName = tableCountries2.get(i).findElements(By.tagName("td"))
                    .get(4).findElement(By.tagName("a")).getAttribute("textContent");

            //список с названиями стран дополняем новой страной
            nameOfCountries.add(countryName);

            //теперь проверим какая гео-зона у страны и если не 0, то перейдем на страницу со страной
            String countryZone = tableCountries2.get(i).findElements(By.tagName("td"))
                    .get(5).getAttribute("textContent");

            if (!countryZone.equals("0")) {
                WebElement goCountryZone = tableCountries2.get(i).findElements(By.tagName("td"))
                        .get(4).findElement(By.tagName("a"));
                goCountryZone.click();
                assertTrue(isElementPresent(By.tagName("h1")));

                // поступаем аналогично, ищем таблицу с зонами на странице страны
                List<WebElement> zonesOfSelectCountry = driver.findElements(By.xpath(".//table[@id='table-zones']//tr"));

                List<String> nameZoneSelContr = new ArrayList<>();
                for (int j = 1; j < zonesOfSelectCountry.size()-1; j++) {
                    String nameZoneSelectCountry = zonesOfSelectCountry.get(j).findElements(By.tagName("td"))
                            .get(2).getText();
                //название зоны добавляем в список
                    nameZoneSelContr.add(nameZoneSelectCountry);
                }
                //создаем дубль списка и сортируем его, проверяем одинаковы ли списки
                // и возвращаемся обратно на страницу со странами
                List<String> nameZoneSelContrSort = new ArrayList<>();
                nameZoneSelContrSort.addAll(nameZoneSelContr);
                Collections.sort(nameZoneSelContrSort);
                Assert.assertTrue(nameZoneSelContr.equals(nameZoneSelContrSort));
                System.out.println("Sort geo-zones for country " + countryName+ " is correct");
                driver.get("http://localhost/litecart/public_html/admin/?app=countries&doc=countries");
                assertTrue(isElementPresent(By.tagName("h1")));
            }
        }
        //в конце возвращаемся к списку стран и проверяем сортировку
        List<String> nameOfCountriesForSort = new ArrayList<>();
        nameOfCountriesForSort.addAll(nameOfCountries);
        Collections.sort(nameOfCountriesForSort);

        Assert.assertTrue(nameOfCountries.equals(nameOfCountriesForSort));
        System.out.println("Sort of country is correct");
    }

    //2. Проверка для пункта меню Geo Zones
    @Test
    public void sortZoneOnGeoZonePage() {
        //авторизовываемся в админке
        loginInAdmin();
        //переходим на страницу с гео-зонами
        driver.get("http://localhost/litecart/public_html/admin/?app=geo_zones&doc=geo_zones");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertTrue(isElementPresent(By.tagName("h1")));

        //получаем таблицу со странами
        List<WebElement> tableGeoZone = driver.findElements(By.xpath(".//table[@class='dataTable']//tr[@class='row']"));

        for (int i = 0; i < tableGeoZone.size(); i++) {
            //получим снова таблицу, т.к. локатор может в дальнейшем испортиться
            List<WebElement> tableGeoZone2 = driver.findElements(By.xpath(".//table[@class='dataTable']//tr[@class='row']"));
            WebElement nameCountry = tableGeoZone2.get(i).findElements(By.tagName("td"))
                    .get(2).findElement(By.tagName("a"));
            nameCountry.click();
            assertTrue(isElementPresent(By.tagName("h1")));
            // ищем таблицу с зонами на странице страны
                List<WebElement> zonesTable = driver.findElements(By.xpath(".//table[@id='table-zones']//tr"));

                List<String> nameZoneOfSelectContry = new ArrayList<>();
                for (int j = 1; j < zonesTable.size()-1; j++) {
                    String nameGeoZ = zonesTable.get(j).findElements(By.tagName("td"))
                            .get(2).findElement(By.xpath(".//select//option[@selected='selected']"))
                            .getAttribute("textContent");
                    //название зоны добавляем в список
                    nameZoneOfSelectContry.add(nameGeoZ);
                }
                //создаем дубль списка и сортируем его, проверяем одинаковы ли списки
                // и возвращаемся обратно на страницу пункта гео-зоны
                List<String> nameZoneOfSelectContrySort = new ArrayList<>();
                nameZoneOfSelectContrySort.addAll(nameZoneOfSelectContry);
                Collections.sort(nameZoneOfSelectContrySort);
                Assert.assertTrue(nameZoneOfSelectContry.equals(nameZoneOfSelectContrySort));
                System.out.println("Sort geo-zones is correct");
            driver.get("http://localhost/litecart/public_html/admin/?app=geo_zones&doc=geo_zones");
            assertTrue(isElementPresent(By.tagName("h1")));
            }
        }
}
