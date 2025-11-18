import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static PlayerInventory inventory = new PlayerInventory();

    public static void main(String[] args) {
        Main menu = new Main();

        boolean isMenuActive = true;
        Scanner scanner = new Scanner(System.in);

        while (isMenuActive) {
            int menuChoice;

            System.out.println("""
                    1. Item hinzufügen
                    2. Inventarliste anzeigen
                    3. Item verwenden
                    4. Item wegwerfen
                    5. Beenden
                    """);

            while (true) {
                try {
                    menuChoice = scanner.nextInt();
                    scanner.nextLine();
                    break;
                } catch (InputMismatchException e) {
                    System.err.println("Gib eine gültige Auswahl ein!");
                    scanner.next();
                }
            }


            switch (menuChoice) {
                case 1:
                    int itemChoice;
                    System.out.println("=== Welches Item möchtest du hinzufügen? ===\n1. Weapon\n2. Consumable");


                    while (true) {
                        try {
                            itemChoice = scanner.nextInt();
                            scanner.nextLine();
                            break;
                        } catch (InputMismatchException e) {
                            System.err.println("Gib etwas gültiges ein!");
                            scanner.next();
                        }
                    }

                    menu.addItem(itemChoice, scanner);
                    break;
                case 2:
                    System.out.println("=== Inventarliste wird angezeigt ===");
                    inventory.listItems();
                    break;
                case 3:
                    String usedItem;
                    System.out.println("=== Welches Item möchtest du verwenden? ===");
                    usedItem = scanner.nextLine();

                    menu.useItem(usedItem);
                    break;
                case 4:
                    String removedItem;
                    System.out.println("=== Welches Item möchtest du wegwerfen? ===");
                    removedItem = scanner.nextLine();

                    menu.removeItem(removedItem);
                    break;
                case 5:
                    isMenuActive = false;
                    break;
                default:
                    System.out.println("Falscher Input!");
            }
        }
        scanner.close();
    }

    private void addItem(int itemChoice, Scanner scanner) {

        switch (itemChoice) {
            case 1:
                String weaponName;
                String weaponDescription;
                double weaponWeight;
                int weaponValue;
                int weaponDamage;

                System.out.println("=== Welche Waffe möchtest du hinzufügen? ===");
                weaponName = scanner.nextLine();

                System.out.println("=== Gib eine kurze Beschreibung der Waffe ein! ===");
                weaponDescription = scanner.nextLine();

                System.out.println("=== Gib das Waffengewicht ein! ===");
                while (true) {
                    try {
                        weaponWeight = scanner.nextDouble();
                        scanner.nextLine();
                        break;
                    } catch (InputMismatchException e) {
                        System.err.println("Gib ein gültiges Gewicht ein!");
                        scanner.next();
                    }
                }

                System.out.println("=== Gib den Wert der Waffe ein! ===");
                while (true) {
                    try {
                        weaponValue = scanner.nextInt();
                        scanner.nextLine();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Gib einen gültigen Wert ein!");
                        scanner.next();
                    }
                }

                System.out.println("=== Gib den Schaden der Waffe ein! ===");
                while (true) {
                    try {
                        weaponDamage = scanner.nextInt();
                        scanner.nextLine();
                        break;
                    } catch (InputMismatchException e) {
                        System.err.println("Gib einen gültigen Schadenswert ein!");
                        scanner.next();
                    }
                }

                Weapon newWeapon = new Weapon(weaponName, weaponDescription, weaponWeight, weaponValue, weaponDamage);
                inventory.addItem(newWeapon);
                break;
            case 2:
                String consumableName;
                String consumableDescription;
                double consumableWeight;
                int consumableValue;
                int healAmount;

                System.out.println("=== Welches Consumable möchtest du hinzufügen? ===");
                consumableName = scanner.nextLine();

                System.out.println("=== Gib eine kurze Beschreibung des Consumables ein! ===");
                consumableDescription = scanner.nextLine();

                System.out.println("=== Gib das Gewicht des Consumables ein! ===");
                while (true) {
                    try {
                        consumableWeight = scanner.nextDouble();
                        scanner.nextLine();
                        break;
                    } catch (InputMismatchException e) {
                        System.err.println("Gib ein gültiges Gewicht ein!");
                        scanner.next();
                    }
                }

                System.out.println("=== Gib den Wert des Consumables ein! ===");
                while (true) {
                    try {
                        consumableValue = scanner.nextInt();
                        scanner.nextLine();
                        break;
                    } catch (InputMismatchException e) {
                        System.err.println("Gib einen gültigen Wert ein!");
                        scanner.next();
                    }
                }

                System.out.println("=== Gib den Healing Amount des Consumables ein! ===");
                while (true) {
                    try {
                        healAmount = scanner.nextInt();
                        scanner.nextLine();
                        break;
                    } catch (InputMismatchException e) {
                        System.err.println("Gib einen gültigen Healing Wert ein!");
                        scanner.next();
                    }
                }

                Consumable newConsumable = new Consumable(consumableName, consumableDescription, consumableWeight, consumableValue, healAmount);
                inventory.addItem(newConsumable);
                break;
            default:
                System.out.println("Ungültiger Eingabewert!");
        }
    }

    private void useItem(String name) {
        Optional<Item> itemToUseOpt = inventory.findItemByName(name);

        if (itemToUseOpt.isPresent()) {
            Item itemToUse = itemToUseOpt.get();
            itemToUse.use();

            if (itemToUse instanceof Consumable) {
                inventory.removeItem(itemToUse);
                System.out.println("Eine Einheit von '" + name + "' wurde verbraucht.");
            } else {
                System.out.println("'" + name + "' kann nicht verbraucht/entfernt werden (kein Consumable).");
            }
        } else {
            System.out.println("'" + name + "' nicht im Inventar gefunden oder nichts zum Verbrauchen da!\n");
        }
    }

    private void removeItem(String name) {
        Optional<Item> itemToRemove = inventory.findItemByName(name);

        itemToRemove.ifPresentOrElse((item -> inventory.removeItem(item)), () -> System.out.println("Es gibt nichts wegzuschmeißen!\n"));
    }
}