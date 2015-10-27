package br.udesc.ceavi.cvfm.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import br.udesc.ceavi.cvfm.model.Search;

public class SearchDoneAdapter extends BaseAdapter {

    private Context context;
    private List<Search> values;

    public SearchDoneAdapter(Context context, List<Search> values) {
        this.context = context;
        this.values = values;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
