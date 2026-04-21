package hh.backend.littlefreelibrary.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import hh.backend.littlefreelibrary.domain.Library;
import hh.backend.littlefreelibrary.domain.LibraryRepository;
import hh.backend.littlefreelibrary.domain.PostRepository;

public class HomeController {

    @GetMapping("/")
    public String home() {
    // public String home(@RequestParam(required = false) String search, Model model) {
        // List<Library> libraries;
        // if (search != null && !search.isBlank()) {
        //     libraries = LibraryRepository
        //             .findByNameContainingIgnoreCaseOrLocationContainingIgnoreCaseOrInstitution_NameContainingIgnoreCase(
        //                     search, search, search);
        // } else {
        //     libraries = LibraryRepository.findAll();
        // }
        // model.addAttribute("libraries", libraries);
        // model.addAttribute("search", search);
        // model.addAttribute("recentPosts", PostRepository.findTop5ByOrderByCreatedAtDesc());
        return "index";
    }
}
