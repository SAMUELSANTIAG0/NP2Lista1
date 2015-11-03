package br.unifor.android.np2.np2lista1.atividade;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.unifor.android.np2.np2lista1.R;
import br.unifor.android.np2.np2lista1.dao.PacienteDao;
import br.unifor.android.np2.np2lista1.dao.entidade.PacienteBean;

public class ListaPacientes extends AppCompatActivity {

    private ListView listViewPacientes;
    private PacienteDao pacienteSelecionado;
    private List<PacienteBean> registrosPaciente;
    private ArrayAdapter<String> adptadorLista;
    private int adptadorLayout = android.R.layout.simple_list_item_1;
    private int selecionado;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pacientes);

        listViewPacientes = (ListView) findViewById(R.id.listView);
        registerForContextMenu(listViewPacientes);
        ArrayList<String> pacientes = preencherDados();
        adptadorLista = new ArrayAdapter<String>(this, adptadorLayout, pacientes);
        listViewPacientes.setAdapter(adptadorLista);

        listViewPacientes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                selecionado = position;
                return false;
            }
        });

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, view, menuInfo);
        getMenuInflater().inflate(R.menu.menu_contexto, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.itemRemover:
                listViewPacientes.removeViewAt(selecionado);
                break;

            case R.id.itemAlterar:
                altDados(intent);
                break;

            case R.id.itemLigar:
                ligarParente(intent);
                break;

            case R.id.itemSMS:
                enviarSMS(intent);
                break;

            case R.id.itemEmail:
                enviarEmail(intent);
                break;

            case R.id.itemDadosADD:
                informarDados(intent);
                break;

            default:
                break;
        }
        return super.onContextItemSelected(item);
    }


    public void enviarEmail(Intent intent){
        if (pacienteSelecionado(selecionado).getEmail() == null){
            msgErro();
        }else {
            intent = new Intent(Intent.ACTION_SEND);
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{pacienteSelecionado(selecionado).getEmail()});
            intent.putExtra(Intent.EXTRA_SUBJECT, pacienteSelecionado(selecionado).getNome() + " - Diagnostico:");
            intent.putExtra(Intent.EXTRA_TEXT, " ");
            startActivity(intent);
        }
    }

    public void enviarSMS(Intent intent){
        if (pacienteSelecionado(selecionado).getParenteCelular() == null){
            msgErro();
        }else {
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("sms: " + pacienteSelecionado(selecionado).getParenteCelular()));
            intent.putExtra("sms_body", "Olá, o paciente " + pacienteSelecionado(selecionado).getNome() + ", precisa de sua presança no Hospital.");
            startActivity(intent);
        }
    }

    public void ligarParente(Intent intent){
        if (pacienteSelecionado(selecionado).getParenteCelular() == null){
            msgErro();
        }else {
            intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel: " + pacienteSelecionado(selecionado).getParenteCelular()));
            startActivity(intent);
        }
    }

    private ArrayList<String> preencherDados() {
        PacienteBean.paciente1.setNome("Joao de Lima");
        PacienteBean.paciente1.addDados("Avenida Brasil, Fortaleza CE", "999999999", "33333333", "joao@gmail.com", "988888888");
        if(PacienteBean.paciente2 != null){
        PacienteBean.paciente2.setNome("Maria Costa");}
        if(PacienteBean.paciente3 != null){
        PacienteBean.paciente3.setNome("Jose Pinheiro");}
        if(PacienteBean.paciente4 != null){
        PacienteBean.paciente4.setNome("Jessica Santos");}
        if(PacienteBean.paciente5 != null){
        PacienteBean.paciente5.setNome("Priscila Silva");}
        ArrayList<String> dados = new ArrayList<>();
        if(PacienteBean.paciente1 != null){
        dados.add(PacienteBean.paciente1.getNome());
        }
        if(PacienteBean.paciente2 != null){
        dados.add(PacienteBean.paciente2.getNome());
        }
        if(PacienteBean.paciente3 != null){
        dados.add(PacienteBean.paciente3.getNome());
        }
        if(PacienteBean.paciente4 != null){
        dados.add(PacienteBean.paciente4.getNome());
        }
        if(PacienteBean.paciente5 != null){
        dados.add(PacienteBean.paciente5.getNome());
        }
        return dados;
    }

    public PacienteBean pacienteSelecionado(int selecionado){

        switch (selecionado){
            case 0:
                PacienteBean.paciente0 = PacienteBean.paciente1;
                break;
            case 1:
                PacienteBean.paciente0 = PacienteBean.paciente2;
                break;
            case 2:
                PacienteBean.paciente0 = PacienteBean.paciente3;
            break;
            case 3:
                PacienteBean.paciente0 = PacienteBean.paciente4;
            break;
            case 4:
                PacienteBean.paciente0 = PacienteBean.paciente5;
            break;
        }
            return PacienteBean.paciente0;
    }

    private void informarDados(Intent intent) {
        intent = new Intent(ListaPacientes.this, DadosADD.class);
        Bundle parametros = new Bundle();
        parametros.putInt("selecionado", selecionado);
        intent.putExtras(parametros);
        startActivity(intent);
    }

    private void altDados(Intent intent) {
        intent = new Intent(ListaPacientes.this, AltDados.class);
        Bundle parametros = new Bundle();
        parametros.putInt("selecionado", selecionado);
        intent.putExtras(parametros);
        startActivity(intent);
    }

    public void msgErro(){
        Context contexto = getApplicationContext();
        String texto = "Ainda não cadastrado";
        int duracao = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(contexto, texto,duracao);
        toast.show();
    }
}