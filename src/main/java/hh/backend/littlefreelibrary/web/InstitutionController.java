package hh.backend.littlefreelibrary.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    }

    // add an institution form
    @GetMapping("/institution/new")
    public String showCreateInstitutionForm(
        @RequestParam(defaultValue = "/institution/list") String returnTo,
        Model model) {
        model.addAttribute("institution", new Institution());
        model.addAttribute("returnTo", returnTo);
        return "institutionform";
    }

    // Save new institution
    @PostMapping("/institution/save")
    public String saveInstitution(Institution institution, 
        @RequestParam(defaultValue = "/institution/list") String returnTo) {
        institutionRepository.save(institution);
        return "redirect:" + returnTo;
    }

    // Delete institution
    @GetMapping("/institution/{id}/delete")
    public String deleteInstitution(@PathVariable("id") Integer id) {
        institutionRepository.deleteById(id);
        return "redirect:/institution/list";
    }

    // Edit institution form
    @GetMapping("/institution/{id}/edit")
    public String showEditInstitutionForm(@PathVariable("id") Integer id, 
        @RequestParam(defaultValue = "/institution/list") String returnTo,
        Model model) {
        Institution institution = institutionRepository.findById(id).orElse(null);
        // If institution not found, redirect to list
        if (institution == null) {
            return "redirect:/institution/list";
        }
        model.addAttribute("institution", institution);
        model.addAttribute("returnTo", returnTo);
        return "institutionform";
    }

}
