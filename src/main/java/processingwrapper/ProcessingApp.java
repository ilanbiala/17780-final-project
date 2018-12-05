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
   * @param width          The window width
   * @param height         The window height
   */
  static void start(BiFunction<Integer, Integer, ProcessingApp> appConstructor, int width, int height) {

    Canvas mainCanvas = new Canvas(width, height);

    class WrapperPApplet extends PApplet {
      private ProcessingApp app;

      public void setup() {
        size(width, height);
        app = appConstructor.apply(width, height);
      }

      public void draw() {
        app.drawFrame(mainCanvas);
        mainCanvas.commitAt(0,0);
      }
    }

    PApplet.main(WrapperPApplet.class);
  }

  /**
   * Draw a frame to the canvas
   *
   * @param mainCanvas The main canvas to draw on.
   */
  void drawFrame(Canvas mainCanvas);
}
