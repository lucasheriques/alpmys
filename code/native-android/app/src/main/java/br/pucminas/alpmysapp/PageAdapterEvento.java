package br.pucminas.alpmysapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import br.pucminas.alpmysapp.models.Evento;

public class PageAdapterEvento extends FragmentStatePagerAdapter{
    private Evento evento;
    public PageAdapterEvento(FragmentManager fm,Evento evento) {
        super(fm);
        this.evento=evento;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                EventoFragment eventoFragment=new EventoFragment(evento);
                return eventoFragment;
            case 1:
                LocalFragment localFragment=new LocalFragment(evento);
                return localFragment;
            case 2:
                EnderecoFragment enderecoFragment=new EnderecoFragment(evento);
                return enderecoFragment;
            case  3:
                IngressoFragment ingressoFragment=new IngressoFragment (evento);
                return ingressoFragment;
            default:
            return  null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
