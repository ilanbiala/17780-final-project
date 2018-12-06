package processingwrapper;

import processing.core.PApplet;

import java.util.Objects;
import java.util.function.BiFunction;

/**
 * {@link ProcessingApp} is the interface implemented by client applications.
 * {@link #start } should be called to begin running a ProcessingApp
 */
public interface ProcessingApp {

  /**
   * Starts running the supplied ProcessingApp.
   * 
   * The most common usage pattern for this will be: {@code start(MyApp::new, width, height)},
   * where {@code MyApp} is a class with a constructor that takes the width and height
   * as parameters.
   *
   * @param appConstructor A constructor which creates a ProcessingApp given the width and height
   * @param windowWidth    The window width
   * @param windowHeight   The window height
   */
  static void start(BiFunction<Double, Double, ProcessingApp> appConstructor, double windowWidth, double windowHeight) {
    Objects.requireNonNull(appConstructor);
    Canvas mainCanvas = Canvas.of(windowWidth, windowHeight);

    class WrapperPApplet extends PApplet {
      private ProcessingApp app;

      @Override
      public void settings() {
        size((int) windowWidth, (int) windowHeight);
      }

      @Override
      public void setup() {
        this.background(255);
        app = appConstructor.apply(windowWidth, windowHeight);
      }

      @Override
      public void draw() {
        this.background(255);
        app.drawFrame(mainCanvas);
        mainCanvas.commit(this);
      }
    }

    String[] args = {"--location=0,0", "WrapperPApplet"};
    PApplet.runSketch(args, new WrapperPApplet());
  }

  /**
   * Draw a frame to the canvas
   *
   * @param mainCanvas The main canvas to draw on.
   */
  void drawFrame(Canvas mainCanvas);
}