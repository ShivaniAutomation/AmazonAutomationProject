package screens;

import org.openqa.selenium.By;

import common.CommonUtility;
import objects.CartScreenObjectRepo;

public class CartScreen extends CommonUtility{
	
	/*
	 * This method verifies the correct product is added to the cart
	 * Parameters - name of the product that was initially selected
	 * Return - true/false depending on the verification result
	*/
	public boolean verifyProductInCart(String prodName) {
		
		driver.findElement(By.id(CartScreenObjectRepo.cartImage)).click();
		
		String productInCart = driver.findElement(By.xpath(CartScreenObjectRepo.cartProductName)).getText();
		if (productInCart.equals(prodName)) {
			return true;
		}
		return false;
		
	}
	
	/*
	 * This method clicks on the checkout button
	 * Parameters - None
	 * Return - None
	*/
	public void proceedToCheckout() {
		
		driver.findElement(By.xpath(CartScreenObjectRepo.checkoutButton)).click();
		
	}

}
