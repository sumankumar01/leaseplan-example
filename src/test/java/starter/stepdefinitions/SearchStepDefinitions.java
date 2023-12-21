package starter.stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;


import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.contains;

public class SearchStepDefinitions {

    @Steps
    public CarsAPI carsAPI;

    @Steps
    public PastaApi pastaAPI;

    @Steps
    public FruitApi fruitApi;

    @When("he calls endpoint {string}")
    public void heCallsEndpoint(String resource) {
        Response respone=SerenityRest.given().get(resource);
        Serenity.setSessionVariable("respone").to(respone);
        respone.prettyPrint();
        if(resource.contains("pasta"))
            Serenity.setSessionVariable("pastaRespone").to(respone);
        else if(resource.contains("cola"))
            Serenity.setSessionVariable("colaRespone").to(respone);
        else
            Serenity.setSessionVariable("respone").to(respone);
    }

    @Then("he sees the results displayed for mango")
    public void heSeesTheResultsDisplayedForMango() {
        restAssuredThat(response -> response.body("title", contains("mango")));
    }

    @Then("he doesn not see the results")
    public void he_Doesn_Not_See_The_Results() {
        //restAssuredThat(response -> response.body("detail.error".g, contains("true".toString())));
       Response response= Serenity.sessionVariableCalled("respone");
        Assert.assertTrue(response.getBody().asString().contains("Not found"));
    }

    @Then("he sees the results displayed for ([^\"]*)$")
    public void heSeesTheResultsDisplayedForpasta(String item) {
        restAssuredThat(response -> response.statusCode(200));

    }
    @Then("we validate the response of ([^\"]*)$")
    public void validateTheResponse(String item) {
        if(item.contains("pasta"))
        {
            Response response= Serenity.sessionVariableCalled("pastaRespone");
            pastaAPI.VerifyImage(response);
        }

    }
    @Then("response should contains ([^\"]*) of ([^\"]*)$")
    public void responseValidation(String validation,String item) {
        if(item.contains("pasta"))
        {
            Response response= Serenity.sessionVariableCalled("pastaRespone");
            response.getBody().asString().contains(item);
        }

    }

    @When("he calls endpoint {string} using post method")
    public void callendpointPost(String resource) {
        Response respone=SerenityRest.given().post(resource);
        respone.prettyPrint();
        if(resource.contains("pasta"))
            Serenity.setSessionVariable("pastaRespone").to(respone);
        else if(resource.contains("cola"))
            Serenity.setSessionVariable("colaRespone").to(respone);
        else
            Serenity.setSessionVariable("respone").to(respone);
    }

}
