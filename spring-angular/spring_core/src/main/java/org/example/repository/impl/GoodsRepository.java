package org.example.repository.impl;

import org.example.model.products.Goods;
import org.example.repository.GeneralCrud;

public class GoodsRepository extends GeneralCrud<Goods> {
    public GoodsRepository() {
        super(Goods.class);
    }
}
