package processingwrapper;

import processing.core.PImage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * An image loaded from a file. An instance of this class can be passed
 * to {@link Canvas#draw} to be drawn on the canvas. Alternatively, the
 * image can be packaged together with its {@link ImageSettings}
 */
public class Image {
  
  // Package-private so it can be drawn from another class.
  final PImage image;
  private final float width;
  private final float height;    

  private Image(String file) {
    try {
      BufferedImage image = ImageIO.read(new File(file));
      this.image = new PImage(image);
      this.width = image.getWidth();
      this.height = image.getHeight();
    } catch (IOException e) {
      throw new IllegalArgumentException("file", e);
    }
  }
  
  /**
   * Create a new image from the file with default settings (no tint;
   * dimensions of image as defined in file).
   * @throws IllegalArgumentException if there is an IOException when reading
   * the file.
   */
  public static Image ofFile(String file) {
    return new Image(file);
  }
  
  /** @return the width (x-dimension) of the image before scaling. */
  public float width() {
    return width;
  }

  /** @return the height (y-dimension) of the image before scaling. */
  public float height() {
    return height;
  }

  // Don't override equals/hashcode; instance equality is good enough here.
}
