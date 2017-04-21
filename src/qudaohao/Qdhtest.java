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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.sun.jna.platform.unix.X11.XClientMessageEvent.Data;

import io.appium.java_client.android.AndroidDriver;

public class Qdhtest {
	private static AndroidDriver driver;

	// "app-LeshiYingYongShangDian-release.apk",,
	// "app-_360ShouJiZhuShou-release.apk",
	// "app-SouGouYingYongShangDian-release.apk"

	/*static String[] apks = { 
		"app-_360ShouJiZhuShou-release.apk",
		"app-A_SC_TouTiao-release.apk",
		"app-A_SC_ShenMa-release.apk",
		"app-BaiDuShouJiZhuShou-release.apk",
		"app-AnZhiYingYongShiChang-release.apk",
		"app-aliyun-release.apk"};*/
	
	
	static String[] apks = {
		"app-TencentYingYongBao-release.apk",
		"app-BaiDuShouJiZhuShou-release.apk",
		"app-XiaoMiYingYongShangDian-release.apk",
		"app-_360ShouJiZhuShou-release.apk",
		"app-HuaWeiYingYongShangDian-release.apk",
		"app-OppoYingYongShangDian-release.apk",
		"app-VivoYingYongShangDian-release.apk",
		"app-LeshiYingYongShangDian-release.apk",
		"app-PPZhuShou-release.apk",
		"app-LenovoLeShangDian-release.apk",
		"app-WanDouJia-release.apk",
		"app-SamsungYingYongShangDian-release.apk",
		"app-MeiZuYingYongShangDian-release.apk",
		"app-SouGouYingYongShangDian-release.apk",
		"app-AnZhiYingYongShiChang-release.apk",
		"app-HFLiveGuanWang-release.apk",
		"app-HFsem1-release.apk",
		"app-HFsem2-release.apk",
		"app-HFsem3-release.apk",
		"app-HFsem4-release.apk",
		"app-HFsem5-release.apk",
		"app-HFsem6-release.apk",
		"app-A_SC_TouTiao-release.apk",
		"app-A_SC_ShenMa-release.apk",
		"app-hf_test1-release.apk",
		"app-hf_test2-release.apk",
		"app-hf_test3-release.apk",
		"app-hf_test4-release.apk",
		"app-hf_test5-release.apk",
		"app-hf_h5-release.apk",
		"app-weibo-release.apk",
		"app-sina-release.apk",
		"app-youdao-release.apk",
		"app-hf_test6-release.apk",
		"app-hf_test7-release.apk",
		"app-hf_test8-release.apk",
		"app-hf_test9-release.apk",
		"app-hf_test10-release.apk",
		"app-hf_test11-release.apk",
		"app-aliyun-release.apk",
		"app-jinli-release.apk",
		"app-mumayi-release.apk",
		"app-hf_test12-release.apk",
		"app-hf_test13-release.apk",
		"app-hf_test14-release.apk",
		"app-hf_test15-release.apk",
		"app-hf_test16-release.apk",
		"app-hf_s1-release.apk",
		"app-hf_s2-release.apk",
		"app-hf_s3-release.apk",
		"app-hf_s4-release.apk",
		"app-hf_s5-release.apk",
		"app-hf_s6-release.apk",
		"app-hf_s7-release.apk",
		"app-hf_s8-release.apk",
		"app-hf_s9-release.apk",
		"app-hf_s10-release.apk",
		"app-hf_s11-release.apk",
		"app-hf_s12-release.apk",
		"app-hf_s13-release.apk",
		"app-hf_s14-release.apk",
		"app-hf_s15-release.apk",
		"app-hf_s16-release.apk",
		"app-hf_s17-release.apk",
		"app-hf_s18-release.apk",
		"app-hf_s19-release.apk",
		"app-hf_s20-release.apk",
		"app-hf_s21-release.apk",
		"app-hf_s22-release.apk",
		"app-hf_s23-release.apk",
		"app-hf_s24-release.apk",
		"app-hf_s25-release.apk",
		"app-hf_s26-release.apk",
		"app-hf_s27-release.apk",
		"app-hf_s28-release.apk",
		"app-hf_s29-release.apk",
		"app-hf_s30-release.apk",
		"app-hf_s31-release.apk",
		"app-hf_s32-release.apk",
		"app-hf_s33-release.apk",
		"app-hf_s34-release.apk",
		"app-hf_s35-release.apk",
		"app-hf_s36-release.apk",
		"app-hf_s37-release.apk",
		"app-hf_s38-release.apk",
		"app-hf_s39-release.apk",
		"app-hf_s40-release.apk",			
		"app-hf_s41-release.apk",
		"app-hf_s42-release.apk",
		"app-hf_s43-release.apk",
		"app-hf_s44-release.apk",
		"app-hf_s45-release.apk",
		"app-hf_s46-release.apk",
		"app-hf_s47-release.apk",
		"app-hf_s48-release.apk",
		"app-hf_s49-release.apk",
		"app-hf_s50-release.apk",
		"app-hf_s51-release.apk",
		"app-hf_s52-release.apk",
		"app-hf_s53-release.apk",
		"app-hf_s54-release.apk",
		"app-hf_s55-release.apk",
		"app-hf_s56-release.apk",
		"app-hf_s57-release.apk",
		"app-hf_s58-release.apk",
		"app-hf_s59-release.apk"};

	
	static int i = 87;
	static boolean flag = false;// �ܷ�ץ��������
	boolean apkFlag = false;// Apps���Ƿ��ж�Ӧ��apk
	static int[] badApk = new int[100];// ��һ��bad��apk�к�
	static int y = 0;//// ��¼��һ��bad��apk

	File classpathRoot = new File(System.getProperty("user.dir"));
	File appDir = new File(classpathRoot, "apps");

	File appLog = new File(classpathRoot, "qudaohao");
	File f = new File(appLog, "log.txt");

	// �ѵ�ǰ����ʱ��д��TXT�ļ�
	@BeforeSuite
	public void getTime() {
		// ��¼logץȡ��ʱ��
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateNowStr = sdf.format(d);

		FileWriter fw = null;
		try {
			// ����ļ����ڣ���׷�����ݣ�����ļ������ڣ��򴴽��ļ�
			// File f = new File("E:\\log.txt");
			File classpathRoot = new File(System.getProperty("user.dir"));
			File appLog = new File(classpathRoot, "qudaohao");
			File f = new File(appLog, "log.txt");
			fw = new FileWriter(f, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(fw);
		pw.println(dateNowStr);
		pw.flush();
		try {
			fw.flush();
			pw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void setUp() throws Exception {

		flag = false;
		apkFlag = false;

		// set up appium
		File app = new File(appDir, apks[i]);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("noSign", "True");
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "Android Emulator");
		capabilities.setCapability("platformVersion", "6.0");
		capabilities.setCapability("app", app.getAbsolutePath());

		capabilities.setCapability("appPackage", "com.starunion.hefantv");
		capabilities.setCapability("appActivity", "com.sagacreate.boxlunch.activity.SplashActivity");

		capabilities.setCapability("unicodeKeyboard", "True");
		capabilities.setCapability("resetKeyboard", "True");

		// ��ȡApps�����е�apk����
		String path = appDir.getAbsolutePath();// ��ȡapk�ĸ�Ŀ¼
		
		//System.out.println(path + "---·��");
		
		File appFiles = new File(path);
		// ��ȡ���ļ��е��ļ�������������
		File[] tempList = appFiles.listFiles();
		// �ж�
		for (int k = 0; k < tempList.length; k++) {
			if (apks[i].equals(tempList[k].getName())) {
				apkFlag = true;// �ҵ���Ӧ��apk������Ϊture
				break;
			}
		}

		if (apkFlag == true) {
			System.out.println();
			System.out.println("��ʼ��װ��" + (i+1) + "��apk������");
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		} else {
			System.out.println("��" + (i+1) + "��apk: " + apks[i] + " û���ҵ�");
			i++;
			setUp();
		}

		// driver.installApp(app.getAbsolutePath());

		/*
		 * //����USB��װ WebElement e =
		 * driver.findElement(By.id("com.miui.securitycenter:id/allow_button"));
		 * e.click();
		 */

		// System.out.println("apk��װ����");
		Thread.sleep(5000);// ��һ��sleep 5s,����ɾ������Ȼ����ץ��������
		testcase();
	}

	private void stopAdb() throws IOException {
		// ͨ������������ȡadb�����·��
		String adbPath = System.getenv("ANDROID_HOME") + "/platform-tools/adb.exe";
		File file = new File(adbPath);
		String command = "adb kill-server";
		Process process = Runtime.getRuntime().exec(command);
	}

	@AfterTest(alwaysRun = true)
	public void tearDown() throws Exception {

	}
	
	public void setUpFirstBad() throws Exception {
		// ����xls�ļ�����ȡ��һ��bad
		// int raw = Xlsfile.search("bad");// ����bad�����к�
		System.out.println("��һ��bad�����к�" + badApk[0]);
		File app = new File(appDir, apks[badApk[0] - 1]);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("noSign", "True");// ȥ��appium��ǩ��
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "Android Emulator");
		capabilities.setCapability("platformVersion", "6.0");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.starunion.hefantv");
		capabilities.setCapability("appActivity", "com.sagacreate.boxlunch.activity.SplashActivity");

		capabilities.setCapability("unicodeKeyboard", "True");
		capabilities.setCapability("resetKeyboard", "True");
		System.out.println("��ʼ��װ��һ��bad��apk");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

		/*
		 * //����USB��װ WebElement e =
		 * driver.findElement(By.id("com.miui.securitycenter:id/allow_button"));
		 * e.click();
		 */

		System.out.println("��һ��bad��apk��װ��ϣ�");
		// adbֹͣ����
		// stopAdb();

	}

	public void testcase() throws Exception {

		// ִ��adb
		getDevices();

		// WebDriverWait wait = new WebDriverWait(driver, 60);
		Thread.sleep(5000);// �ڶ���sleep 5s��������У�����ץ����

		if (flag == false) {
			System.out.println("��" + (i + 1) + "��apkû��ץ��������");
		}

		if(i<apks.length){
			System.out.println("**************** ��" + (i + 1) + "��apk�� " + apks[i] + " ������� ****************");
			System.out.println();
		}
		
		driver.removeApp("com.starunion.hefantv");
		driver.quit();
		i++;
		if (i < apks.length) {
			Thread.sleep(5000);// ������sleep 5s������ɾ��������Ӱ��
			setUp();
		} else {
			setUpFirstBad();
		}

	}


	public static String compareQDH() {
		String content = Xlsfile.readxls("QDH", 3, (i + 1));
		return content;
	}

	public void getDevices() {
		// ͨ������������ȡadb�����·��
		String adbPath = System.getenv("ANDROID_HOME") + "/platform-tools/adb.exe";
		File file = new File(adbPath);
		String command = "adb logcat";
		execCommand(command);

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

						// �ж��Ƿ���&q_channel=
						if (line.contains("&q_channel=")) {

							flag = true;

							String[] tmp = line.split("&q_channel="); // ����--��ÿ�����ݲ�ֳ�һ������
							System.out.println(tmp[tmp.length - 1]);// ��ȡ���һ������ʱ������
							txtLog(tmp[tmp.length - 1]);// ����������
							String content = compareQDH();
							if (content.equals(tmp[tmp.length - 1])) {
								Xlsfile.writexls("QDH", 4, (i + 1), "OK");
								System.out.println(
										"������:" + apks[i] + " &&& " + content + " = " + tmp[tmp.length - 1] + "	����OK");
							} else {
								
								//�ж��Ƿ��ǰ�װ��һ��bad��apk
								if(i<apks.length){
									Xlsfile.writexls("QDH", 4, (i + 1), "bad");
									System.out.println(
										"������:" + apks[i] + " &&& " + content + " != " + tmp[tmp.length - 1] + "	����bad");
									badApk[y] = i + 1;
									y++;
								}
								
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

	// д���ļ�
	public static void txtLog(String str) {
		FileWriter fw = null;
		try {
			// ����ļ����ڣ���׷�����ݣ�����ļ������ڣ��򴴽��ļ�
			// File f = new File("E:\\log.txt");
			File classpathRoot = new File(System.getProperty("user.dir"));
			File appLog = new File(classpathRoot, "qudaohao");
			File f = new File(appLog, "log.txt");
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
	
	//���Ե���������ȡ����Ȩ���ύ������ˣ�

}