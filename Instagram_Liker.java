import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Instagram_Liker
{
  public static void main(String[] args)
  {
	// All the class names which change frequently
	String loginBox = "_2hvTZ";
	String loginButton = "_5f5mN";
	String mastName = "_1SP8R";
	String photoName = "tiVCN";
	String heart = "Szr5J";
	String stories = "ho19H";
	String username = "Enter Username Here";
	String password = "Enter Password Here";
	  
	// Locate the selenium chromedriver file 
    System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Selenium\\chromedriver.exe");
    // Mute chrome before initiation
    ChromeOptions chrome_options = new ChromeOptions();
    chrome_options.addArguments("--mute-audio");
    // Open chrome and login to instagram
    WebDriver driver = new ChromeDriver(chrome_options);
    driver.get("https://www.instagram.com/accounts/login/");
    JavascriptExecutor executor = (JavascriptExecutor)driver;
    driver.findElements(By.className(loginBox)).get(0).sendKeys(new CharSequence[] { username });
    driver.findElements(By.className(loginBox)).get(1).sendKeys(new CharSequence[] { password });
    executor.executeScript("document.getElementsByClassName('" + loginButton + "')[0].click();", new Object[0]);
    
    // Scroll to extend page, return to top
    for (int i = 0; i < 25; i++)
    {
      executor.executeScript("window.scrollTo(0,document.body.scrollHeight);", new Object[0]);
      try
      {
        Thread.sleep(1000L);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    executor.executeScript("window.scrollTo(0,0);", new Object[0]);
    
    
    //  Initialize elements for the like algorithm
    Actions actions = new Actions(driver);
    WebElement mast = driver.findElements(By.className(mastName)).get(0);
    int mastSize;
    int tracker = 0; 
    List<WebElement> photoList;
    WebElement photoButton;
    
    
    // Loop through a reasonable amount of times
    for (int i=0; i<50 ; i++)
    {
    	// Get a list of all available photos in the page (there should be ~6)
		photoList = mast.findElements(By.className(photoName));
		mastSize = mast.findElements(By.className(photoName)).size();
		// Loop through these ~6 photos
		for (int j=0 ; j<mastSize-1 ; j++) {
			// From the list of 6 photos, get the j'th photo and scroll to this element
			photoButton = photoList.get(j);
					    
	        // Determine whether the element has a "Like" or "Unlike" inner text
			if (photoButton.findElements(By.className(heart)).get(0).getAttribute("innerHTML").equals("Like")) {
				// If "Like" inner text, then click
				photoButton.findElements(By.className(heart)).get(0).click();
			}
		}
		
		// Before exit, scroll to the last photo
		actions.moveToElement(photoList.get(photoList.size()-1)).perform();
    }
    
    // Click stories and scroll through.
    if(driver.findElements(By.className(stories)).size()>0)
    	driver.findElements(By.className(stories)).get(0).click();
  }
}

