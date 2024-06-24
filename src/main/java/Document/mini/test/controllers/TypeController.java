package Document.mini.test.controllers;

import Document.mini.test.model.Type;
import Document.mini.test.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/type")
public class TypeController {

    private final ITypeService iTypeService;

    @Autowired
    public TypeController(ITypeService iTypeService) {
        this.iTypeService = iTypeService;
    }

    @GetMapping(value = "/list")
    public String listType(ModelMap modelMap) {
        Iterable<Type> types = iTypeService.findAll();
        modelMap.addAttribute("types", types);
        return "typeList";
    }

    @GetMapping("/create")
    public String createFormType(ModelMap modelMap) {
        modelMap.addAttribute("type", new Type());
        return "typeCreate";
    }

    @PostMapping("/create")
    public String createType(@Validated @ModelAttribute("type") Type type, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        return iTypeService.checkCreateTypeValidate(type, bindingResult, redirectAttributes);
    }

    @GetMapping("/edit/{id}")
    public String updateFormType(@PathVariable Long id, ModelMap modelMap) {
        return iTypeService.checkEditTypeForm(id, modelMap);
    }

    @PostMapping("/edit/{id}")
    public String updateType(@Validated @ModelAttribute("type") Type type, BindingResult bindingResult,
                             RedirectAttributes redirect) {
        return iTypeService.checkEditTypeValidate(type, bindingResult, redirect);
    }

    @PostMapping(value = "/delete/{id}")
    public String deleteType(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        iTypeService.remove(id);
        redirectAttributes.addFlashAttribute("message", "Delete Type successfully");
        return "redirect:/type/list";
    }
}