package br.pucminas.alpmysapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import br.pucminas.alpmysapp.models.Evento;
import br.pucminas.alpmysapp.models.Ingresso;
import br.pucminas.alpmysapp.models.TipoIngresso;


public class IngressoAdapter extends ArrayAdapter<TipoIngresso> {
    private final Context context;
    private final ArrayList<TipoIngresso> elementos;
    public IngressoAdapter(Context context,ArrayList<TipoIngresso> objects) {
        super(context, R.layout.tipo_ingresso_item, objects);
        this.context = context;
        this.elementos=objects;
    }

    @NonNull

    public View getView(final int position, View convertView, ViewGroup parent)  {
        LayoutInflater inflater=  (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView=  inflater.inflate(R.layout.tipo_ingresso_item,  parent,  false);
        final TextInputEditText tedtTipoIngresso=(TextInputEditText) rowView.findViewById(R.id.tedtTipoIngresso);
        final TextInputEditText tedtValor=(TextInputEditText) rowView.findViewById(R.id.tedtValor);
        final TextInputEditText tedtQuantidade=(TextInputEditText) rowView.findViewById(R.id.tedtQuantidade);
        Button button= (Button) rowView.findViewById(R.id.buttonIngresso);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TipoIngresso tipoIngresso= elementos.get(position);
                tipoIngresso.setValor(Long.parseLong(tedtValor.getText().toString()));
                tipoIngresso.setQuantidade(Long.parseLong(tedtQuantidade.getText().toString()));
                tipoIngresso.setTipo(tedtTipoIngresso.getText().toString());

                ArrayList<Ingresso> ingressos=new ArrayList<Ingresso>();
                for(int i=0;i<tipoIngresso.getQuantidade();i++){
                    Ingresso ingresso=new Ingresso();
                    ingresso.setNumeroDoIngresso(i+"");
                    ingressos.add(ingresso);
                }

                tipoIngresso.setIngressos(ingressos);

                Log.i("Ingresso","ingressos adicionados");

            }
        });
       /* tedtQuantidade.setText(elementos.get(position).getQuantidade().toString());
        tedtTipoIngresso.setText(elementos.get(position).getTipo());
        tedtValor.setText(elementos.get(position).getValor().toString());*/
        return rowView;
    }
}
