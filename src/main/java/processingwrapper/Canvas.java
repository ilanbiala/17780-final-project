package processingwrapper;

import java.util.ArrayList;
import java.util.List;

public class Canvas {
  private int width, height;
  private List<Drawable> drawables;
  private List<Position> positions;
  private java.awt.Color backgroundColor;

  public Canvas(int width, int height) {
    this.width = width;
    this.height = height;
    this.drawables = new ArrayList<>();
    this.positions = new ArrayList<>();
  }

  public void draw(Drawable drawable, Position pos) {
    this.drawables.add(drawable);
    this.positions.add(pos);
  }

  public void draw(Shape shape, ShapeSettings shapeSettings, Position pos) {
    this.drawables.add(Drawable.ofShape(shape, shapeSettings));
    this.positions.add(pos);
  }

  public void draw(Image img, Position pos) {
    this.drawables.add(Drawable.ofImage(img));
    this.positions.add(pos);
  }

  public void draw(Image img, ImageSettings imgSettings, Position pos) {
    this.drawables.add(Drawable.ofImage(img));
    this.positions.add(pos);
  }

  public void draw(Canvas canvas, Position pos) {
    this.drawables.add(Drawable.ofCanvas(canvas));
    this.positions.add(pos);
  }

  public void fill(java.awt.Color color) {
    this.drawables.clear();
    this.positions.clear();
    this.backgroundColor = color;
  }

  void commitAt(int x, int y) {

  }

  public int width() {
    return this.width;
  }
  public int height() {
    return this.height;
  }
}
