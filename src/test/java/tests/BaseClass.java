package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import common.CommonUtility;
import io.appium.java_client.MobileElement;
import screens.CartScreen;
import screens.HomeScreen;
import screens.ProductScreen;

public class BaseClass extends CommonUtility{
	
	public HomeScreen homeScreen = new HomeScreen();
	public ProductScreen productScreen = new ProductScreen();
	public CartScreen cartScreen = new CartScreen();
	
	@BeforeTest
	public void setup() {
		
		//Initialize capabilities and launch the application
		initializeCapabilities();
		System.out.println("Launching the application");
		
	}
	
	/*
	 * This test is to search for a product, add it to the cart, and move to checkout after confirming the details
	*/
	@Test
	public void searchProductAndCheckoutInAmazon() throws Exception {
		
		//Wait added for the first screen to load
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		MobileElement skipSignIn = driver.findElement(By.id("com.amazon.mShop.android.shopping:id/skip_sign_in_button"));
		skipSignIn.click();
		
		//search for a product using the search bar option
		homeScreen.enterSearchText(readData("searchAndAddProduct", "searchProduct"));
		
		//select from search results list
		homeScreen.selectFromSearchResults();
		
		//product details screen - capture the details
		String selectedProductName = productScreen.getProductDetails();
		
		//add the product to the cart
		productScreen.addToCart();
		
		//go to the cart and verify product details
		Boolean isProductCorrect = cartScreen.verifyProductInCart(selectedProductName);
		Assert.assertTrue(isProductCorrect, "Product added to the cart do not match the selected product");
		
		//checkout
		cartScreen.proceedToCheckout();
		
	}
	
	@AfterTest
	public void teardown() {
		//quit the driver
		driver.quit();
	}

}
