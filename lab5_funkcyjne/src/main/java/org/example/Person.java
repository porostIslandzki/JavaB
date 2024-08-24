package org.example;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
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
    //W klasie Person napisz bezargumentową metodę, która zwróci
    // napis sformatowany według składni PlantUML.
    // Napis, korzystając z diagramu obiektów,
    // powinien przedstawiać obiekt osoby na rzecz
    // której została wywołana metoda oraz jej rodziców
    // (o ile są zdefiniowani). Obiekty powinny zawierać nazwę
    // osoby. Od dziecka do rodziców należy poprowadzić strzałki.

    public String toUML() {
        StringBuilder sb = new StringBuilder();

        // Definiowanie obiektu osoby
        sb.append("object ").append(name).append(" as \"").append(name).append("\"\n");

        // Definiowanie obiektów rodziców, jeśli są zdefiniowani
        for (Person parent : parents) {
            sb.append("object ").append(parent.getName()).append(" as \"").append(parent.getName()).append("\"\n");
            sb.append(name).append(" --> ").append(parent.getName()).append(" : parent\n");
        }

        return sb.toString();
    }

    //W klasie Person napisz statyczną metodę, która przyjmie
    // listę osób. Lista powinna zwrócić podobny jak w poprzedni
    // zadaniu napis. Tym razem powinien on zawierać wszystkie
    // osoby w liście i ich powiązania.

    public static String listToUML(List<Person> people){
        StringBuilder sb = new StringBuilder();
        Set<String> defined = new HashSet<>();

        for(Person p : people){
            //Dodajemy obiekty osoby, jeśli nie był jeszcze dodany
            if(!defined.contains(p.getName())){
                String addP = p.toUML();
                sb.append(addP);
                defined.add(p.getName());
            }
            //Dodajemy obiekty rodziców, jeśli jeszcze nie byli dodani
            for(Person parent : p.getParents()){
                if(!defined.contains(parent.getName())){
                    sb.append(parent.toUML());
                    defined.add(parent.getName());
                }
            }
        }
        return sb.toString();
    }
    //W klasie Person napisz statyczną metodę,
    // która przyjmie listę osób oraz napis substring.
    // Metoda powinna zwrócić listę osób z listy wejściowej,
    // ograniczoną do osób, których nazwa zawiera substring.

    public static List<Person> substringCatcher(List<Person> people, String substr){
        List<Person> peopleWithSubstr = new ArrayList<>();
        people.stream()
                .filter(p -> p.getName().contains(substr))
                .forEach(p -> peopleWithSubstr.add(p));
        return peopleWithSubstr;
        //metoda jest ok, ale można użyć od razu
        // return people.stream()
        //        .filter(p -> p.getName().contains(substr))
        //        .collect(Collectors.toList());
    }

    //W klasie Person napisz statyczną metodę,
    // która przyjmie listę osób. Metoda powinna zwrócić listę
    // osób z listy wejściowej, posortowanych według roku urodzenia.

    public static List<Person> birthdaySort(List<Person> people){
        List<Person> sort = new ArrayList<>(people);
        Collections.sort(sort, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getBirth().compareTo(o2.getBirth());
            }
        }
        );
        return sort;
    } //metoda jest poprawna ale chat gpt jak zwykle drze jape
    //że można to zrobić łatwiej:
    // List<Person> sortedList = new ArrayList<>(people);
    //sortedList.sort(Comparator.comparing(Person::getBirth));
    //return sortedList;

    //W klasie Person napisz statyczną metodę,
    // która przyjmie listę osób. Metoda powinna zwrócić listę
    // zmarłych osób z listy wejściowej, posortowanych malejąco
    // według długości życia.

    public static List<Person> deadPeople(List<Person> people){
        List<Person> deadOnes = new ArrayList<>();
        for(Person p : people){
            if(p.getDeath() != null){
                deadOnes.add(p);
            }
        }
        deadOnes.sort((o1, o2) -> {
            long life1 = ChronoUnit.YEARS.between(o1.getBirth(), o1.getDeath());
            long life2 = ChronoUnit.YEARS.between(o2.getBirth(), o2.getDeath());
            return Long.compare(life2, life1); //sortowanie malejące
        });
        return deadOnes;
    }

    //tu piszę komparator do tego gówna na dole
    public class compBirth implements Comparator<Person>{
        public int compare(Person a, Person b){
           return a.getBirth().compareTo(b.getBirth());
        }
    }

    //W klasie Person napisz statyczną metodę, która przyjmie
    // listę osób.
    //Metoda powinna zwrócić najstarszą żyjącą osobę.

    public static Person oldestOne(List<Person> people){
        return people.stream()
                .filter(p -> p.getDeath() == null) //Filtrujemy tylko żyjące osoby
                .min(Comparator.comparing(Person::getBirth)) //Szukamy najwcześniejszej daty urodzenia
                .orElse(null); //zwracamy null, jeśli brak żyjących osób
    }
}
