package br.com.webeleven.agenda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import br.com.webeleven.agenda.services.SearchAddressService;

public class SearchAddressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_address);

        findViewById(R.id.search_address_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText addressKeyword = (EditText) findViewById(R.id.search_address_keyword);
                final TextView textViewResponse = (TextView) findViewById(R.id.search_address_info);
                SearchAddressService searchService = new SearchAddressService();

                searchService.getAddressInformation(
                        addressKeyword.getText().toString(),
                        SearchAddressActivity.this,
                        textViewResponse);
            }
        });
    }
}
