import java.util.Date;

// Oda tipini temsil eden enum
enum RoomType {
    STANDARD,
    KING
}

public class RoomModel implements Rentable {
    private int id;
    private int roomNumber;
    private RoomType type;
    private boolean isEmpty;

    private int price;

    public RoomModel(int id, int roomNumber, RoomType type, boolean isEmpty,int price) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.type = type;
        this.isEmpty = isEmpty;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public RoomType getType() {
        return type;
    }

    @Override
    public boolean isAvailable() {
        return isEmpty;
    }

    @Override
    public void rent() {
        isEmpty = false;
    }
}