import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        //Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
        //System.out.println(persons);

        Stream<Person> young = persons.stream();
        int cnt = Math.toIntExact(young.filter(value -> value.getAge() < 18)
                .count());
        System.out.println(cnt);

        List<String> conscript = persons.stream()
                .filter(value -> value.getSex() == Sex.MAN)
                .filter(value -> value.getAge() >= 18 && value.getAge() <= 30)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(conscript);

        List<Person> worked = persons.stream()
                .filter(value -> value.getAge() >= 18 && value.getAge() <= 60)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());
        System.out.println(worked);

    }
}
