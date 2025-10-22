public class Static {
  // 1. The common interface
  interface Shape {
    void draw();
  }

  // 2. Concrete classes impleenting the interface
  // Note: Constructors are package-private to encourage factory use
  static class Circle implements Shape {
    private Circle() {} // Private constructor

    @Override
    public void draw() {
      System.out.println("Drawing Circle");
    }
  }

  static class Rectangle implements Shape {
    private Rectangle() {}

    @Override
    public void draw() {
      System.out.println("Drawing Rectangle");
    }
  }

  // 3. The Factory class with the static factory method
  public static class ShapeFactory {
    // The static factory method
    public static Shape getShape(String shapeType) {
      if (shapeType == null) {
        return null;
      }
      if (shapeType.equalsIgnoreCase("CIRCLE")) {
        return new Circle();
      } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
        return new Rectangle();
      }
      return null;
    }
  }

  public static void main(String[] args) {
    // Get an object of Circle and call its draw method.
    Shape shape1 = ShapeFactory.getShape("CIRCLE");
    shape1.draw();

    // Get an object of Rectangle and call its draw method.
    Shape shape2 = ShapeFactory.getShape("RECTANGLE");
    shape2.draw();
  }
}
