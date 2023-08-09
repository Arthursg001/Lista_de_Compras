package com.example.listacompra.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.listacompra.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listacompra.R;
import com.example.listacompra.controller.ShoppingListController;
import com.example.listacompra.model.ShoppingItem;
import com.example.listacompra.model.ShoppingListAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editTextItem, editTextQuantity;
    private Button btnAddItem;
    private ListView listViewItems;
    private ArrayList<ShoppingItem> itemsList;
    private ShoppingListAdapter shoppingListAdapter;
    private ShoppingListController shoppingListController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextItem = findViewById(R.id.editTextItem);
        editTextQuantity = findViewById(R.id.editTextQuantity);
        btnAddItem = findViewById(R.id.btnAddItem);
        listViewItems = findViewById(R.id.listViewItems);

        itemsList = new ArrayList<>();
        shoppingListAdapter = new ShoppingListAdapter(this, itemsList);
        listViewItems.setAdapter(shoppingListAdapter);

        shoppingListController = new ShoppingListController(this);
        loadItemsFromDatabase();

        shoppingListAdapter.setOnDeleteButtonClickListener(new ShoppingListAdapter.OnDeleteButtonClickListener() {
            @Override
            public void onDeleteButtonClick(int position) {
                deleteItemFromDatabase(position);
            }
        });

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItemToDatabase();
            }
        });
    }

    private void loadItemsFromDatabase() {
        itemsList.clear();
        itemsList.addAll(shoppingListController.getItems());
        shoppingListAdapter.notifyDataSetChanged();
    }

    private void addItemToDatabase() {
        String itemName = editTextItem.getText().toString().trim();
        String quantityString = editTextQuantity.getText().toString().trim();

        if (!itemName.isEmpty()) {
            int itemQuantity = 0;
            if (!quantityString.isEmpty()) {
                itemQuantity = Integer.parseInt(quantityString);
            }

            long itemId = shoppingListController.addItem(itemName, itemQuantity);

            if (itemId != -1) {
                loadItemsFromDatabase();
                editTextItem.getText().clear();
                editTextQuantity.getText().clear();
            } else {
                Toast.makeText(this, "Falha ao adicionar item", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Insira o nome do item", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteItemFromDatabase(int position) {
        ShoppingItem item = itemsList.get(position);
        shoppingListController.deleteItem(item.getId());
        loadItemsFromDatabase();
    }
}
