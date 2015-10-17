package br.udesc.ceavi.cvfm.webservice.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import br.udesc.ceavi.cvfm.model.Item;

public class ItemResponse {

    @SerializedName("item")
    private List<Item> itemList;

    public List<Item> getItemList() {
        return itemList;
    }
}
