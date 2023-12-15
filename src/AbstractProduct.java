abstract class AbstractProduct implements Product {
    private String name;
    private double price;

    AbstractProduct(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    // Реализация нового метода
    @Override
    public String getDescription() {
        return "Товар: " + name + ", Цена: $" + price;
    }
}