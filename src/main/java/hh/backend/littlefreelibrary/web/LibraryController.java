package hh.backend.littlefreelibrary.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import hh.backend.littlefreelibrary.domain.LibraryRepository;
import hh.backend.littlefreelibrary.domain.InstitutionRepository;
import hh.backend.littlefreelibrary.domain.Library;
import hh.backend.littlefreelibrary.domain.LibraryTypeRepository;


@Controller
public class LibraryController {

    // Database repositories 
    private final LibraryRepository libraryRepository;
    private final InstitutionRepository institutionRepository;
    private final LibraryTypeRepository libraryTypeRepository;

    public LibraryController(LibraryRepository libraryRepository, InstitutionRepository institutionRepository, LibraryTypeRepository libraryTypeRepository) {
        this.libraryRepository = libraryRepository;
        this.institutionRepository = institutionRepository;
        this.libraryTypeRepository = libraryTypeRepository;
    }

    // CRUD operations
    // display library list
    @GetMapping("/library/list")
    public String getLibraries(Model model) {
        model.addAttribute("libraries", libraryRepository.findAll());
        model.addAttribute("institutions", institutionRepository.findAll());
        model.addAttribute("libraryTypes", libraryTypeRepository.findAll());
        return "librarylist";
    }
    
    // add a library form
    @GetMapping("/library/new")
    public String showCreateLibraryForm(Model model) {
        model.addAttribute("library", new Library());
        model.addAttribute("institutions", institutionRepository.findAll());
        model.addAttribute("libraryTypes", libraryTypeRepository.findAll());
        return "libraryform";
    }

    // Save new library
    @PostMapping("/library/save")
    public String saveLibrary(Library library) {
        libraryRepository.save(library);
        return "redirect:/library/list";
    }

    // Delete library
    @GetMapping("/library/{id}/delete")
    public String deleteLibrary(@PathVariable("id") Integer id) {
        libraryRepository.deleteById(id);
        return "redirect:/library/list";
    }

    // Edit library
    @GetMapping("/library/{id}/edit")
    public String showEditLibraryForm(@PathVariable("id") Integer id, Model model) {
        Library library = libraryRepository.findById(id).orElse(null);
        // If library not found, redirect to list
        if (library == null) {
            return "redirect:/library/list";
        }
        model.addAttribute("library", library);
        model.addAttribute("institutions", institutionRepository.findAll());
        model.addAttribute("libraryTypes", libraryTypeRepository.findAll());
        return "libraryform";
    }

}
