public class Builder {
  // The complex object we want to build
  static class Computer {
    // Required
    private final String cpu;
    private final int ram;
    // Optional
    private final int storage;
    private final String graphicsCard;

    private Computer(Builder builder) {
      this.cpu = builder.cpu;
      this.ram = builder.ram;
      this.storage = builder.storage;
      this.graphicsCard = builder.graphicsCard;
    }

    @Override
    public String toString() {
      return "Computer [CPU=]"
          + cpu
          + ", RAM="
          + ram
          + "GB Storage="
          + storage
          + "GB, GraphicsCard="
          + graphicsCard
          + "]";
    }

    // The static inner Builder class
    public static class Builder {
      // Required mirrored
      private final String cpu;
      private final int ram;
      // Optional
      private int storage = 256;
      private String graphicsCard = "Integrated";

      public Builder(String cpu, int ram) {
        this.cpu = cpu;
        this.ram = ram;
      }

      public Builder setStorage(int storage) {
        this.storage = storage;
        return this;
      }

      public void setGraphicsCard(String graphicsCard) {
        this.graphicsCard = graphicsCard;
        return this;
      }

      public Computer build() {
        return new Computer(this);
      }
    }
  }

  public static void main(String[] args) {
    // create a basic computer with only required attributes
    Computer basicComputer = new Computer.Builder("Intel i5", 8).build();
    System.out.println("Basic Computer Config: " + basicComputer);

    // Create a gaming computer with optional attributes by chaining methods
    Computer gamingComputer =
        new Computer.Builder("AMD Ryzen 7", 32)
            .setStorage(1024)
            .setGraphicsCard("NVIDIA RTX 4080")
            .build();
    System.out.println("Gaming Computer Config: " + gamingComputer);
  }
}
