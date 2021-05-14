package com.example.intentchatapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class User2 extends AppCompatActivity {


    public static  final String EXTRA_MASAGE_KEY ="";
    public static  final int MY_CODE = 1;
    public  static  final String MY_REPLAY = "";
    private static final int RESULT_LOAD_IMAGE = 1000;
    public static final int CONTACT_PERMISSION_CODE = 1010;
    public static final int CONTEXT_PICK_CODE = 2101;
    EditText myEditMsg2;
    ImageView avt;
    TextView tvMainName;
    LinearLayout myLinearLayout2;
    ScrollView myScrollview2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user2);
        avt = (ImageView) findViewById(R.id.imageView2);
        myEditMsg2 = (EditText) findViewById(R.id.EditMsg2);
        myLinearLayout2 = (LinearLayout) findViewById(R.id.linearLayout2);
        myScrollview2 = (ScrollView) findViewById(R.id.mainActScrollView2);
        tvMainName = (TextView) findViewById(R.id.tvMainName2);
        tvMainName.setText(MainActivity.m);
        Bitmap imgBitmap = MainActivity.imageBitmap;
        if (imgBitmap != null)
            avt.setImageBitmap(imgBitmap);

        Refresh();

    }

    private void Refresh() {
        myLinearLayout2.removeAllViews();
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
                myLinearLayout2.addView(this.DisplayReceivedMessage(User1.myMessageManage.sender.get(i)));
            else  myLinearLayout2.addView(this.DisplaySendedMessage(User1.myMessageManage.sender.get(i)));
        }
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myScrollview2.fullScroll(ScrollView.FOCUS_DOWN);
    }

    private void DisplayPictureReceiver(String s) {

        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lparams.gravity = Gravity.RIGHT;
        lparams.setMargins(200,0,80,30);
        lparams.height = 500;
        lparams.width = 500;
        ImageView imageView;
        imageView = new ImageView(this);
        imageView.setImageBitmap(BitmapFactory.decodeFile(s));
        imageView.setLayoutParams(lparams);
        myLinearLayout2.addView(imageView);
    }

    private void DisplayPictureSender(String s) {
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //lparams.gravity = Gravity.RIGHT;
        lparams.setMargins(50,0,200,30);
        lparams.height = 500;
        lparams.width = 500;
        ImageView imageView;
        imageView = new ImageView(this);
        imageView.setImageBitmap(BitmapFactory.decodeFile(s));
        imageView.setLayoutParams(lparams);
        myLinearLayout2.addView(imageView);
    }




    public void SwitchOnpressUser2(View view)
    {
        finish();
    }



    public void SendOnpressUser2(View view)
    {
        String temp = myEditMsg2.getText().toString();
        myEditMsg2.setText("");
        if(temp.isEmpty()) return;
        User1.myMessageManage.AddReceiver(temp);
        Refresh();
        myScrollview2.fullScroll(ScrollView.FOCUS_DOWN);

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

    public void OnclickEditext2(View view)
    {
        myScrollview2.fullScroll(ScrollView.FOCUS_DOWN);
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



    public void PictureMessageOnclick(View view)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!=
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, MainActivity.PERMISSION_REQUESS);
        }
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);
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
        if (requestCode== RESULT_LOAD_IMAGE)
        {
            if (resultCode == RESULT_OK){

                Uri selectedImage = data.getData();
                String[]  filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);
                cursor.close();
                User1.myMessageManage.AddReciverPicture(picturePath);
                Refresh();
                myScrollview2.fullScroll(ScrollView.FOCUS_DOWN);            }

        }
        if (requestCode == CONTEXT_PICK_CODE) {
            Cursor cursor, cursor1;
            Uri uri = data.getData();
            cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor.moveToFirst()) {
                String contactID = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                String contactThumnail = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.PHOTO_THUMBNAIL_URI));
                String idResult = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                int idResultHold = Integer.parseInt(idResult);
                if (idResultHold == 1) {
                    cursor1 = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactID, null, null);
                    while (cursor1.moveToNext()) {
                        String contactNumber = cursor1.getString(cursor1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        String mess =contactName + "\n" + contactNumber;
                        User1.myMessageManage.AddReceiver(mess);
                        Refresh();
                    }
                    cursor1.close();

                    cursor.close();
                }
            }
        }

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
    private boolean checkContactPermission()
    {
        boolean result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_DENIED;
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