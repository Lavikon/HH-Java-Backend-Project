package hh.backend.littlefreelibrary.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import hh.backend.littlefreelibrary.domain.Institution;
import hh.backend.littlefreelibrary.domain.InstitutionRepository;

@Controller
public class InstitutionController {

    // Database repository
    private final InstitutionRepository institutionRepository;

    public InstitutionController(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    // CRUD operations
    // display institution list
    @GetMapping("/institution/list")
    public String listInstitutions(Model model) {
        model.addAttribute("institutions", institutionRepository.findAll());
        return "institutionlist";
        // TODO: create institutionlist.html and add functionality to it
    }

    // add an institution form
    @GetMapping("/institution/new")
    public String showCreateInstitutionForm(Model model) {
        model.addAttribute("institution", new Institution());
        return "institutionform";
        // TODO: create institutionform.html and add functionality to it, also add edit functionality to it
    }

    // Save new institution
    @GetMapping("/institution/save")
    public String saveInstitution(Institution institution) {
        institutionRepository.save(institution);
        return "redirect:/institution/list";
    }

    // Delete institution
    @GetMapping("/institution/{id}/delete")
    public String deleteInstitution(@PathVariable("id") Integer id) {
        institutionRepository.deleteById(id);
        return "redirect:/institution/list";
    }

    // Edit institution form
    @GetMapping("/institution/{id}/edit")
    public String showEditInstitutionForm(@PathVariable("id") Integer id, Model model) {
        Institution institution = institutionRepository.findById(id).orElse(null);
        // If institution not found, redirect to list
        if (institution == null) {
            return "redirect:/institution/list";
        }
        model.addAttribute("institution", institution);
        return "institutionform";
    }

}
