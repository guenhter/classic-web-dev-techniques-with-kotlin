package at.fhooe;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookControllerPlainString {
    private DataSource dataSource = new DummyDataSource();

    @GetMapping("/plain")
    @ResponseBody
    public String readBook() {
    Book book = dataSource.readBook("Kotlin in Action");

    return "<!DOCTYPE html>" +
            "<html lang=\"en\">" +
            "<head>" +
            "    <meta charset=\"UTF-8\">" +
            "    <title>" + book.getName() +  "</title>" +
            "    <link rel=\"stylesheet\" href=\"css/style.css\">" +
            "</head>" +
            "<body>" +
            "    <header class=\"header\">" +
            "        <h1>" + book.getName() +  "</h1>" +
            "    </header>" +
            "    <article class=\"main\">" +
            "        <h2>Chapters</h2>" +
            "        <div id=\"chapters\">" +
            "            <ol>" +
            book.getChapters().stream().map(ch -> "<li>" + ch + "</li>").reduce("", (c1, c2) -> c1 + c2) +
            "            </ol>" +
            "        </div>" +
            "    </article>" +
            "    <aside class=\"aside\">" +
            "        <img class=\"cover\" src=\"" + book.getImageUrl() +  "\" alt=\"Kotlin in Action - Cover\">" +
            "    </aside>" +
            "    <footer>" +
            "        <p>Copyright 2018</p>" +
            "    </footer>" +
            "</body>" +
            "</html>";
    }
}
