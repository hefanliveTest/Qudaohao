package qudaohao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;

public class Qdhtest {
	private static AndroidDriver driver;

	@BeforeSuite(alwaysRun = true)
	public void setUp() throws Exception {
		// set up appium
		File classpathRoot = new File(System.getProperty("user.dir"));
		File appDir = new File(classpathRoot, "apps");
		File app = new File(appDir, "hefanlive.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "Android Emulator");
		capabilities.setCapability("platformVersion", "6.0");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.starunion.hefantv");
		capabilities.setCapability("appActivity", "com.sagacreate.boxlunch.activity.SplashActivity");

		capabilities.setCapability("unicodeKeyboard", "True");
		capabilities.setCapability("resetKeyboard", "True");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}

	@AfterTest(alwaysRun = true)
	public void tearDown() throws Exception {
		// driver.quit();
		driver.removeApp("io.appium.android.ime");
	}

	@Test
	public void testcase() throws IOException, InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement e = wait.until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {

				return d.findElement(By.id("com.android.packageinstaller:id/permission_allow_button"));
			}

		});

		// 执行adb
		getDevices();

		// 执行adb 的logcat命令，测试
		/*
		 * Process process=Runtime.getRuntime().exec("adb logcat");
		 * process.waitFor(); InputStreamReader isr=new
		 * InputStreamReader(process.getInputStream());
		 * 
		 * Scanner sc=new Scanner(isr); while(sc.hasNext()){
		 * System.out.println(sc.next()); System.out.println("logcat-------"); }
		 */
		//////////////////////////////////////////////////////////////////////

		AppLibs a = new AppLibs();

		for (int i = 1; i <= 10; i++) {
			System.out.println("for start i=" + i);
			if (Xlsfile.isempty("hefanlive_testcase2", 1, i)) {
				break;
			}
			WebElement elementname = a.element(driver, Xlsfile.search(Xlsfile.readxls("hefanlive_testcase2", 1, i)));

			String execute = Xlsfile.readxls("hefanlive_testcase2", 2, i);
			String value = Xlsfile.readxls("hefanlive_testcase2", 3, i);
			a.execute(driver, elementname, execute, value);
		}

		driver.pinch(200, 500);

	}

	public String[] getDevices() {
		String command = "adb logcat";
		System.out.println(command);
		ArrayList devices = new ArrayList();

		try {
			Process process = Runtime.getRuntime().exec(command);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			String line = bufferedReader.readLine();
			while (line != null) {
				System.out.println(line);

				// 写入文件
				txtLog(line);
				
				//line = bufferedReader.readLine();
			}
			process.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (String[]) devices.toArray(new String[] {});

	}

	// 写入文件
	public static void txtLog(String str) {
		FileWriter fw = null;
		try {
			// 如果文件存在，则追加内容；如果文件不存在，则创建文件
			File f = new File("E:\\dd.txt");
			fw = new FileWriter(f, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		pw.println(str);
		pw.flush();
		try {
			fw.flush();
			pw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
