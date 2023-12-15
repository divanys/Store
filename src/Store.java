import java.util.ArrayList;
import java.util.List;

class Store {
    private List<Product> products;
    private List<Employee> employees;
    private List<Customer> customers;

    Store() {
        this.products = new ArrayList<>();
        this.employees = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    // Продажа товара клиенту
    public String sellProduct(Customer customer, Product product) {
        if (products.contains(product)) {
            products.remove(product);
            customer.purchase(product);
            return "Продан " + product.getDescription() + " клиенту " + customer.getDetails();
        } else {
            return "Товара нет в наличии.";
        }
    }

    // Получение списка клиентов
    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Product> getProducts() {
        return products;
    }

    // Реализация нового метода для вывода информации о магазине
    public String displayStoreDetails() {
        String info = "\nИнформация о магазине:" +
                "\n   Количество продуктов: " + products.size() +
                "\n   Количество сотрудников: " + employees.size() +
                "\n   Количество клиентов: " + customers.size();

        // Вывод продуктов в консоль
        info += "\nСписок продуктов:\n";
        for (Product product : products) {
            info += product.getDescription();
            info += "\n";
        }

        return info;
    }

}
