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
  
  private final float width;
  private final float height;
  
  private Rectangle(float width, float height) {
    assert(isPositive(width));
    assert(isPositive(height));
    this.width = width;
    this.height = height;
  }

  /**
   * Create a new rectangle with the specified width and height.
   * @throws IllegalArgumentException if the width and height are not
   *   positive, finite floats.
   */
  public static Rectangle of(float width, float height) {
    if (!isPositive(width)) throw new IllegalArgumentException("width");
    if (!isPositive(height)) throw new IllegalArgumentException("height");
    return new Rectangle(width, height);
  }
  
  /**
   * @return the width (x dimension) of the rectangle.
   */
  public float width() {
    return width;
  }
  
  /**
   * @return the height (y dimension) of the rectangle.
   */
  public float height() {
    return height;
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
  ShapeType type() {
    return Shape.ShapeType.RECTANGLE;
  }

}
