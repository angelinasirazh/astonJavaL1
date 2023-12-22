import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static final String VOWELS = "аеёиоуыэюя";

    public static void main(String[] args) {
        final String directory = "resources";
        Path pathToFile = Path.of(directory, "текст2.txt");
        Path pathToJavaCodeFile = Path.of(directory, "Trader.java");
        Path pathToResultFile = Path.of(directory, "Trader1.java");
        Path pathToResultFile2 = Path.of(directory, "Trader2.java");

        printFirstVowels(pathToFile);
        split();
        printEqualsLastFirstSymbol(pathToFile);
        split();
        printMaxDigitCount(pathToFile);
        split();
        replacePublicToPrivate(pathToJavaCodeFile, pathToResultFile.toString());
        split();
        reverseEveryLine(pathToJavaCodeFile.toString(), pathToResultFile2.toString());

    }

    private static void printFirstVowels(Path path) {
        try (Scanner scanner = new Scanner(path)) {
            System.out.println("1. Задан файл с текстом, найти и вывести в консоль все слова, начинающиеся с гласной буквы.");
            while (scanner.hasNext()) {
                String word = scanner.next();
                char firstSymbol = word.charAt(0);
                if (VOWELS.indexOf(firstSymbol) != -1) {
                    System.out.println(word);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printEqualsLastFirstSymbol(Path path) {
        try (Scanner scanner = new Scanner(path)) {
            System.out.println("2. Задан файл с текстом, найти и вывести в консоль все слова, " +
                    " для которых последняя буква одного слова совпадает с первой буквой следующего.");
            String prev = null;
            if (scanner.hasNext()) {
                prev = scanner.next();
            }
            while (scanner.hasNext()) {
                String current = scanner.next();
                char lastSymbol = prev.charAt(prev.length() - 1);
                char firstSymbol = current.charAt(0);
                if (lastSymbol == firstSymbol) {
                    System.out.println(prev + " " + current);
                }
                prev = current;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printMaxDigitCount(Path filename) {
        System.out.println("3. Задан файл с текстом. В каждой строке найти и вывести наибольшее число цифр, идущих подряд.");
        try {
            Files.readAllLines(filename)
                    .stream()
                    .map(Main::findMaxDigitCount)
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int findMaxDigitCount(String s) {
        int result = 0;
        int counter = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                counter++;
            } else {
                if (result < counter) {
                    result = counter;
                }
                counter = 0;
            }
        }
        if (result < counter) {
            result = counter;
        }
        return result;
    }

    private static void replacePublicToPrivate(Path javaCodeFile, String resultFile) {
        System.out.println("4. Задан файл с java-кодом. Прочитать текст программы из файла и все слова public " +
                "в объявлении атрибутов и методов класса заменить на слово private." +
                " Результат сохранить в другой заранее созданный файл.");
        try {
            String getString = Files.readString(javaCodeFile);
            String resultString = getString.replace("public", "private");
            Files.writeString(Path.of(resultFile), resultString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Создан новый файл " + resultFile);
    }

    private static void reverseEveryLine(String javaCodeFile, String resultFile) {
        System.out.println("5. Задан файл с java-кодом. Прочитать текст программы из файла и записать в другой файл" +
                " в обратном порядке символы каждой строки.");
        try (BufferedReader reader = new BufferedReader(new FileReader(javaCodeFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(resultFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(new StringBuffer().append(line).reverse().append("\n").toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Создан новый файл " + resultFile);
    }

    private static void split() {
        System.out.println("-".repeat(150));
    }
}