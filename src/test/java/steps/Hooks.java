package steps;

import context.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigurationReader;
import utils.DriverFactory;
import java.time.Duration;

public class Hooks {
    public Hooks(TestContext context){
        this.context = context;
    }

    private final TestContext context;
    Scenario scenario;

    @Before
    public void before(Scenario scenario) {

        context.driver = DriverFactory.getDriver(scenario);
        long duration = Long.parseLong(ConfigurationReader.get("timeout"));
        context.wait = new WebDriverWait(context.driver, Duration.ofSeconds(duration));
        context.action = new Actions(context.driver);
//        context.driver.get(ConfigurationReader.get("url"));
        this.scenario = scenario;

    }

    @After
    public void after() {
        if (context.driver != null) {
            context.driver.quit();
            this.scenario = scenario;
        }
    }
}
