package FindFiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите путь папки для поиска файла, например: (C:\\Program Files)");
        System.out.println("=================================================================");
        System.out.print("Ввод: ");
        Path path = Paths.get(in.nextLine());
        //При ввода требуемого файла обязательно укажите расширения файла, например ".txt"
        System.out.println("\nВведите имя требуемого файла, например: (text.txt)");
        System.out.println("==================================================");
        System.out.print("Ввод: ");
        List<Path> result = findByFileName(path, in.nextLine());
        System.out.println("\nФайл находится в папке:");
        System.out.println("=======================");
        result.forEach(System.out::println);
    }

    public static List <Path> findByFileName(Path path, String fileName) throws IOException {
        List <Path> result;
        try (Stream<Path> pathStream = Files.find(path, Integer.MAX_VALUE, (p, basicFileAttributes)
                -> p.getFileName().toString().equalsIgnoreCase(fileName))) {
            result = pathStream.collect(Collectors.toList());
        } catch (Exception e) {
            throw new IOException("Папка отсутсвует " + e);
        }
        return result;
    }
}