package processingwrapper;

import processing.core.PApplet;

/**
 * The {@link Position} class is used to specify the position where an object is drawn on a canvas. A {@link Position}
 * instance is immutable; once created, any modifications will return a new instance.
 */
public class Position {

  /**
   * The {@link DrawMode} class is used to specify the drawing mode of objects.
   */
  public enum DrawMode {
    TOP_LEFT_CORNER(PApplet.CORNER),
    CENTER(PApplet.CENTER);

    private int processingDrawMode;

    private DrawMode(int processingDrawMode) {
      this.processingDrawMode = processingDrawMode;
    }

    int processingDrawMode() {
      return this.processingDrawMode;
    }
  }

  private final DrawMode drawMode;
  private final double x;
  private final double y;

  private Position(double x, double y, DrawMode drawMode) {
    this.x = x;
    this.y = y;
    this.drawMode = drawMode;
  }

  /**
   * @return the x-coordinate of this {@link Position} instance.
   */
  public double x() {
    return x;
  }

  /**
   * @return the y-coordinate of this {@link Position} instance.
   */
  public double y() {
    return y;
  }

  /**
   * @return the drawing mode of this {@link Position} instance.
   */
  public DrawMode drawMode() {
    return drawMode;
  }

  /**
   * Creates a {@link Position} instance with the given coordinates.
   *
   * @param x The x-coordinate of the center of the object to be drawn.
   * @param y The y-coordinate of the center of the object to be drawn.
   * @return A {@link Position} instance.
   */
  public static Position centeredAt(double x, double y) {
    return new Position(x, y, DrawMode.CENTER);
  }

  /**
   * Creates a {@link Position} instance with the given coordinates.
   *
   * @param x The x-coordinate of the top left corner of the object to be drawn.
   * @param y The y-coordinate of the top left corner of the object to be drawn.
   * @return A {@link Position} instance.
   */
  public static Position topLeftCornerAt(double x, double y) {
    return new Position(x, y, DrawMode.TOP_LEFT_CORNER);
  }

  /**
   * Translates the given {@link Position} by the given deltas.
   *
   * @param dx The delta in the horizontal direction to translate the object by.
   * @param dy The delta in the vertical direction to translate the object by.
   * @return A new {@link Position} instance.
   */
  public Position translateBy(double dx, double dy) {
    return new Position(this.x + dx, this.y + dy, this.drawMode);
  }
}
