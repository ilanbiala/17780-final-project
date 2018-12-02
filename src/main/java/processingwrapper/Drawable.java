package processingwrapper;

import java.util.Objects;
import java.util.Optional;

/**
 * Instances of this class are packages of a drawable thing (like a shape
 * or an image) and settings for how to draw them (like {@link ImageSettings}
 * or {@link ShapeSettings}). Instances of this can be passed as an argument
 * to {@link Canvas#draw}.
 * 
 * All methods throw {@link NullPointerException} if any argument is null.
 */
public class Drawable {
  
	enum DrawableType {
		CANVAS, RECTANGLE, ELLIPSE, CIRCLE, IMAGE;
	}
  
  private Drawable(Shape shape, ShapeSettings settings) {
    assert(Objects.nonNull(shape));
    assert(Objects.nonNull(settings));
    this.shape = shape;
    shapeSettings = settings;
    isImage = false;
  }

  private Drawable(Image image, Optional<ImageSettings> settings) {
    assert(Objects.nonNull(image));
    assert(Objects.nonNull(settings));
    this.image = image;
    imageSettings = settings;
    isImage = true;
  }
  
  /**
   * Create a package of a shape and settings with which to draw the shape.
   */
  public static Drawable ofShape(Shape shape, ShapeSettings settings) {
    return new Drawable(Objects.requireNonNull(shape), Objects.requireNonNull(settings));
  }
  
  /**
   * Create a drawable image with the default settings (the size of the image
   * as given from the file, and no tint).
   */
  public static Drawable ofImage(Image image) {
    return new Drawable(Objects.requireNonNull(image), Optional.empty());
  }
  
  /**
   * Create a package of an image and settings with which to draw the image.
   */
  public static Drawable ofImage(Image image, ImageSettings settings) {
    return new Drawable(Objects.requireNonNull(image),
        Optional.of(Objects.requireNonNull(settings)));
  }

  private final boolean isImage;
  
  // These are set if isImage is false.
  private ShapeSettings shapeSettings;
  private Shape shape;
  
  // These are set if isImage is true;
  private Optional<ImageSettings> imageSettings;
  private Image image;
  
  // Return the underlying drawable type.
  DrawableType type() {
    if (isImage) {
      return DrawableType.IMAGE;
    } else {
      return shape.type();
    }
  }
  
  // Only call these if type() returns IMAGE.
  Optional<ImageSettings> imageSettings() {
    assert(isImage);
    return imageSettings;
  }
  Image image() {
    assert(isImage);
    return image;
  }
  
  // Only call these if type() doesn't return IMAGE.
  ShapeSettings shapeSettings() {
    assert(!isImage);
    return shapeSettings;
  }
  Shape shape() {
    assert(!isImage);
    return shape;
  }
}
