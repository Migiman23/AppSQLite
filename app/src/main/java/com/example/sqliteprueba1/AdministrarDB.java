package com.example.sqliteprueba1;


import android.content.ContentValues;
import android.database.Cursor;
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


public class AdministrarDB extends Fragment {

    TextView idAd,NombAd,TelAd;
    Button Buscar,Modificar,Eliminar;

    ConexionSQLite conn;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View Vista=inflater.inflate(R.layout.fragment_administrar_db, container, false);

        conn=new ConexionSQLite(getContext(),"bd_users",null,1);

        idAd=Vista.findViewById(R.id.idAdmin);
        NombAd=Vista.findViewById(R.id.NombreAdmin);
        TelAd=Vista.findViewById(R.id.TelAdmin);

        Buscar=Vista.findViewById(R.id.btnBucarUs);
        Modificar=Vista.findViewById(R.id.btnModUs);
        Eliminar=Vista.findViewById(R.id.btnEliUs);

        Buscar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BuscarUsuario();
            }
        });

        Modificar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ModificarUsuario();
            }
        });

        Eliminar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EliminarUsuario();
            }
        });



        return Vista;
    }

    public void ModificarUsuario(){

        SQLiteDatabase db= conn.getReadableDatabase();
        String[] ActualizarPor={idAd.getText().toString()};

        ContentValues values= new ContentValues();
        values.put(Constantes.CAMPO_NOMBRE,NombAd.getText().toString());
        values.put(Constantes.CAMPO_TELEFONO,TelAd.getText().toString());

        db.update(Constantes.TABLA_USUARIOS,values,Constantes.CAMPO_ID+"=?",ActualizarPor);
        Toast.makeText(getContext(),"Usuario Eliminado actualizado",Toast.LENGTH_LONG).show();
        db.close();
    }


    public void BuscarUsuario(){

        SQLiteDatabase db= conn.getReadableDatabase();
        String[] Consultar={idAd.getText().toString()};
        String[] campos={Constantes.CAMPO_NOMBRE,Constantes.CAMPO_TELEFONO};

        try{
            Cursor cursor=db.query(Constantes.TABLA_USUARIOS,campos,Constantes.CAMPO_ID+"=?",Consultar,null,null,null);
            //Nombre de la tabla----Obtener----Campo de busqueda-------------id de busqueda---...
            cursor.moveToFirst();

            NombAd.setText(cursor.getString(0));
            TelAd.setText(cursor.getString(1));

        }catch (Exception e){
            Toast.makeText(getContext(),"No existe el usuario",Toast.LENGTH_LONG).show();

        }

    }

    public void EliminarUsuario(){
        SQLiteDatabase db= conn.getReadableDatabase();
        String[] EliminarPor={idAd.getText().toString()};

        db.delete(Constantes.TABLA_USUARIOS,Constantes.CAMPO_ID+"=?",EliminarPor);
        Toast.makeText(getContext(),"Usuario Eliminado",Toast.LENGTH_LONG).show();
        db.close();

    }


}
