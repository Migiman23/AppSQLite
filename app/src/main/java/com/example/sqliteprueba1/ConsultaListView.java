package com.example.sqliteprueba1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sqliteprueba1.Entidades.Usuario;
import com.example.sqliteprueba1.Utilidades.Constantes;

import java.util.ArrayList;


public class ConsultaListView extends Fragment {

    ListView ListviewPer;
    ArrayList<String>  ListaUsuarios;
    ArrayList<Usuario> ListaDatosUsuarios;
    ConexionSQLite conn;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View Vista=inflater.inflate(R.layout.fragment_consulta_list_view, container, false);

        conn = new ConexionSQLite(getContext(),"db_users",null,1);

        ListviewPer=Vista.findViewById(R.id.ListaUsuarios);

            ConsultaPersonas();

        ArrayAdapter <String> adaptador=new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,ListaUsuarios);
        ListviewPer.setAdapter(adaptador);

        ListviewPer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Datid="id: "+ListaDatosUsuarios.get(position).getId()+"\n";
                       Datid+="Nombre: "+ListaDatosUsuarios.get(position).getNombre()+"\n";
                       Datid+="Telefono: "+ListaDatosUsuarios.get(position).getTelefono()+"\n";

                Toast.makeText(getContext(),Datid,Toast.LENGTH_LONG).show();
            }
        });
        return Vista;
    }

    private void ConsultaPersonas(){
        SQLiteDatabase db=conn.getReadableDatabase();

        Usuario persona;
        ListaDatosUsuarios= new ArrayList<>();
        Cursor cursor= db.rawQuery("SELECT * FROM "+Constantes.TABLA_USUARIOS+" ORDER BY "+Constantes.CAMPO_ID,null);

        while(cursor.moveToNext()){

            persona=new Usuario();

            persona.setId(cursor.getInt(0));
            persona.setNombre(cursor.getString(1));
            persona.setTelefono(cursor.getString(2));

            ListaDatosUsuarios.add(persona);
        }
        ObtenerLista();
    }
    private void ObtenerLista(){
        ListaUsuarios =new ArrayList<>();

        for (int i=0;i<ListaDatosUsuarios.size();i++){
            ListaUsuarios.add(ListaDatosUsuarios.get(i).getId()+"-"+ListaDatosUsuarios.get(i).getNombre()+"-"+ListaDatosUsuarios.get(i).getTelefono());
        }
    }
}
