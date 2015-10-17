package br.udesc.ceavi.cvfm.webservice.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import br.udesc.ceavi.cvfm.model.User;

public class ResearcherResponse {

    @SerializedName("user")
    private List<User> researcherList;

    public List<User> getResearcherList() {
        return researcherList;
    }
}
