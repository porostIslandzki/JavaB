package org.example;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Person {

    private static final String REGEX = ",";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private String name;
    private LocalDate birth;
    private LocalDate death;
    private List<Person> parents; // lista rodziców
    private static Map<String, Person> personMap = new HashMap<>();
    private String parent1Name;
    private String parent2Name;

    // Konstruktor klasy Person
    public Person(String name, String birth, String death, String parent1Name, String parent2Name) {
        this.name = name;
        this.birth = parseDate(birth);  // Konwersja daty urodzenia ze String na LocalDate
        this.death = parseDate(death);  // Konwersja daty śmierci ze String na LocalDate
        this.parents = new ArrayList<>();
        this.parent1Name = parent1Name;
        this.parent2Name = parent2Name;
    }

    // Metoda konwertująca String na LocalDate z obsługą wyjątków
    private LocalDate parseDate(String date) {
        if (date == null || date.trim().isEmpty()) {
            return null;  // Zwraca null, jeśli data jest pusta lub null
        }
        try {
            return LocalDate.parse(date.trim(), DATE_TIME_FORMATTER);  // Parsuje datę do LocalDate
        } catch (DateTimeParseException e) {
            System.out.println("Nieprawidłowy format daty: " + date);  // Obsługuje błędy formatu daty
            return null;
        }
    }

    // Statyczna metoda wytwórcza, która tworzy obiekt Person na podstawie linii CSV
    public static Person fromCsvLine(String line) {
        String[] tempArr = line.split(REGEX);
        String name = tempArr.length > 0 ? tempArr[0].trim() : "";
        String birth = tempArr.length > 1 ? tempArr[1].trim() : "";
        String death = tempArr.length > 2 ? tempArr[2].trim() : "";
        String parent1Name = tempArr.length > 3 ? tempArr[3].trim() : "";
        String parent2Name = tempArr.length > 4 ? tempArr[4].trim() : "";
        return new Person(name, birth, death, parent1Name, parent2Name);
    }

    public void addParent(Person parent) {
        this.parents.add(parent);
    }

    // Metoda do wczytywania danych z pliku CSV
    public static List<Person> fromCsv(String path) throws IOException {
        List<Person> personList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line = reader.readLine(); // Pomijanie pierwszej linii (nagłówka)
            while ((line = reader.readLine()) != null) {
                Person person = fromCsvLine(line);
                if (person != null) {
                    try {
                        person.validateLifespan();
                        person.validateAmbiguity(personList);
                        personList.add(person);
                        personMap.put(person.getName(), person);
                    } catch (NegativeLifespanException | AmbiguousPersonException e) {
                        System.err.println(e.getMessage());
                        e.printStackTrace();
                    }
                }
            }

            // Druga faza: Przypisywanie rodziców
            for (Person person : personList) {
                if (person.parent1Name != null && !person.parent1Name.isEmpty()) {
                    Person parent1 = personMap.get(person.parent1Name);
                    if (parent1 != null) {
                        person.addParent(parent1);
                    }
                }

                if (person.parent2Name != null && !person.parent2Name.isEmpty()) {
                    Person parent2 = personMap.get(person.parent2Name);
                    if (parent2 != null) {
                        person.addParent(parent2);
                    }
                }
                //Walidacja wieku rodziców
                //Przechwyć ten wyjątek tak, aby nie zablokował dodania takiej osoby, a jedynie poprosił
                // użytkownika o potwierdzenie lub odrzucenie takiego przypadku
                // za pomocą wpisania znaku “Y” ze standardowego wejścia.
                try {
                    person.validateParent(person.getParents());
                } catch (ParentingAgeException e) {
                    Scanner scanner = new Scanner(System.in /*terminal*/);
                    String ask = scanner.next();
                    System.err.println(e.getMessage());
                    System.out.println("Czy chcesz zatwierdzić ten przypadek? " +
                            "(Wpisz 'Y' aby zatwierdzić, cokolwiek innego " +
                            "aby odrzucić): ");
                    if (ask.equalsIgnoreCase("Y")){
                        System.out.println("Przypadek Zatwierdzony.");
                    }
                    else {
                        System.err.println("Przypadek odrzucony.");
                        e.printStackTrace();
                    }


                }
            }
        }
        return personList;
    }

    // Walidacja długości życia
    private void validateLifespan() throws NegativeLifespanException {
        if (death != null && death.isBefore(birth)) {
            throw new NegativeLifespanException("Błąd: Data śmierci jest wcześniejsza niż data urodzenia dla osoby: " + name);
        }
    }

    //Walidacja rodzica
    private void validateParent(List<Person> parents ) throws ParentingAgeException {
        for (Person parent : parents) {
            // Sprawdzenie, czy rodzic był wystarczająco stary w momencie narodzin dziecka
            if (parent.getBirth() != null && ChronoUnit.YEARS.between(parent.getBirth(), this.getBirth()) < 15) {
                throw new ParentingAgeException("Rodzic " + parent.getName() + " miał mniej niż 15 lat w chwili narodzin dziecka: " + this.getName());
            }
            // Sprawdzenie, czy rodzic żył w chwili narodzin dziecka
            if (parent.getDeath() != null && parent.getDeath().isBefore(this.getBirth())) {
                throw new ParentingAgeException("Rodzic " + parent.getName() + " zmarł przed narodzinami dziecka: " + this.getName());
            }
        }
    }

    private void validateAmbiguity(List<Person> people) throws AmbiguousPersonException {
        for (Person person : people) {
            if (person.getName().equals(getName())) {
                throw new AmbiguousPersonException("Znaleziono duplikat dla osoby: " + person.getName());
            }
        }
    }

    // Gettery
    public String getName() {
        return name;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public LocalDate getDeath() {
        return death;
    }

    public List<Person> getParents() {
        return parents;
    }

    //W klasie Person napisz statyczne metody toBinaryFile i
    // fromBinaryFile, które zapiszą i odczytają listę osób do i
    // z pliku binarnego.

    private static void toBinaryFile(List<Person> personList, String path) throws IOException {
        DataOutputStream out = null;
        try {
            out = new DataOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(path)
                    )
            );
            for (Person person : personList) {
                out.writeUTF(person.getName());
                out.writeUTF(person.getBirth() != null ? person.getBirth().toString() : "");
                out.writeUTF(person.getDeath() != null ? person.getDeath().toString() : "");
                out.writeUTF(person.parent1Name != null ? person.parent1Name : "");
                out.writeUTF(person.parent2Name != null ? person.parent2Name : "");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(out != null) out.close();
        }
    }

    private static List<Person> fromBinaryFile (String path) throws IOException{
        DataInputStream in = null;
        List<Person> personList = new ArrayList<>();
        try {
            in = new DataInputStream(
                    new BufferedInputStream(
                            new FileInputStream(path)
                    )
            );
            while (true) {
                try {
                    String name = in.readUTF();
                    String birth = in.readUTF();
                    String death = in.readUTF();
                    String parent1Name = in.readUTF();
                    String parent2Name = in.readUTF();

                    Person person = new Person(name, birth, death, parent1Name, parent2Name);
                    personList.add(person);
                } catch (EOFException e) {
                    break; // Koniec pliku
                }
            }
        } catch (IOException e){
                e.printStackTrace();
            } finally {
            if(in != null) in.close();
        }
        return personList;
    }
}
