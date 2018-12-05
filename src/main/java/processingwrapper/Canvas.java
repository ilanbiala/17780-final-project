package processingwrapper;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * A canvas is an entity that contains elements to be drawn on the
 * screen. A canvas itself can be added to other canvases, and
 * an instance of {@link Drawable} can be created out of a canvas
 * (so that a method can return a canvas, an image, or a shape to
 * be drawn on the screen).
 *
 * A canvas is created via the static method {@link Canvas.of}. A
 * "main canvas" is also provided to the client via the
 * {@link ProcessingApp#draw} method upon which the client is meant
 * to draw shapes, images, and other canvases.
 *
 * All methods in this class throw {@link NullPointerException} if
 * provided a null argument.
 */
public class Canvas {
  private int width, height;
  private List<Drawable> drawables;
  private List<Position> positions;
  private java.awt.Color backgroundColor;

  private Canvas(int width, int height) {
    assert(width > 0 && height > 0);
    this.width = width;
    this.height = height;
    drawables = new ArrayList<>();
    positions = new ArrayList<>();
    backgroundColor = null;
  }

  /**
   * Create a canvas with the specified (positive) width and height.
   * @throws IllegalArgumentException if width or height is not positive.
   */
  public static Canvas of(int width, int height) {
    if (width <= 0) throw new IllegalArgumentException("width non-positive");
    if (height <= 0) throw new IllegalArgumentException("height non-positive");
    return new Canvas(width, height);
  }

  /** @return the width of the canvas as specified by the creator of the canvas. */
  public int width() {
    return this.width;
  }

  /** @return the height of the canvas as specified by the creator of the canvas. */
  public int height() {
    return this.height;
  }

  /** Draw the provided object at the given position. */
  public void draw(Drawable drawable, Position pos) {
    Objects.requireNonNull(drawable);
    Objects.requireNonNull(pos);
    drawables.add(drawable);
    positions.add(pos);
  }

  /** Draw the provided shape with the given settings at the given position. */
  public void draw(Shape shape, ShapeSettings shapeSettings, Position pos) {
    Objects.requireNonNull(shape);
    Objects.requireNonNull(shapeSettings);
    Objects.requireNonNull(pos);
    drawables.add(Drawable.ofShape(shape, shapeSettings));
    positions.add(pos);
  }

  /** Draw the provided image at the given position. */
  public void draw(Image img, Position pos) {
    Objects.requireNonNull(img);
    Objects.requireNonNull(pos);
    drawables.add(Drawable.ofImage(img));
    positions.add(pos);
  }

  /** Draw the provided image with the given settings at the given position. */
  public void draw(Image img, ImageSettings imgSettings, Position pos) {
    Objects.requireNonNull(img);
    Objects.requireNonNull(imgSettings);
    Objects.requireNonNull(pos);
    drawables.add(Drawable.ofImage(img));
    positions.add(pos);
  }

  /** Draw the provided canvas at the given position. */
  public void draw(Canvas canvas, Position pos) {
    Objects.requireNonNull(canvas);
    Objects.requireNonNull(pos);
    drawables.add(Drawable.ofCanvas(canvas));
    positions.add(pos);
  }

  /**
   * Clear the canvas of all elements drawn on it by filling
   * it with the provided color.
   *
   * @param color The color with which to fill the canvas.
   */
  public void fill(java.awt.Color color) {
    Objects.requireNonNull(color);
    drawables.clear();
    positions.clear();
    backgroundColor = color;
  }

  // Impose shape settings as global drawing settings.
  // Not externally accessible! Just used by us when committing to the actual canvas.
  private void setGlobalShapeSettings(PApplet app, ShapeSettings settings) {
    if (settings.strokeWeight() != 0) {
      app.strokeWeight(settings.strokeWeight());
      app.stroke(settings.strokeColor().getRGB());
    } else {
      app.noStroke();
    }
    app.colorMode(PApplet.RGB);
    app.fill(settings.fillColor().getRGB());
  }

  // Internally-used function that flushes the drawn entities out to the screen.
  void commit(PApplet app) {
    commitAt(app, 0, 0, width, height, new HashSet<>());
  }

  // Internally-used function that flushes the drawn entities out to the screen
  // within the bounding box given by the coordinates, and failing if we encounter
  // a cycle.
  private void commitAt(PApplet app, int xLo, int yLo, int xHi, int yHi, Set<Canvas> seen) {
    assert (drawables.size() == positions.size());

    // If a canvas is nested within another canvas, its actual width on the screen may be
    // smaller than the user-specified width. (This happens if the nested canvas would
    // extend outside the boundary imposed by the enclosing canvas.)
    // effectiveWidth <= width
    int effectiveWidth = Math.min(width, xHi - xLo);
    int effectiveHeight = Math.min(height, yHi - yLo);

    // Draw a rectangle for the canvas.
    if (backgroundColor != null) {
      app.rectMode(PApplet.CORNER);
      app.fill(backgroundColor.getRGB());
      app.noStroke();
      app.rect(xLo, yLo, effectiveWidth, effectiveHeight);
    }

    // Only allow drawing within a subrectangle.
    app.imageMode(PApplet.CORNER);
    app.clip(xLo, yLo, effectiveWidth, effectiveHeight);

    for (int i = 0; i < drawables.size(); i++) {
      Drawable drawable = drawables.get(i);
      Position pos = positions.get(i);

      // Select whether the drawn thing is to be centered or left-aligned.
      Position.DrawMode drawMode = pos.drawMode();

      switch (drawable.type()) {
        case IMAGE: {
          Optional<ImageSettings> imgSettingsOpt = drawable.imageSettings();
          PImage image = drawable.image().image;
          app.imageMode(drawMode.processingDrawMode());
          if (imgSettingsOpt.isPresent()) {
            var imgSettings = imgSettingsOpt.get();
            app.image(image, 
                (float) (xLo + pos.x()),
                (float) (yLo + pos.y()),
                (float) imgSettings.width(),
                (float) imgSettings.height());
          } else {
            app.image(image, (float) (xLo + pos.x()), (float) (yLo + pos.y()));
          }
          break;
        }

        case ELLIPSE: {
          setGlobalShapeSettings(app, drawable.shapeSettings());
          app.ellipseMode(drawMode.processingDrawMode());
          Shape shape = drawable.shape();
          app.ellipse(
              (float) (xLo + pos.x()),
              (float) (yLo + pos.y()),
              (float) shape.width(),
              (float) shape.height());
          break;
        }

        case RECTANGLE: {
          setGlobalShapeSettings(app, drawable.shapeSettings());
          app.rectMode(drawMode.processingDrawMode());
          Shape shape = drawable.shape();
          app.rect(
              (float) (xLo + pos.x()),
              (float) (yLo + pos.y()),
              (float) shape.width(),
              (float) shape.height());
          break;
        }

        case CANVAS:
          Canvas canvas = drawable.canvas();
          // Uh-oh!
          if (seen.contains(canvas)) {
            throw new IllegalStateException("The created main canvas has a canvas drawn as a sub-canvas of itself.");
          }

          int dx, dy;
          if (drawMode == Position.DrawMode.CENTER) {
            dx = (int) pos.x() - canvas.width() / 2;
            dy = (int) pos.y() - canvas.height() / 2;
          } else {
            dx = (int) pos.x();
            dy = (int) pos.y();
          }

          // Figure out new coordinates
          int xLoNew = xLo + dx;
          int xHiNew = Math.min(xHi, xLoNew + canvas.width());
          int yLoNew = yLo + dy;
          int yHiNew = Math.min(yHi, yLoNew + canvas.height());
          seen.add(canvas);
          canvas.commitAt(app, xLoNew, yLoNew, xHiNew, yHiNew, seen);
          seen.remove(canvas);

          // We have to re-set clip after recursive call to the canvas.
          app.imageMode(PApplet.CORNER);
          app.clip(xLo, yLo, effectiveWidth, effectiveHeight);
          break;
      }
    }

    this.drawables.clear();
    this.positions.clear();
    this.backgroundColor = null;
  }
}
