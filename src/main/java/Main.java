import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws JsonProcessingException {

        List<Book> books = new ArrayList<>();
        books.add(new Book(1,"Learning Java", "Matt"));
        books.add(new Book(2,"More Java", "Matt Again"));
        books.add(new Book(3,"Advanced Java", "A Different Matt"));

        books.stream().forEach ( book -> book.setPrice(new BigDecimal("100"))   );

        ObjectMapper om = new ObjectMapper();
        System.out.println(om.writeValueAsString(books));

    }
}
