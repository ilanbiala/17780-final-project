package processingwrapper;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    this.backgroundColor = null;
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

  void commitAt(PApplet app, int x, int y) {
    assert (drawables.size() == positions.size());

//    app.imageMode(app.CORNER);
//    app.clip(x, y, this.width, this.height);

    if (this.backgroundColor != null) {
      app.rectMode(app.CORNER);
      app.fill(this.backgroundColor.getRGB());
      app.rect(x, y, this.width, this.height);
    }

    for (int i = 0; i < drawables.size(); i++) {
      Drawable drawable = drawables.get(i);
      Position pos = positions.get(i);

      switch (drawable.type()) {
        case IMAGE: {
          Optional<ImageSettings> imgSettingsOpt = drawable.imageSettings();
          PImage pimg = drawable.image().image;
          app.imageMode(pos.drawMode().processingDrawMode());

          if (imgSettingsOpt.isPresent()) {
            var imgSettings = imgSettingsOpt.get();
            app.image(pimg, x + pos.x(), y + pos.y(), imgSettings.width(), imgSettings.height());
          } else {
            app.image(pimg, x + pos.x(), y + pos.y());
          }
          break;
        }

        case ELLIPSE: {
          var settings = drawable.shapeSettings();
          app.strokeWeight(settings.strokeWeight());
          app.colorMode(app.RGB);
          app.stroke(settings.strokeColor().getRGB());
          app.fill(settings.fillColor().getRGB());

          app.ellipseMode(pos.drawMode().processingDrawMode());
          app.ellipse(x + pos.x(), y + pos.y(), drawable.shape().width(), drawable.shape().height());
          break;
        }

        case RECTANGLE: {
          var settings = drawable.shapeSettings();
          app.strokeWeight(settings.strokeWeight());
          app.colorMode(app.RGB);
          app.stroke(settings.strokeColor().getRGB());
          app.fill(settings.fillColor().getRGB());

          app.rectMode(pos.drawMode().processingDrawMode());
          app.rect(x + pos.x(), y + pos.y(), drawable.shape().width(), drawable.shape().height());
          break;
        }

        case CANVAS:
          switch (pos.drawMode()) {

            case TOP_LEFT_CORNER:
              drawable.canvas().commitAt(app, (int) (x + pos.x()), (int) (y + pos.y()));
              break;
            case CENTER:
              float xpos = x + pos.x() - (float) drawable.canvas().width() / 2;
              float ypos = y + pos.y() - (float) drawable.canvas().height() / 2;

              drawable.canvas().commitAt(app, (int) (xpos), (int) (ypos));
              break;
          }
          break;
      }
    }
  }

  public int width() {
    return this.width;
  }

  public int height() {
    return this.height;
  }
}
