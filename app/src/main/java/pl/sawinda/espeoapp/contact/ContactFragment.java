package pl.sawinda.espeoapp.contact;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import pl.sawinda.espeoapp.R;

public class ContactFragment extends Fragment implements ContactContract.View{
    public ContactFragment() {
    }
    private ContactPresenter contactPresenter;
    private final int CALL_PERMISSION = 1337;
    private final int WRITE_CONTACTS_PERMISSION = 1338;
    ImageView ivPhoto;
    private LinearLayout lnPhone;
    private LinearLayout lnMail;
    private LinearLayout lnLinkedin;
    private FloatingActionButton fab;

    public static ContactFragment newInstance() {
        ContactFragment fragment = new ContactFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contactPresenter = new ContactPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        ivPhoto = (ImageView) view.findViewById(R.id.ivPhoto);
        Glide.with(getActivity()).load(R.drawable.my_photo).into(ivPhoto);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    int hasContactPermission = getActivity().checkSelfPermission(Manifest.permission.WRITE_CONTACTS);
                    if (hasContactPermission != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[] {Manifest.permission.WRITE_CONTACTS},
                                WRITE_CONTACTS_PERMISSION);
                        return;
                    } else {
                        contactPresenter.addContact();
                    }
                }
            }
        });

        lnPhone = (LinearLayout) view.findViewById(R.id.lnPhone);
        lnPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    int hasCallPermission = getActivity().checkSelfPermission(Manifest.permission.CALL_PHONE);
                    if (hasCallPermission != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[] {Manifest.permission.CALL_PHONE},
                                CALL_PERMISSION);
                        return;
                    } else {
                        contactPresenter.phoneCall();
                    }
                }
            }
        });

        lnMail = (LinearLayout) view.findViewById(R.id.lnMail);
        lnMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactPresenter.sendEmail();
            }
        });

        lnLinkedin = (LinearLayout) view.findViewById(R.id.lnLinkedin);
        lnLinkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactPresenter.openLinkedin();
            }
        });


        return view;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case CALL_PERMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    contactPresenter.phoneCall();
                } else {
                    Toast.makeText(getActivity(), "CALL_PERMISSION Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            case WRITE_CONTACTS_PERMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    contactPresenter.addContact();
                } else {
                    Toast.makeText(getActivity(), "WRITE_CONTACTS Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }




}
