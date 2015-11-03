package br.unifor.android.np2.np2lista1.atividade;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import br.unifor.android.np2.np2lista1.R;
import br.unifor.android.np2.np2lista1.dao.entidade.PacienteBean;

public class DadosADD extends AppCompatActivity {

    private int selecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_add);
        Intent intent = getIntent();
        Bundle args = intent.getExtras();
        selecionado = args.getInt("selecionado");
        setSelecionado(selecionado);
        TextView txtNome = (TextView) findViewById(R.id.editTextNome);
        txtNome.setText(PacienteBean.paciente0.getNome());

    }

    public void setSelecionado(int selecionado) {
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
    }

    public void getSelecionado() {

        switch (selecionado){
            case 0:
                PacienteBean.paciente1 = PacienteBean.paciente0;
                break;
            case 1:
                PacienteBean.paciente2 = PacienteBean.paciente0;
                break;
            case 2:
                PacienteBean.paciente3 = PacienteBean.paciente0;
                break;
            case 3:
                PacienteBean.paciente4 = PacienteBean.paciente0;
                break;
            case 4:
                PacienteBean.paciente5 = PacienteBean.paciente0;
                break;
        }
    }

    public void CliqueConfirmarDados(View view){
        EditText txtEndereco = (EditText) findViewById(R.id.editTextEndereco);
        EditText txtCelular = (EditText) findViewById(R.id.editTextCelular);
        EditText txtEmail = (EditText) findViewById(R.id.editTextEmail);
        EditText txtTelefone = (EditText) findViewById(R.id.editTextTelefone);
        EditText txtParenteCelular = (EditText) findViewById(R.id.editTextParenteCelular);
        PacienteBean.paciente0.setEndereco(txtEndereco.getText().toString());
        PacienteBean.paciente0.setTelefone(txtTelefone.getText().toString());
        PacienteBean.paciente0.setCelular(txtCelular.getText().toString());
        PacienteBean.paciente0.setEmail(txtEmail.getText().toString());
        PacienteBean.paciente0.setParenteCelular(txtParenteCelular.getText().toString());

        getSelecionado();
        Intent intent = new Intent(DadosADD.this, ListaPacientes.class);
        startActivity(intent);
    }
}