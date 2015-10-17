package br.udesc.ceavi.cvfm.webservice.service;

import br.udesc.ceavi.cvfm.webservice.response.SourceResponse;
import retrofit.Callback;
import retrofit.http.GET;

public interface SourceService {

    @GET("/rest/source/seekall")
    void seekAll(Callback<SourceResponse> cb);

}
