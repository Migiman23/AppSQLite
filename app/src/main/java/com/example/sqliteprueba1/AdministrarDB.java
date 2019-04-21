package com.example.sqliteprueba1;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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

        conn=new ConexionSQLite(getContext(),"bd_users",null,1);//Helper da acceso a la bd

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

    /*public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnBucarUs:
                BuscarUsuario();
                break;
            case R.id.btnModUs:
                ModificarUsuario();
                break;
            case R.id.btnEliUs:
                EliminarUsuario();
                break;
        }
    }*/

    public void BuscarUsuario(){

        SQLiteDatabase db= conn.getReadableDatabase();
        String[] Consultar={idAd.getText().toString()};//Id para buscar usuario
        String[] campos={Constantes.CAMPO_NOMBRE,Constantes.CAMPO_TELEFONO};//Campos a buscar

        if (idAd.getText().toString().equals("")) {
            Toast.makeText(getContext(),"Id de usuario no especificado",Toast.LENGTH_LONG).show();

        } else {
            try {
                Cursor cursor = db.query(Constantes.TABLA_USUARIOS, campos, Constantes.CAMPO_ID + "=?", Consultar, null, null, null);
                //Nombre de la tabla----Obtener----Campo de busqueda-------------id de busqueda---...
                cursor.moveToFirst();

                NombAd.setText(cursor.getString(0));
                TelAd.setText(cursor.getString(1));

            } catch (Exception e) {
                Toast.makeText(getContext(), "El usuario no existe", Toast.LENGTH_LONG).show();
                limpiar();
            }
        }
    }

    public void ModificarUsuario(){

        SQLiteDatabase db= conn.getWritableDatabase();//SQLiteDatabase db= conn.getReadableDatabase();
        String[] ActualizarPor={idAd.getText().toString()};

        ContentValues values= new ContentValues();
        values.put(Constantes.CAMPO_NOMBRE,NombAd.getText().toString());
        values.put(Constantes.CAMPO_TELEFONO,TelAd.getText().toString());

        if (idAd.getText().toString().equals("")) {
            Toast.makeText(getContext(),"Id de usuario no especificado",Toast.LENGTH_LONG).show();

        } else {

                String[] campos={Constantes.CAMPO_NOMBRE,Constantes.CAMPO_TELEFONO};//Campos a buscar

            try {
                Cursor cursor = db.query(Constantes.TABLA_USUARIOS, campos, Constantes.CAMPO_ID + "=?", ActualizarPor, null, null, null);
                cursor.moveToFirst();

                if (cursor.getString(0) == null || cursor.getString(1) == null) {//debug? permite que ocurra la exception si no existe el registro
                    //Toast.makeText(getContext(), "Error :no existen datos", Toast.LENGTH_LONG).show();
                } else {
                    db.update(Constantes.TABLA_USUARIOS, values, Constantes.CAMPO_ID + "=?", ActualizarPor);
                    Toast.makeText(getContext(), "Usuario Eliminado y actualizado", Toast.LENGTH_LONG).show();
                    limpiar();
                }
            }catch (Exception e){
                Toast.makeText(getContext(), "Error al ejecutar acción o el usuario no existe", Toast.LENGTH_LONG).show();
                limpiar();
            }
        }
        db.close();
    }

    public void EliminarUsuario(){
        SQLiteDatabase db= conn.getWritableDatabase();
        String[] EliminarPor={idAd.getText().toString()};

        if (idAd.getText().toString().equals("")) {  //Verificar que el campo no esté vacío
            Toast.makeText(getContext(),"Id de usuario no especificado",Toast.LENGTH_LONG).show();

        } else {
            String[] campos={Constantes.CAMPO_NOMBRE,Constantes.CAMPO_TELEFONO};//Campos a buscar

            try {//No es necesario verificar que los datos existan, solo el registro)
                Cursor cursor = db.query(Constantes.TABLA_USUARIOS, campos, Constantes.CAMPO_ID + "=?", EliminarPor, null, null, null);
                cursor.moveToFirst();

                if (cursor.getString(0) == null || cursor.getString(1) == null) {//debug? permite que ocurra la exception si no existe el registro
                    //Toast.makeText(getContext(), "Error :no existen datos", Toast.LENGTH_LONG).show();
                } else {
                    db.delete(Constantes.TABLA_USUARIOS, Constantes.CAMPO_ID + "=?", EliminarPor);
                    Toast.makeText(getContext(), "Usuario Eliminado", Toast.LENGTH_LONG).show();
                    limpiar();
                }
            }catch (Exception e){
                Toast.makeText(getContext(), "Error al ejecutar acción o el usuario no existe", Toast.LENGTH_LONG).show();
                limpiar();
            }
        }
        db.close();

    }


    public void limpiar(){
        idAd.setText("");
        NombAd.setText("");
        TelAd.setText("");
    }
}
