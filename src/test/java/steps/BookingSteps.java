package steps;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class BookingSteps {
    String hotelName;

    @Given("User id looking for hotels like {string}")
    public void userIdLookingForHotelsLike(String hotelName) {
        this.hotelName = hotelName;
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

    @And("Rating of the hotel is {string}")
    public void ratingOfTheHotelIs(String arg0) {
    }


}
