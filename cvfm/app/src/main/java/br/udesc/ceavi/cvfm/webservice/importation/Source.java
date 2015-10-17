package br.udesc.ceavi.cvfm.webservice.importation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import br.udesc.ceavi.cvfm.base.AppContext;
import br.udesc.ceavi.cvfm.webservice.response.ItemResponse;
import br.udesc.ceavi.cvfm.webservice.response.SourceResponse;
import br.udesc.ceavi.cvfm.webservice.service.ItemService;
import br.udesc.ceavi.cvfm.webservice.service.SourceService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

public class Source {

    public void importSources(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        RestAdapter restAdapter = new RestAdapter
                .Builder()
                .setEndpoint(AppContext.SERVICE_URL)
                .setConverter(new GsonConverter(gson))
                .build();
        SourceService service = restAdapter.create(SourceService.class);
        service.seekAll(new Callback<SourceResponse>() {
            @Override
            public void success(SourceResponse sourceResponse, Response response) {
                List<br.udesc.ceavi.cvfm.model.Source> olds =
                        br.udesc.ceavi.cvfm.model.Source.seekAll(AppContext.CONTEXT);
                List<br.udesc.ceavi.cvfm.model.Source> news = sourceResponse.getSourceList();
                for(br.udesc.ceavi.cvfm.model.Source s: news){
                    if(!olds.contains(s)){
                        if(s.onTheList(olds)){
                            s.update(AppContext.CONTEXT);
                        } else {
                            s.save(AppContext.CONTEXT);
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
