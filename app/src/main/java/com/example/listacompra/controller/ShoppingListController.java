package com.example.listacompra.controller;

import android.content.Context;

import com.example.listacompra.database.DBHelper;
import com.example.listacompra.model.ShoppingItem;

import java.util.ArrayList;

public class ShoppingListController {
    private DBHelper dbHelper;

    public ShoppingListController(Context context) {
        dbHelper = new DBHelper(context);
    }

    public ArrayList<ShoppingItem> getItems() {
        return dbHelper.getAllItems();
    }

    public long addItem(String name, int quantity) {
        ShoppingItem newItem = new ShoppingItem();
        newItem.setName(name);
        newItem.setQuantity(quantity);
        return dbHelper.addItem(newItem);
    }

    public void deleteItem(int itemId) {
        dbHelper.deleteItem(itemId);
    }
}