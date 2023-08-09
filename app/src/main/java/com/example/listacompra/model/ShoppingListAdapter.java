package com.example.listacompra.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.listacompra.R;

import java.util.ArrayList;

public class ShoppingListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ShoppingItem> itemsList;
    private OnDeleteButtonClickListener deleteButtonClickListener;

    public interface OnDeleteButtonClickListener {
        void onDeleteButtonClick(int position);
    }

    public ShoppingListAdapter(Context context, ArrayList<ShoppingItem> itemsList) {
        this.context = context;
        this.itemsList = itemsList;
    }

    public void setOnDeleteButtonClickListener(OnDeleteButtonClickListener listener) {
        this.deleteButtonClickListener = listener;
    }

    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        TextView textViewItemName = convertView.findViewById(R.id.textViewItemName);
        Button btnDeleteItem = convertView.findViewById(R.id.btnDeleteItem);

        ShoppingItem item = itemsList.get(position);
        textViewItemName.setText(item.getName());

        btnDeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deleteButtonClickListener != null) {
                    deleteButtonClickListener.onDeleteButtonClick(position);
                }
            }
        });

        return convertView;
    }
}
