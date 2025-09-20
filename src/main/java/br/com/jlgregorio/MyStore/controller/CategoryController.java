package br.com.jlgregorio.MyStore.controller;

import br.com.jlgregorio.MyStore.model.CategoryModel;
import br.com.jlgregorio.MyStore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

//    @GetMapping
//    public String index(){
//        return "category/index";
//    }

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public ModelAndView index(){
        List<CategoryModel> categories = categoryRepository.findAll();
        ModelAndView mv = new ModelAndView("category/index");
        mv.addObject("categories", categories);
        return mv;
    }


}
