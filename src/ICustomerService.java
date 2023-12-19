import java.util.List;

public interface ICustomerService {
    void addCustomer(CustomerModel customer);
    void removeCustomer(int customerId);
    List<CustomerModel> getAllCustomers();
}