package processingwrapper;

public class Canvas {
  private int width, height;

  public Canvas(int width, int height) {
    this.width = width;
    this.height = height;
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

  void flushAt(int x, int y) {

  }

  public int width() {
    return this.width;
  }
  public int height() {
    return this.height;
  }
}
