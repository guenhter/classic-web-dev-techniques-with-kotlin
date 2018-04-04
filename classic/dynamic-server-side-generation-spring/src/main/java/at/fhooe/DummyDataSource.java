package at.fhooe;

import java.util.Arrays;
import java.util.List;

public class DummyDataSource implements DataSource {
    @Override
    public Book readBook(String title) {
        String imageUrl = "img/kotlin-in-action-cover.jpg";

        List<String> chapters = Arrays.asList(
                "Ch 1. Kotlin: what and why",
                "Ch 2. Kotlin basics",
                "Ch 3. Defining and calling functions",
                "Ch 4. Classes, objects, and interfaces",
                "Ch 5. Programming with lambdas",
                "Ch 6. The Kotlin type system",
                "Ch 7. Operator overloading and other conventions",
                "Ch 8. Higher-order functions: lambdas as parameters and return values",
                "Ch 9. Generics",
                "Ch 10. Annotations and reflection",
                "Ch 11. DSL construction",
                "App A. Building Kotlin projects",
                "App B. Documenting Kotlin code",
                "App C. The Kotlin ecosystem");

        return new Book(title, chapters, imageUrl);
    }
}
