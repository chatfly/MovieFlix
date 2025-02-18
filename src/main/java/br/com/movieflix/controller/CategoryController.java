package br.com.movieflix.controller;

import br.com.movieflix.controller.request.CategoryRequest;
import br.com.movieflix.controller.response.CategoryReponse;
import br.com.movieflix.entity.Category;
import br.com.movieflix.mapper.CategoryMapper;
import br.com.movieflix.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// TODO: Anotar que @Autowired é uma forma de fazer injeção de dependência
// TODO: Anotar que @RequiredArgsConstructor faz todas as injeções de dependência que precisamos
// TODO: Anotar sobre o arquivo de configuração "application.yaml"
// TODO: Anotar mais profundamente sobre streams, maps, tolist, orElse e .build()
// TODO: Anotar mais profundamente sobre as diferenças entre os packages dessa aplicação e suas funções
// TODO: Anotar sobre os records de request, response e sobre o mapper
// TODO: Anotar sobre os packages request e response dentro do package controller
// TODO: Anotar sobre o nullable e sobre o lenght em annotations de column

@RestController
@RequestMapping("/movieflix/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryReponse>> getAll() {
        List<CategoryReponse> categories = categoryService.findAll()
                .stream()
                .map(CategoryMapper::toCategoryReponse)
                .toList();

        return ResponseEntity.ok(categories);
    }

    @PostMapping
    public ResponseEntity<CategoryReponse> save(@RequestBody CategoryRequest request) {
        Category newCategory = CategoryMapper.toCategory(request);
        Category savedCategory = categoryService.saveCategory(newCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryMapper.toCategoryReponse(savedCategory));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryReponse> getById(@PathVariable Long id) {
        return categoryService.findById(id)
                .map(category -> ResponseEntity.ok(CategoryMapper.toCategoryReponse(category)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (categoryService.findById(id).isPresent()) {
            categoryService.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.notFound().build();
    }

}
