package processingwrapper.usages;

import java.awt.Color;

import processing.core.PApplet;
import processingwrapper.Canvas;
import processingwrapper.Circle;
import processingwrapper.Position;
import processingwrapper.ProcessingApp;
import processingwrapper.Rectangle;
import processingwrapper.ShapeSettings;

public class ScreenSaver {
  
  public static void main(String[] args) {
    ProcessingApp.start(ScreensaverApp::new, 800, 800);
    // Old code:
    // PApplet.main(OldScreensaverApp.class);
  }
  
  static class ScreensaverApp implements ProcessingApp {
    private static final double V = 3;
    private final int width, height;
    private double x, y;
    private double xVelocity, yVelocity;
    public ScreensaverApp(int width, int height) {
      this.width = width;
      this.height = height;
      x = 0;
      y = 0;
      xVelocity = Math.random() * V;
      yVelocity = Math.random() * V;
    }

    @Override
    public void drawFrame(Canvas mainCanvas) {
      int w = 200, h = 200;

      Canvas box = Canvas.of(w, h);
      box.fill(Color.GREEN);
      box.draw(Rectangle.of(20, 20),
        ShapeSettings.createWithStroke(3, Color.BLUE),
        Position.centeredAt(3 * w / 4, 3 * h / 4));
      
      box.draw(Circle.of(40),
          ShapeSettings.createWithStroke(4, Color.CYAN),
          Position.centeredAt(w / 4, h / 4));

      x += xVelocity;
      y += yVelocity;

      if (x >= width - w - 1) xVelocity = -xVelocity;
      if (x <= 0) xVelocity = -xVelocity;
      if (y >= height - h - 1) yVelocity = -yVelocity;
      if (y <= 0) yVelocity = -yVelocity;
      
      mainCanvas.draw(box, Position.topLeftCornerAt(x, y));
    }
  }
  
  // BEWARE: This code is the old, bad processing code.
  // We include it for reference.
  // Do not write a library that makes us do things like this.
  public static class OldScreensaverApp extends PApplet {
    private static final float V = 3.0f;
    float x, y, xVelocity, yVelocity;
    
    @Override
    public void settings() {
      size(800, 800);
    }
    
    @Override
    public void setup() {
      x = 0;
      y = 0;
      xVelocity = (float) Math.random() * V;
      yVelocity = (float) Math.random() * V;
    }
    
    @Override
    public void draw() {
      int w = 200, h = 200;
      // better clear, just in case
      background(255, 255, 255);
      rectMode(PApplet.CORNER);
      fill(0, 255, 0); // fill(GREEN)
      rect(x, y, w, h); // draw subcanvas
      noFill();
      strokeWeight(3);
      stroke(0, 0, 255); // fill(BLUE)
      rect(x + 3 * w / 4, y + 3 * h / 4, 20, 20); // Manually calculate offsets
      // Keep noFill() setting from above
      strokeWeight(2);
      stroke(255, 0, 0); // fill(RED)
      ellipseMode(PApplet.CORNER);
      ellipse(x + w / 4, y + h / 4, 40, 40); // Circle.of(40)
      // Better remember these, otherwise things will turn red
      noStroke();

      x += xVelocity;
      y += yVelocity;

      if (x >= width - w - 1) xVelocity = -xVelocity;
      if (x <= 0) xVelocity = -xVelocity;
      if (y >= height - h - 1) yVelocity = -yVelocity;
      if (y <= 0) yVelocity = -yVelocity;
    }
  }
}
