package br.udesc.ceavi.cvfm.webservice.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import br.udesc.ceavi.cvfm.model.Source;

public class SourceResponse {

    @SerializedName("source")
    private List<Source> sourceList;

    public List<Source> getSourceList() {
        return sourceList;
    }
}
