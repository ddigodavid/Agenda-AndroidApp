package br.com.webeleven.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ListaAlunosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        String[] names = {"Rodrigo", "Camila", "Wendel", "Felipe"};

        ArrayAdapter<String> namesArrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);

        ((ListView) findViewById(R.id.ListNames)).setAdapter(namesArrayAdapter);

        ((Button) findViewById(R.id.list_add_contact)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent formIntent = new Intent(ListaAlunosActivity.this, FormActivity.class);
                startActivity(formIntent);
            }
        });
    }
}
