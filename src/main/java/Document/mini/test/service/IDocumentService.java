package Document.mini.test.service;

import Document.mini.test.model.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

public interface IDocumentService extends IGenerateService<Document>{

    Page<Document> findAll(Pageable pageable);

    Page<Document> findAllByNameContaining(Pageable pageable, String name);

    Page<Document> checkSearch(Pageable pageable, Optional<String> search);

    String checkEditDocumentForm(Long id , ModelMap modelMap);

    String checkCreateDocumentValidate(Document document , BindingResult bindingResult , RedirectAttributes redirectAttributes);

    String checkEditDocumentValidate(Document document , BindingResult bindingResult , RedirectAttributes redirectAttributes);

    String checkViewDocument(Long id , ModelMap modelMap);
}
