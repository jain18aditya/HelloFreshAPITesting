package api.testRunner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"api.steps"},
        plugin = {"html:target/html/cucumber.html", "json:target/html/cucumber.json",
                "pretty:target/users-pretty.txt",
                "junit:target/users-results.xml"
        },
        tags = {"@e2e"},
        monochrome = true,
        strict = true
)
public class CountryTestRunner
{

}
