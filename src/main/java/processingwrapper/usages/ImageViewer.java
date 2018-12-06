package processingwrapper.usages;

import processingwrapper.Canvas;
import processingwrapper.Image;
import processingwrapper.ImageSettings;
import processingwrapper.Position;
import processingwrapper.ProcessingApp;

// These imports are just for the old, buggy example from Processing.
import processing.core.PApplet;
import processing.core.PImage;

public class ImageViewer {
  public static void main(String[] args) {
    ProcessingApp.start(ImageViewerApp::new, 800, 800);

    // Uncomment this to see what the old code would have done.
    // OldImageViewerWithRuntimeError.run();
  }

  static class ImageViewerApp implements ProcessingApp {
    private final int width;
    private final int height;

    final Canvas dogCanvas;
    final Canvas catCanvas;
    final Canvas wolfCanvas;
    final Canvas eagleCanvas;

    final Image dog = Image.ofFile("images/dog.jpg");
    final Image cat = Image.ofFile("images/cat.jpg");
    final Image wolf = Image.ofFile("images/wolf.jpg");
    final Image eagle = Image.ofFile("images/eagle.jpg");

    final Position centered;
    final ImageSettings sized;

    ImageViewerApp(int width, int height) {
      this.width = width;
      this.height = height;

      int w = width / 2, h = height / 2;
      dogCanvas = Canvas.of(w, h);
      catCanvas = Canvas.of(w, h);
      wolfCanvas = Canvas.of(w, h);
      eagleCanvas = Canvas.of(w, h);

      float buffer = 10;
      sized = ImageSettings.createWithSize(w - buffer, h - buffer);
      centered = Position.centeredAt(w / 2, h / 2);
    }

    @Override
    public void drawFrame(Canvas mainCanvas) {
      // Draw image on canvas.
      dogCanvas.draw(dog, sized, centered);
      catCanvas.draw(cat, sized, centered);
      wolfCanvas.draw(wolf, sized, centered);
      eagleCanvas.draw(eagle, sized, centered);

      // Draw subcanvases on main canvas
      mainCanvas.draw(dogCanvas, Position.topLeftCornerAt(0, 0));
      mainCanvas.draw(catCanvas, Position.topLeftCornerAt(width / 2, 0));
      mainCanvas.draw(wolfCanvas, Position.topLeftCornerAt(0, height / 2));
      mainCanvas.draw(eagleCanvas, Position.topLeftCornerAt(width / 2, height / 2));
    }
  }

  // WARNING: BAD CODE FOLLOWS.
  // This is what the code would have looked like in the old API.
  // This would actually throw a runtime error, because loadImage can only be run from `setup`.
  @SuppressWarnings("unused")
  private static class OldImageViewerWithRuntimeError {
    public static void run() {
      PApplet.main(MyApp.class);
    }

    public static class MyApp extends PApplet {
      PImage image = loadImage("images/dog.jpg");

      @Override
      public void setup() {
        size(800, 800);
      }

      @Override
      public void draw() {
        image(image, 0, 0);
      }
    }
  }
}
