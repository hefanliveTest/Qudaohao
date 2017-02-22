package qudaohao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

	/*
	 * String[] apks = { "com.starunion.hefanlive" }; int i = 0;
	 */

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
		capabilities.setCapability("platformVersion", "5.1.1");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.starunion.hefantv");
		// capabilities.setCapability("appPackage", apks[i]);
		capabilities.setCapability("appActivity", "com.sagacreate.boxlunch.activity.SplashActivity");

		capabilities.setCapability("unicodeKeyboard", "True");
		capabilities.setCapability("resetKeyboard", "True");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	}

	@AfterTest(alwaysRun = true)
	public void tearDown() throws Exception {
		// driver.quit();
		// driver.removeApp("io.appium.android.ime");

//		driver.removeApp("com.starunion.hefantv");
		
		// 获取.txt中log中渠道号
		getQDH();
		
		Thread.sleep(5000);
		
		driver.removeApp("com.starunion.hefantv");
		
		// i++;
		// setUp();
	}

	public String compareQDH() {
		String content = Xlsfile.readxls("QDH", 3, 17);
		return content;
	}

	public void getQDH() throws IOException {
		File file = new File("E:\\log.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = null;
		while ((line = br.readLine()) != null) { // 一次读取一行
			System.out.println(line);
			//判断是否有&q_channel=
			if(line.contains("&q_channel=")){
				String[] tmp = line.split("&q_channel="); // 根据--将每行数据拆分成一个数组
				System.out.println(tmp[tmp.length-1]);//获取最后一个数即时渠道号
				String content = compareQDH();
				if(content.equals(tmp[tmp.length-1])){
					Xlsfile.writexls("QDH", 4, 17, "OK");
				}else{
					Xlsfile.writexls("QDH", 4, 17, "bad");
				}
				
				break;
			}
			
			/*for (int i = 0; i < tmp.length; i++) {
				System.out.println("\t" + tmp[i]); // tmp[1]就是你想要的bb
			}
			if (line.endsWith("bb")) {
				// 判断本行是否以bb结束
				System.out.println("这是我想要的: " + tmp[1]);
			}*/
		}
		br.close();

	}

	@Test
	public void testcase() throws IOException, InterruptedException {

		// 执行adb
		getDevices();

		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement e = wait.until(new ExpectedCondition<WebElement>() {
			public WebElement apply(WebDriver d) {

				return d.findElement(By.id("com.android.packageinstaller:id/permission_allow_button"));
			}

		});

		AppLibs a = new AppLibs();

		for (int i = 1; i <= 1; i++) {
			System.out.println("for start i=" + i);
			if (Xlsfile.isempty("hefanlive_testcase2", 1, i)) {
				break;
			}
			WebElement elementname = a.element(driver, Xlsfile.search(Xlsfile.readxls("hefanlive_testcase2", 1, i)));

			String execute = Xlsfile.readxls("hefanlive_testcase2", 2, i);
			String value = Xlsfile.readxls("hefanlive_testcase2", 3, i);
			a.execute(driver, elementname, execute, value);
		}

		driver.pinch(200, 500);// 缩小屏幕

	}

	public void getDevices() {
		// 通过环境变量获取adb程序的路径
		String adbPath = System.getenv("ANDROID_HOME") + "/platform-tools/adb.exe";
		File file = new File(adbPath);
		String command = "adb logcat";
		execCommand(command);
		/*
		 * String adbPath = System.getenv("ANDROID_HOME") +
		 * "/platform-tools/adb.exe"; File file = new File(adbPath); if
		 * (file.exists()) { System.out.println("请输入adb命令:"); Scanner scanner =
		 * new Scanner(System.in); String command = scanner.nextLine(); //
		 * 开始执行命令 execCommand(command); } else {
		 * System.out.println("尚未配置AndroidSDK"); }
		 */

	}

	// 执行命令函数
	public static void execCommand(String cmd) {
		BufferedReader inputStream = null;
		BufferedReader errorStream = null;
		try {
			Process process = Runtime.getRuntime().exec(cmd);
			// 获取正确的输入流
			inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
			// 起新线程来将结果输出
			readLine(inputStream);
			// 获取错误的输入流
			errorStream = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			// 起新线程读取错误流
			readLine(errorStream);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 读取返回的结果
	private static void readLine(final BufferedReader br) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				String line = null;
				StringBuffer str = new StringBuffer();
				try {
					// 循环读取输入流
					while ((line = br.readLine()) != null) {
						// System.out.println("@@@" + line);// 控制台输出
						txtLog(line);

					}
					br.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	// 写入文件
	public static void txtLog(String str) {
		FileWriter fw = null;
		try {
			// 如果文件存在，则追加内容；如果文件不存在，则创建文件
			File f = new File("E:\\log.txt");
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

	// 获取log后，进行对比
}
