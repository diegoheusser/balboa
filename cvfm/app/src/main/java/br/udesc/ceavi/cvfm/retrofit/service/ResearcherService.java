package br.udesc.ceavi.cvfm.retrofit.service;

import br.udesc.ceavi.cvfm.retrofit.response.ResearcherResponse;
import retrofit.Callback;
import retrofit.http.GET;

public interface ResearcherService {

    @GET("/rest/researcher/seekall")
    void seekResearchers(Callback<ResearcherResponse> cb);

}
