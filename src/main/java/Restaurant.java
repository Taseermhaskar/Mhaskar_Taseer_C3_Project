import java.time.Clock;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    public LocalTime openingTime;
    public LocalTime closingTime;
    public Clock clock;
    private final String name;
    private final String location;
    private final List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this(name, location, openingTime, closingTime, null);
    }

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime, Clock clock) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.clock = clock;
    }

    public boolean isRestaurantOpen() {
        return getCurrentTime().compareTo(closingTime) > 0 && getCurrentTime().compareTo(openingTime) < 0;
    }

    public LocalTime getCurrentTime() {
        return clock == null ? LocalTime.now() : LocalTime.now(clock);
    }

    public List<Item> getMenu() {
        return menu;
    }

    private Item findItemByName(String itemName) {
        for (Item item : menu) {
            if (item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name, price);
        menu.add(newItem);
    }

    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }

    public void displayDetails() {
        System.out.println("Restaurant:" + name + "\n"
                + "Location:" + location + "\n"
                + "Opening time:" + openingTime + "\n"
                + "Closing time:" + closingTime + "\n"
                + "Menu:" + "\n" + getMenu());

    }

    public String getName() {
        return name;
    }


    public int getItemTotal(List<String> items) {
        int total = 0;

        for (String itemName: items) {
            Item item = findItemByName(itemName);
            if (item != null) {
                total += item.getPrice();
            }
        }

        return total;
    }
}
