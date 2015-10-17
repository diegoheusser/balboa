package br.udesc.ceavi.cvfm.webservice.service;

import br.udesc.ceavi.cvfm.webservice.response.ResearcherResponse;
import retrofit.Callback;
import retrofit.http.GET;

public interface ResearcherService {

    @GET("/rest/researcher/seekall")
    void seekResearchers(Callback<ResearcherResponse> cb);

}
