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

    ListView Listview;
    ArrayList<String>  ListaDatos;
    ArrayList<Usuario>  ListaUsuarios;
    ConexionSQLite conn;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View Vista=inflater.inflate(R.layout.fragment_consulta_list_view, container, false);

        conn = new ConexionSQLite(getContext(),"db_users",null,1);

        Listview=Vista.findViewById(R.id.ListaUsuarios);

            ConsultaPErsonas();

        ArrayAdapter <String> adaptador=new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,ListaDatos);
        Listview.setAdapter(adaptador);
        Listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Datid="id: "+ListaUsuarios.get(position).getId()+"\n";
                       Datid+="Nombre: "+ListaUsuarios.get(position).getNombre()+"\n";
                       Datid+="Telefono: "+ListaUsuarios.get(position).getTelefono()+"\n";

                Toast.makeText(getContext(),Datid,Toast.LENGTH_LONG).show();
            }
        });

        return Vista;
    }

    private void ConsultaPErsonas(){
        SQLiteDatabase db=conn.getReadableDatabase();

        Usuario usuario;
        ListaUsuarios= new ArrayList<>();
        Cursor cursor= db.rawQuery("SELECT * FROM "+Constantes.TABLA_USUARIOS,null);

        while(cursor.moveToNext()){
            usuario=new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setTelefono(cursor.getString(2));

            ListaUsuarios.add(usuario);
        }

      //  db.close();
        ObtenerLista();
    }
    private void ObtenerLista(){

        ListaDatos =new ArrayList<>();

        for(int i=0;i<ListaUsuarios.size();i++){
            ListaDatos.add(ListaUsuarios.get(i).getId()+"-"+ListaUsuarios.get(i).getNombre());
        }
    }
}
