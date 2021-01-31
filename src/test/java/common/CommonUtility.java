package common;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.Dictionary;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.opencsv.CSVReader;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class CommonUtility {
	
	//This is a common utility class for reusable functions
	
	public DesiredCapabilities caps = new DesiredCapabilities();
	public Properties properties;
	public static AppiumDriver<MobileElement> driver;
	
	public static String capabilities_file = "src\\test\\resources\\capabilities.properties";
	public static String testdata_file = "src\\test\\resources\\testdata.csv";
	public static Dictionary<String, Dictionary<String, String>> readCSV = null;
	
	/*
	 * This method defines the capabilities viz. device and application details
	 * Pre-requisite - Appium server should be started on port defined in capabilities.properties file
	 * No return value
	*/
	public void initializeCapabilities() {
		try {
			properties = lookUpProperties(capabilities_file);
			caps.setCapability(MobileCapabilityType.PLATFORM_NAME, properties.getProperty("platform_name"));
			caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, properties.getProperty("platform_version"));
			caps.setCapability(MobileCapabilityType.DEVICE_NAME, properties.getProperty("device_name"));
			caps.setCapability(MobileCapabilityType.UDID, properties.getProperty("UDID"));
			caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
			caps.setCapability(MobileCapabilityType.APP, properties.getProperty("app"));
			caps.setCapability("appPackage", properties.getProperty("app_package"));
			caps.setCapability("appActivity", properties.getProperty("app_activity"));
			
			URL url = new URL(properties.getProperty("server_url"));
			driver = new AppiumDriver<MobileElement>(url, caps);			
		}catch(Exception exp) {
			System.out.println(exp.getCause());
			System.out.println(exp.getMessage());
			exp.printStackTrace();
		}
	}
	
	/*
	 * This method reads the property file
	 * Parameters - location and name of the file
	 * Return - Properties object
	*/
	public static Properties lookUpProperties(String fileName) {
		InputStream input = null;
		Properties prop = new Properties();
		try {
			input = new FileInputStream(fileName);
			prop.load(input);
			input.close();
			return prop;			
		}catch(Exception exp) {
			System.out.println(exp.getCause());
			System.out.println(exp.getMessage());
			exp.printStackTrace();
		}
		return null;
	}
	
	/*
	 * This method reads the values from the testdata.csv
	 * Parameters - scenario name and the key
	 * Return - key's respective value
	*/
	public String readData(String scenarioName, String key) throws Exception {

		CSVReader reader = new CSVReader(new FileReader(testdata_file));

		//Traverse each row to find the scenarioName and extract the value for key mentioned in the parameter
		List<String[]> allRows = reader.readAll();
		for(String[] row : allRows) {
			for (int i=0; i<row.length ; ++i) {
				if (row[i].trim().equals(key)) {
					return (row[i+1].trim());
				}
			}
		}
		return null;
	}
	
	/*
	 * This method scrolls the screen up, say, to make the objects towards the bottom of the page visible 
	 * Parameters - the initial and the final x an y coordinates, similar to how a finger moves in order to swipe on a mobile screen 
	 * No return value
	*/
	public void scrollUp(int startX, int startY, int endX, int endY) {
		
		TouchAction touchAction = new TouchAction(driver);
	    PointOption pointStart = PointOption.point(startX, startY);
	    PointOption pointEnd = PointOption.point(endX, endY);
		
	    WaitOptions waitOption = WaitOptions.waitOptions(Duration.ofMillis(1000));
	    touchAction.press(pointStart).waitAction(waitOption).moveTo(pointEnd).release().perform();
	    
	}


}