package br.pucminas.alpmysapp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.pucminas.alpmysapp.models.Evento;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EventoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the  factory method to
 * create an instance of this fragment.
 */
@SuppressLint("ValidFragment")
public class EventoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextInputEditText tiedtNomeEvento, tiedtDescricao,tiedtHoraInicio,tiedtHoraTermino,tiedtLinkPagina;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Evento evento;
    private OnFragmentInteractionListener mListener;

    @SuppressLint("ValidFragment")
    public EventoFragment(Evento evento) {
        this.evento=evento;
        Log.i("EVENTO","Evento Fragment Nome"+evento.toString());
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_evento, container, false);
        tiedtNomeEvento = (TextInputEditText)view.findViewById(R.id.tiedtNomeEvento);
        tiedtDescricao = (TextInputEditText) view.findViewById(R.id.tiedtDescricao);
        tiedtHoraInicio=(TextInputEditText) view.findViewById(R.id.tiedtHorarioInicio);
        tiedtHoraTermino=(TextInputEditText) view.findViewById(R.id.tiedtHorarioTermino);
        tiedtLinkPagina=(TextInputEditText) view.findViewById(R.id.tiedtLinkPagina);
        tiedtHoraInicio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               // buttonCadastro.setEnabled(validaForm());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tiedtHoraInicio.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(tiedtHoraInicio.getText().toString().isEmpty()){
                    tiedtHoraInicio.setError(getString(R.string.tiedt_vazio));
                }
            }
        });
        tiedtHoraInicio.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                final Calendar calendar= Calendar.getInstance();
                int ano=calendar.get(Calendar.YEAR);
                int mes=calendar.get(Calendar.MONTH);
                int dia=calendar.get(Calendar.DAY_OF_MONTH);
                int hora=calendar.get(Calendar.HOUR_OF_DAY);
                int minuto=calendar.get(Calendar.MINUTE);
                final TimePickerDialog timePickerDialog2=new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker timePicker, int hora, int minuto) {
                        calendar.set(Calendar.HOUR_OF_DAY,hora);
                        calendar.set(Calendar.MINUTE,minuto);
                        SimpleDateFormat simpleDateFormat= new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
                        Date data=calendar.getTime();
                        simpleDateFormat.format(data);
                        tiedtHoraInicio.setText(simpleDateFormat.format(data));
                        String dataText [] =simpleDateFormat.format(data).split(" ");
                        evento.setHorarioInicio(dataText[0]+"T"+dataText[1]+".00Z");



                    }
                },hora,minuto,true);
                DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
                        timePickerDialog2.show();
                        calendar.set(Calendar.YEAR, ano);
                        calendar.set(Calendar.MONTH, mes);
                        calendar.set(Calendar.DAY_OF_MONTH, dia);

                    }
                },ano,mes,dia);
                datePickerDialog.show();


            }
        });
        tiedtHoraTermino.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(tiedtHoraTermino.getText().toString().isEmpty()){
                    tiedtHoraTermino.setError(getString(R.string.tiedt_vazio));
                }
            }
        });
        tiedtHoraTermino.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //buttonCadastro.setEnabled(validaForm());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tiedtHoraTermino.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final Calendar calendar=Calendar.getInstance();
                int ano=calendar.get(Calendar.YEAR);
                int mes=calendar.get(Calendar.MONTH);
                int dia=calendar.get(Calendar.DAY_OF_MONTH);
                int hora=calendar.get(Calendar.HOUR_OF_DAY);
                int minuto=calendar.get(Calendar.MINUTE);
                final TimePickerDialog timePickerDialog=new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hora, int minuto) {
                        calendar.set(Calendar.HOUR_OF_DAY,hora);
                        calendar.set(Calendar.MINUTE,minuto);
                        SimpleDateFormat simpleDateFormat= new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
                        Date data=calendar.getTime();
                        tiedtHoraTermino.setText(simpleDateFormat.format(data));
                        String dataText [] =simpleDateFormat.format(data).split(" ");
                        evento.setHorarioTermino(dataText[0]+"T"+dataText[1]+".00Z");

                    }
                },hora,minuto,true);
                DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int ano, int mes, int dia) {
                        timePickerDialog.show();
                        calendar.set(Calendar.YEAR, ano);
                        calendar.set(Calendar.MONTH, mes);
                        calendar.set(Calendar.DAY_OF_MONTH, dia);
                    }
                },ano,mes,dia);
                datePickerDialog.show();

            }
        });
        tiedtNomeEvento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(tiedtNomeEvento.getText().toString().isEmpty()){
                    tiedtNomeEvento.setError(getString(R.string.tiedt_vazio));
                }
            }
        });
        tiedtNomeEvento.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    evento.setNome(tiedtNomeEvento.getText().toString());
                }catch (IllegalArgumentException iae){
                    tiedtNomeEvento.setError(getString(R.string.tiedt_vazio));
                }
                //buttonCadastro.setEnabled(validaForm());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tiedtDescricao.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(tiedtDescricao.getText().toString().isEmpty()){
                    tiedtDescricao.setError(getString(R.string.tiedt_vazio));
                }
            }
        });
        tiedtDescricao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    evento.setDescricao(tiedtDescricao.getText().toString());
                }catch (IllegalArgumentException iae){
                    tiedtDescricao.setError(getString(R.string.tiedt_vazio));
                }
                //buttonCadastro.setEnabled(validaForm());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
