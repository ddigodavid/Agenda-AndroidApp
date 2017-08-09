package br.com.webeleven.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ContactsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        String[] names = {"Rodrigo", "Camila", "Wendel", "Felipe"};

        ArrayAdapter<String> namesArrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);

        ((ListView) findViewById(R.id.ListNames)).setAdapter(namesArrayAdapter);

        findViewById(R.id.list_add_contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent formIntent = new Intent(ContactsListActivity.this, FormActivity.class);
                startActivity(formIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.list_menu_search_address:
                Intent searchAddressIntent = new Intent(this, SearchAddressActivity.class);
                startActivity(searchAddressIntent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
