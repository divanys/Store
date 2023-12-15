abstract class AbstractEmployee implements Employee {
    private String name;

    AbstractEmployee(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String doWork() {
        return name + " выполняет свою работу.";
    }

    // Реализация нового метода
    @Override
    public String getDetails() {
        return name;
    }
}