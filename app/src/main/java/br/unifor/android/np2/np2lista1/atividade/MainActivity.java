package br.unifor.android.np2.np2lista1.atividade;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import br.unifor.android.np2.np2lista1.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void cliqueLista(View view){
        Intent intencao = new Intent(MainActivity.this,ListaPacientes.class);
        startActivity(intencao);
    }
}
