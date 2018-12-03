package processingwrapper;

import processing.core.PApplet;

/**
 * The {@link Position} class is used to specify the position where an object is drawn on a canvas. A {@link Position}
 * instance is immutable; once created, any modifications will return a new instance.
 */
public class Position {

  private enum DrawMode {
    TOP_LEFT_CORNER(PApplet.CORNER),
    CENTER(PApplet.CENTER);

    int processingDrawMode;

    DrawMode(int processingDrawMode) {
      this.processingDrawMode = processingDrawMode;
    }

    int getProcessingDrawMode() {
      return this.processingDrawMode;
    }
  }

  private final DrawMode drawMode;
  private final float x;
  private final float y;

  private Position(float x, float y, DrawMode drawMode) {
    this.x = x;
    this.y = y;
    this.drawMode = drawMode;
  }

  /**
   * Creates a {@link Position} instance with the given coordinates.
   *
   * @param x The x-coordinate of the center of the object to be drawn.
   * @param y The y-coordinate of the center of the object to be drawn.
   * @return A {@link Position} instance.
   */
  static Position centeredAt(float x, float y) {
    return new Position(x, y, DrawMode.CENTER);
  }

  /**
   * Creates a {@link Position} instance with the given coordinates.
   *
   * @param x The x-coordinate of the top left corner of the object to be drawn.
   * @param y The y-coordinate of the top left corner of the object to be drawn.
   * @return A {@link Position} instance.
   */
  static Position topLeftCornerAt(float x, float y) {
    return new Position(x, y, DrawMode.TOP_LEFT_CORNER);
  }

  /**
   * Translates the given {@link Position} by the given deltas.
   *
   * @param dx The delta in the horizontal direction to translate the object by.
   * @param dy The delta in the vertical direction to translate the object by.
   * @return A new {@link Position} instance.
   */
  Position translateBy(float dx, float dy) {
    return new Position(this.x + dx, this.y + dy, this.drawMode);
  }
}
