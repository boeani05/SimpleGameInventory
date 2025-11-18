public class Consumable extends Item {
    private final int healAmount;

    public Consumable(String name, String description, double weight, int value, int healAmount) {
        super(name, description, weight, value);
        this.healAmount = healAmount;
    }

    @Override
    public void use() {
        System.out.printf("Heilung um %d...%n", healAmount);
    }

    @Override
    public String toString() {
        return String.format("""
                %s %dG (%d HEALING, %.2f):
                %s
                """,
                name,
                value * quantity,
                healAmount,
                weight * quantity,
                description
        );
    }
}
