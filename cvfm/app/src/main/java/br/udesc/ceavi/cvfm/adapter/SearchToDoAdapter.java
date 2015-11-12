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
import br.udesc.ceavi.cvfm.base.AppContext;
import br.udesc.ceavi.cvfm.model.Search;

public class SearchToDoAdapter extends BaseAdapter {

    private final Context context;
    private final List<Search> values;

    public SearchToDoAdapter(Context context, List<Search> values) {
        super();
        this.context = context;
        this.values = values;
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
        View view = inflater.inflate(R.layout.todo_list_item, parent, false);
        TextView textViewItem = (TextView)
                view.findViewById(R.id.todo_list_item_text_view_item);
        TextView textViewOldPrice = (TextView)
                view.findViewById(R.id.todo_list_item_text_view_old_price);
        TextView textViewOldBrand = (TextView)
                view.findViewById(R.id.todo_list_item_text_view_old_brand);
        TextView textViewOldSpecification = (TextView)
                view.findViewById(R.id.todo_list_item_text_view_old_specification);
        final EditText editTextNewPrice = (EditText)
                view.findViewById(R.id.todo_list_item_edit_text_new_price);
        EditText editTextNewBrand = (EditText)
                view.findViewById(R.id.todo_list_item_edit_text_new_brand);
        EditText editTextNewSpecification = (EditText)
                view.findViewById(R.id.todo_list_item_edit_text_new_specification);
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
                    values.get(position).setNewPrice(
                            Double.parseDouble(((EditText) v).getText().toString()));
                    if (position > 0 && values.get(position - 1).getNewPrice() > 0) {
                        values.get(position - 1).update(context);
                        values.remove(position - 1);
                        notifyDataSetChanged();
                    }

                }
            }
        });
        editTextNewBrand.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    values.get(position).setNewBrand(((EditText) v).getText().toString());
                }
            }
        });
        editTextNewSpecification.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    values.get(position).setNewSpecification(((EditText)v).getText().toString());
                    if (position == 0 && values.size() == 1) {
                        values.get(position).update(context);
                    }
                }
            }
        });
        return view;
    }

}
