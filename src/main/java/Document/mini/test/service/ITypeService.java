package Document.mini.test.service;

import Document.mini.test.model.Type;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface ITypeService extends IGenerateService<Type>{
    String checkEditTypeForm(Long id , ModelMap modelMap);

    String checkCreateTypeValidate(Type type , BindingResult bindingResult , RedirectAttributes redirectAttributes);

    String checkEditTypeValidate(Type type , BindingResult bindingResult , RedirectAttributes redirectAttributes);

}
