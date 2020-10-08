package common;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class WebObjectUtils {
    public static List<String> getDropdownValues(List<WebElement> dropdownList) {
        List<String> actSubjectHeading = new ArrayList<>();
        for (WebElement element : dropdownList) {
            actSubjectHeading.add(element.getText());
        }
        return actSubjectHeading;
    }
}
