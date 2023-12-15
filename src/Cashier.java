class Cashier extends AbstractEmployee {
    Cashier(String name) {
        super(name);
    }

    @Override
    public String doWork() {
        return getName() + " обслуживает клиентов.";
    }

    // Реализация нового метода
    public void handlePayment(double amount) {
        System.out.println(getName() + " принимает оплату: $" + amount);
    }

    // Реализация нового метода для вывода подробной информации о кассире
    @Override
    public String getDetails() {
        return super.getDetails() + ", Должность: Кассир";
    }
}