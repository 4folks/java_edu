package org.example.repository.impl;

import org.example.model.Purchase;
import org.example.repository.GeneralCrud;

public class PurchaseRepository extends GeneralCrud<Purchase> {
    public PurchaseRepository() {
        super(Purchase.class);
    }
}
