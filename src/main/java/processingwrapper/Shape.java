package processingwrapper;

import java.util.Objects;

/**
 * If a class implements this class, then instances of that class
 * can be combined with {@link ShapeSettings} to be drawn on the canvas
 * as an argument to {@link Canvas#draw}. Alternatively, an instance
 * of {@link Drawable} can be created (which packs together a shape with
 * its settings) out of an instance of Shape and an instance of ShapeSettings.
 * 
 * The only subclasses of this class are {@link Rectangle}, {@link Ellipse},
 * and {@link Circle}.
 */
public abstract class Shape {
	// Package-private constructor means that the only subclasses
	// of Shape belong to the processingwrapper package.
	Shape(double width, double height) {
	  assert(isPositive(width));
	  assert(isPositive(height));
	  this.width = width;
	  this.height = height;
	}
	
	final protected double width;
	final protected double height;
	
  /**
   * @return the width (x dimension) of the shape.
   */
  public double width() {
    return width;
  }
  
  /**
   * @return the height (y dimension) of the shape.
   */
  public double height() {
    return height;
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(width, height);
  }
  
	// Return if a double is positive (i.e. finite and greater than 0.)
  static boolean isPositive(double f) {
    return Double.isFinite(f) && f > 0.0;
  }
	
	/**
	 * @return the type of shape object.
	 */
	abstract Drawable.DrawableType type();
}