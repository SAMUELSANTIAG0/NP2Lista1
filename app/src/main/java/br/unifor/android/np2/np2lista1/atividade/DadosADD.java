package br.unifor.android.np2.np2lista1.atividade;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import br.unifor.android.np2.np2lista1.R;
import br.unifor.android.np2.np2lista1.dao.PacienteDao;
import br.unifor.android.np2.np2lista1.dao.entidade.PacienteBean;

public class DadosADD extends AppCompatActivity {

//    private int selecionado;
    private PacienteBean pacienteSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_add);
        pacienteSelecionado = getIntent().getExtras().getParcelable("pacienteSelecionado");
        TextView txtNome = (TextView) findViewById(R.id.editTextNome);
        txtNome.setText(pacienteSelecionado.getNome());

    }


    public void CliqueConfirmarDados(View view){
        EditText txtEndereco = (EditText) findViewById(R.id.editTextEndereco);
        EditText txtCelular = (EditText) findViewById(R.id.editTextCelular);
        EditText txtEmail = (EditText) findViewById(R.id.editTextEmail);
        EditText txtTelefone = (EditText) findViewById(R.id.editTextTelefone);
        EditText txtParenteCelular = (EditText) findViewById(R.id.editTextParenteCelular);
        pacienteSelecionado.setEndereco(txtEndereco.getText().toString());
        pacienteSelecionado.setTelefone(txtTelefone.getText().toString());
        pacienteSelecionado.setCelular(txtCelular.getText().toString());
        pacienteSelecionado.setEmail(txtEmail.getText().toString());
        pacienteSelecionado.setParenteCelular(txtParenteCelular.getText().toString());

        PacienteDao dao = new PacienteDao(DadosADD.this);
        dao.editarRegistro(pacienteSelecionado);
        dao.close();

        Intent intent = new Intent(DadosADD.this, ListaPacientes.class);
        startActivity(intent);
    }
}
