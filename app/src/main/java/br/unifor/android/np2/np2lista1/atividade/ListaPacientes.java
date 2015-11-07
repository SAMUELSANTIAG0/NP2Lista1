package br.unifor.android.np2.np2lista1.atividade;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.unifor.android.np2.np2lista1.R;
import br.unifor.android.np2.np2lista1.dao.PacienteDao;
import br.unifor.android.np2.np2lista1.dao.entidade.PacienteBean;

public class ListaPacientes extends AppCompatActivity {

    private ListView listViewPacientes;
    private PacienteBean pacienteSelecionado;
    private List<PacienteBean> registrosPaciente;
    private ArrayAdapter<PacienteBean> adaptadorLista;
    private int adptadorLayout = android.R.layout.simple_list_item_1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pacientes);

        listViewPacientes = (ListView) findViewById(R.id.listView);

        PacienteDao dao = new PacienteDao(ListaPacientes.this);
        registrosPaciente = dao.consultarRegistros();

        if (registrosPaciente.isEmpty()) {

            PacienteBean paciente1 = new PacienteBean("Andre de Lima", "Avenida Brasil, Fortaleza CE", "999999999", "33333333", "andre@gmail.com", "988888888");
            PacienteBean paciente2 = new PacienteBean("Maria Costa");
            PacienteBean paciente3 = new PacienteBean("Jose Pinheiro");
            PacienteBean paciente4 = new PacienteBean("Jessica Santos");
            PacienteBean paciente5 = new PacienteBean("Priscila Silva");

            dao.inserirRegistro(paciente1);
            dao.inserirRegistro(paciente2);
            dao.inserirRegistro(paciente3);
            dao.inserirRegistro(paciente4);
            dao.inserirRegistro(paciente5);

            registrosPaciente = dao.consultarRegistros();
        }

        adaptadorLista = new ArrayAdapter<PacienteBean>(this, adptadorLayout, registrosPaciente);

        listViewPacientes.setAdapter(adaptadorLista);
        registerForContextMenu(listViewPacientes);

        listViewPacientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(ListaPacientes.this, "Paciente: " + registrosPaciente.get(position).getNome(),
                        Toast.LENGTH_LONG).show();
            }
        });

        listViewPacientes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                pacienteSelecionado = (PacienteBean) adaptadorLista.getItem(position);
                return false;
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lista_pacientes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_contexto, menu);

        ListView lv = (ListView) v;
        AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final PacienteBean paciente = (PacienteBean) lv.getItemAtPosition(acmi.position);
        String nome = paciente.getNome();
        menu.setHeaderTitle(nome);

    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.itemRemover:
                removerPaciente();
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


    public void removerPaciente() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Deseja remover o paciente : " + pacienteSelecionado.getNome()
                + " ?");
        builder.setIcon(R.drawable.ic_menu_delete);
        builder.setPositiveButton("sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                PacienteDao pacienteDAO = new PacienteDao(ListaPacientes.this);
                pacienteDAO.removerRegistro(pacienteSelecionado);

                registrosPaciente.remove(pacienteSelecionado);
                registrosPaciente = pacienteDAO.consultarRegistros();
                pacienteSelecionado = null;
                adaptadorLista.notifyDataSetChanged();

            }
        });

        builder.setNegativeButton("Não", null);
        AlertDialog dialog = builder.create();
        dialog.setTitle("Confirmar operação");
        dialog.show();
    }

    public void enviarEmail(Intent intent) {
        if (pacienteSelecionado.getEmail() == null) {
            msgErro();
        } else {
            intent = new Intent(Intent.ACTION_SEND);
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{pacienteSelecionado.getEmail()});
            intent.putExtra(Intent.EXTRA_SUBJECT, pacienteSelecionado.getNome() + " - Diagnostico:");
            intent.putExtra(Intent.EXTRA_TEXT, " ");
            startActivity(intent);
        }
    }

    public void enviarSMS(Intent intent) {
        if (pacienteSelecionado.getParenteCelular() == null) {
            msgErro();
        } else {
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("sms: " + pacienteSelecionado.getParenteCelular()));
            intent.putExtra("sms_body", "Olá, o paciente " + pacienteSelecionado.getNome() + ", precisa de sua presança no Hospital.");
            startActivity(intent);
        }
    }

    public void ligarParente(Intent intent) {
        if (pacienteSelecionado.getParenteCelular() == null) {
            msgErro();
        } else {
            intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel: " + pacienteSelecionado.getParenteCelular()));
            startActivity(intent);
        }
    }

    private void informarDados(Intent intent) {
        intent = new Intent(ListaPacientes.this, DadosADD.class);
        intent.putExtra("pacienteSelecionado", (Parcelable) pacienteSelecionado);
        startActivity(intent);
    }

    private void altDados(Intent intent) {
        intent = new Intent(ListaPacientes.this, AltDados.class);
        intent.putExtra("pacienteSelecionado", (Parcelable) pacienteSelecionado);
        startActivity(intent);
    }

    public void msgErro() {
        Context contexto = getApplicationContext();
        String texto = "Ainda não cadastrado";
        int duracao = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(contexto, texto, duracao);
        toast.show();
    }
}