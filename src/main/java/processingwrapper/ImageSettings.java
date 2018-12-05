package processingwrapper;

import static java.lang.Float.NaN;

import java.awt.Color;

/**
 * The {@link ImageSettings} class is used to define the aesthetic properties of an Image. An {@link ImageSettings}
 * instance is immutable; once created, any modifications will return a new instance.
 */
public class ImageSettings {
  // Processing uses white to specify a tint with no color.
  // Since we've specified white with an alpha of 1,
  // effectively no tint will be applied.
  private static final Color NO_TINT = Color.WHITE;
  // If the user creates an ImageSettings instance without specifying the
  // size, we'll be able to check for NaN and use the loaded image's size.
  private static final double DEFAULT_SIZE = NaN;

  private final Color tint;
  private final double width;
  private final double height;

  private ImageSettings(Color tint) {
    this.tint = tint;
    this.width = DEFAULT_SIZE;
    this.height = DEFAULT_SIZE;
  }

  private ImageSettings(double width, double height) {
    this.tint = NO_TINT;
    this.width = width;
    this.height = height;
  }

  private ImageSettings(Color tint, double width, double height) {
    this.tint = tint;
    this.width = width;
    this.height = height;
  }

  /**
   * @return The tint of this {@link ImageSettings} instance.
   */
  public Color tint() {
    return tint;
  }

  /**
   * @return The width of this {@link ImageSettings} instance.
   */
  public double width() {
    return width;
  }

  /**
   * @return The height of this {@link ImageSettings} instance.
   */
  public double height() {
    return height;
  }

  /**
   * Creates an {@link ImageSettings} instance with the given tint.
   *
   * @param tint The tint to set.
   * @return A new {@link ImageSettings} instance.
   */
  public static ImageSettings createWithTint(Color tint) {
    return new ImageSettings(tint);
  }

  /**
   * Creates a new {@link ImageSettings} instance with the given tint and the settings of the calling instance.
   *
   * @param tint the tint to set.
   * @return A new {@link ImageSettings} instance.
   */
  public ImageSettings withTint(Color tint) {
    return new ImageSettings(tint, this.width, this.height);
  }

  /**
   * Creates an {@link ImageSettings} instance with the given width and height.
   *
   * @param width  the width to set.
   * @param height the height to set.
   * @return A new {@link ImageSettings} instance.
   */
  public static ImageSettings createWithSize(double width, double height) {
    return new ImageSettings(width, height);
  }

  /**
   * Creates a new {@link ImageSettings} instance with the given width, height, and the settings of the calling
   * instance.
   *
   * @param width  the width to set.
   * @param height the height to set.
   * @return A new {@link ImageSettings} instance.
   */
  public ImageSettings withSize(double width, double height) {
    return new ImageSettings(this.tint, width, height);
  }
}
