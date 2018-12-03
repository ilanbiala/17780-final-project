package processingwrapper;

/**
 * A circle that can be drawn in the screen. Like other {@link Shape}
 * objects, an instance of Circle just contains information about its
 * dimensions, not its position or color. Position and color are specified
 * in the call to {@link Canvas#draw}; or an instance of {@link Drawable}
 * can be created combining a circle with drawing settings.
 */
public class Circle extends Ellipse {
 
  private final float radius;
  
  private Circle(float radius) {
    super(2*radius, 2*radius);
    assert(isPositive(radius));
    this.radius = radius;
  }
  
  /**
   * Create a new circle with the specified radius.
   */
  public static Circle of(float radius) {
    if (!isPositive(radius)) throw new IllegalArgumentException("radius");
    return new Circle(radius);
  }
  
  /**
   * @return the radius of the circle.
   */
  public float radius() {
    return radius;
  }

  @Override
  Drawable.DrawableType type() {
    return Drawable.DrawableType.CIRCLE;
  }

  /**
   * A circle is equal to another circle with the same radius,
   * or to an ellipse with the same width and height.
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof Ellipse) {
      Ellipse e = (Ellipse) o;
      return 2 * radius == e.width() && 2 * radius == e.height();
    } else if (o instanceof Circle) {
      Circle c = (Circle) o;
      return radius == c.radius;
    }
    return false;
  }
  
  @Override
  public String toString() {
    return String.format("Circle(%.3f)", radius);
  }

}
