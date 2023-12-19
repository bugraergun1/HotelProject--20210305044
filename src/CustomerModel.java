import java.util.Date;

public class CustomerModel implements Rentable {
    private int id;
    private String name;
    private String lastName;
    private Date birthDate;
    private String identityNumber;

    public CustomerModel(int id, String name, String lastName, String identityNumber, Date birthDate) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.identityNumber = identityNumber;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    @Override
    public boolean isAvailable() {
        return true; // Her zaman müsait, çünkü müşteri bağımsız olarak ele alındı.
    }

    @Override
    public void rent() {
        // Müşteri her zaman müsait olduğu için bu metodun içeriği boş bırakıldı.
    }
}