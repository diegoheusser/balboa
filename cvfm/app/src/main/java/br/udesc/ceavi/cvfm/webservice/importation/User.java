package br.udesc.ceavi.cvfm.webservice.importation;

import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import br.udesc.ceavi.cvfm.base.AppContext;
import br.udesc.ceavi.cvfm.webservice.response.ResearcherResponse;
import br.udesc.ceavi.cvfm.webservice.service.ResearcherService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

public class User {

    public void importUsers(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        RestAdapter restAdapter = new RestAdapter
                .Builder()
                .setEndpoint(AppContext.SERVICE_URL)
                .setConverter(new GsonConverter(gson))
                .build();

        ResearcherService service = restAdapter.create(ResearcherService.class);

        service.seekResearchers( new Callback<ResearcherResponse>() {
            @Override
            public void success(ResearcherResponse obj, Response response) {
                List<br.udesc.ceavi.cvfm.model.User> oldResearchers = br.udesc.ceavi.cvfm.model.User.seekAll(AppContext.CONTEXT);
                List<br.udesc.ceavi.cvfm.model.User> newResearchers = obj.getResearcherList();
                System.out.println(oldResearchers.toString());
                System.out.println(newResearchers.toString());
                for(br.udesc.ceavi.cvfm.model.User u: newResearchers){
                    if(!oldResearchers.contains(u)){
                        if(u.onTheList(oldResearchers)){
                            u.update(AppContext.CONTEXT);
                        } else {
                            u.save(AppContext.CONTEXT);
                        }
                    }
                }

            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println(error.toString());
                Toast.makeText(AppContext.CONTEXT, "Offline", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
