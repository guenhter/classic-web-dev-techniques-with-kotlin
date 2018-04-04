package at.fhooe;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookControllerThymeleaf {
    private DataSource dataSource = new DummyDataSource();

    @GetMapping("/template")
    public String readBook(Model model) {
        Book book = dataSource.readBook("Kotlin in Action");

        model.addAttribute("name", book.getName());
        model.addAttribute("chapters", book.getChapters());
        model.addAttribute("imageUrl", book.getImageUrl());
        return "book";
    }
}
