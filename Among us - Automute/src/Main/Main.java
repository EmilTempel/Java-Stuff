package Main;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import image.Comparator;

public class Main {
	static boolean muted;
	
	public static void main(String[] args) throws IOException, AWTException {
		JFrame off = new JFrame();
		off.setVisible(true);
		off.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	Robot bot = new Robot();
	
	double deviation_threshold_shhh = 35;
	double deviation_threshold_discuss = 35;
	double deviation_threshold_eject = 35;
	
//	BufferedImage shhh = ImageIO.read(new File("shhh.jpeg"));
	BufferedImage discuss = ImageIO.read(Main.class.getResource(("/discuss.jpeg")));
	BufferedImage eject = ImageIO.read(Main.class.getResource(("/eject.jpeg")));
	
	muted = false;
	
	Timer timer = new Timer();
	TimerTask task = new TimerTask() {
		public void run() {
//			if(!muted && check(shhh,deviation_threshold_discuss, bot)) {
//				mute(bot);
//				muted = true;
//			}
			if(muted && check(discuss,deviation_threshold_discuss, bot) ) {
				mute(bot);
				muted = false;
			}
			if(!muted && check(eject,deviation_threshold_discuss, bot)) {
				mute(bot);
				muted = true;
			}
		}
	};
	
	timer.scheduleAtFixedRate(task, 0L, 30L);
	}
	
	public static boolean check(BufferedImage img, double threshold, Robot bot) {
		BufferedImage screen = bot.createScreenCapture(new Rectangle(0,0,1920,1080));
		Comparator comp = new Comparator(img,screen);
		System.out.println(comp.averageDeviation());
		if(comp.averageDeviation() < threshold) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public static void mute(Robot bot) {
		bot.keyPress(KeyEvent.VK_F9);
		bot.keyRelease(KeyEvent.VK_F9);
	}
}
