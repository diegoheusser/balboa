package br.udesc.ceavi.cvfm.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import br.udesc.ceavi.cvfm.R;
import br.udesc.ceavi.cvfm.model.Search;

public class SearchAdapter extends BaseAdapter {

    private final Context context;
    private final List<Search> values;

    public SearchAdapter(Context context, int controleid) {
        super();
        this.context = context;
        this.values = Search.seekAllByControl(context,controleid);
    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public Object getItem(int position) {
        return values.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row_search, parent, false);
        TextView textViewItem = (TextView)
                view.findViewById(R.id.row_search_text_view_item);
        TextView textViewOldPrice = (TextView)
                view.findViewById(R.id.row_search_text_view_old_price);
        TextView textViewOldBrand = (TextView)
                view.findViewById(R.id.row_search_text_view_old_brand);
        TextView textViewOldSpecification = (TextView)
                view.findViewById(R.id.row_search_text_view_old_specification);
        EditText editTextNewPrice = (EditText)
                view.findViewById(R.id.row_search_edit_text_new_price);
        EditText editTextNewBrand = (EditText)
                view.findViewById(R.id.row_search_edit_text_new_brand);
        EditText editTextNewSpecification = (EditText)
                view.findViewById(R.id.row_search_edit_text_new_specification);
        textViewItem.setText(values.get(position).getItem().getIdentifier()
                + " - "
                + values.get(position).getItem().getDescription());
        textViewOldPrice.setText("R$ "+values.get(position).getOldPrice());
        textViewOldBrand.setText(values.get(position).getOldBrand());
        textViewOldSpecification.setText(values.get(position).getOldSpecification());
        editTextNewPrice.setText(values.get(position).getNewPrice()+"");
        editTextNewBrand.setText(values.get(position).getNewBrand());
        editTextNewSpecification.setText(values.get(position).getNewSpecification());

        editTextNewPrice.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    values.get(position).setNewDate(new Date());
                }
            }
        });
        return view;
    }

}
