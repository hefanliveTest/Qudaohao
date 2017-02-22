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
		
		// ��ȡ.txt��log��������
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
		while ((line = br.readLine()) != null) { // һ�ζ�ȡһ��
			System.out.println(line);
			//�ж��Ƿ���&q_channel=
			if(line.contains("&q_channel=")){
				String[] tmp = line.split("&q_channel="); // ����--��ÿ�����ݲ�ֳ�һ������
				System.out.println(tmp[tmp.length-1]);//��ȡ���һ������ʱ������
				String content = compareQDH();
				if(content.equals(tmp[tmp.length-1])){
					Xlsfile.writexls("QDH", 4, 17, "OK");
				}else{
					Xlsfile.writexls("QDH", 4, 17, "bad");
				}
				
				break;
			}
			
			/*for (int i = 0; i < tmp.length; i++) {
				System.out.println("\t" + tmp[i]); // tmp[1]��������Ҫ��bb
			}
			if (line.endsWith("bb")) {
				// �жϱ����Ƿ���bb����
				System.out.println("��������Ҫ��: " + tmp[1]);
			}*/
		}
		br.close();

	}

	@Test
	public void testcase() throws IOException, InterruptedException {

		// ִ��adb
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

		driver.pinch(200, 500);// ��С��Ļ

	}

	public void getDevices() {
		// ͨ������������ȡadb�����·��
		String adbPath = System.getenv("ANDROID_HOME") + "/platform-tools/adb.exe";
		File file = new File(adbPath);
		String command = "adb logcat";
		execCommand(command);
		/*
		 * String adbPath = System.getenv("ANDROID_HOME") +
		 * "/platform-tools/adb.exe"; File file = new File(adbPath); if
		 * (file.exists()) { System.out.println("������adb����:"); Scanner scanner =
		 * new Scanner(System.in); String command = scanner.nextLine(); //
		 * ��ʼִ������ execCommand(command); } else {
		 * System.out.println("��δ����AndroidSDK"); }
		 */

	}

	// ִ�������
	public static void execCommand(String cmd) {
		BufferedReader inputStream = null;
		BufferedReader errorStream = null;
		try {
			Process process = Runtime.getRuntime().exec(cmd);
			// ��ȡ��ȷ��������
			inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
			// �����߳�����������
			readLine(inputStream);
			// ��ȡ�����������
			errorStream = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			// �����̶߳�ȡ������
			readLine(errorStream);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ��ȡ���صĽ��
	private static void readLine(final BufferedReader br) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				String line = null;
				StringBuffer str = new StringBuffer();
				try {
					// ѭ����ȡ������
					while ((line = br.readLine()) != null) {
						// System.out.println("@@@" + line);// ����̨���
						txtLog(line);

					}
					br.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	// д���ļ�
	public static void txtLog(String str) {
		FileWriter fw = null;
		try {
			// ����ļ����ڣ���׷�����ݣ�����ļ������ڣ��򴴽��ļ�
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

	// ��ȡlog�󣬽��жԱ�
}
