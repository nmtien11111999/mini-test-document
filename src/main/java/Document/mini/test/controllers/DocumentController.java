package Document.mini.test.controllers;

import Document.mini.test.model.Document;
import Document.mini.test.model.Type;
import Document.mini.test.service.IDocumentService;
import Document.mini.test.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping(value = "/document")
public class DocumentController {
    @Autowired
    private IDocumentService iDocumentService;

    @Autowired
    private ITypeService iTypeService;

    @ModelAttribute("types")
    public Iterable<Type> listType() {
        return iTypeService.findAll();
    }

    @GetMapping(value = "/list")
    public String listDocument(ModelMap modelMap, @PageableDefault(5) Pageable pageable, @RequestParam("search") Optional<String> search) {
        Page<Document> documentList = iDocumentService.checkSearch(pageable, search);
        modelMap.addAttribute("documents", documentList);
        modelMap.addAttribute("search", search.orElse(""));
        return "documentList";
    }

    @GetMapping(value = "/create")
    public String addDocument(ModelMap modelMap) {
        modelMap.addAttribute("document", new Document());
        return "documentCreate";
    }

    @PostMapping(value = "/create")
    public String add(@Validated @ModelAttribute("document") Document document, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        return iDocumentService.checkCreateDocumentValidate(document, bindingResult, redirectAttributes);
    }

    @GetMapping(value = "/edit/{id}")
    public String editForm(@PathVariable Long id, ModelMap modelMap) {
        return iDocumentService.checkEditDocumentForm(id, modelMap);
    }

    @PostMapping(value = "/edit/{id}")
    public String editDocument(@Validated @ModelAttribute("document") Document document, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        return iDocumentService.checkEditDocumentValidate(document, bindingResult, redirectAttributes);
    }

    @PostMapping(value = "/delete/{id}")
    public String deleteDocument(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        iDocumentService.remove(id);
        redirectAttributes.addFlashAttribute("message", "delete successful");
        return "redirect:/document/list";
    }

    @GetMapping(value = "/view/{id}")
    public String viewDocument(@PathVariable Long id, ModelMap modelMap) {
        return iDocumentService.checkViewDocument(id,modelMap);
    }
}