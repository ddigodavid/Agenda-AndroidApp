package br.com.webeleven.agenda.services;

import android.widget.RatingBar;
import android.widget.TextView;
import br.com.webeleven.agenda.Entities.Contact;
import br.com.webeleven.agenda.FormActivity;
import br.com.webeleven.agenda.R;

public class FormHelperService {

    public Contact getFormData(FormActivity activity) {
        Contact contact = new Contact();

        contact
            .setName(((TextView) activity.findViewById(R.id.form_name)).getText().toString())
            .setAddress(((TextView) activity.findViewById(R.id.form_address)).getText().toString())
            .setPhone(((TextView) activity.findViewById(R.id.form_phone)).getText().toString())
            .setEmail(((TextView) activity.findViewById(R.id.form_email)).getText().toString())
            .setScore((double) ((RatingBar) activity.findViewById(R.id.form_rating)).getProgress());

        return contact;
    }

}
