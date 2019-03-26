package com.example.sqliteprueba1;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqliteprueba1.Utilidades.Constantes;


public class FormularioRegistro extends Fragment {

    TextView id,nombre,tel;
    Button Acep,Can;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View Vista=inflater.inflate(R.layout.fragment_formulario_registro, container, false);

        id= Vista.findViewById(R.id.id_Form);
        nombre= Vista.findViewById(R.id.Nombre_Form);
        tel=Vista.findViewById(R.id.Tel_Form);

        Acep= Vista.findViewById(R.id.Aceptar_Form);
        Can=Vista.findViewById(R.id.Can_Form);

        Acep.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                RegistrarUsuario();
            }
        });
        Can.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Inicio fr=new Inicio();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.Cont_Main,fr)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return Vista;
    }

    public void RegistrarUsuario(){

        ConexionSQLite conn=new ConexionSQLite(getContext(),"bd_users",null,1);

        SQLiteDatabase db= conn.getWritableDatabase(); //Para escribir

        try {

            ContentValues Values = new ContentValues();

            Values.put(Constantes.CAMPO_ID,id.getText().toString());
            Values.put(Constantes.CAMPO_NOMBRE, nombre.getText().toString());
            Values.put(Constantes.CAMPO_TELEFONO, tel.getText().toString());

            long idResult = db.insert(Constantes.TABLA_USUARIOS, Constantes.CAMPO_ID, Values);

            Toast.makeText(getContext(), "Id registro:" + idResult, Toast.LENGTH_SHORT).show();
        }catch(SQLException e){
            Toast.makeText(getContext(), "Error:" +e.toString(), Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

}
