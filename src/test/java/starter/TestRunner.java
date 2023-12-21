package starter;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        tags= "@positive or  @pasta_negative-02 or @pasta_negative-01 or @pasta_positive",
        plugin = { "pretty", "json:target/cucumber-reports/Cucumber.json",
                             "html:target/cucumber-reports/report.html" }
)
public class TestRunner {}
