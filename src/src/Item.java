import java.util.Objects;

abstract public class Item {
    protected final String name;
    protected final String description;
    protected final double weight;
    protected int value;
    protected int quantity;

    public Item(String name, String description, double weight, int value) {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.value = value;
        this.quantity = 1;
    }

    public void use() {
        System.out.printf("Verwende %s...%n", name);
    }

    @Override
    public String toString() {
        return String.format("""
                %s %dG (%.2f):
                %s
                """,
                name,
                value,
                weight,
                description
                );
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
        }
    }

    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }

    public void decreaseQuantity(int amount) {
        this.quantity -= amount;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public double getWeight() {
        return weight;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Item item = (Item) obj;

        return name.equals(item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
