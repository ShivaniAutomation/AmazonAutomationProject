package screens;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import common.CommonUtility;
import objects.CartScreenObjectRepo;
import objects.HomeScreenObjectRepo;
import objects.ProductScreenObjectRepo;

public class ProductScreen extends CommonUtility{
	
	/*
	 * This method captures the product details
	 * Parameters -None
	 * Return - name of the product as string
	*/
	public String getProductDetails() {
		
		String prodName = driver.findElement(By.xpath(ProductScreenObjectRepo.productName)).getText();
		return prodName;
		
	}
	
	/*
	 * This method adds the product to the cart
	 * Parameters - None
	 * Return - None
	*/
	public void addToCart() {
		
		scrollUp(50, 1500, 50, 10);
		driver.findElement(By.xpath(ProductScreenObjectRepo.addToCartButton)).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath(ProductScreenObjectRepo.closeAddedToCartButton)).click();
		
	}

}
