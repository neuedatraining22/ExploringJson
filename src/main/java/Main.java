import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        List<Book> books = new ArrayList<>();
        books.add(new Book(1,"Learning Java", "Matt"));
        books.add(new Book(2,"More Java", "Matt Again"));
        books.add(new Book(3,"Advanced Java", "A Different Matt"));

        books.stream().forEach ( book -> book.setPrice(new BigDecimal("100"))   );

        ObjectMapper om = new ObjectMapper();
        System.out.println(om.writeValueAsString(books));

        String nextLineCharacter = System.lineSeparator();
        String homeFolder = System.getProperty("user.home");
        //String separator = File.separator;

//        Path path = Paths.get(homeFolder+"/books.txt");
//        List<String> data = books.stream().map(book -> book.toString()).collect(Collectors.toList());
//        Files.write(path, data, StandardOpenOption.APPEND);

        //WRITING A CSV FILE
        Path path = Paths.get(homeFolder+"/books.csv");
        String header = "id, title, author" + nextLineCharacter;
        Files.write(path, header.getBytes(), StandardOpenOption.CREATE);
        List<String> data = books.stream().map(book -> {
            return book.getId() + ", \"" + book.getTitle() + "\", \"" + book.getAuthor() + "\"";
        }).collect(Collectors.toList());
        Files.write(path, data, StandardOpenOption.APPEND);


        List<Book> loadedBooks = new ArrayList<>();

        //READ A CSV FILE
        Scanner scanner = new Scanner(path);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!line.startsWith("id")) {
                //line = 1, "Learning Java", "Matt"
                String[] fields = line.split(",");
                //fields[0] = 1, fields[1]= "Learning Java"  fields[2]= "Matt"
                fields[1] = fields[1].trim().replaceAll("\"", "");
                fields[2] = fields[2].trim().replaceAll("\"", "");
                //fields[0] = 1, fields[1]= Learning Java  fields[2]= Matt
                Book book = new Book(Integer.parseInt(fields[0]), fields[1], fields[2]);
                loadedBooks.add(book);
            }
        }

        System.out.println(loadedBooks);



    }
}
