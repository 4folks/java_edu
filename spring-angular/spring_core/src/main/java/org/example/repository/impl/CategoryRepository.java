package org.example.repository.impl;

import org.example.model.products.Category;
import org.example.repository.GeneralCrud;

public class CategoryRepository extends GeneralCrud<Category> {
    public CategoryRepository() {
        super(Category.class);
    }
}
