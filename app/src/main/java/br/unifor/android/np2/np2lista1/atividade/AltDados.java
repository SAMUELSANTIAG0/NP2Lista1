package br.unifor.android.np2.np2lista1.atividade;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import br.unifor.android.np2.np2lista1.R;
import br.unifor.android.np2.np2lista1.dao.PacienteDao;
import br.unifor.android.np2.np2lista1.dao.entidade.PacienteBean;

public class AltDados extends AppCompatActivity {

    private PacienteBean pacienteSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alt_dados);
        pacienteSelecionado = getIntent().getExtras().getParcelable("pacienteSelecionado");

        EditText txtNome = (EditText) findViewById(R.id.editTextNome);
        EditText txtEndereco = (EditText) findViewById(R.id.editTextEndereco);
        EditText txtCelular = (EditText) findViewById(R.id.editTextCelular);
        EditText txtEmail = (EditText) findViewById(R.id.editTextEmail);
        EditText txtTelefone = (EditText) findViewById(R.id.editTextTelefone);
        EditText txtParenteCelular = (EditText) findViewById(R.id.editTextParenteCelular);
        txtNome.setText(pacienteSelecionado.getNome());
        txtEndereco.setText(pacienteSelecionado.getEndereco());
        txtCelular.setText(pacienteSelecionado.getCelular());
        txtTelefone.setText(pacienteSelecionado.getTelefone());
        txtEmail.setText(pacienteSelecionado.getEmail());
        txtParenteCelular.setText(pacienteSelecionado.getParenteCelular());

    }

    public void CliqueConfirmarDados(View view){
        EditText txtNome = (EditText) findViewById(R.id.editTextNome);
        EditText txtEndereco = (EditText) findViewById(R.id.editTextEndereco);
        EditText txtCelular = (EditText) findViewById(R.id.editTextCelular);
        EditText txtEmail = (EditText) findViewById(R.id.editTextEmail);
        EditText txtTelefone = (EditText) findViewById(R.id.editTextTelefone);
        EditText txtParenteCelular = (EditText) findViewById(R.id.editTextParenteCelular);
        pacienteSelecionado.setNome(txtNome.getText().toString());
        pacienteSelecionado.setEndereco(txtEndereco.getText().toString());
        pacienteSelecionado.setTelefone(txtTelefone.getText().toString());
        pacienteSelecionado.setCelular(txtCelular.getText().toString());
        pacienteSelecionado.setEmail(txtEmail.getText().toString());
        pacienteSelecionado.setParenteCelular(txtParenteCelular.getText().toString());

        PacienteDao dao = new PacienteDao(AltDados.this);
        dao.editarRegistro(pacienteSelecionado);
        dao.close();

        Intent intent = new Intent(AltDados.this, ListaPacientes.class);
        startActivity(intent);
    }

}
