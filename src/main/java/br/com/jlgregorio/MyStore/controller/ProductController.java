package br.com.jlgregorio.MyStore.controller;

import br.com.jlgregorio.MyStore.exceptions.ResourceNotFoundException;
import br.com.jlgregorio.MyStore.model.CategoryModel;
import br.com.jlgregorio.MyStore.model.ProductModel;
import br.com.jlgregorio.MyStore.repository.CategoryRepository;
import br.com.jlgregorio.MyStore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/{id}/edit")
    public ModelAndView edit( @PathVariable long id ){
        var found = productRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Produto não encontrado!")
        );
        ModelAndView mv = new ModelAndView("product/edit");
        mv.addObject("product", found);
        mv.addObject("categories", categoryRepository.findAll());
        return mv;
    }

    @PutMapping("/{id}")
    public String update(@PathVariable long id,
            @ModelAttribute("product") ProductModel productModel,
                         RedirectAttributes redirectAttributes){
        var found = productRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Produto não encontrado!")
        );
        found.setName(productModel.getName());
        found.setCategory(productModel.getCategory());
        productRepository.save(found);
        redirectAttributes.addFlashAttribute(
                "message", "Produto atualizado com sucesso!");
        return "redirect:/products";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id,
                         RedirectAttributes redirectAttributes){
        var found = productRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Produto não encontrado!")
        );
        productRepository.delete(found);
        redirectAttributes.addFlashAttribute("message",
                "Produto excluído com sucesso!");
        return "redirect:/products";
    }

    @GetMapping("/{id}/show")
    public ResponseEntity<ProductModel> show(@PathVariable long id){
        var found = productRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Produto não encontrado!")
        );
        return new ResponseEntity<>(found, HttpStatus.OK);
    }

}
