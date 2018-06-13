package com.dice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();

		driver.manage().window().fullscreen();

		// set universal wait time in case web page is slow
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		String url = "http://dice.com";
		driver.get(url);

		String actualTitle = driver.getTitle();
		String expectedTitle = "Job Search for Technology Professionals | Dice.com ";

		if (actualTitle.equals(expectedTitle)) {
			System.out.println("Step Pass");
		} else {
			System.out.println("Step Fail");
		//	throw new RuntimeException("Step Fail...");
		}

		String keyword = "SDET";
		driver.findElement(By.id("search-field-keyword")).clear();
		driver.findElement(By.id("search-field-keyword")).sendKeys(keyword);
		String location = "20707";
		driver.findElement(By.id("search-field-location")).sendKeys(location);
		driver.findElement(By.id("findTechJobs")).submit();

		String count = driver.findElement(By.id("posiCountId")).getText();
		System.out.println("positions: " + count);

		int countResult = Integer.parseInt(count.replace(",", ""));

		if (countResult > 0) {
			System.out.println(
					"Step Fail - Keyword: " + keyword + " search returned " + countResult + " results in " + location);
		}

//		driver.close();
	}

}
