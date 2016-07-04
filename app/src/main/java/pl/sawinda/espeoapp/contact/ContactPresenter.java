package pl.sawinda.espeoapp.contact;

import android.content.ContentProviderOperation;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.widget.Toast;

import java.util.ArrayList;

import pl.sawinda.espeoapp.R;

public class ContactPresenter implements ContactContract.Actions{
    private ContactFragment contactView;

    public ContactPresenter(ContactFragment contactView) {
        this.contactView = contactView;
    }

    @Override
    public void phoneCall(){
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:"+contactView.getActivity().getResources().getString(R.string.my_phone)));
        contactView.startActivity(intent);
    }

    @Override
    public void addContact(){
        ArrayList<ContentProviderOperation> ops = new ArrayList();
        ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI).withValue("account_type", null).withValue("account_name", null).build());
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/name").withValue("data1", contactView.getResources().getString(R.string.my_name)).build());

        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference("raw_contact_id", 0).withValue("mimetype", "vnd.android.cursor.item/phone_v2").withValue("data1", contactView.getResources().getString(R.string.my_phone)).withValue("data2", Integer.valueOf(2)).build());

        try {
            contactView.getActivity().getContentResolver().applyBatch("com.android.contacts", ops);
            Toast.makeText(contactView.getActivity(), "Contact has been added", Toast.LENGTH_SHORT)
                    .show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendEmail(){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto",contactView.getResources().getString(R.string.my_email), null));
        contactView.getActivity().startActivity(Intent.createChooser(emailIntent, contactView.getResources().getString(R.string.send_email)));
    }

    @Override
    public void openLinkedin(){
        String url = "http://www.linkedin.com/in/mateusz-sawinda";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        contactView.getActivity().startActivity(i);
    }

}
