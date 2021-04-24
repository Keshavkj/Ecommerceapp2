

package com.android.vendorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class Add_product extends AppCompatActivity {
    FirebaseStorage storage;
    StorageReference storageReference;
ImageButton imgbtn1;
    ImageButton imgbtn2;
    ImageButton imgbtn3;
    EditText category;
    EditText p_name;
    EditText p_amount;
    EditText gst;
    EditText delivery_charge;
    EditText offer;
    Button upload;
    DatabaseReference databaseproduct;
    private Uri imgUri;
    Uri selectedImage1;
    Uri selectedImage2;
    Uri selectedImage3;

    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case 1234:
                if(resultCode == RESULT_OK){
                    Uri selectedImage1 = data.getData();
                    imgbtn1.setImageURI(selectedImage1);
                    Toast.makeText(this, "Please select Image less than 250*250 for better visibility", Toast.LENGTH_SHORT).show();

                   // String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    //Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                   // cursor.moveToFirst();

                  //  int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                  //  String filePath = cursor.getString(columnIndex);
                  //  cursor.close();

                  //  Bitmap yourSelectedImage = BitmapFactory.decodeFile(filePath);
                    StorageReference reference = storageReference.child("picture/" + UUID.randomUUID().toString());
                    reference.putFile(selectedImage1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        }
                    })  .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    }) .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                        }
                    });
                    //databaseproduct.child("pr").setValue(selectedImage1);
                    break;
                    //imgbtn2.setImageUri(yourSelectedImage);
                    /* Now you have choosen image in Bitmap format in object "yourSelectedImage". You can use it in way you want! */
                }

            case 1235:
                if(resultCode == RESULT_OK){
                    Uri selectedImage2 = data.getData();
                    imgbtn2.setImageURI(selectedImage2);
                    Toast.makeText(this, "Please select Image less than 250*250 for better visibility", Toast.LENGTH_SHORT).show();

                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage2, filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();


                    Bitmap yourSelectedImage = BitmapFactory.decodeFile(filePath);
                    break;
                    //imgbtn2.setImageUri(yourSelectedImage);
                    /* Now you have choosen image in Bitmap format in object "yourSelectedImage". You can use it in way you want! */
                }
            case 1236:
                if(resultCode == RESULT_OK){
                    Uri selectedImage3 = data.getData();
                    imgbtn3.setImageURI(selectedImage3);
                    Toast.makeText(this, "Please select Image less than 250*250 for better visibility", Toast.LENGTH_SHORT).show();

                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage3, filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();


                    Bitmap yourSelectedImage = BitmapFactory.decodeFile(filePath);
                    break;
                    //imgbtn2.setImageUri(yourSelectedImage);
                    /* Now you have choosen image in Bitmap format in object "yourSelectedImage". You can use it in way you want! */
                }
        }

    };


    protected void onCreate(Bundle savedInstanceState) {
        DatabaseReference databaseproduct = FirebaseDatabase.getInstance().getReference("");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        imgbtn2=findViewById(R.id.img_btn2);
        imgbtn1=findViewById(R.id.img_btn1);
        imgbtn3=findViewById(R.id.img_btn3);
        category=findViewById(R.id.et1);
        p_name=findViewById(R.id.et2);
        p_amount=findViewById(R.id.et3);
        gst=findViewById(R.id.et4);
        delivery_charge=findViewById(R.id.et5);
        offer=findViewById(R.id.et6);
        upload=findViewById(R.id.btn_upload);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        imgbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                final int ACTIVITY_SELECT_IMAGE = 1234;
                startActivityForResult(i, ACTIVITY_SELECT_IMAGE);
            }
        });
        imgbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                final int ACTIVITY_SELECT_IMAGE = 1235;
                startActivityForResult(i, ACTIVITY_SELECT_IMAGE);
            }
        });
        imgbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                final int ACTIVITY_SELECT_IMAGE = 1236;
                startActivityForResult(i, ACTIVITY_SELECT_IMAGE);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String p_name1=p_name.getText().toString();
                String  category1=category.getText().toString();
                String p_amount1=p_amount.getText().toString();
             String gst1=gst.getText().toString();
             String Del_charge=delivery_charge.getText().toString();
             String offer1=offer.getText().toString();
            // String imgbtn=imgbtn1.getIm

               if (!(TextUtils.isEmpty(category.getText().toString()))) {
                    //String id = databaseStudent.push().getKey();
                    //sname = Sname.getText().toString();
                    // sid = Sid.getText().toString();
                    // classname = classes.getSelectedItem().toString();
                    //spass = spassword.getText().toString();


                   // String p_name1=p_name.getText().toString();
                   // String  category1=category.getText().toString();
                    //String p_amount1=p_amount.getText().toString();,
                    //String gst1=gst.getText().toString();
                   // String Del_charge=delivery_charge.getText().toString();
                   // String offer1=offer.getText().toString();
                   StorageReference reference1= storageReference.child("picture/" + UUID.randomUUID().toString());

                   reference1.putFile(selectedImage1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                       @Override
                       public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                       }
                   })  .addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception e) {

                       }
                   }) .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                       @Override
                       public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                       }
                   });
                   StorageReference reference = storageReference.child("picture/" + UUID.randomUUID().toString());
                   reference.putFile(selectedImage1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                       @Override
                       public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                       }
                   })  .addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception e) {

                       }
                   }) .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                       @Override
                       public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                       }
                   });
                   Product Product =new Product( category1, p_name1,p_amount1,gst1,Del_charge,offer1,selectedImage1,selectedImage2,selectedImage3);

                    databaseproduct.child("pr").setValue(Product);
                    Toast.makeText(getApplicationContext(),"student added successfully", Toast.LENGTH_LONG).show();

                }else {
                    Toast.makeText(getApplicationContext(),"fields cannot be empty", Toast.LENGTH_LONG).show();
               }
            }
        });

    }


}
class Product {
    String p_name1;
    String  category1;
    String p_amount1;
    String gst1;
    String Del_charge;
    String offer1;
    Uri selectedImage1;
    Uri selectedImage2;
    Uri selectedImage3;

  /*  public Student(String sname, String sid){

    }*/

    public Product(String category1, String p_name1,String p_amount1,String gst1,String Del_charge,String offer1,Uri selectedimage1,Uri selectedimage2,Uri selectedimage3) {
        this.category1 = category1;
        this.p_name1 = p_name1;
        this.p_amount1 = p_amount1;
        this.gst1 = gst1;
        this.Del_charge = Del_charge;
        this.offer1 = offer1;
        this.selectedImage1=selectedimage1;
        this.selectedImage2=selectedimage2;
        this.selectedImage3=selectedimage3;
    }



    public String getCategory1() { return category1; }

    public String getP_name1() {
        return p_name1;
    }
    public String getP_amount1() {
        return p_amount1;
    }

    public String getgst1() { return gst1; }

    public String getDel_charge(){ return Del_charge;}

    public String getOffer1(){return offer1;}
    public Uri getSelectedImage1(){return selectedImage1;}
    public Uri getSelectedImage2(){return selectedImage2;}
    public Uri getSelectedImage3(){return selectedImage3;}


}

