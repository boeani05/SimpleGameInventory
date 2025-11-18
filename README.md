# SimpleGameInventory - Einfaches Spiel-Inventarsystem (Java CLI)

## ✨ Projektbeschreibung

Dieses Projekt implementiert ein rudimentäres Inventarsystem für ein fiktives Computerspiel, basierend auf Java-Kommandozeilen-Interaktionen (CLI). Es dient dazu, grundlegende Konzepte der objektorientierten Programmierung (OOP) wie Vererbung und Polymorphie, sowie die Verwendung von generischen Collections (insbesondere `ArrayList` für das Inventar) in einem praktischen Kontext zu festigen.

Das System ermöglicht es einem Spieler, verschiedene Arten von Gegenständen in einem Inventar zu verwalten, wobei Aspekte wie Item-Typen, Gewichtsbeschränkungen und das Stapeln von Items berücksichtigt werden.

**Ziel:** Dieses Projekt wurde als Lernübung erstellt, um das Verständnis für Java-Grundlagen und OOP zu vertiefen. Es ist besonders relevant für meine Ziele im Game Development und als Vorbereitung für meine Ausbildung zum Anwendungsentwickler.

## 🚀 Funktionen

*   **Item-Hierarchie:**
    *   Abstrakte Basisklasse `Item` mit grundlegenden Eigenschaften (`Name`, `Beschreibung`, `Gewicht`, `Wert`) und einer `use()`-Methode.
    *   Spezifische Unterklassen wie `Weapon` (mit `Schaden`) und `Consumable` (mit `Heilwert`), die die `use()`-Methode polymorph überschreiben.
*   **Generisches Inventar (`PlayerInventory`):**
    *   Verwaltet Items mithilfe einer `ArrayList<Item>`.
    *   Methoden zum Hinzufügen, Entfernen und Auflisten von Items.
    *   Berechnung des Gesamtgewichts und Gesamtwerts des Inventars.
    *   Suche nach Items anhand ihres Namens.
*   **Item Stacking:**
    *   `Consumable`-Items können gestapelt werden, d.h., mehrere gleiche Items belegen nur einen Inventarplatz, und ihre Menge wird entsprechend erhöht.
    *   Implementierung von `equals()` und `hashCode()` in der `Item`-Klasse, um das korrekte Stapeln zu gewährleisten.
*   **Gewichtsbeschränkung:**
    *   Das Inventar hat eine maximale Traglast (`MAX_WEIGHT`). Items können nur hinzugefügt werden, wenn das Gesamtgewicht die Grenze nicht überschreitet.
*   **Interaktives Kommandozeilen-Interface (CLI):**
    *   Ein benutzerfreundliches Menü zur Interaktion mit dem Inventar (Hinzufügen, Anzeigen, Verwenden, Wegwerfen).
    *   Robuste Fehlerbehandlung für ungültige Benutzereingaben (z.B. nicht-numerische Eingaben bei erwarteter Zahl).
    *   Konsistente Ressourcennutzung (einzelner `Scanner`) und sauberes Schließen des `Scanner`s.
    *   Die `useItem`-Funktion berücksichtigt, ob ein Item ein `Consumable` ist, um es nach Gebrauch aus dem Inventar zu entfernen oder seine Menge zu reduzieren.

## 🛠️ Technologien

*   Java (JDK 17+)
*   Standard Java Collections (`ArrayList`, `Optional`)
*   `Scanner` für Benutzereingaben

## ▶️ Wie man es ausführt

1.  **Kompilieren:** Stelle sicher, dass du ein Java Development Kit (JDK) installiert hast. Navigiere im Terminal oder der Kommandozeile zum Hauptverzeichnis des Projekts, wo sich deine `.java`-Dateien befinden, und kompiliere die Klassen:
    ```bash
    javac *.java
    ```
2.  **Ausführen:** Starte das Programm über die `Main`-Klasse:
    ```bash
    java Main
    ```
    Das Programm wird ein interaktives Menü in der Konsole anzeigen, über das du mit dem Inventar interagieren kannst.

## 📝 Zukünftige Erweiterungen (Ideen)

*   **Persistenz:** Speichern und Laden des Inventars in/aus einer Datei (z.B. CSV, JSON) oder einer Datenbank.
*   **Grafische Benutzeroberfläche (GUI):** Implementierung einer GUI (z.B. mit Swing, JavaFX), um das Inventar visuell darzustellen.
*   **Komplexere Item-Interaktionen:** Zum Beispiel: Items kombinieren, Items reparieren, Effekte von Consumables auf Spielerstatistiken.
*   **Mehr Item-Typen:** Rüstungen, Quest-Items, etc.
*   **Fehlerbehandlung verbessern:** Detailliertere Fehlermeldungen für den Benutzer.
