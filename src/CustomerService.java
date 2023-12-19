import java.util.ArrayList;
import java.util.List;

public class CustomerService implements ICustomerService {
    private List<CustomerModel> customers;

    public CustomerService() {
        this.customers = new ArrayList<>();
        // Dummy veriler
        customers.add(new CustomerModel(1, "John", "Doe", "12345678901", null));
        customers.add(new CustomerModel(2, "Jane", "Smith", "98765432101", null));
    }

    @Override
    public void addCustomer(CustomerModel customer) {
        customers.add(customer);
        System.out.println("Müşteri eklendi: " + customer.getName());
    }

    @Override
    public void removeCustomer(int customerId) {
        customers.removeIf(c -> c.getId() == customerId);
        System.out.println("Müşteri silindi: ID " + customerId);
    }

    @Override
    public List<CustomerModel> getAllCustomers() {
        return new ArrayList<>(customers);
    }
}
