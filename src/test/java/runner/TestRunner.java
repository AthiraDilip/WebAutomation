package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/features/ContactUs.feature",
        glue= {"src/test/java/"},
        plugin = { "pretty" },
        strict=true,
        monochrome = true
)

public class TestRunner {

}
