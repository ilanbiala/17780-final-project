package processingwrapper;

import processing.core.PApplet;

import java.util.function.BiFunction;

/**
 * {@link ProcessingApp} is the interface implemented by client applications.
 * {@link #start } should be called to begin running a ProcessingApp
 */
public interface ProcessingApp {
  /**
   * Starts running the supplied ProcessingApp
   *
   * @param appConstructor A constructor which creates a ProcessingApp given the width and height
   * @param windowWidth    The window width
   * @param windowHeight   The window height
   */
  static void start(BiFunction<Integer, Integer, ProcessingApp> appConstructor, int windowWidth, int windowHeight) {
<<<<<<< HEAD
=======
    System.out.println("width = " + windowWidth + ", height = " + windowHeight);


>>>>>>> fix ProcessingApp
    Canvas mainCanvas = new Canvas(windowWidth, windowHeight);

    class WrapperPApplet extends PApplet {
      private ProcessingApp app;

      @Override
      public void settings() {
        size(windowWidth, windowHeight);
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
        mainCanvas.commitAt(this, 0, 0);
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