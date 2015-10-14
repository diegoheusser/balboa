package br.udesc.ceavi.cvfm.retrofit.service;


import br.udesc.ceavi.cvfm.retrofit.response.ControlResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

public interface ControlService {

    @GET("/rest/control/seekall/{researcherid}")
    void seekControlsByResearcher(@Path("researcherid") int researcherid, Callback<ControlResponse> cb);

}
