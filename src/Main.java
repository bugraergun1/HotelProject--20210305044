import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Dolu ve boş odaları görmek ister misiniz? (Evet/Hayır): ");
        String showRoomsOption = scanner.nextLine();

        Rentable standardRoom = new RoomModel(1, 101, RoomType.STANDARD, true,200);
        Rentable kingRoom = new RoomModel(2, 201, RoomType.KING, false,300); // Örnek olarak bir boş King oda ekledim.

        if ("Evet".equalsIgnoreCase(showRoomsOption)) {
            showRentables(standardRoom, kingRoom);

            System.out.print("Giriş yapmak istiyor musunuz? (Evet/Hayır): ");
            String loginOption = scanner.nextLine();

            if ("Evet".equalsIgnoreCase(loginOption)) {
                handleCustomerLogin(scanner);

                Rentable selectedRentable = selectAvailableRoom(scanner,standardRoom, kingRoom);

                if (selectedRentable != null) {
                    rentRoom(selectedRentable);
                } else {
                    System.out.println("Üzgünüz, şu anda boş oda bulunmamaktadır.");
                }
            } else {
                System.out.println("Giriş yapmak istemediniz. İyi günler!");
            }
        } else {
            handleCustomerLogin(scanner);
            Rentable selectedRentable = selectAvailableRoom(scanner,standardRoom, kingRoom);

            if (selectedRentable != null) {
                rentRoom(selectedRentable);
            } else {
                System.out.println("Üzgünüz, şu anda boş oda bulunmamaktadır.");
            }
        }

        System.out.println("Hoşçakalın! Program kapatılıyor.");
        scanner.close();
    }

    private static void handleCustomerLogin(Scanner scanner) {
        System.out.print("Müşteri ID: ");
        int customerId = getInputWithValidation(scanner, Integer.class, "Lütfen müşteri ID girin: ");

        System.out.print("Müşteri Adı: ");
        String customerName = scanner.nextLine();

        System.out.print("Müşteri Soyadı: ");
        String customerLastName = scanner.nextLine();

        System.out.print("Müşteri Kimlik Numarası: ");
        String identityNumber = scanner.nextLine();

        System.out.print("Müşteri Giriş Tarihi (yyyy-MM-dd): ");
        Date birthDate = getInputWithValidation(scanner, Date.class, "Lütfen müşteri giriş tarihini girin (yyyy-MM-dd): ");

        CustomerService customerService = new CustomerService();
        customerService.addCustomer(new CustomerModel(customerId, customerName, customerLastName, identityNumber, birthDate));
    }

    private static Rentable selectAvailableRoom(Scanner scanner, Rentable... rentables) {
        System.out.println("Boş Odalar:");

        long availableRoomCount = Arrays.stream(rentables)
                .filter(Rentable::isAvailable)
                .peek(rentable -> {
                    System.out.println("ID: " + rentable.hashCode() +
                            ", Tip: " + ((RoomModel) rentable).getType() +
                            ", Ücret: " + ((RoomModel) rentable).getPrice() +
                            ", Oda Numarası: " + ((RoomModel) rentable).getRoomNumber());
                })
                .count();

        if (availableRoomCount > 1) {
            while (true) {
                System.out.print("Birden fazla boş oda bulunmaktadır. Lütfen bir oda seçin (ID): ");
                int selectedRoomId = getInputWithValidation(scanner, Integer.class, "");

                Rentable selectedRentable = Arrays.stream(rentables)
                        .filter(rentable -> rentable.hashCode() == selectedRoomId)
                        .findFirst()
                        .orElse(null);

                if (selectedRentable != null) {
                    return selectedRentable;
                } else {
                    System.out.println("Geçersiz oda ID'si. Lütfen tekrar deneyin.");
                }
            }
        } else {
            return Arrays.stream(rentables)
                    .filter(Rentable::isAvailable)
                    .findFirst()
                    .orElse(null);
        }
    }


    private static void rentRoom(Rentable rentable) {
        RoomCustomerModel roomCustomer = new RoomCustomerModel(rentable);
        roomCustomer.rentRoom();
        System.out.println("Teşekkür ederiz! Giriş işlemi tamamlandı.");
    }

    private static void showRentables(Rentable... rentables) {
        System.out.println("Kiralık Nesneler:");
        for (Rentable rentable : rentables) {
            System.out.println("ID: " + rentable.hashCode() +
                    ", Durum: " + (rentable.isAvailable() ? "Boş" : "Dolu"));
        }
    }

    private static <T> T getInputWithValidation(Scanner scanner, Class<T> type, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Lütfen bir değer girin.");
                continue;
            }

            try {
                if (type == Integer.class) {
                    return type.cast(Integer.parseInt(input));
                } else if (type == Date.class) {
                    return type.cast(new SimpleDateFormat("yyyy-MM-dd").parse(input));
                } else {
                    System.out.println("Beklenmeyen veri tipi. Lütfen doğru veri tipinde giriş yapın.");
                }
            } catch (NumberFormatException | ParseException e) {
                System.out.println("Hatalı giriş. Lütfen doğru formatta giriş yapın.");
            }
        }
    }
}
