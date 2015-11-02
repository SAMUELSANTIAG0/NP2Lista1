package br.unifor.android.np2.np2lista1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import br.unifor.android.np2.np2lista1.dao.entidade.PacienteBean;

/**
 * Created by Samuel Santiago on 02/11/2015.
 */
public class PacienteDao extends SQLiteOpenHelper{

    public static final int VERSAO = 1;
    public static final String TABELA = "paciente";
    public static final String DATABASE = "PACIENTE.DB";

    public PacienteDao(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public PacienteDao(Context context){
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE " + TABELA
                + "(id INTEGER PRIMARY KEY, "
                + "nome TEXT, "
                + "celular TEXT, "
                + "email TEXT, ";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void inserirRegistro(PacienteBean paciente) {
        ContentValues valores = new ContentValues();

        valores.put("nome", paciente.getNome());
        valores.put("celular", paciente.getNome());
        valores.put("email", paciente.getNome());

        getWritableDatabase().insert(TABELA, null, valores);
    }

    public ArrayList<PacienteBean> consultarRegistros(){

        ArrayList<PacienteBean> pacienteAcademias = new ArrayList<PacienteBean>();
        String sql = "Select * from academia order by nome" ;
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);


        // Os blocos try, catch e finally são utilizados para o tratamento de exceções
        try{
            while(cursor.moveToNext()){
                // Criação da instancia de paciente utilizando informações
                // provenientes da base de dados
                PacienteBean paciente = new PacienteBean();

                // Construindo o objeto a partir dos registros da base de dados
                paciente.setId(cursor.getInt(0));
                paciente.setNome(cursor.getString(1));
                paciente.setCelular(cursor.getString(2));
                paciente.setEmail(cursor.getString(3));

                // Adicionando a instancia de paciente a lista de academias
                pacienteAcademias.add(paciente);
            }
        }catch(SQLException sqle){

        }finally{
            cursor.close();
        }

        // Ao final deve ser retornada a lista com todo os academias
        // existentes na base de dados
        return pacienteAcademias;
    }

}
