import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class CashReceipt extends JFrame {
    private JPanel panel1;
    private JButton regCashierButton;
    private JButton regProductButton;
    private JButton regCustomerButton;
    private JLabel infoAboutNeedRegLabel;
    private JList EmployeeList1;
    private JList CustomerList2;
    private JList ProductList3;
    private JSpinner spinner1;
    private JLabel choiceProductLabel;
    private JLabel choiceCustomerLabel;
    private JLabel choiceEmployeeLabel;
    private JLabel choiceCountLabel;
    private JButton buyAndShowCashReceiptButton;
    private JLabel writeInfoLabel;
    private JButton regManagerButton;
    private JCheckBox yearsOld18CheckBox;
    private JLabel yearsOld18Label;
    private JButton showInfoAboutStoreButton;
    private Store store;

    private void createUIComponents() {
        // TODO: place custom component creation code here
        panel1 = new JPanel();
        regCashierButton = new JButton();
        regManagerButton = new JButton();
        regProductButton = new JButton();
        regCustomerButton = new JButton();
        infoAboutNeedRegLabel = new JLabel();
        EmployeeList1 = new JList();
        CustomerList2 = new JList();
        ProductList3 = new JList();
        spinner1 = new JSpinner();
        choiceProductLabel = new JLabel();
        choiceCustomerLabel = new JLabel();
        choiceEmployeeLabel = new JLabel();
        choiceCountLabel = new JLabel();
        buyAndShowCashReceiptButton = new JButton();
        yearsOld18CheckBox = new JCheckBox();
        yearsOld18Label = new JLabel();
        writeInfoLabel = new JLabel();
        showInfoAboutStoreButton = new JButton();
    }

    public CashReceipt(Store store) {
        this.store = store;
        this.getContentPane().add(panel1);
        setTitle("Магазин");
        Dimension size = new Dimension(720, 360);
        setMinimumSize(size);
        setSize(size);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // обработчики кнопок регистрации (нажав на кнопку, пользователь сможет ввести данные в диалоговом окне)
        regCashierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Введите фамилию кассира: ");
                Cashier cashier = new Cashier(name);
                store.addEmployee(cashier);

                updateEmployeeList();
            }
        });

        regManagerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Введите фамилию менеджера: ");
                Manager manager = new Manager(name);
                store.addEmployee(manager);

                updateEmployeeList();
            }
        });

        regProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Введите название продукта:");
                double price = Double.parseDouble(JOptionPane.showInputDialog("Введите цену продукта:"));

                // Запрашиваем тип продукта (пищевой или электроника)
                String[] options = {"Пищевой", "Электроника"};
                int productType = JOptionPane.showOptionDialog(null, "Выберите тип продукта:", "Выбор типа продукта",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                // Создаем продукт в зависимости от выбранного типа
                Product product;
                if (productType == 0) {
                    product = new FoodProduct(name, price);
                } else {
                    product = new ElectronicsProduct(name, price);
                }

                store.addProduct(product);

                updateProductList();
            }
        });

        regCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Введите имя покупателя:");

                RegularCustomer regularCustomer = new RegularCustomer(name);
                store.addCustomer(regularCustomer);

                updateCustomerList();
            }
        });

        // кнопка формирования чека. после нажатия появится информационное окно, в котором будет сам чек о покупке
        buyAndShowCashReceiptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Получаем выбранный продукт из списка
                int selectedProductIndex = ProductList3.getSelectedIndex();
                if (selectedProductIndex == -1) {
                    JOptionPane.showMessageDialog(null, "Выберите продукт.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Product selectedProduct = store.getProducts().get(selectedProductIndex);

                // Получаем выбранного сотрудника
                int selectedEmployeeIndex = EmployeeList1.getSelectedIndex();
                if (selectedEmployeeIndex == -1) {
                    JOptionPane.showMessageDialog(null, "Выберите сотрудника.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Employee selectedEmployee = store.getEmployees().get(selectedEmployeeIndex);

                // Получаем выбранного покупателя
                int selectedCustomerIndex = CustomerList2.getSelectedIndex();
                if (selectedCustomerIndex == -1) {
                    JOptionPane.showMessageDialog(null, "Выберите покупателя.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Customer selectedCustomer = store.getCustomers().get(selectedCustomerIndex);

                // Получаем количество продуктов из spinner1
                int quantity = (int) spinner1.getValue();
                if (quantity <= 0) {
                    JOptionPane.showMessageDialog(null, "Введите количество продуктов больше нуля.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (selectedProduct.getName().equalsIgnoreCase("Алкоголь")) {
                    // Проверяем, установлен ли флажок yearsOld18CheckBox
                    if (!yearsOld18CheckBox.isSelected()) {
                        // Выводим предупреждение
                        JOptionPane.showMessageDialog(null, "Покупка алкоголя разрешена только лицам старше 18 лет.",
                                "Предупреждение", JOptionPane.WARNING_MESSAGE);
                        return; // Прерываем выполнение метода
                    }
                }

                // Получаем цену для чека
                int totalPrice = getPriceForCashReceipt(quantity, selectedProduct);

                // Формируем чек
                String cashReceipt = "Чек:\n" +
                        "Сотрудник: " + selectedEmployee.getDetails() + "\n" +
                        "Покупатель: " + selectedCustomer.getDetails() + "\n" +
                        "Продукт: " + selectedProduct.getDescription() + "\n" +
                        "Количество: " + quantity + "\n" +
                        "Цена за единицу: $" + selectedProduct.getPrice() + "\n" +
                        "Общая цена: $" + totalPrice + "\n";

                // Отображаем чек в диалоговом окне
                JOptionPane.showMessageDialog(null, cashReceipt, "Чек", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        showInfoAboutStoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, store.displayStoreDetails(), "Информация о магазине", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }


    // методы для получения данных, которые ввёл ранее пользователь
    private void updateEmployeeList() {
        // Получаем список сотрудников из магазина
        List<Employee> employees = store.getEmployees();

        // Преобразуем список в массив строк
        String[] employeeNames = new String[employees.size()];
        for (int i = 0; i < employees.size(); i++) {
            employeeNames[i] = employees.get(i).getDetails();
        }

        // Обновляем JList
        EmployeeList1.setListData(employeeNames);
    }

    private void updateProductList() {
        // Получаем список продуктов из магазина
        List<Product> products = store.getProducts();

        // Преобразуем список в массив строк
        String[] productNames = new String[products.size()];
        for (int i = 0; i < products.size(); i++) {
            productNames[i] = products.get(i).getDescription();
        }

        ProductList3.setListData(productNames);
    }

    private void updateCustomerList() {
        // Получаем список клиентов из магазина
        List<Customer> customers = store.getCustomers();

        // Преобразуем список в массив строк
        String[] customerNames = new String[customers.size()];
        for (int i = 0; i < customers.size(); i++) {
            customerNames[i] = customers.get(i).getDetails();
        }

        // Обновляем JList
        CustomerList2.setListData(customerNames);
    }

    private int getPriceForCashReceipt(int quantity, Product product) {
        // Получаем цену продукта и умножаем на количество
        return (int) (quantity * product.getPrice());
    }
}
