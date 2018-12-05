package processingwrapper.usages;

import processingwrapper.*;
import processingwrapper.Canvas;

import java.awt.*;

public class SolarSystem implements ProcessingApp {

  private static final double
    DEGREES_IN_A_CIRCLE = 360,
    DAYS_IN_A_VENUS_YEAR = 225,
    DAYS_IN_A_YEAR = 365.25,
    MONTHS_IN_A_YEAR = 12,
    DAYS_IN_A_MONTH = DAYS_IN_A_YEAR/MONTHS_IN_A_YEAR;

  private final double venusOrbitRadius, earthOrbitRadius, moonOrbitRadius;
  private double venusAngleDeg, earthAngleDeg, moonAngleDeg;

  private final Position center, earthCenter;
  private final Canvas earthOrbitCanvas;

  private final Drawable sun, venus, earth, moon;
  private static final ShapeSettings
    SUN_PROPERTIES = ShapeSettings.createWithFill(Color.ORANGE),
    VENUS_PROPERTIES = ShapeSettings.createWithFill(Color.RED),
    EARTH_PROPERTIES = ShapeSettings.createWithFill(Color.BLUE),
    MOON_PROPERTIES = ShapeSettings.createWithFill(Color.GRAY);

  public SolarSystem(int width, int height) {
    double sunRadius = width/10;
    double venusRadius = sunRadius/5.2f;
    double earthRadius = sunRadius/5;
    double moonRadius = earthRadius/3;

    venusOrbitRadius = Math.abs(3.25f*sunRadius);
    earthOrbitRadius = Math.abs(4*sunRadius);
    moonOrbitRadius = earthRadius + 2*moonRadius;

    int earthOrbitCanvasSize = (int) (3*earthRadius + 2*moonRadius);
    venusAngleDeg = 0;
    earthAngleDeg = 0;
    moonAngleDeg = 0;

    center = Position.centeredAt(width/2, height/2);
    earthOrbitCanvas = Canvas.of(earthOrbitCanvasSize, earthOrbitCanvasSize);
    earthCenter = Position.centeredAt(earthOrbitCanvasSize / 2, earthOrbitCanvasSize / 2);

    // Since we are frequently drawing the sun with the same properties, we package together
    // the shape and its properties.
    sun = Drawable.ofShape(Circle.of(sunRadius), SUN_PROPERTIES);
    venus = Drawable.ofShape(Circle.of(venusRadius), VENUS_PROPERTIES);
    earth = Drawable.ofShape(Circle.of(earthRadius), EARTH_PROPERTIES);
    moon = Drawable.ofShape(Circle.of(moonRadius), MOON_PROPERTIES);
  }

  /**
   * Draw a frame to the canvas
   *
   * @param mainCanvas The main canvas to draw on.
   */
  @Override
  public void drawFrame(Canvas mainCanvas) {
    Position venusCenter = center.translateBy(
        venusOrbitRadius * Math.cos(Math.toRadians(venusAngleDeg)),
        venusOrbitRadius * Math.sin(Math.toRadians(venusAngleDeg)));
    Position earthOrbitCanvasCenter = center.translateBy(
        earthOrbitRadius * Math.cos(Math.toRadians(earthAngleDeg)),
        earthOrbitRadius * Math.sin(Math.toRadians(earthAngleDeg)));
    Position moonCenter = earthCenter.translateBy(
        moonOrbitRadius * Math.cos(Math.toRadians(moonAngleDeg)),
        moonOrbitRadius * Math.sin(Math.toRadians(moonAngleDeg)));

    mainCanvas.draw(sun, center);
    mainCanvas.draw(venus, venusCenter);
    earthOrbitCanvas.draw(earth, earthCenter);
    earthOrbitCanvas.draw(moon, moonCenter);
    mainCanvas.draw(earthOrbitCanvas, earthOrbitCanvasCenter);
    venusAngleDeg += DEGREES_IN_A_CIRCLE / DAYS_IN_A_VENUS_YEAR;
    earthAngleDeg += DEGREES_IN_A_CIRCLE / DAYS_IN_A_YEAR;
    moonAngleDeg += DEGREES_IN_A_CIRCLE / DAYS_IN_A_MONTH;
  }

  public static void main(String[] args) {
    int width = 600;
    int height = 600;

    ProcessingApp.start(SolarSystem::new, width, height);
  }
}
