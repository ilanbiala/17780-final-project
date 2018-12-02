package processingwrapper;

import static java.lang.Float.NaN;

import java.awt.Color;

/**
 * The {@link ImageSettings} class is used to define the aesthetic properties of an Image.
 */
public class ImageSettings {
  // Processing uses white to specify a tint with no color.
  // Since we've specified white with an alpha of 1,
  // effectively no tint will be applied.
  private static final Color NO_TINT = Color.WHITE;
  // If the user creates an ImageSettings instance without specifying the
  // size, we'll be able to check for NaN and use the loaded image's size.
  private static final float DEFAULT_SIZE = NaN;

  private Color tint;
  private float width;
  private float height;

  private ImageSettings(Color tint) {
    this.tint = tint;
    this.width = DEFAULT_SIZE;
    this.height = DEFAULT_SIZE;
  }

  private ImageSettings(float width, float height) {
    this.tint = NO_TINT;
    this.width = width;
    this.height = height;
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
   * Sets the tint of the calling instance.
   *
   * @param tint the tint to set.
   * @return The calling {@link ImageSettings} instance.
   */
  public ImageSettings withTint(Color tint) {
    this.tint = tint;
    return this;
  }

  /**
   * Creates an {@link ImageSettings} instance with the given width and height.
   *
   * @param width  the width to set.
   * @param height the height to set.
   * @return A new {@link ImageSettings} instance.
   */
  public static ImageSettings createWithSize(float width, float height) {
    return new ImageSettings(width, height);
  }

  /**
   * Sets the size of the calling instance.
   *
   * @param width  the width to set.
   * @param height the height to set.
   * @return The calling {@link ImageSettings} instance.
   */
  public ImageSettings withSize(float width, float height) {
    this.width = width;
    this.height = height;
    return this;
  }
}
