import java.util.*;

public class SetExample {
    public static void main(String[] args) {

        List<Integer> numbers =
                Arrays.asList(10, 20, 10, 30, 20, 40);

        Set<Integer> uniqueNumbers =
                new HashSet<>(numbers);

        System.out.println(uniqueNumbers);
    }
}
