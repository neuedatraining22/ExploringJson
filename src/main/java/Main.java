import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws JsonProcessingException {

        List<Book> books = new ArrayList<>();
        books.add(new Book(1,"Learning Java", "Matt"));
        books.add(new Book(2,"More Java", "Matt Again"));
        books.add(new Book(3,"Advanced Java", "A Different Matt"));

        ObjectMapper om = new ObjectMapper();
        System.out.println(om.writeValueAsString(books));

    }
}
