package starter.stepdefinitions;




import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import java.awt.print.Book;
import java.util.List;
import java.util.Map;


public class PastaApi {

    @Step("Verify the image link")
    public void VerifyImage(Response response)
    {
      String responseAsString=response.getBody().asString();

        List<String> responseSize = response.jsonPath().getList("$");

        for(int i = 0; i < responseSize.size(); i++) {
            String imageLink = JsonPath.read(responseAsString,"$["+i+"].image");
            System.out.println(imageLink);
            int statusCode=SerenityRest.given().get(imageLink).statusCode();
            Assert.assertEquals(statusCode,200);

        }
    }


}
