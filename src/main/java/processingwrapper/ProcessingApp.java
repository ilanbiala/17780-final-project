package processingwrapper;

import processing.core.PApplet;

import java.util.Objects;
import java.util.function.BiFunction;

/**
 * {@link ProcessingApp} is the interface implemented by client applications.
 * {@link #start } should be called to begin running a ProcessingApp.
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
   * Once an app is started, this method is called once per frame. The implementer
   * of this method should write the logic to draw the appropriate entities on
   * the canvas. The main canvas is to be modified by a series of
   * calls to {@link Canvas#draw(Drawable, Position)} (or one of its overloadings).
   * Note that the main canvas (and all of its subcanvases) will be cleared following
   * the execution of this method, so the whole canvas should be reconstructed
   * each time this method executes.
   *
   * @param mainCanvas The main canvas to draw on.
   */
  void drawFrame(Canvas mainCanvas);
}