package br.pucminas.alpmysapp;

import android.annotation.SuppressLint;
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
import android.widget.Button;

import br.pucminas.alpmysapp.models.Evento;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EnderecoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the  factory method to
 * create an instance of this fragment.
 */
@SuppressLint("ValidFragment")
public class EnderecoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Evento evento;
    private TextInputEditText tiedtLogradouro,tiedtNumero,tiedtComplemento,tiedtBairro,tiedtCidade,tiedtUf,tiedtCep;
    private OnFragmentInteractionListener mListener;

    @SuppressLint("ValidFragment")
    public EnderecoFragment(Evento evento) {
        this.evento=evento;
        Log.i("EVENTO","Endereço Fragment"+evento.toString());
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
        View view=inflater.inflate(R.layout.fragment_endereco, container, false);
        tiedtLogradouro=(TextInputEditText) view.findViewById(R.id.tiedtLogradouro);
        tiedtNumero=(TextInputEditText) view.findViewById(R.id.tiedtNumero);
        tiedtComplemento=(TextInputEditText) view.findViewById(R.id.tiedtComplemento);
        tiedtBairro=(TextInputEditText) view.findViewById(R.id.tiedtBairro);
        tiedtCidade=(TextInputEditText) view.findViewById(R.id.tiedtCidade);
        tiedtUf=(TextInputEditText) view.findViewById(R.id.tiedtUf);
        tiedtCep=(TextInputEditText) view.findViewById(R.id.tiedtCep);
        tiedtLogradouro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    evento.getLocal().getEndereco().setLogradouro(tiedtLogradouro.getText().toString());
                }catch (IllegalArgumentException iae){
                    tiedtLogradouro.setError(getString(R.string.tiedt_vazio));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tiedtNumero.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    evento.getLocal().getEndereco().setNumero(Integer.parseInt(tiedtNumero.getText().toString()));
                }catch (IllegalArgumentException iae){
                    tiedtNumero.setError(getString(R.string.tiedt_vazio));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tiedtComplemento.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    evento.getLocal().getEndereco().setComplemento(tiedtComplemento.getText().toString());
                }catch (IllegalArgumentException iae){
                    tiedtComplemento.setError(getString(R.string.tiedt_vazio));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tiedtBairro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    evento.getLocal().getEndereco().setBairro(tiedtBairro.getText().toString());
                }catch (IllegalArgumentException iae){
                    tiedtBairro.setError(getString(R.string.tiedt_vazio));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tiedtCidade.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    evento.getLocal().getEndereco().setCidade(tiedtCidade.getText().toString());
                }catch (IllegalArgumentException iae){
                    tiedtCidade.setError(getString(R.string.tiedt_vazio));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tiedtUf.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    evento.getLocal().getEndereco().setUf(tiedtUf.getText().toString());
                }catch (IllegalArgumentException iae){
                    tiedtUf.setError(getString(R.string.tiedt_vazio));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        tiedtCep.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try{
                    evento.getLocal().getEndereco().setCep(tiedtCep.getText().toString());
                }catch (IllegalArgumentException iae){
                    tiedtCep.setError(getString(R.string.tiedt_vazio));
                }
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
