public class Weapon extends Item {
    private final int damage;

    public Weapon(String name, String description, double weight, int value, int damage) {
        super(name, description, weight, value);
        this.damage = damage;
    }

    @Override
    public void use() {
        System.out.printf("Greife an (%d Schaden)...%n", damage);
    }

    @Override
    public String toString() {
        return String.format("""
                %s %dG (%d DMG, %.2f):
                %s
                """,
                name,
                value * quantity,
                damage,
                weight * quantity,
                description
        );
    }
}
