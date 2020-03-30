package com.recook.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Document(value = "list")
public class ShoppingList {

    @Id
    private long id;

    @NotBlank
    @Size(max = 100)
    @Indexed( unique = true)
    private String listName;

    @Indexed
    private ShoppingItem[] listItems;

    private String getItemsAsString() {
        String res = "";
        for(ShoppingItem item : this.listItems) {
            res.concat(item.toString());
        }
        return res;
    }

    @Override
    public String toString() {
        return "Shopping Liste [ID: " + id + ", Listenname: " + listName + ", Einträge: " + this.getItemsAsString() + "]";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public ShoppingItem[] getListItems() {
        return listItems;
    }

    public void setListItems(ShoppingItem[] listItems) {
        this.listItems = listItems;
    }
}
