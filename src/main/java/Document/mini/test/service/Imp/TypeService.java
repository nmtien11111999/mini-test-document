package Document.mini.test.service.Imp;

import Document.mini.test.model.Type;
import Document.mini.test.repository.ITypeRepository;
import Document.mini.test.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Service
public class TypeService implements ITypeService {

    private final ITypeRepository iTypeRepository;

    @Autowired
    public TypeService(ITypeRepository iTypeRepository) {
        this.iTypeRepository = iTypeRepository;
    }

    @Override
    public Iterable<Type> findAll() {
        return iTypeRepository.findAll();
    }

    @Override
    public void save(Type type) {
        iTypeRepository.save(type);
    }

    @Override
    public Optional<Type> findById(Long id) {
        return iTypeRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        iTypeRepository.deleteById(id);
    }

    @Override
    public String checkEditTypeForm(Long id, ModelMap modelMap) {
        Optional<Type> type = findById(id);
        if (type.isPresent()){
            modelMap.addAttribute("type" , type.get());
            return "typeEdit";
        }
        return "error";
    }

    @Override
    public String checkCreateTypeValidate(Type type, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasFieldErrors()){
            return "typeCreate";
        } else {
            save(type);
            redirectAttributes.addFlashAttribute("message", "Create new Type successfully");
            return "redirect:/type/list";
        }
    }

    @Override
    public String checkEditTypeValidate(Type type, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasFieldErrors()){
            return "typeEdit";
        } else {
            save(type);
            redirectAttributes.addFlashAttribute("message", "Update Type successfully");
            return "redirect:/type/list";
        }
    }
}