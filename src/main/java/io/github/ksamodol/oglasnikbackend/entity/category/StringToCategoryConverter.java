package io.github.ksamodol.oglasnikbackend.entity.category;


import org.springframework.core.convert.converter.Converter;

public class StringToCategoryConverter implements Converter<String, Category> {
    @Override
    public Category convert(String s) {
        return Category.valueOf(s.toUpperCase());
    }
}
