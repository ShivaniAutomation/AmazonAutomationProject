package screens;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import common.CommonUtility;
import objects.HomeScreenObjectRepo;

public class HomeScreen extends CommonUtility{
	
	/*
	 * This method enters a text in to the search bar
	 * Parameters - text string to be searched
	 * Return - None
	*/
	public void enterSearchText(String searchText) {
		
		driver.findElement(By.id(HomeScreenObjectRepo.searchBox));
		driver.findElement(By.id(HomeScreenObjectRepo.searchBox)).click();
		driver.findElement(By.id(HomeScreenObjectRepo.searchBox)).clear();
		driver.findElement(By.id(HomeScreenObjectRepo.searchBox)).sendKeys(searchText);
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).perform();
		
	}
	
	/*
	 * This method selects a product from the result list of searched product
	 * Parameters - None
	 * Return - None
	*/
	public void selectFromSearchResults() {
		
		//click on the first element of search text's suggestions
		//driver.findElement(By.id(HomeScreenObjectRepo.searchSuggestion)).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//select any product from the list displayed for the searched text
		driver.findElement(By.id(HomeScreenObjectRepo.searchSelect)).click();
		
	}

}
