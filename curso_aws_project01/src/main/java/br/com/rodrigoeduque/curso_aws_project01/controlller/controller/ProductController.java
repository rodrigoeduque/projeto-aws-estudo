package br.com.rodrigoeduque.curso_aws_project01.controlller.controller;

import br.com.rodrigoeduque.curso_aws_project01.model.Product;
import br.com.rodrigoeduque.curso_aws_project01.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<?> findAllProducts() {

        List<Product> products = productRepository.findAll();

        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {

        Product product = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "Identificador Inválido"));

        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        Product newProduct = productRepository.save(product);

        URI uri = UriComponentsBuilder.fromPath("api/products/{id}").buildAndExpand(newProduct.getId()).toUri();

        return ResponseEntity.created(uri).body(newProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody Product product, @PathVariable Long id) {
        if (productRepository.existsById(id)) {
            product.setId(id);

            Product updateProduct = productRepository.save(product);
            return ResponseEntity.ok(updateProduct);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404)));
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/byCode")
    public ResponseEntity<?> findByCode(@RequestParam String code) {
        Optional<Product> byCode = productRepository.findByCode(code);

        if (byCode.isPresent()) {
            return ResponseEntity.ok(byCode.get());
        }
        return ResponseEntity.notFound().build();
    }

}
