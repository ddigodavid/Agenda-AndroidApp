package br.com.webeleven.agenda.services;

import android.widget.RatingBar;
import android.widget.TextView;
import br.com.webeleven.agenda.Entities.Contact;
import br.com.webeleven.agenda.FormActivity;
import br.com.webeleven.agenda.R;

public class FormHelperService {

    private FormActivity activity;
    private Contact contact;

    public FormHelperService(FormActivity activity) {

        this.activity = activity;
    }

    public Contact getFormData() {
        contact
            .setName(((TextView) activity.findViewById(R.id.form_name)).getText().toString())
            .setAddress(((TextView) activity.findViewById(R.id.form_address)).getText().toString())
            .setPhone(((TextView) activity.findViewById(R.id.form_phone)).getText().toString())
            .setEmail(((TextView) activity.findViewById(R.id.form_email)).getText().toString())
            .setScore(((RatingBar) activity.findViewById(R.id.form_rating)).getRating());

        return contact;
    }

    public void fillForm(Contact contact) {
        ((TextView) activity.findViewById(R.id.form_name)).setText(contact.getName());
        ((TextView) activity.findViewById(R.id.form_address)).setText(contact.getAddress());
        ((TextView) activity.findViewById(R.id.form_phone)).setText(contact.getPhone());
        ((TextView) activity.findViewById(R.id.form_email)).setText(contact.getEmail());
        ((RatingBar) activity.findViewById(R.id.form_rating)).setRating(contact.getScore());

        this.contact = contact;
    }
}
