class RegularCustomer implements Customer {
    private String name;

    RegularCustomer(String name) {
        this.name = name;
    }

    @Override
    public String purchase(Product product) {
        return name + " купил " + product.getDescription();
    }

    // Реализация нового метода для вывода подробной информации о клиенте
    @Override
    public String getDetails() {
        return name;
    }
}
