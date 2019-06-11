package amazon;
 
import io.appium.java_client.android.AndroidDriver;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
 
public class StartApplication {
 
 private static AndroidDriver driver;
 public static void main(String[] args) throws MalformedURLException, InterruptedException {
 
 File classpathRoot = new File(System.getProperty("user.dir"));
 File appDir = new File(classpathRoot, "/Apps/Amazon/");
 File app = new File(appDir, "Amazon.apk");
 
 DesiredCapabilities capabilities = new DesiredCapabilities();
 capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
 capabilities.setCapability("deviceName", "Galaxy A3 (2016)");
 capabilities.setCapability("platformVersion", "7.0"); // Galaxy A3 (2016)
 capabilities.setCapability("platformName", "Android");
 capabilities.setCapability("app", app.getAbsolutePath());
 capabilities.setCapability("appPackage", "com.amazon.mShop.android.shopping");
 capabilities.setCapability("appActivity", "com.amazon.mShop.home.HomeActivity");
 //capabilities.setCapability("appActivity", "com.amazon.mShop.startup.StartupLocalizationSelectionActivity");

//com.amazon.mShop.android.shopping/com.amazon.mShop.home.HomeActivity
//com.amazon.mShop.android.shopping/com.amazon.mShop.startup.StartupLocalizationSelectionActivity 
//com.amazon.mShop.home.HomeActivity
//com.amazon.mShop.startup.StartupLocalizationSelectionActivity
//com.amazon.mShop.splashscreen.StartupActivity
//com.amazon.mShop.home.web.MShopWebGatewayActivity
 
 
  
 driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
 driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
 Thread.sleep(10000);
 
 //driver.findElementByXPath("//android.widget.Button[@content-desc='¿Ï·á']").click();
 //Thread.sleep(3000);

 driver.findElementById("com.amazon.mShop.android.shopping:id/skip_sign_in_button").click();
 Thread.sleep(3000);

 driver.findElementById("com.amazon.mShop.android.shopping:id/rs_search_src_text").click();
 Thread.sleep(3000);
 
 //driver.findElementById("com.amazon.mShop.android.shopping:id/rs_search_src_text").sendKeys("ºÏ\n");
 driver.findElementById("com.amazon.mShop.android.shopping:id/rs_search_src_text").sendKeys("books\n");
 Thread.sleep(3000);
 
 // This is to kill the Android driver
 Thread.sleep(10000);
 driver.quit();
 
  
// for (int i=0; i<7; i++) {
	//Click button menu on Helloworld02 menu
	 //driver.findElementById("com.example.toronto.helloworld02:id/btn_01").click();
	 //Thread.sleep(1000);
// }

// for (int i=0; i<10; i++) {
	 //driver.findElementById("com.example.toronto.helloworld02:id/btn_02").click();
	 //Thread.sleep(1000);
// }
//  
// // Click on Main menu
// driver.findElementByClassName("android.widget.Button").click();
// Thread.sleep(1000);
//
// // Using Xpath ( Class Name + Text Name )
// driver.findElementByXPath("//android.widget.Button[@text='BUTTON 02']").click();
// Thread.sleep(1000);
// 
// // Using Xpath & Sibling
// driver.findElementByXPath("//android.widget.Button[@text='BUTTON 02']/following-sibling::android.widget.EditText").click();
// Thread.sleep(1000); 
// 
// // Using Xpath ( Class Name + Text Name )
// driver.findElementByXPath("//android.widget.Button[@text='BUTTON 01']").click();
// Thread.sleep(1000);
// 
// // Using Xpath & Sibling
// driver.findElementByXPath("//android.widget.Button[@text='BUTTON 01']/following-sibling::android.widget.EditText").click();
// Thread.sleep(1000); 
// 
// driver.findElementByXPath("//android.widget.Button[@text='BUTTON 01']/following-sibling::android.widget.EditText").sendKeys("My books");
 
////Entering Password using Xpath & Sibling strategy
//driver.findElementByXPath("//android.view.View[@content-desc='Password']/following-sibling::android.view.View/android.widget.EditText").sendKeys("Your_Password");
//driver.findElementByXPath("//android.widget.Button[@content-desc='BUTTON 02']/following-sibling::android.view.View/android.widget.EditText").click();
 
 
 // Click on Home link under Main menu
//Not support - driver.findElement(By.name("Home")).click();
//// Click on Sign In link on the Home Screen
//Not support - driver.findElementByName("BUTTON 02").click();
//// Entering UserName using Parent node strategy
//WebElement parentElement = driver.findElement(By.name("Amazon Sign In"));
//List<WebElement> childElements = parentElement.findElements(By.className("android.view.View"));
//WebElement mainElement = childElements.get(4);
//mainElement.findElement(By.className("android.widget.EditText")).sendKeys("Your_UserName");
 
//// Click on Sign In button
//driver.findElement(By.name("Sign in")).click();



 }
 
}

/*
package amazon;
 
import io.appium.java_client.android.AndroidDriver;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
 
public class StartApplication {
 
 private static AndroidDriver driver;
 public static void main(String[] args) throws MalformedURLException, InterruptedException {
 
 File classpathRoot = new File(System.getProperty("user.dir"));
 File appDir = new File(classpathRoot, "/Apps/Amazon/");
 File app = new File(appDir, "helloworld02.apk");
 
 DesiredCapabilities capabilities = new DesiredCapabilities();
 capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
 capabilities.setCapability("deviceName", "Galaxy A3 (2016)");
 capabilities.setCapability("platformVersion", "7.0"); // Galaxy A3 (2016)
 capabilities.setCapability("platformName", "Android");
 capabilities.setCapability("app", app.getAbsolutePath());
 capabilities.setCapability("appPackage", "com.example.toronto.helloworld02");
 capabilities.setCapability("appActivity", "com.example.toronto.helloworld02.MainActivity");

  
 driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
 driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
 Thread.sleep(2000);
  
// for (int i=0; i<7; i++) {
	//Click button menu on Helloworld02 menu
	 //driver.findElementById("com.example.toronto.helloworld02:id/btn_01").click();
	 //Thread.sleep(1000);
// }

// for (int i=0; i<10; i++) {
	 //driver.findElementById("com.example.toronto.helloworld02:id/btn_02").click();
	 //Thread.sleep(1000);
// }
  
 // Click on Main menu
 driver.findElementByClassName("android.widget.Button").click();
 Thread.sleep(1000);

 // Using Xpath ( Class Name + Text Name )
 driver.findElementByXPath("//android.widget.Button[@text='BUTTON 02']").click();
 Thread.sleep(1000);
 
 // Using Xpath & Sibling
 driver.findElementByXPath("//android.widget.Button[@text='BUTTON 02']/following-sibling::android.widget.EditText").click();
 Thread.sleep(1000); 
 
 // Using Xpath ( Class Name + Text Name )
 driver.findElementByXPath("//android.widget.Button[@text='BUTTON 01']").click();
 Thread.sleep(1000);
 
 // Using Xpath & Sibling
 driver.findElementByXPath("//android.widget.Button[@text='BUTTON 01']/following-sibling::android.widget.EditText").click();
 Thread.sleep(1000); 
 
 driver.findElementByXPath("//android.widget.Button[@text='BUTTON 01']/following-sibling::android.widget.EditText").sendKeys("My books");
 
////Entering Password using Xpath & Sibling strategy
//driver.findElementByXPath("//android.view.View[@content-desc='Password']/following-sibling::android.view.View/android.widget.EditText").sendKeys("Your_Password");
//driver.findElementByXPath("//android.widget.Button[@content-desc='BUTTON 02']/following-sibling::android.view.View/android.widget.EditText").click();
 
 
 // Click on Home link under Main menu
//Not support - driver.findElement(By.name("Home")).click();
//// Click on Sign In link on the Home Screen
//Not support - driver.findElementByName("BUTTON 02").click();
//// Entering UserName using Parent node strategy
//WebElement parentElement = driver.findElement(By.name("Amazon Sign In"));
//List<WebElement> childElements = parentElement.findElements(By.className("android.view.View"));
//WebElement mainElement = childElements.get(4);
//mainElement.findElement(By.className("android.widget.EditText")).sendKeys("Your_UserName");
 
//// Click on Sign In button
//driver.findElement(By.name("Sign in")).click();


 
 // This is to kill the Android driver
 Thread.sleep(10000);
 driver.quit();
 
 }
 
}
*/