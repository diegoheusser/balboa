package br.udesc.ceavi.cvfm.retrofit.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import br.udesc.ceavi.cvfm.model.Control;

public class ControlResponse {

    @SerializedName("control")
    private List<Control> controlList;

    public List<Control> getControlList() {
        return controlList;
    }
}
