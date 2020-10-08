package setup;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

public class Hooks {
    DriverSetup driverSetup = new DriverSetup();

    @When("Start the driver instance")
    public void createDriverInstance() {
        driverSetup.startDriver();

    }

    @And("Kill the driver instance")
    public void killDriverInstance() {
        driverSetup.killDriver();
    }

//    @AfterStep
//    public void afterStep(Scenario scenario){
//        System.out.println(scenario.toString());
//    }

}
