"# cucumber" 
The Project contains examples of how it is possible to specify dynamic locators that can self-heal for open-source Web UI Testing Frameworks, such as those with Selenium-Webdriver.

This project is managed with Maven in Java 8. Third-party libraries include JUnit, TestNG, Selenium-Webdriver 3, JSoup, and FuzzyWuzzy for lexical fuzzy matching.

Download and build the project with Maven: mvn clean
You can also load the project into IntelliJ for Java and run the demo. To run the demo, execute the test: src/test/java/selfheal/tests/regression/RegistrationSelfHealingWorkflowTest 
When you run this test it will use a workflow that in turn relies on the page object src/main/java/framework/pageObjects/RegistrationFormSelfHealing, which uses several auto discovery strategies to find elements on the page, make sure they work by testing with Selenium, add them to cache, and using them to execute the test.
Note that this is just a stating point and a demo. If you wanted to use this approach on your projects you most likely would need to define your own strategies. The demo using a static HTML page, so real web sites require synchronization and may have more complex actions required. However, in theory, it would be possible to use this approach on any web site.

My original design used discovery directly in the workflows. I think it is better to use a hybrid approach and use auto discovery where it makes sense. Forms are very suitable, as would be pages that have repeating elements that are generated based on data. You can mix and match your static locators with discovery style locators. All you really need for your project is a discovery class such as the one in this project:

/src/main/java/io/nickbaynham/automation/selfhealing/controllers/AutoDiscover.java

If you are using page objects, you can use the characteristics mapping to define the scope of where to look and the pattern suitable to how you usually locate your elements. Creating a pattern can be re-used to find elements, cache them, and self-heal if they stop working.

