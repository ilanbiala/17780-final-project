package processingwrapper.usages;

import processingwrapper.*;
import processingwrapper.Canvas;

import java.awt.*;

public class SolarSystem implements ProcessingApp {

  float SUN_RADIUS, EARTH_RADIUS, MOON_RADIUS;
  int earthOrbitCanvasSize;

  Position center, earthOrbitCanvasCenter, earthCenter;
  Canvas earthOrbitCanvas;

  Circle sun;
  Circle earth;
  Circle moon;
  ShapeSettings sunProperties = ShapeSettings.createWithFill(Color.ORANGE);
  ShapeSettings earthProperties = ShapeSettings.createWithFill(Color.BLUE);
  ShapeSettings moonProperties = ShapeSettings.createWithFill(Color.GRAY);

  public SolarSystem(int width, int height) {
    SUN_RADIUS = width/10;
    EARTH_RADIUS = SUN_RADIUS/5;
    MOON_RADIUS = EARTH_RADIUS/3;
    earthOrbitCanvasSize = (int) (3*EARTH_RADIUS + 2*MOON_RADIUS);

    center = Position.centeredAt(width/2, height/2);
    earthOrbitCanvasCenter = center.translateBy(0, 2*SUN_RADIUS);
    earthCenter = earthOrbitCanvasCenter.translateBy(0, 0);
    earthOrbitCanvas = new Canvas(earthOrbitCanvasSize, earthOrbitCanvasSize);

    sun = Circle.of(SUN_RADIUS);
    earth = Circle.of(EARTH_RADIUS);
    moon = Circle.of(MOON_RADIUS);
  }

  /**
   * Draw a frame to the canvas
   *
   * @param mainCanvas The main canvas to draw on.
   */
  @Override
  public void drawFrame(Canvas mainCanvas) {
    mainCanvas.draw(sun, sunProperties, center);
    mainCanvas.draw(earth, earthProperties, earthOrbitCanvasCenter);
    earthOrbitCanvas.draw(earth, earthProperties, Position.centeredAt(0, 0));
    mainCanvas.draw(earthOrbitCanvas, earthOrbitCanvasCenter);
  }

  public static void main(String[] args) {
    int width = 800;
    int height = 600;

    ProcessingApp.start(SolarSystem::new, width, height);
  }
}
