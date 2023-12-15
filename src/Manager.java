class Manager extends AbstractEmployee {
    Manager(String name) {
        super(name);
    }

    @Override
    public String doWork() {
        return getName() + " управляет работой магазина.";
    }

    // Реализация нового метода
    public String conductMeeting() {
        return getName() + " проводит совещание.";
    }

    // Реализация нового метода для вывода подробной информации о менеджере
    @Override
    public String getDetails() {
        return super.getDetails() + ", Должность: Менеджер";
    }
}