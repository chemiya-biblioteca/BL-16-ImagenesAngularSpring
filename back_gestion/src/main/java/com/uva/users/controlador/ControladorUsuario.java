package com.uva.users.controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.uva.users.excepcion.UsuarioExcepcion;
import com.uva.users.modelo.ImageModel;
import com.uva.users.modelo.Product;
import com.uva.users.repository.ProductRepository;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "*")
public class ControladorUsuario {
    private final ProductRepository repository;

    ControladorUsuario(ProductRepository repository) {
        this.repository = repository;
    }

    @PostMapping(value = {"/addNewProduct"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Product addNewProduct(@RequestPart("product") Product product ,@RequestPart("imageFile") MultipartFile[] file) {
        try {
            Set<ImageModel> images = uploadImage(file); 
            product.setProductImages(images);
            return repository.save(product); 
            } catch (Exception e) {
           
            System.out.println(e.getMessage()); 
            return null;
            }
    }

    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<ImageModel> imageModels = new HashSet<>();
        for (MultipartFile file: multipartFiles) {
            ImageModel imageModel = new ImageModel(
            file.getOriginalFilename(),
            file.getContentType(),
            file.getBytes()
        );
        imageModels.add(imageModel);
        }
        return imageModels;
    }


    @GetMapping("/getAllProducts")
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<Product>();

        repository.findAll().forEach(productVisto -> products.add(productVisto));

        return products;

    }
   

}


/*
 * 
 * @RestController
public class ProductController {
@Autowired
private Product Service productService;
 public Product addNewProduct() {

}

}
 * 
 * 
 * 
 */
