package qudaohao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
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
	// private static AndroidDriver[] driver;
	private static AndroidDriver driver;

	String[] apks = { "hefanlive.apk", "app-SouGouYingYongShangDian-release.apk" };
	static int i = 0;

	File classpathRoot = new File(System.getProperty("user.dir"));
	File appDir = new File(classpathRoot, "apps");

	@Test
	public void setUp() throws Exception {
		// set up appium
		// File classpathRoot = new File(System.getProperty("user.dir"));
		// File appDir = new File(classpathRoot, "apps");
		File app = new File(appDir, apks[i]);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "Android Emulator");
		capabilities.setCapability("platformVersion", "5.1.1");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.starunion.hefantv");
		capabilities.setCapability("appActivity", "com.sagacreate.boxlunch.activity.SplashActivity");

		capabilities.setCapability("unicodeKeyboard", "True");
		capabilities.setCapability("resetKeyboard", "True");
		System.out.println("%%%%%%%%%%%%%");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		System.out.println("****************");

		testcase();

	}

	// @Test
	public void testcase() throws Exception {

		// 执行adb
		getDevices();

		System.out.println("web");
		WebDriverWait wait = new WebDriverWait(driver, 60);
		Thread.sleep(30000);

		System.out.println(i + "=======");
		driver.removeApp("com.starunion.hefantv");
		driver.quit();
		i++;
		System.out.println(i + "&&&&&&&&&&&&");
		if (i < 2) {
			Thread.sleep(5000);
			setUp();
		}

	}

	@AfterTest(alwaysRun = true)
	public void tearDown() throws Exception {

	}

	public static String compareQDH() {
		String content = Xlsfile.readxls("QDH", 3, (i + 1));
		return content;
	}

	public void getDevices() {
		// 通过环境变量获取adb程序的路径
		String adbPath = System.getenv("ANDROID_HOME") + "/platform-tools/adb.exe";
		File file = new File(adbPath);
		String command = "adb logcat";
		execCommand(command);

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

						// 判断是否有&q_channel=
						if (line.contains("&q_channel=")) {
							String[] tmp = line.split("&q_channel="); // 根据--将每行数据拆分成一个数组
							System.out.println(tmp[tmp.length - 1]);// 获取最后一个数即时渠道号
							txtLog(tmp[tmp.length - 1]);// 保存渠道号
							String content = compareQDH();
							if (content.equals(tmp[tmp.length - 1])) {
								Xlsfile.writexls("QDH", 4, (i + 1), "OK");
							} else {
								Xlsfile.writexls("QDH", 4, (i + 1), "bad");
							}

							break;
						}
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

}
