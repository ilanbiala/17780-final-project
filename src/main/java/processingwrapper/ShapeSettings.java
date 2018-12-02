package processingwrapper;

import java.awt.Color;

/**
 * The {@link ShapeSettings} class is used to define the aesthetic properties of the {@link Rectangle},
 * {@link Ellipse}, and {@link Circle} shapes.
 */
public class ShapeSettings {
  private static final Color NO_COLOR = new Color(0, 0, 0, 255);

  private Color fillColor;
  private float strokeWeight;
  private Color strokeColor;

  private ShapeSettings(Color fillColor) {
    this.fillColor = fillColor;
    this.strokeWeight = 0;
    this.strokeColor = NO_COLOR;
  }

  private ShapeSettings(float strokeWeight, Color strokeColor) {
    this.fillColor = NO_COLOR;
    this.strokeWeight = strokeWeight;
    this.strokeColor = strokeColor;
  }

  /**
   * Creates a {@link ShapeSettings} instance with the given fill color.
   *
   * @param fillColor The fill color to set.
   * @return A new {@link ShapeSettings} instance.
   */
  public static ShapeSettings createWithFill(Color fillColor) {
    return new ShapeSettings(fillColor);
  }

  /**
   * Sets the fill color of the calling instance.
   *
   * @param fillColor The fill color to set.
   * @return The calling {@link ShapeSettings} instance.
   */
  public ShapeSettings withFill(Color fillColor) {
    this.fillColor = fillColor;
    return this;
  }

  /**
   * Creates a {@link ShapeSettings} instance with the given stroke weight and color.
   *
   * @param strokeWeight The stroke weight to set.
   * @param strokeColor  The stroke color to set.
   * @return A new {@link ShapeSettings} instance.
   */
  public static ShapeSettings createWithStroke(float strokeWeight, Color strokeColor) {
    return new ShapeSettings(strokeWeight, strokeColor);
  }

  /**
   * Sets the stroke weight of the calling instance.
   *
   * @param strokeWeight The stroke weight to set.
   * @param strokeColor  The stroke color to set.
   * @return The calling {@link ShapeSettings} instance.
   */
  public ShapeSettings withStroke(float strokeWeight, Color strokeColor) {
    this.strokeWeight = strokeWeight;
    this.strokeColor = strokeColor;
    return this;
  }
}
