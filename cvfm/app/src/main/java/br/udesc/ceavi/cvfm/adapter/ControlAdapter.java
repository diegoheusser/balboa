package br.udesc.ceavi.cvfm.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import br.udesc.ceavi.cvfm.R;
import br.udesc.ceavi.cvfm.SearchActivity;
import br.udesc.ceavi.cvfm.base.AppContext;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row_control, parent, false);
        final ImageButton imageButtonAction = (ImageButton)
                view.findViewById(R.id.row_control_button_action);
        LinearLayout linearLayoutSource = (LinearLayout)
                view.findViewById(R.id.row_control_linear_layout_source);
        TextView textViewDescription = (TextView)
                view.findViewById(R.id.row_control_text_view_description);
        TextView textViewLocalization = (TextView)
                view.findViewById(R.id.row_control_text_view_localization);
        TextView textViewMonth = (TextView)
                view.findViewById(R.id.row_control_text_view_month);
        TextView textViewWeek = (TextView)
                view.findViewById(R.id.row_control_text_view_week);
        textViewDescription.setText(values.get(position).getSource().getDescription());
        textViewLocalization.setText(values.get(position).getSource().getLocalization());
        textViewMonth.setText(month(values.get(position).getMonth()));
        textViewWeek.setText(values.get(position).getWeek() + "ª semana de ");


        switch(values.get(position).getStatus()){
            case 0:{
                imageButtonAction.setImageResource(R.mipmap.upload);
                break;
            }
            case 1:{
                imageButtonAction.setImageResource(R.mipmap.ok);
                break;
            }
            case 2:{
                imageButtonAction.setImageResource(R.mipmap.error);
                break;
            }
        }

        imageButtonAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(values.get(position).getStatus()){
                    case 0:{ //upload
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage(R.string.dialog_upload_message)
                                .setTitle(R.string.dialog_upload_title);
                        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                values.get(position).setStatus(2);
                                notifyDataSetChanged();
                            }
                        });
                        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                        break;
                    }
                    case 1:{ //ok
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage(R.string.dialog_ok_message)
                                .setTitle(R.string.dialog_ok_title);
                        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                values.remove(position);
                                notifyDataSetChanged();
                            }
                        });
                        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                        break;
                    }
                    case 2:{ //error
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage(R.string.dialog_error_message)
                                .setTitle(R.string.dialog_error_title);
                        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                values.get(position).setStatus(1);
                                notifyDataSetChanged();
                            }
                        });
                        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                        break;
                    }
                }

            }
        });
        linearLayoutSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppContext.CONTROL = values.get(position);
                Intent it = new Intent(context, SearchActivity.class);
                context.startActivity(it);
            }
        });
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
