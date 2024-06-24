package Document.mini.test.service.Imp;

import Document.mini.test.model.Document;
import Document.mini.test.model.Type;
import Document.mini.test.repository.IDocumentRepository;
import Document.mini.test.service.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Service
public class DocumentService implements IDocumentService {
    @Autowired
    private IDocumentRepository iDocumentRepository;

    @Override
    public Iterable<Document> findAll() {
        return iDocumentRepository.findAll();
    }

    @Override
    public void save(Document document) {
        iDocumentRepository.save(document);
    }

    @Override
    public Optional<Document> findById(Long id) {
        return iDocumentRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        iDocumentRepository.deleteById(id);
    }


    @Override
    public Page<Document> findAll(Pageable pageable) {
        return iDocumentRepository.findAll(pageable);
    }

    @Override
    public Page<Document> findAllByNameContaining(Pageable pageable, String name) {
        return iDocumentRepository.findAllByNameContaining(pageable, name);
    }

    @Override
    public Page<Document> checkSearch(Pageable pageable, Optional<String> search) {
        Page<Document> documents;
        if (search.isPresent()) {
            documents = findAllByNameContaining(pageable, search.get());
        } else {
            documents = findAll(pageable);
        }
        return documents;
    }

    @Override
    public String checkEditDocumentForm(Long id, ModelMap modelMap) {
        Optional<Document> document = findById(id);
        if (document.isPresent()) {
            modelMap.addAttribute("document", document.get());
            return "documentEdit";
        }
        return "error";
    }

    @Override
    public String checkCreateDocumentValidate(@Validated @ModelAttribute("document") Document document, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasFieldErrors()) {
            return "documentCreate";
        } else {
            save(document);
            redirectAttributes.addFlashAttribute("message", "Create successful");
            return "redirect:/document/list";
        }
    }

    @Override
    public String checkEditDocumentValidate(@Validated Document document, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasFieldErrors()) {
            return "documentEdit";
        } else {
            save(document);
            redirectAttributes.addFlashAttribute("message", "Edit successful");
            return "redirect:/document/list";
        }
    }

    @Override
    public String checkViewDocument(Long id, ModelMap modelMap) {
        Optional<Document> document = findById(id);
        if (document.isPresent()) {
            modelMap.addAttribute("document", document.get());
            return "documentView";
        }
        return "error";
    }
}