package framework.selfheal.discovery.controllers;

import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.*;
import java.util.*;

/**
 *    A new version that will provide static methods to handle dynamic locators.
 *    First, a lookup in cache to verify that it is not already available
 *    Followed by returning the cached value, if available
 *    Should the cached value fail, we will need to self-heal by autonomously looking for a new locator
 *
 *    Note: Remember to call serializeCache() after the test is executed so that locators are saved for reuse
 *
 *    Characteristics:
 *
 *    Name              Description
 *    ---------------   -------------------------------------------------------------------------------------------
 *    Scope             A CSS Selector to find an element that should be the root for discovery purposes
 *    TagName           The tag name: input, button, etc
 *    Attributes        A comma-separated list of attributes to search in (the best match based on combined)
 *    AttributeMatcher  The matcher value to match in the provided attributes
 *    InnerText         Find the element using the inner text of the element
 *    cssSelector       The resulting generated dynamic element locator As a cssSelector, or default selector
 *    shouldBePresent   Set to true if you want to check presence with Selenium
 *    shouldBeVisible   Set to true if you want to check visibility with Selenium
 *    shouldBeEnabled   Set to true if you want to check if the element is enabled
 *    threshold         Set to a percentage (100 = exact; 0 = closest; 25 = low; 50 = medium; 75 = near match, etc)
 *                          default is 50
 *
 *
 */
public class AutoDiscover {

    /**
     *   getLocator - The API for working with Auto Discovery to get a dynamic locator
     *
     *   The cached value will be returned if it exists, otherwise a new locator will be generated
     *   autonomously and cached
     *
     *   A new dynamically generated locator can be assumed to be present, visible, and enabled
     *
     *   Throws an ElementNotFoundException if locator cannot be determined based on provided characteristics
     *
     * @param characteristics - A map of key/value pairs that describes the characteristics of the element we are looking for
     *
     * @return A CSS Locator As a String
     *
     * @throws ElementNotFoundException - If a locator cannot be generated based on the characteristics provided
     */
    public static String getLocator(Map<String, String> characteristics) throws Exception {
        String locator = null; // we will attempt to find a locator As a CSS Selector String
        try {
            // try to find a locator using auto-discovery
            locator = findElement(characteristics);
            checkWithSelenium(characteristics, locator);  // we can use Selenium to check that is present, visible, enabled
            cache.put(flatten(characteristics), locator); // it's been confirmed, adding to cache
            return locator;
        } catch (Exception ex) {                              // note that if the cached locator casused a problem it will be overwritten by discovery
            System.out.println("Auto Discovery: Couldn't find the element requested dynamically: " + ex.getMessage());
            showCharacteristics(characteristics);
            throw ex; // Auto Discovery failed given the provided characteristics for locating an element
        }
    }

    /**
     *  checkWithSelenium() - If the characteristics include shouldBePresent, shouldBeVisible, shouldBeEnabled we can verify with Selenium
     *
     * @param characteristics - A map of the characteristics provided to locate an element in the DOM
     * @param cssSelector - The cssSelector generated or provided in cache As a String
     * @return - Returns the cssSelector so that we can chain the methods
     * @throws Exception - Selenium will throw an exception when checking presence, visiblity, enabled status of an element
     */
    private static String checkWithSelenium(Map<String,String> characteristics, String cssSelector) throws Exception {
        if (characteristics.get("shouldBePresent").equals("true")) isPresent(cssSelector);
        if (characteristics.get("shouldBeVisible").equals("true")) isVisible(cssSelector);
        if (characteristics.get("shouldBeEnabled").equals("true")) isEnabled(cssSelector);
        return cssSelector;
    }

    /**
     *   findElement() - Returns the dynamic locator for the element based on the characteristics provided
     *
     * @param characteristics - collection of characteristics desired in the resulting element locator As a Map
     *
     * @return - returns the CSS Selector for the desired element As a String
     *
     * @throws ElementNotFoundException - If the locator strategy cannot be determined, an element not found exception is thrown
     *
     */
    private static String findElement(Map<String, String> characteristics) throws Exception {

        // If available, use the Scope characteristic to reduce the elements to work with to a root element in the DOM
        // Can be used by page objects to prevent collisions where similarly labelled elements might be found

        // Scope is a key/value pair containing a CSS Selector, i.e. Scope = "#registrationForm"
        //     returns all elements with parent of the root element associated with the ID

        List<Element> elements = getDocument().getAllElements();            // A List of all the elements discoverable in the DOM
        if (characteristics.containsKey("Scope")) {
            elements = getAllElementsInScope(characteristics.get("Scope")); // Reduces the list of elements to only those with root of the scoped element
        }

        // If Characteristics contain a label, use this characteristic to locate the element
        // Note that you must have an element with tag label that includes attribute "for" specifying the id of another element
        if (characteristics.containsKey("Label")) {
            List<Element> labelElements = filterElements(elements, (Element element) -> element.tagName().equals("label"));
            try {
                return filterByLabel(labelElements, characteristics);
            } catch (Exception e) {
                System.out.println("Label Characteristic given, but discovery failed to find the element. " + e.getMessage());
                throw e;
            }
        }

        // If the Tag Name is provided, filter elements by Tag Name
        // Example: TagName = "input"
        if (characteristics.containsKey("TagName")) {
            elements = filterElements(elements, (Element element) -> element.tagName().equals(characteristics.get("TagName")));
        }

        // Find by Inner Text
        if (characteristics.containsKey("InnerText")) {
            List<String> innerTextOfScopedElements = new ArrayList<>();
            for (Element element : elements) {
                innerTextOfScopedElements.add(element.wholeText());
            }

            int threshold = FUZZY_SCORING_LIMIT;
            if (characteristics.containsKey("threshold")) {
                threshold = Integer.parseInt(characteristics.get("threshold"));
            }

            ExtractedResult result = FuzzySearch.extractOne(characteristics.get("InnerText"), innerTextOfScopedElements);
            if (result.getScore() >= threshold) {
                return elements.get(result.getIndex()).cssSelector();
            }
        }

        // Find by attribute
        if (characteristics.containsKey("Attributes") && characteristics.containsKey("AttributeMatcher")) {
            // Get a list of attributes to find elements by
            String[] attributes = characteristics.get("Attributes").split(",");
            for (String attribute : attributes) {
                try {
                    int threshold = FUZZY_SCORING_LIMIT;
                    if (characteristics.containsKey("threshold")) threshold = Integer.parseInt(characteristics.get("threshold"));
                    return getBestMatchForAttributeValue(elements, attribute, characteristics.get("AttributeMatcher"), threshold);
                } catch (Exception e) {
                    System.out.println("No Match: " + attribute + " = " + characteristics.get("AttributeMatcher"));
                }
            }
        }
        throw new ElementNotFoundException("Auto Discovery didn't find the element using the Characteristics provided.");
    }

    /**
     * getElementsWithMatchingAttributeValue() -- Search the scoped list of elements for a matching value in the attribute
     *
     * @param elements - The provided scoped list of elements
     * @param attribute - The attribute on each element to match the value to
     * @return - A list containing zero or more matches
     */
    private static String getBestMatchForAttributeValue(List<Element> elements, String attribute, String attributeMatcher, int threshold) throws ElementNotFoundException {

        List<Element> result = new ArrayList<>();
        // First we need a list of the attribute values for each of the elements
        List<String> values = new ArrayList<>();
        for (Element element : elements) {
            values.add(element.attributes().get(attribute));
        }

        // Get the list item for the highest score for all elements of tag "label"
        ExtractedResult extractedResult = FuzzySearch.extractOne(attributeMatcher, values);
        if (extractedResult.getScore() >= threshold) {
            int index = extractedResult.getIndex();
            Element matchingElement = elements.get(index);
            return matchingElement.cssSelector();
        }
        throw new ElementNotFoundException("No match for Element with Attribute: " + attribute + " of value: " + attributeMatcher);
    }

    /**
     *  getDocument() - Get a copy of the DOM that is up-to-date
     *
     * @return - Copy of the DOM As a JSOUP Document
     */
    private static Document getDocument() {
        String html = WebController.getInstance().getHtml();
        return Jsoup.parse(html);
    }
    private static List<Element> getAllElementsInScope(String scope) {
        String html = WebController.getInstance().getHtml();
        Document document = Jsoup.parse(html);
        return document.getAllElements().select(scope);
    }

    private static void isVisible(String locator) throws Exception {
        WebController.getInstance().isVisible(locator);
    }

    private static void isEnabled(String locator) throws Exception {
        WebController.getInstance().isEnabled(locator);
    }

    private static void isPresent(String locator) throws Exception {
        WebController.getInstance().isPresent(locator);
    }

    /**
     *   flatten() -- Merge all characteristics into a key to use for the cache
     *
     * @param characteristics - The map of characteristics desired to find a specific locator in the DOM
     * @return - returns an encorded key As a String
     */
    private static String flatten(Map<String, String> characteristics) {
        StringBuilder result = new StringBuilder();
        for (String key : characteristics.keySet()) {
            if (! key.equalsIgnoreCase("cssSelector")) {
                result.append(key).append(characteristics.get(key));
            }
        }
        return result.toString().replace(" ", "");
    }

    /**
     * getCachedLocator - Retrieve a Locator from the Cache
     * @param characteristics - Used to create a unique key
     * @return - The locator, should it exist in the cache
     * @throws CachedLocatorNotFound - If not found
     */
    public static String getCachedLocator(Map<String,String> characteristics) throws CachedLocatorNotFound {
        String key = flatten(characteristics);
        if (cache == null) {
            try {
                loadCache();
            } catch (IOException e) {
                System.out.println("Cache File src/test/resources/cache/cache.properties not found. Initializing Cache.");
            }
        }
        String locator = cache.getProperty(key);
        if (locator == null) throw new CachedLocatorNotFound("Locator not in cache: " + key);
        System.out.println("Using Cached CSS Selector: " + locator);
        return locator;
    }

    private static void loadCache() throws IOException {
        cache = new Properties();
        InputStream input = new FileInputStream("src/test/resources/cache/cache.properties");
        cache.load(input);
        System.out.println("Properties loaded: " + cache.size());
    }

    /**
     *   filterElements() - A common method for filtering which can pass in a specific matching criteria
     *
     * @param elements = The filtered collection of elements to work with
     * @param predicate = The method for matching
     * @return = A list of filtered elements based on the method criteria
     */
    private static List<Element> filterElements(List<Element> elements, WebElementPredicate predicate) {
        List<Element> result = new ArrayList<>();
        for (Element element : elements) {
            if (predicate.test(element)) {
                result.add(element);
            }
        }
        return result;
    }

    /**
     *   filterByLabel() - Method of filtering a collection of elements by relative label
     *
     *   Finds any matching labels with text provided by characteristics using fuzzy matching.
     *   If the label has a "For" attribute then the element with the id matching this attribute is returned
     *
     * @return = Elements that match the criteria
     */
    private static String filterByLabel(List<Element> labels, Map<String,String> characteristics) throws NoElementsFoundException, ElementNotFoundException, Exception {
        int fuzzy_threshold = FUZZY_SCORING_LIMIT;
        if (characteristics.containsKey("threshold") && characteristics.get("userFuzzyMatching").equalsIgnoreCase("false"))
            fuzzy_threshold = Integer.parseInt(characteristics.get("threshold"));
        List<Element> result = new ArrayList<>();
        List<String> labelText = new ArrayList<>();
        if (labels != null && !labels.isEmpty()) {
            // Create a List containing the inner text of all the labels in a list of all elements in scope with the "label" tag
            for (Element label : labels) {
                labelText.add(label.wholeText());
            }

            // Get the list item for the highest score for all elements of tag "label"
            ExtractedResult extractedResult = FuzzySearch.extractOne(characteristics.get("Label"), labelText);

            // If the highest score is higher than the threshold score for being accepted, we can take the element and generate a locator
            if (extractedResult.getScore() >= fuzzy_threshold) {
                String id = labels.get(extractedResult.getIndex()).attr("for");
                Element element = getDocument().attr("id", id);
                String cssSelector = element.cssSelector();
                cache.put(flatten(characteristics), cssSelector);
                characteristics.put("cssSelector", cssSelector);
                return cssSelector;
            } else {
                // Score was not high enough to represent a matching label
                System.out.println("No elements with a label " + characteristics.get("Label") + " were found.");
                throw new ElementNotFoundException("Element was not found with label. " + flatten(characteristics));
            }
        }
        throw new ElementNotFoundException("No Label Elements were found in scope.");
    }

    /**
     *   showCharacteristics(Map<String, String>)
     *          - Log to Standard Output Stream the Locator Characteristics Provided
     *
     * @param characteristics - Characteristics to find a locator provided by the page object
     */
    private static void showCharacteristics(Map<String, String> characteristics) {
        System.out.println("Characteristics provided:");
        for (String key : characteristics.keySet()) {
            System.out.println("Characteristic: " + key + " token: " + characteristics.get(key));
        }
    }

    public static void serializeCache() {
        if (cache == null) {
            System.out.println("Request to Save the Cache recieved, but no cache was ever created.");
            return;
        }
        try (OutputStream output = new FileOutputStream(CACHE_PATH)) {
            cache.store(output, null);
        } catch (IOException io) {
            System.out.println("*** Unable to write Cache to a File ***");
        }
    }

    private static Properties cache;  // everything should happen dynamically, except for the cache that is loaded once and re-serialized at the end
    private static final String CACHE_PATH = "src/test/resources/cache/cache.properties";  //ToDO This should be a property of the testing framework
    private static final int FUZZY_SCORING_LIMIT = 50;  // You can tune auto discovery fuzzy matching by increasing this value so that matches below a
                                                       // specific value result in a fail (prevents false positives,ie, picking the closest match anyway when label shouldn't be found)
}