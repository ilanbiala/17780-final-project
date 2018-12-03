package processingwrapper;

import java.util.ArrayList;
import java.util.List;

public class Canvas {
  private int width, height;
  private List<Drawable> drawables;
  private List<Position> positions;

  public Canvas(int width, int height) {
    this.width = width;
    this.height = height;
    this.drawables = new ArrayList<>();
    this.positions = new ArrayList<>();
  }

  public void draw(Drawable drawable, Position pos) {

  }

  public void draw(Shape shape, ShapeSettings shapeSettings, Position pos) {

  }

  public void draw(Image img, Position pos) {

  }

  public void draw(Image img, ImageSettings imgSettings, Position pos) {

  }

  public void draw(Canvas canvas, Position pos) {

  }

  public void fill(java.awt.Color color) {

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
