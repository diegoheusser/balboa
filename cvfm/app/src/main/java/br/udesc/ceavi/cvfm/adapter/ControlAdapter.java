package br.udesc.ceavi.cvfm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.udesc.ceavi.cvfm.R;
import br.udesc.ceavi.cvfm.model.Control;

public class ControlAdapter extends BaseAdapter {

    private final Context context;
    private final List<Control> values;

    public ControlAdapter(Context context, List<Control> values){
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
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row_control, parent, false);
        TextView textViewPercentage = (TextView)
                view.findViewById(R.id.list_activity_control_text_view_percentage);
        TextView textViewDescription = (TextView)
                view.findViewById(R.id.list_activity_control_text_view_description);
        TextView textViewLocalization = (TextView)
                view.findViewById(R.id.list_activity_control_text_view_localization);
        TextView textViewMonth = (TextView)
                view.findViewById(R.id.list_activity_control_text_view_month);
        TextView textViewWeek = (TextView)
                view.findViewById(R.id.list_activity_control_text_view_week);
        textViewPercentage.setText(String.valueOf(values.get(position).getPercentage())+"%");
        textViewDescription.setText(values.get(position).getSource().getDescription());
        textViewLocalization.setText(values.get(position).getSource().getLocalization());
        textViewMonth.setText(month(values.get(position).getMonth()));
        textViewWeek.setText(values.get(position).getWeek() + "ª semana de ");
        return view;
    }

    private String month(int month){
        switch(month){
            case 1: {
                return "Janeiro";
            }
            case 2: {
                return "Fevereiro";
            }
            case 3: {
                return "Março";
            }
            case 4:{
                return "Abril";
            }
            case 5: {
                return "Maio";
            }
            case 6: {
                return "Junho";
            }
            case 7: {
                return "Julho";
            }
            case 8: {
                return "Agosto";
            }
            case 9: {
                return "Setembro";
            }
            case 10: {
                return "Outubro";
            }
            case 11: {
                return "Novembro";
            }
            case 12: {
                return "Dezembro";
            }
            default: {
                return " ";
            }
        }
    }
}
