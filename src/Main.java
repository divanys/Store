import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        Store store = new Store();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CashReceipt(store).setVisible(true);
            }
        });
    }
}