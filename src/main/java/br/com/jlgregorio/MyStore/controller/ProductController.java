package br.com.jlgregorio.MyStore.controller;

import br.com.jlgregorio.MyStore.model.CategoryModel;
import br.com.jlgregorio.MyStore.model.ProductModel;
import br.com.jlgregorio.MyStore.repository.CategoryRepository;
import br.com.jlgregorio.MyStore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

//    @GetMapping
//    public String index(){
//        return "product/index";
//    }

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public String index(Model model){
        List<ProductModel> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "product/index";
    }

    @GetMapping("/create")
    public ModelAndView create(){
        ModelAndView mv = new ModelAndView("product/create");
        ProductModel productModel = new ProductModel();
        productModel.setCategory(new CategoryModel());
        List<CategoryModel> categories = categoryRepository.findAll();
        mv.addObject("product", productModel);
        mv.addObject("categories", categories);
        return mv;
    }

    @PostMapping
    public String save(@ModelAttribute("product") ProductModel productModel,
                       RedirectAttributes redirectAttributes){
        productRepository.save(productModel);
        redirectAttributes.addFlashAttribute("message",
                "Produto salvo com sucesso!");
        return "redirect:/products";
    }


}
