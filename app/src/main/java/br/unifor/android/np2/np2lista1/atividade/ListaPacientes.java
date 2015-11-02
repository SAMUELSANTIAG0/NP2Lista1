package br.unifor.android.np2.np2lista1.atividade;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.unifor.android.np2.np2lista1.R;
import br.unifor.android.np2.np2lista1.dao.PacienteDao;
import br.unifor.android.np2.np2lista1.dao.entidade.PacienteBean;

public class ListaPacientes extends AppCompatActivity {

    private ListView listViewPacientes;
    private PacienteDao pacienteSelecionado;
    private List<PacienteBean> registrosPaciente;
    private ArrayAdapter<PacienteBean> adptadorLista;
    private int adptadorLayout = android.R.layout.simple_list_item_1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pacientes);

        listViewPacientes = (ListView) findViewById(R.id.listView);
        registerForContextMenu(listViewPacientes);
        PacienteDao dao = new PacienteDao(ListaPacientes.this);
        registrosPaciente = dao.consultarRegistros();

        //dando erro
        adptadorLista = new ArrayAdapter<PacienteBean>(this, adptadorLayout, registrosPaciente);
        listViewPacientes.setAdapter(adptadorLista);


    }
}