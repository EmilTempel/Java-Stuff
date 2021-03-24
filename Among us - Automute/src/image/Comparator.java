package image;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Comparator {
	BufferedImage img1;
	BufferedImage img2;

	int width;
	int height;

	public Comparator(BufferedImage img1, BufferedImage img2) {
		this.img1 = img1;
		this.img2 = img2;

		width = img1.getWidth();
		height = img1.getHeight();
	}

	public double averageDeviation() {
		double deviation_sum = 0;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Color c1 = new Color(img1.getRGB(x, y));
				Color c2 = new Color(img2.getRGB(x, y));
				double Rdev = Math.abs(c1.getRed() - c2.getRed());
				double Gdev = Math.abs(c1.getGreen() - c2.getGreen());
				double Bdev = Math.abs(c1.getBlue() - c2.getBlue());
				deviation_sum += (Rdev + Gdev + Bdev) / 3;
			}
		}
		deviation_sum = deviation_sum/(width*height);
		return deviation_sum;
	}

}
