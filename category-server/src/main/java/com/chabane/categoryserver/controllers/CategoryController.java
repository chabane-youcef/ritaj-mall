package com.chabane.categoryserver.controllers;

import com.chabane.categoryserver.dao.CategoryDao;
import com.chabane.categoryserver.exceptions.CategoryNotFoundException;
import com.chabane.categoryserver.models.Category;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private Environment environment;

    @GetMapping(value = "/categories")
    public List<Category> allCategories() {
        List<Category> categories = categoryDao.findAll();
        for (Category category : categories) {
            category.setImage(
                    getImageFullUrl(category.getImage())
            );
        }
        return categories;
    }

    @GetMapping(value = "/categories/{id]")
    public Category getCategory(@PathVariable("id") int id) {
        Category category = categoryDao.findById(id);

        if (category == null) {
            throw new CategoryNotFoundException();
        }

        category.setImage(
                getImageFullUrl(category.getImage())
        );

        return category;
    }

    @GetMapping(value = "/categories-by-ids/{ids}")
    public List<Category> getCategoryById(@PathVariable("ids") String ids) {
        ArrayList<Integer> idsList = new ArrayList<>();
        String[] idsArray = ids.split(",");
        for (String id : idsArray) {
            idsList.add(Integer.parseInt(id));
        }

        List<Category> categories = categoryDao.findAllById(idsList);

        for (Category category : categories) {
            category.setImage(
                    getImageFullUrl(category.getImage())
            );
        }

        return categories;
    }

    @PostMapping(value = "/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCategory(@RequestBody Category category){
        categoryDao.save(category);
    }

    @PutMapping(value = "/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateCategory(@RequestBody Category category) {

        categoryDao.save(category);
    }

    @DeleteMapping(value = "/categories/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable("id") int id) {

        Category category = categoryDao.findById(id);

        if(category == null) {
            throw new CategoryNotFoundException();
        }

        categoryDao.delete(category);
    }

    private String getImageFullUrl(String imageUrl) {
        String port = environment.getProperty("local.server.port");
        String host = "http://localhost";

        try {
            host = InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {

        }

        return "http://" + host + ":" + port + "/images/" + imageUrl;
    }
}
