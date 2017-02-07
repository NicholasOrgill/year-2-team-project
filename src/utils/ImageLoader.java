package utils;

import java.awt.Image;
import java.awt.Toolkit;

public class ImageLoader {
	public static Image loadImageFromResource(String path) {
		Image image = Toolkit.getDefaultToolkit().getImage(path);
		return image;
	}
}
