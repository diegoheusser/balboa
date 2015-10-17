package br.udesc.ceavi.cvfm.dao.search;


import java.util.List;

import br.udesc.ceavi.cvfm.dao.standard.StandardDAO;
import br.udesc.ceavi.cvfm.model.Search;

public interface SearchDAO extends StandardDAO<Search> {

    List<Search> seekAllByControl(int controlid);

}
