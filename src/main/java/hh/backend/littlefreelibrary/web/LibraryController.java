package hh.backend.littlefreelibrary.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import hh.backend.littlefreelibrary.domain.LibraryRepository;
import hh.backend.littlefreelibrary.domain.InstitutionRepository;
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
    @GetMapping("/list")
    public String getLibraries(Model model) {
        model.addAttribute("libraries", libraryRepository.findAll());
        model.addAttribute("institutions", institutionRepository.findAll());
        model.addAttribute("libraryTypes", libraryTypeRepository.findAll());
        return "librarylist";
    }
    
    // add a library form

    // Save new library

    // Delete library

    // Edit library

}
