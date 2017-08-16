package br.com.webeleven.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import br.com.webeleven.agenda.DAOs.ContactDAO;
import br.com.webeleven.agenda.Entities.Contact;

import java.io.Serializable;
import java.util.List;

public class ContactsListActivity extends AppCompatActivity {

    private ListView contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        contactsList = (ListView) findViewById(R.id.ListNames);

        findViewById(R.id.list_add_contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent formIntent = new Intent(ContactsListActivity.this, FormActivity.class);
                startActivity(formIntent);
            }
        });

        registerForContextMenu(contactsList);
        contactsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Contact contact = (Contact) adapterView.getItemAtPosition(position);

                Intent formIntent = new Intent(ContactsListActivity.this, FormActivity.class);
                formIntent.putExtra("contact", contact);
                startActivity(formIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.refreshContactsList();
    }

    private void refreshContactsList() {
        ContactDAO contactDAO = new ContactDAO(this);
        List<Contact> contacts = contactDAO.getAllContacts();
        contactDAO.close();

        ArrayAdapter<Contact> contactArrayAdapter =
                new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, contacts);

        contactsList.setAdapter(contactArrayAdapter);
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        menu.add("Deletar").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

                Contact contact = (Contact) contactsList.getItemAtPosition(info.position);

                ContactDAO contactDAO = new ContactDAO(ContactsListActivity.this);
                contactDAO.delete(contact);
                contactDAO.close();


                refreshContactsList();

                Toast.makeText(ContactsListActivity.this, String.format("%s deletado.", contact.getName()), Toast.LENGTH_SHORT).show();

                return false;
            }
        });
    }
}
