package br.udesc.ceavi.cvfm.webservice.service;


import br.udesc.ceavi.cvfm.model.Control;
import br.udesc.ceavi.cvfm.webservice.response.ControlResponse;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface ControlService {

    @GET("/rest/control/seekall/{researcherid}")
    void seekControlsByResearcher(@Path("researcherid") int researcherid, Callback<ControlResponse> cb);

    @POST("/rest/control/update")
    void update(@Body Control control, Callback<Control> cb);

}
