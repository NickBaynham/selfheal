package dataproviders;

import org.testng.annotations.DataProvider;

public class RegistrationData {
    @DataProvider(name = "registration")
    public Object[][] registrationData() {
        return new Object[][]{
                {
                    "Ada", "Lovelace", "ALovelace", "Orlando", "FL", "32832", true
                },
                {
                    "Ian", "Parker", "IParker", "Boulder", "CO", "80304", true
                },
                {
                    "Tamika", "Jordan", "TJordan", "New York", "NY", "10019", true
                }
        };
    }
}