package processingwrapper.usages;

import processingwrapper.*;
import processingwrapper.Canvas;

import java.awt.*;

public class SolarSystem implements ProcessingApp {

  static final double DEGREES_IN_A_CIRCLE = 360;
  static final double DAYS_IN_A_YEAR = 365.25;
  static final double MONTHS_IN_A_YEAR = 12;
  static final double DAYS_IN_A_MONTH = DAYS_IN_A_YEAR/MONTHS_IN_A_YEAR;

  float SUN_RADIUS, EARTH_RADIUS, MOON_RADIUS, EARTH_ORBIT_RADIUS, MOON_ORBIT_RADIUS;
  int earthOrbitCanvasSize;
  double earthAngleDeg, moonAngleDeg;

  Position center, earthOrbitCanvasCenter, earthCenter, moonCenter;
  Canvas earthOrbitCanvas;

  Circle sun, earth, moon;
  ShapeSettings sunProperties = ShapeSettings.createWithFill(Color.ORANGE);
  ShapeSettings earthProperties = ShapeSettings.createWithFill(Color.BLUE);
  ShapeSettings moonProperties = ShapeSettings.createWithFill(Color.GRAY);

  public SolarSystem(int width, int height) {
    SUN_RADIUS = width/10;
    EARTH_RADIUS = SUN_RADIUS/5;
    MOON_RADIUS = EARTH_RADIUS/3;
    earthOrbitCanvasSize = (int) (3*EARTH_RADIUS + 2*MOON_RADIUS);
    earthAngleDeg = 0;
    moonAngleDeg = 0;

    center = Position.centeredAt(width/2, height/2);
    earthCenter = Position.centeredAt(earthOrbitCanvasSize/2, earthOrbitCanvasSize/2);

    EARTH_ORBIT_RADIUS = Math.abs(4*SUN_RADIUS);
    MOON_ORBIT_RADIUS = EARTH_RADIUS + 2*MOON_RADIUS;

    earthOrbitCanvasCenter = center.translateBy((float) (EARTH_ORBIT_RADIUS*Math.cos(Math.toRadians(earthAngleDeg))), (float) (EARTH_ORBIT_RADIUS*Math.sin(Math.toRadians(earthAngleDeg))));
    moonCenter = earthCenter.translateBy((float) (MOON_ORBIT_RADIUS*Math.cos(Math.toRadians(moonAngleDeg))), (float) (MOON_ORBIT_RADIUS*Math.sin(Math.toRadians(moonAngleDeg))));
//    moonCenter = earthCenter.translateBy(0, EARTH_RADIUS + 2*MOON_RADIUS);
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
    earthOrbitCanvasCenter = center.translateBy((float) (EARTH_ORBIT_RADIUS*Math.cos(Math.toRadians(earthAngleDeg))), (float) (EARTH_ORBIT_RADIUS*Math.sin(Math.toRadians(earthAngleDeg))));
    moonCenter = earthCenter.translateBy((float) (MOON_ORBIT_RADIUS*Math.cos(Math.toRadians(moonAngleDeg))), (float) (MOON_ORBIT_RADIUS*Math.sin(Math.toRadians(moonAngleDeg))));

    mainCanvas.draw(sun, sunProperties, center);
    earthOrbitCanvas.draw(earth, earthProperties, earthCenter);
    earthOrbitCanvas.draw(moon, moonProperties, moonCenter);
    mainCanvas.draw(earthOrbitCanvas, earthOrbitCanvasCenter);
    earthAngleDeg += DEGREES_IN_A_CIRCLE/DAYS_IN_A_YEAR;
    moonAngleDeg += DEGREES_IN_A_CIRCLE/DAYS_IN_A_MONTH;
  }

  public static void main(String[] args) {
    int width = 600;
    int height = 600;

    ProcessingApp.start(SolarSystem::new, width, height);
  }
}
