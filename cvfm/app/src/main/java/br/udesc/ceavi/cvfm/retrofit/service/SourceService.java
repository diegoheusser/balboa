package br.udesc.ceavi.cvfm.retrofit.service;

import br.udesc.ceavi.cvfm.retrofit.response.SourceResponse;
import retrofit.Callback;
import retrofit.http.GET;

public interface SourceService {

    @GET("/rest/source/seekall")
    void seekAll(Callback<SourceResponse> cb);

}
