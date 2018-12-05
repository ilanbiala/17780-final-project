package processingwrapper.usages;

import processingwrapper.*;
import processingwrapper.Canvas;

import java.awt.*;

public class SolarSystem implements ProcessingApp {

  float SUN_RADIUS;

  Position center;
  Circle sun;
  ShapeSettings sunProperties = ShapeSettings.createWithFill(Color.ORANGE);

  public SolarSystem(int width, int height) {
    SUN_RADIUS = width/10;

    center = Position.centeredAt(width/2, height/2);
    sun = Circle.of(SUN_RADIUS);
  }

  /**
   * Draw a frame to the canvas
   *
   * @param mainCanvas The main canvas to draw on.
   */
  @Override
  public void drawFrame(Canvas mainCanvas) {
    mainCanvas.draw(sun, sunProperties, center);
  }

  public static void main(String[] args) {
    int width = 800;
    int height = 600;

    ProcessingApp.start(SolarSystem::new, width, height);
  }
}
