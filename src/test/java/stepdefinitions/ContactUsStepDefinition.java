package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

import static common.WebObjectUtils.getDropdownValues;
import static constants.TestConstants.*;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static setup.DriverSetup.getDriverInstance;
import static utils.PropertyReader.getPropValue;

public class ContactUsStepDefinition {

    @Given("Open the Home page - YourLogo site")
    public void openTheYourLogoSite() {
        getDriverInstance().get(getPropValue("url"));
    }

    @Then("{string} page with title {string} is displayed")
    public void thePageTitleIs(String pageName, String expTitle) {
        Assert.assertEquals(getDriverInstance().getTitle(), expTitle, format(ERROR_TITLE_MISMATCH_PAGE_IS_NOT_DISPLAYED, pageName));
    }

    @When("I click on {string}")
    public void iClickOn(String linkName) {
        switch (linkName.toUpperCase()) {
            case LINK_CONTACT_US:
                getDriverInstance().findElement(By.linkText("Contact us")).click();
                break;
            case LINK_SIGN_IN:
                getDriverInstance().findElement(By.linkText("Sign in")).click();
                break;
            default:
                System.out.println("Error: Invalid Link - Please add implementation");
                Assert.fail("Error: Invalid Link Used");
        }
    }

    @Then("{string} dropdown should contain below values")
    public void dropdownShouldContainBelowValues(String subjectHeading, List<String> expDropdownValues) {
        WebDriverWait wait = new WebDriverWait(getDriverInstance(), 30);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("#id_contact > option"), 1));
        List<String> actSubjectHeading = getDropdownValues(getDriverInstance().findElements(By.cssSelector("#id_contact > option")));
        Assert.assertEquals(actSubjectHeading, expDropdownValues, "Error: The values in " + subjectHeading + "dropdown does not match");
    }

    @When("Subject heading dropdown is {string}")
    public void subjectHeadingDropdownIs(String subHeadingValue) {
        WebDriverWait wait = new WebDriverWait(getDriverInstance(), 20);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("#id_contact > option"), 1));
        WebElement webElementSubHeadingDropdown = getDriverInstance().findElement(By.id("id_contact"));
        Select subHeadingDropdown = new Select(webElementSubHeadingDropdown);
        subHeadingDropdown.selectByVisibleText(subHeadingValue);

    }

    @Then("{string} message for {string} dropdown is displayed")
    public void messageForDropdownIsDisplayed(String expMessage, String subHeadingValue) {
        String actMessage = new String();
        switch (subHeadingValue.toUpperCase()) {
            case FIELD_CUSTOMER_SERVICE:
                actMessage = getDriverInstance().findElement(By.id("desc_contact2")).getText();
                break;
            case FIELD_WEBMASTER:
                actMessage = getDriverInstance().findElement(By.id("desc_contact1")).getText();
                break;
            default:
                System.out.println("Error: Invalid dropdown selection: " + subHeadingValue.toUpperCase());
                Assert.fail("Error: Invalid dropdown selection");
        }
        Assert.assertEquals(actMessage, expMessage, "Error: Message for " + subHeadingValue + " dropdown not displayed");
    }

    @When("{string} field value is given below values")
    public void fieldValueIsGivenBelow(String fieldName, List <String> fieldValues) {
        switch (fieldName.toUpperCase()) {
            case FIELD_EMAIL_ADDRESS:
                fieldValues.forEach(value -> {
                    getDriverInstance().findElement(By.id("email")).clear();
                    getDriverInstance().findElement(By.id("email")).sendKeys(value);
                });
                break;
            case MESSAGE:
                fieldValues.forEach(value -> {
                    getDriverInstance().findElement(By.id("message")).clear();
                    getDriverInstance().findElement(By.id("message")).sendKeys(value);
                });
                break;
            default:
                System.out.println("Error: Invalid field name: " + fieldName.toUpperCase());
                Assert.fail("Error: Invalid field name");
        }
    }

    @And("User clicks on {string} button")
    public void userClicksOnButton(String buttonName) {
        switch (buttonName.toUpperCase()) {
            case BUTTON_SEND:
                getDriverInstance().findElement(By.id("submitMessage")).click();
                break;
            case BUTTON_HOME:
                getDriverInstance().findElement(By.cssSelector("div#center_column a")).click();
                break;
            default:
                System.out.println("Error: Invalid field name");
                Assert.fail("Error: Invalid field name");
//                findElement(By.)
        }
    }

    @Then("Error messages should be displayed for {string} field")
    public void errorMessagesShouldBeDisplayedForField(String fieldName) {
        switch (fieldName.toUpperCase()) {
            case DROPDOWN_SUBJECT_HEADING:
                String expFieldErrorMessage1 = "There is 1 error";
                String expFieldErrorMessage2 = "Please select a subject from the list provided.";
                String actFieldErrorMessage1 = getDriverInstance().findElement(By.cssSelector("div.alert.alert-danger > p")).getText();
                String actFieldErrorMessage2 = getDriverInstance().findElement(By.cssSelector("div.alert.alert-danger li")).getText();
                Assert.assertEquals(actFieldErrorMessage1, expFieldErrorMessage1, "Field validation message(s) for " + fieldName + " field not displayed correctly");
                Assert.assertEquals(actFieldErrorMessage2, expFieldErrorMessage2, "Field validation message(s) for " + fieldName + " field not displayed correctly");
                break;
            case FIELD_EMAIL_ADDRESS:
                expFieldErrorMessage1 = "There is 1 error";
                expFieldErrorMessage2 = "Invalid email address.";
                actFieldErrorMessage1 = getDriverInstance().findElement(By.xpath("//*[@class=\"alert alert-danger\"]//p[text()='There is 1 error']")).getText();
                actFieldErrorMessage2 = getDriverInstance().findElement(By.xpath("//*[@class=\"alert alert-danger\"]//li[text()='Invalid email address.']")).getText();
                Assert.assertEquals(actFieldErrorMessage1 + actFieldErrorMessage2, expFieldErrorMessage1 + expFieldErrorMessage2, "Field validation message(s) for " + fieldName + " field not displayed correctly");
                break;
            case MESSAGE:
                expFieldErrorMessage1 = "There is 1 error";
                expFieldErrorMessage2 = "The message cannot be blank.";
                actFieldErrorMessage1 = getDriverInstance().findElement(By.xpath("//*[@class=\"alert alert-danger\"]//p[text()='There is 1 error']")).getText();
                actFieldErrorMessage2 = getDriverInstance().findElement(By.xpath("//*[@class=\"alert alert-danger\"]//li[text()='The message cannot be blank.']")).getText();
                Assert.assertEquals(actFieldErrorMessage1 + actFieldErrorMessage2, expFieldErrorMessage1 + expFieldErrorMessage2, "Field validation message(s) for " + fieldName + " field not displayed correctly");
                break;

            default:
                System.out.println("Error: Invalid field name");
                Assert.fail("Error: Invalid field name");

        }
    }

    @Then("Success message should be displayed")
    public void successMessageShouldBeDisplayed() {
        String expSuccessMessage = "Your message has been successfully sent to our team.";
        String actSuccessMessage = getDriverInstance().findElement(By.cssSelector("p.alert.alert-success")).getText();
        Assert.assertEquals(actSuccessMessage, expSuccessMessage, "Error: Success message not displayed after sending message");
    }

    @And("{string} button should be displayed")
    public void buttonShouldBeDisplayed(String buttonName) {
        switch (buttonName.toUpperCase()) {
            case BUTTON_HOME:
                Assert.assertTrue(getDriverInstance().findElement(By.cssSelector("div#center_column a")).isDisplayed(),
                        "Error:" + buttonName + " button not displayed");
                break;
            default:
                System.out.println("Error: Invalid button name");
                Assert.fail("Error: Invalid button name");
        }

    }

    @And("User attaches a file in {string} field")
    public void userAttachesAFileInField(String fieldName) {
        switch (fieldName.toUpperCase()) {
            case FIELD_ATTACH_FILE:
                getDriverInstance().findElement(By.id("fileUpload")).sendKeys(getPropValue("attachmentFilePath"));
                break;
            default:
                System.out.println("Error: Invalid field name");
                Assert.fail("Error: Invalid field name");
        }
    }

    @Then("validate the error message dropdown is displayed")
    public void validateTheErrorMessageDropdownIsDisplayed(DataTable dataTable) {
        List<Map<String, String>> subjectsAndErrorMessage = dataTable.asMaps(String.class, String.class);
        subjectsAndErrorMessage.forEach(subjectsAndErrorMessageItem -> {
            subjectHeadingDropdownIs(subjectsAndErrorMessageItem.get("subject heading"));
            messageForDropdownIsDisplayed(subjectsAndErrorMessageItem.get("error message"), subjectsAndErrorMessageItem.get("subject heading"));
        });
    }
}
