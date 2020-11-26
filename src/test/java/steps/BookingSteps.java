package steps;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import pages.SearchResultPage;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.testng.Assert.assertEquals;

public class BookingSteps {
    String hotelName;
    SearchResultPage searchResultPage = new SearchResultPage();
    private static final String URL = "https://www.booking.com/searchresults.en-gb.html";

    @Given("User id looking for hotels like {string}")
    public void userIdLookingForHotelsLike(String hotelName) {
        this.hotelName = hotelName;
    }

    @Given("User opens search page")
    public void userOpensSearchPage() {
        open(URL);
    }

    @When("User does search")
    public void userDoesSearch() {

        open ("https://www.booking.com/searchresults.en-gb.html");
        $(By.id("ss")).sendKeys(hotelName);
        $(By.id("ss")).click();
        $(".sb-searchbox__button").click();
    }

    @Then("Hotel {string} should be on the first page")
    public void hotelShouldBeOnTheFirstPage(String expectedName) {
        ArrayList<String> hotelsName = new ArrayList<>();
        for (SelenideElement hotel : $$(".sr-hotel__name ")){
            hotelsName.add(hotel.getText());
        }
        assertThat(hotelsName, hasItem(expectedName));
    }

    @And("Rating of the hotel {string} is {string}")
    public void ratingOfTheHotelHotelIsRating(String hotelName, String rating) {
        String expectedRating = searchResultPage.getRating(hotelName);
        assertEquals(expectedRating, rating, "Рейтинг не соответствует");
    }

}
