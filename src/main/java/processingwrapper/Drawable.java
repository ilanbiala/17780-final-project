package processingwrapper;

import java.util.Objects;
import java.util.Optional;

/**
 * Instances of this class are packages of a drawable thing (like a shape
 * or an image or a canvas) and settings for how to draw them (like {@link ImageSettings}
 * or {@link ShapeSettings}). Instances of this can be passed as an argument
 * to {@link Canvas#draw}.
 * 
 * All methods throw {@link NullPointerException} if any argument is null.
 */
public class Drawable {
  
	enum DrawableType {
		CANVAS, RECTANGLE, ELLIPSE, IMAGE;
	}
  
  private Drawable(Shape shape, ShapeSettings settings) {
    assert(Objects.nonNull(shape));
    assert(Objects.nonNull(settings));
    this.shape = shape;
    shapeSettings = settings;
    type = shape.type();
  }

  private Drawable(Image image, Optional<ImageSettings> settings) {
    assert(Objects.nonNull(image));
    assert(Objects.nonNull(settings));
    this.image = image;
    imageSettings = settings;
    type = DrawableType.IMAGE;
  }
  
  private Drawable(Canvas canvas) {
    assert(Objects.nonNull(canvas));
	  this.canvas = canvas;
	  type = DrawableType.CANVAS;
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
  
  /**
   * Create a drawable canvas.
   */
  public static Drawable ofCanvas(Canvas canvas) {
    return new Drawable(Objects.requireNonNull(canvas));
  }

  private final DrawableType type;
  
  // Return the underlying drawable type.
  DrawableType type() {
    return type;
  }
  
  // Only call these if type() returns IMAGE.
  private Optional<ImageSettings> imageSettings;
  private Image image;
  Optional<ImageSettings> imageSettings() {
    assert(type == DrawableType.IMAGE);
    return imageSettings;
  }
  Image image() {
    assert(type == DrawableType.IMAGE);
    return image;
  }
  
  // Only call these if type() doesn't return IMAGE or CANVAS.
  private ShapeSettings shapeSettings;
  private Shape shape;
  ShapeSettings shapeSettings() {
    assert(type != DrawableType.IMAGE && type != DrawableType.CANVAS);
    return shapeSettings;
  }
  Shape shape() {
    assert(type != DrawableType.IMAGE && type != DrawableType.CANVAS);
    return shape;
  }
  
  // Only call these if type() returns CANVAS.
  private Canvas canvas;
  Canvas canvas() {
    assert(type == DrawableType.CANVAS);
    return canvas;
  }
}
