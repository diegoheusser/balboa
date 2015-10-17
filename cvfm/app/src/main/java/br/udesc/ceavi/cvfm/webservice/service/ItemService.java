package br.udesc.ceavi.cvfm.webservice.service;

import br.udesc.ceavi.cvfm.webservice.response.ItemResponse;
import retrofit.http.GET;
import retrofit.Callback;

public interface ItemService {

    @GET("/rest/item/seekall")
    void seekAll(Callback<ItemResponse> cb);

}
