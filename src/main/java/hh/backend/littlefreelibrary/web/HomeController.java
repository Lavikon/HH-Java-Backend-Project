package hh.backend.littlefreelibrary.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import hh.backend.littlefreelibrary.domain.LibraryRepository;
import hh.backend.littlefreelibrary.domain.PostRepository;

@Controller
public class HomeController {

    private final LibraryRepository libraryRepository;
    private final PostRepository postRepository;

    public HomeController(LibraryRepository libraryRepository, PostRepository postRepository) {
        this.libraryRepository = libraryRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/")
    //public String home() {
    public String home(Model model) {
        // librarylist
        model.addAttribute("libraries", libraryRepository.findTop10byOrderByIdAsc());
        // Fetch the 5 most recent posts and add to model
        model.addAttribute("recentPosts", postRepository.findTop5ByOrderByCreatedAtDesc());
        return "index";
    }
}
