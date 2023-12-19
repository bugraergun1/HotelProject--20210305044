public class RoomCustomerModel {
    private Rentable rentable;

    public RoomCustomerModel(Rentable rentable) {
        this.rentable = rentable;
    }

    public Rentable getRentable() {
        return rentable;
    }

    public void rentRoom() {
        rentable.rent();
        System.out.println("Oda kiralandı!");
    }
}