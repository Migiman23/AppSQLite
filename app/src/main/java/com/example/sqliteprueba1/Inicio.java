package com.example.sqliteprueba1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Inicio extends Fragment {

    Button Registrar,Administrar,Spinner,ListView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View Vista=inflater.inflate(R.layout.fragment_inicio, container, false);

        ConexionSQLite conn=new ConexionSQLite(getContext(),"bd_users",null,1);


        Registrar= Vista.findViewById(R.id.Registrar_id);
        Administrar=Vista.findViewById(R.id.Consultar_id);
        Spinner=Vista.findViewById(R.id.ConsSpinner_id);
        ListView=Vista.findViewById(R.id.ConsList_id);

        Registrar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                FormularioRegistro fr=new FormularioRegistro();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.Cont_Main,fr)
                        .addToBackStack(null)
                        .commit();
            }
        });

        Administrar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                AdministrarDB fr=new AdministrarDB();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.Cont_Main,fr)
                        .addToBackStack(null)
                        .commit();
            }
        });
        Spinner.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                ConsultaSpinner fr=new ConsultaSpinner();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.Cont_Main,fr)
                        .addToBackStack(null)
                        .commit();
            }
        });
        ListView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                ConsultaListView fr=new ConsultaListView();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.Cont_Main,fr)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return Vista;
    }


}
