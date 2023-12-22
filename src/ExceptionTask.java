import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class ExceptionTask {
    public static final String message = "Возникло исключение ";

    public static void main(String[] args) {

        nullPointerExample();
        arrayIndexOutOfBoundsExample();

        wrapCustomExceptionExample();
        randomExceptionExample();

        myExceptionClassExample();

    }

    private static void nullPointerExample() {
        System.out.println("1. Объявите ссылочную переменную со значением null.\n" +
                "Вызовите метод у этой переменной.\n" +
                "Отловить возникшее исключение c помощью блока try-catch.");
        Integer link = null;
        try {
            link.intValue();
        } catch (Exception e) {
            System.out.println(message + e);
        }
    }

    private static void arrayIndexOutOfBoundsExample() {
        System.out.println("2. Написать код, который создаст, а затем отловит ArrayIndexOutOfBoundsException.");
        int[] array = new int[]{1, 2};
        try {
            int element = array[2];
        } catch (Exception e) {
            System.out.println(message + e);
        }
    }

    private static void myExceptionClassExample() {
        System.out.println("3. Создать собственный класс-исключение - наследник класса Exception.\n" +
                "Создать метод, выбрасывающий это исключение. Вызвать этот метод и отловить исключение.\n" +
                "Вывести stack trace в консоль.");
        try {
            throw new MyException();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void wrapCustomExceptionExample() {
        System.out.println("4. Бросить одно из существующих в JDK исключений,\n " +
                "отловить его и оберните его в свое кастомное исключение указав в качестве причины отловленное.");
        try {
            int s = 100 / 0;
        } catch (ArithmeticException e) {
            System.out.println(new MyException(e).getCause());
        }
    }

    private static void randomExceptionExample() {
        System.out.println("5. Создать метод случайным образом выбрасывающий одно из 3-х видов исключений.\n" +
                "Вызвать этот метод в блоке try-catch, отлавливающим любое из 3-х.");
        Map<Integer, Exception> exceptions = new HashMap<>();
        exceptions.put(1, new FileNotFoundException());
        exceptions.put(2, new ArithmeticException());
        exceptions.put(3, new ArrayIndexOutOfBoundsException());

        try {
            throw exceptions.get((int) (Math.random() * 2) + 1);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
