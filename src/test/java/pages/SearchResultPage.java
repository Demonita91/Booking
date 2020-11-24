package pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;


public class SearchResultPage {

    private static final String HOTEL_RATING = "//span[contains(text(), '%s')]/ancestor::div[@class='sr_item_main_block']/following::*//div[@class='bui-review-score__badge']";

    public String getRating(String hotelName) {
        return $(By.xpath(String.format(HOTEL_RATING, hotelName))).text();
    }
}
