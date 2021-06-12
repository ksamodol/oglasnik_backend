package io.github.ksamodol.oglasnikbackend.controllers;

import io.github.ksamodol.oglasnikbackend.entity.category.Category;
import io.github.ksamodol.oglasnikbackend.entity.listing.Condition;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class EnumController {
    @GetMapping("/category")
    public List<String> findAllCategories(){
        return Arrays.stream(Category.values()).map(x -> StringUtils.capitalize(x.toString().toLowerCase())).collect(Collectors.toList());
    }
    @GetMapping("/condition")
    public List<String> findAllConditions(){
        return Arrays.stream(Condition.values()).map(x -> StringUtils.capitalize(x.toString().toLowerCase())).collect(Collectors.toList());
    }

}
