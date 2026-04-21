package hh.backend.littlefreelibrary.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.backend.littlefreelibrary.domain.Library;
import hh.backend.littlefreelibrary.domain.LibraryRepository;


@CrossOrigin
@Controller
@RequestMapping("/rest") // adds "/rest" to all endpoints
public class LibraryRestController {

    // repo
    private final LibraryRepository libraryRepository;

    public LibraryRestController(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    // REST to get all libraries
    @GetMapping("library/all")
    public @ResponseBody List<Library> findAllLibrariesRest() {
        return (List<Library>) libraryRepository.findAll();
    }

    // REST to get library by id
    @GetMapping("/library/{id}")
    public @ResponseBody Library getOneLibraryRest(@PathVariable(name = "id") Integer libraryId) {
        return libraryRepository.findById(libraryId).orElse(null);
    }   
    
}
