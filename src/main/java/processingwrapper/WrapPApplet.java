package processingwrapper;

import processing.core.PApplet;

public class WrapPApplet extends PApplet {
  private ProcessingApp app;

  public void setup() {
    size(800, 600);
  }

  public void draw() {
    rect(10, 10, 100, 100);
  }
}