package processingwrapper;

import java.util.Objects;

/**
 * A rectangle that can be drawn in the screen. Like other {@link Shape}
 * objects, an instance of Rectangle just contains information about its
 * dimensions, not its position or color. Position and color are specified
 * in the call to {@link Canvas#draw}; or an instance of {@link Drawable}
 * can be created combining an rectangle with drawing settings.
 */
public class Rectangle extends Shape {
  
  private Rectangle(double width, double height) {
    super(width, height);
  }

  /**
   * Create a new rectangle with the specified width and height.
   * @throws IllegalArgumentException if the width and height are not
   *   positive, finite doubles.
   */
  public static Rectangle of(double width, double height) {
    if (!isPositive(width)) throw new IllegalArgumentException("width");
    if (!isPositive(height)) throw new IllegalArgumentException("height");
    return new Rectangle(width, height);
  }
  
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Rectangle)) return false;
    Rectangle r = (Rectangle) o;
    return width == r.width && height == r.height;
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(width, height);
  }
  
  @Override
  public String toString() {
    return String.format("Rectangle(%.3f,%.3f)", width, height);
  }

  @Override
  Drawable.DrawableType type() {
    return Drawable.DrawableType.RECTANGLE;
  }

}
