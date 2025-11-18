import java.util.*;

public class PlayerInventory {
    private final List<Item> items = new ArrayList<>();
    public final static double MAX_WEIGHT = 100.0;

    public void addItem(Item item) {
        boolean isItemStacked = false;

        if (item instanceof Consumable) {
            for (Item consumable : items) {
                if (consumable.equals(item)) {
                    if (getTotalWeight() + item.getWeight() <= MAX_WEIGHT) {
                        consumable.increaseQuantity(item.getQuantity());
                        isItemStacked = true;

                        System.out.printf("'%s' gestapelt. Neue Menge: %d%n", item.getName(), consumable.getQuantity());
                        break;
                    } else {
                        System.out.printf("Kann '%s' nicht stapeln. Inventar ist zu schwer!%n", item.getName());
                        return;
                    }
                }
            }
        }

        if (!isItemStacked) {
            if (getTotalWeight() + item.getWeight() * item.getQuantity() <= MAX_WEIGHT) {
                items.add(item);
                System.out.printf("'%s' zum Inventar hinzugefügt.%n", item.getName());
            } else {
                System.out.printf("'%s' ist zu schwer für dein momentanes Inventar.%n", item.getName());
            }
        }
    }

    public void removeItem(Item itemToRemove) {
        Optional<Item> foundItemOpt = findItemByName(itemToRemove.getName());
        if (foundItemOpt.isPresent()) {
            Item existingItem = foundItemOpt.get();
            if (existingItem.getQuantity() > 1 && itemToRemove instanceof Consumable) {
                existingItem.decreaseQuantity(1);
                System.out.printf("Ein '%s' entfernt. Verbleibende Menge: %d%n", existingItem.getName(), existingItem.getQuantity());
            } else {
                items.remove(existingItem); // Entferne das Item komplett
                System.out.printf("'%s' komplett aus dem Inventar entfernt.%n", existingItem.getName());
            }
        } else {
            System.out.printf("'%s' nicht im Inventar gefunden.%n", itemToRemove.getName());
        }
    }

    public void listItems() {
        if (items.isEmpty()) {
            System.out.println("Das Inventar ist leer.\n");
            return;
        }
        System.out.println("--- Inventar-Liste ---");
        for (Item item : items) {
            System.out.println(item.toString());
        }
        System.out.printf("""
                Gesamtgewicht: %.2f
                Gesamtwert: %d
                """,
                getTotalWeight(),
                getTotalValue()
                );
        System.out.println("--------------------");
    }

    public double getTotalWeight() {
        double finalWeight = 0.0;
        for (Item item : items) {
            finalWeight += item.getWeight() * item.getQuantity(); // Gewicht * Menge
        }
        return finalWeight;
    }

    public int getTotalValue() {
        int finalValue = 0;
        for (Item item : items) {
            finalValue += item.getValue() * item.getQuantity(); // Wert * Menge
        }
        return finalValue;
    }

    public Optional<Item> findItemByName(String name) {
        return items.stream()
                .filter(item -> item.getName().equalsIgnoreCase(name))
                .findFirst();
    }


}
