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
	static boolean flag = false;// 能否抓到渠道号
	boolean apkFlag = false;// Apps中是否有对应的apk
	static int[] badApk = new int[100];// 第一个bad的apk行号
	static int y = 0;//// 记录第一次bad的apk

	File classpathRoot = new File(System.getProperty("user.dir"));
	File appDir = new File(classpathRoot, "apps");

	File appLog = new File(classpathRoot, "qudaohao");
	File f = new File(appLog, "log.txt");

	// 把当前运行时间写入TXT文件
	@BeforeSuite
	public void getTime() {
		// 记录log抓取的时间
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateNowStr = sdf.format(d);

		FileWriter fw = null;
		try {
			// 如果文件存在，则追加内容；如果文件不存在，则创建文件
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

		// 获取Apps中所有的apk名字
		String path = appDir.getAbsolutePath();// 获取apk的根目录
		
		//System.out.println(path + "---路径");
		
		File appFiles = new File(path);
		// 获取该文件夹的文件数，存入数组
		File[] tempList = appFiles.listFiles();
		// 判断
		for (int k = 0; k < tempList.length; k++) {
			if (apks[i].equals(tempList[k].getName())) {
				apkFlag = true;// 找到对应的apk则设置为ture
				break;
			}
		}

		if (apkFlag == true) {
			System.out.println();
			System.out.println("开始安装第" + (i+1) + "个apk。。。");
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		} else {
			System.out.println("第" + (i+1) + "个apk: " + apks[i] + " 没有找到");
			i++;
			setUp();
		}

		// driver.installApp(app.getAbsolutePath());

		/*
		 * //允许USB安装 WebElement e =
		 * driver.findElement(By.id("com.miui.securitycenter:id/allow_button"));
		 * e.click();
		 */

		// System.out.println("apk安装启动");
		Thread.sleep(5000);// 第一次sleep 5s,可以删除，仍然可以抓到渠道号
		testcase();
	}

	private void stopAdb() throws IOException {
		// 通过环境变量获取adb程序的路径
		String adbPath = System.getenv("ANDROID_HOME") + "/platform-tools/adb.exe";
		File file = new File(adbPath);
		String command = "adb kill-server";
		Process process = Runtime.getRuntime().exec(command);
	}

	@AfterTest(alwaysRun = true)
	public void tearDown() throws Exception {

	}
	
	public void setUpFirstBad() throws Exception {
		// 查找xls文件，获取第一个bad
		// int raw = Xlsfile.search("bad");// 返回bad所在行号
		System.out.println("第一个bad所在行号" + badApk[0]);
		File app = new File(appDir, apks[badApk[0] - 1]);
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("noSign", "True");// 去除appium的签名
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "Android Emulator");
		capabilities.setCapability("platformVersion", "6.0");
		capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability("appPackage", "com.starunion.hefantv");
		capabilities.setCapability("appActivity", "com.sagacreate.boxlunch.activity.SplashActivity");

		capabilities.setCapability("unicodeKeyboard", "True");
		capabilities.setCapability("resetKeyboard", "True");
		System.out.println("开始安装第一个bad的apk");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

		/*
		 * //允许USB安装 WebElement e =
		 * driver.findElement(By.id("com.miui.securitycenter:id/allow_button"));
		 * e.click();
		 */

		System.out.println("第一个bad的apk安装完毕！");
		// adb停止工作
		// stopAdb();

	}

	public void testcase() throws Exception {

		// 执行adb
		getDevices();

		// WebDriverWait wait = new WebDriverWait(driver, 60);
		Thread.sleep(5000);// 第二次sleep 5s，必须得有，否则抓不到

		if (flag == false) {
			System.out.println("第" + (i + 1) + "个apk没有抓到渠道号");
		}

		if(i<apks.length){
			System.out.println("**************** 第" + (i + 1) + "个apk： " + apks[i] + " 运行完毕 ****************");
			System.out.println();
		}
		
		driver.removeApp("com.starunion.hefantv");
		driver.quit();
		i++;
		if (i < apks.length) {
			Thread.sleep(5000);// 第三次sleep 5s，可以删除，不受影响
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

							flag = true;

							String[] tmp = line.split("&q_channel="); // 根据--将每行数据拆分成一个数组
							System.out.println(tmp[tmp.length - 1]);// 获取最后一个数即时渠道号
							txtLog(tmp[tmp.length - 1]);// 保存渠道号
							String content = compareQDH();
							if (content.equals(tmp[tmp.length - 1])) {
								Xlsfile.writexls("QDH", 4, (i + 1), "OK");
								System.out.println(
										"渠道包:" + apks[i] + " &&& " + content + " = " + tmp[tmp.length - 1] + "	——OK");
							} else {
								
								//判断是否是安装第一个bad的apk
								if(i<apks.length){
									Xlsfile.writexls("QDH", 4, (i + 1), "bad");
									System.out.println(
										"渠道包:" + apks[i] + " &&& " + content + " != " + tmp[tmp.length - 1] + "	——bad");
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

	// 写入文件
	public static void txtLog(String str) {
		FileWriter fw = null;
		try {
			// 如果文件存在，则追加内容；如果文件不存在，则创建文件
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
	
	//测试第三个人拉取（无权限提交代码的人）

}