package com.example.intentchatapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.util.LayoutDirection;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class User1 extends AppCompatActivity {
    public Context myContext;
    public static MyMessage myMessageManage = new MyMessage();
    public ImageView imageView;// = new ImageView();
    EditText myEditTextUsr1;
    LinearLayout myLinearLayoutUsr1;
    ScrollView myScrollViewUsr1;
    public static  final String EXTRA_MASAGE_KEY ="";
    public static  final int MY_CODE = 1;
    public static final int REQUEST_IMAGE_CAPTURE = 100000;
    public static final int REQUEST_CALL= 502;
    public static final int CONTACT_PERMISSION_CODE = 503;
    public static final int CONTEXT_PICK_CODE = 504;

    private static final int RESULT_LOAD_IMAGE = 14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user1);
        myEditTextUsr1 = (EditText) findViewById(R.id.EditMsg);
        myScrollViewUsr1 = (ScrollView) findViewById(R.id.mainActScrollView);
        myLinearLayoutUsr1 = (LinearLayout) findViewById(R.id.linearlayout);
        imageView = new ImageView(this);

    }
    public void Refresh()
    {
        myLinearLayoutUsr1.removeAllViews();
        int lenght = User1.myMessageManage.sender.size();
        for(int i = 0 ; i < lenght; i++)
        {
            if (User1.myMessageManage.isPicture.get(i))
            {
                if (User1.myMessageManage.isSender.get(i)) {
                    DisplayPictureSender(User1.myMessageManage.sender.get(i));
                }
                else
                {
                    DisplayPictureReceiver(User1.myMessageManage.sender.get(i));
                }

            } else
            if(User1.myMessageManage.isSender.get(i))
                myLinearLayoutUsr1.addView(this.DisplaySendedMessage(User1.myMessageManage.sender.get(i)));
            else  myLinearLayoutUsr1.addView(this.DisplayReceivedMessage(User1.myMessageManage.sender.get(i)));
            myScrollViewUsr1.fullScroll((ScrollView.FOCUS_DOWN));
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myScrollViewUsr1.fullScroll((ScrollView.FOCUS_DOWN));

    }

    private void DisplayPictureSender(String s) {
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lparams.gravity = Gravity.RIGHT;
        lparams.setMargins(200,0,80,30);
        lparams.height = 500;
        lparams.width = 500;
        ImageView imageView;
        imageView = new ImageView(this);
        imageView.setImageBitmap(BitmapFactory.decodeFile(s));
        imageView.setLayoutParams(lparams);
        myLinearLayoutUsr1.addView(imageView);
    }


    private void DisplayPictureReceiver(String s) {


        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //lparams.gravity = Gravity.RIGHT;
        lparams.setMargins(50,0,200,30);
        lparams.height = 500;
        lparams.width = 500;
        ImageView imageView;
        imageView = new ImageView(this);
        imageView.setImageBitmap(BitmapFactory.decodeFile(s));
        imageView.setLayoutParams(lparams);
        myLinearLayoutUsr1.addView(imageView);
    }

    private View DisplayReceivedMessage(String temp) {

        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lparams.setMargins(80,0,200,0);
        final TextView textView = new TextView(this);
        textView.setLayoutParams(lparams);
        textView.setBackgroundResource(R.drawable.nhancuoi9);
        textView.setText(temp);
        //textView.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        textView.setPadding(60,60,60,60);
        textView.setTextSize(16);
        textView.setTextColor(0xff000000);
        return textView;
    }
    private TextView DisplaySendedMessage(String temp) {
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lparams.gravity = Gravity.RIGHT;
        lparams.setMargins(200,0,80,0);
        final TextView textView = new TextView(this);
        textView.setLayoutParams(lparams);
        textView.setBackgroundResource(R.drawable.guicuoi9);
        textView.setPadding(60,60,60,60);
        textView.setText(temp);
        textView.setTextSize(16);
        textView.setTextColor(0xffffffff);
        return textView;

    }

    public void sendPhotoOnClicked(View view)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, MainActivity.PERMISSION_REQUESS);
        }
            Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, RESULT_LOAD_IMAGE);
    }

    public void SwitchOnpressUser1(View view)
    {

        Intent sent = new Intent(this, MainActivity.user2.getClass());

            startActivityForResult(sent,MY_CODE);
    }

    public void OnclickEditext2(View view)
    {
        myScrollViewUsr1.fullScroll(ScrollView.FOCUS_DOWN);
    }
    public void SendOnpressUser1(View view)
    {
            String temp = myEditTextUsr1.getText().toString();
            if(temp.isEmpty()) return;
            myMessageManage.AddSender(temp);
            Refresh();
            myEditTextUsr1.setText("");
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MainActivity.PERMISSION_REQUESS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show();
                    //finish();
                }
                break;
                case REQUEST_CALL:
                    for (int i = 0; i < permissions.length; i++) {
                        if (permissions[i].equals(Manifest.permission.CALL_PHONE)) {
                            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                                CallMeBaby();
                            }
                            //else Toast.makeText(this, "Permission denied...", Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
            case CONTACT_PERMISSION_CODE:
                for (int i = 0; i < permissions.length; i++) {
                    if (permissions[i].equals(Manifest.permission.READ_CONTACTS)) {
                        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                            pickContactIntent();
                        }
                        else Toast.makeText(this, "Permissinog denied...", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE) {
            if (resultCode == RESULT_OK) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();
                DisplayPictureSender(picturePath);
                myMessageManage.AddSenderPicture(picturePath);
                Refresh();
                myScrollViewUsr1.fullScroll(ScrollView.FOCUS_DOWN);

            }

        }
        else if (requestCode == CONTEXT_PICK_CODE) {
            if (resultCode == RESULT_OK)
            {Cursor cursor, cursor1;
            Uri uri = data.getData();
            cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor.moveToFirst()) {
                String contactID = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String contactThumnail = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI));
                String idResult = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                int idResultHold = Integer.parseInt(idResult);
                if (idResultHold == 1) {
                    cursor1 = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactID, null, null);
                    while (cursor1.moveToNext()) {
                        String contactNumber = cursor1.getString(cursor1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        String mess =contactName + "\n" + contactNumber;
                        myMessageManage.AddSender(mess);
                        Refresh();
                    }
                    cursor1.close();

                    cursor.close();
                }
            }}
        }
        else
            Refresh();
        myScrollViewUsr1.fullScroll(ScrollView.FOCUS_DOWN);
    }





    public void LocateOnclick(View view)
    {
        Uri gmmIntentUri = Uri.parse("geo:10.87,106.8");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);

    }

    public void ContactOnclick(View view)
    {
        if (checkContactPermission())
        {
            pickContactIntent();
        }
        else
        {
            requestContactPermission();
        }
    }


    public void CallOnclick(View view)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M  && ContextCompat.checkSelfPermission(User1.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(User1.this ,new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        }
        CallMeBaby();
    }
    private void CallMeBaby() {
        String dial = "tel:" + MainActivity.n;
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(dial));

        try {
                startActivity(intent);
                myMessageManage.AddSender("*** Main user made a call to "+ MainActivity.m);
                Refresh();
        }
        catch (ActivityNotFoundException e) {
            System.out.println(e);
        }
    }

    private boolean checkContactPermission()
    {
        boolean result = ContextCompat.checkSelfPermission(User1.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_DENIED;
        return result;
    }

    private void requestContactPermission()
    {
        String[] permission = {Manifest.permission.READ_CONTACTS};
        ActivityCompat.requestPermissions(this, permission, CONTACT_PERMISSION_CODE);
    }

    private void pickContactIntent()
    {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, CONTEXT_PICK_CODE);
    }







}