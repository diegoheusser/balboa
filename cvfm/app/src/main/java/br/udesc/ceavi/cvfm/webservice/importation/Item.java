package br.udesc.ceavi.cvfm.webservice.importation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import br.udesc.ceavi.cvfm.base.AppContext;
import br.udesc.ceavi.cvfm.webservice.response.ItemResponse;
import br.udesc.ceavi.cvfm.webservice.service.ItemService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

public class Item {

    public void importItems(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        RestAdapter restAdapter = new RestAdapter
                .Builder()
                .setEndpoint(AppContext.SERVICE_URL)
                .setConverter(new GsonConverter(gson))
                .build();
        ItemService service = restAdapter.create(ItemService.class);
        service.seekAll(new Callback<ItemResponse>() {
            @Override
            public void success(ItemResponse itemResponse, Response response) {
                List<br.udesc.ceavi.cvfm.model.Item> olds =
                        br.udesc.ceavi.cvfm.model.Item.seekAll(AppContext.CONTEXT);
                List<br.udesc.ceavi.cvfm.model.Item> news = itemResponse.getItemList();
                for(br.udesc.ceavi.cvfm.model.Item i: news){
                    if(!olds.contains(i)){
                        if(i.onTheList(olds)){
                            i.update(AppContext.CONTEXT);
                        } else {
                            i.save(AppContext.CONTEXT);
                        }
                    }
                }
            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
            }
        });
    }
}
