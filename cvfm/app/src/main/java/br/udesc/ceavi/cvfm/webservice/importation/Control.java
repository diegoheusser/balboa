package br.udesc.ceavi.cvfm.webservice.importation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import br.udesc.ceavi.cvfm.base.AppContext;
import br.udesc.ceavi.cvfm.webservice.response.ControlResponse;
import br.udesc.ceavi.cvfm.webservice.service.ControlService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

public class Control {

    public void importControlsByUser(final int userid){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        RestAdapter restAdapter = new RestAdapter
                .Builder()
                .setEndpoint(AppContext.SERVICE_URL)
                .setConverter(new GsonConverter(gson))
                .build();
        ControlService service = restAdapter.create(ControlService.class);
        service.seekControlsByResearcher(userid, new Callback<ControlResponse>() {
            @Override
            public void success(ControlResponse controlResponse, Response response) {
                List<br.udesc.ceavi.cvfm.model.Control> olds =
                        br.udesc.ceavi.cvfm.model.Control.seekAllByResearcher(AppContext.CONTEXT,userid);
                List<br.udesc.ceavi.cvfm.model.Control> news = controlResponse.getControlList();
                List<br.udesc.ceavi.cvfm.model.Search> oldsSearches = new ArrayList<>();
                for(br.udesc.ceavi.cvfm.model.Control c: news){
                    if(!olds.contains(c)){
                        if(c.onTheList(olds)){
                            c.update(AppContext.CONTEXT);
                        } else {
                            c.save(AppContext.CONTEXT);
                        }
                    }
                    if(c.getSearches() != null && c.getSearches().size()>0){
                        oldsSearches =
                                br.udesc.ceavi.cvfm.model.Search.seekAllByControl(
                                        AppContext.CONTEXT,
                                        c.getId()
                                );
                        for(br.udesc.ceavi.cvfm.model.Search s: c.getSearches()){
                            s.setControl(c);
                            if(!oldsSearches.contains(s)){
                                if(s.onTheList(oldsSearches)){
                                    s.update(AppContext.CONTEXT);
                                } else {
                                    s.save(AppContext.CONTEXT);
                                }
                            }
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
