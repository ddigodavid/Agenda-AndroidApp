package br.com.webeleven.agenda;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;
import br.com.webeleven.agenda.DAOs.ContactDAO;
import br.com.webeleven.agenda.Entities.Contact;
import br.com.webeleven.agenda.services.FormHelperService;

public class FormActivity extends AppCompatActivity {

    private FormHelperService helperService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        helperService = new FormHelperService(this);

        Intent intent = getIntent();
        if (intent.hasExtra("contact")) {
            Contact contact = (Contact) intent.getSerializableExtra("contact");
            helperService.fillForm(contact);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_form, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.form_menu_save:
                Contact contact = this.helperService.getFormData();

                ContactDAO contactDAO = new ContactDAO(this);
                contactDAO.store(contact);
                contactDAO.close();

                Toast.makeText(FormActivity.this, String.format("Contato %s salvo!", contact.getName()), Toast.LENGTH_SHORT).show();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
