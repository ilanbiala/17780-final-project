package processingwrapper;

import java.util.Objects;

/**
 * An ellipse that can be drawn in the screen. Like other {@link Shape}
 * objects, an instance of Ellipse just contains information about its
 * dimensions, not its position or color. Position and color are specified
 * in the call to {@link Canvas#draw}; or an instance of {@link Drawable}
 * can be created combining an ellipse with drawing settings.
 * 
 * An instance of this class can represent a Circle if the width and height
 * are the same.
 */
public class Ellipse extends Shape {
 
  // package-private so that Circle can instantiate.
  Ellipse(double width, double height) {
    super(width, height);
  }
  
  /**
   * Create a new ellipse with the specified width and height.
   * @param width The x dimension of the ellipse.
   * @param height The y dimension of the ellipse.
   */
  public static Ellipse of(double width, double height) {
    if (!isPositive(width)) throw new IllegalArgumentException("width");
    if (!isPositive(height)) throw new IllegalArgumentException("height");
    return new Ellipse(width, height);
  }
  
  @Override
  Drawable.DrawableType type() {
    return Drawable.DrawableType.ELLIPSE;
  }

  /**
   * An ellipse is equal to an ellipse with the same width an
   * height, or to a circle with the same radius (if the ellipse
   * is in fact a circle).
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof Ellipse) {
      Ellipse e = (Ellipse) o;
      return width == e.width && height == e.height;
    } else if (o instanceof Circle) {
      Circle c = (Circle) o;
      return width == 2 * c.radius() && height == 2 * c.radius();
    }
    return false;
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(width, height);
  }
  
  @Override
  public String toString() {
    return String.format("Ellipse(%.3f,%.3f)", width, height);
  }

}
