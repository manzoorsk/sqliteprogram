package com.example.admin.sqliteprogram;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText,editText2,editText3,editText4;
    Button button,button2,button3,button4;
    DataBaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb=new DataBaseHelper(this);

        editText=(EditText)findViewById(R.id.editText);
        editText2=(EditText)findViewById(R.id.editText2);
        editText3=(EditText)findViewById(R.id.editText3);
        editText4=(EditText)findViewById(R.id.editText4);
        button=(Button)findViewById(R.id.button);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        button4=(Button)findViewById(R.id.button4);
        addData();
        viewData();
        UpdateData();
        DeleteData();
    }
    public void UpdateData(){
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdate = myDb.updateData(editText.getText().toString(),editText2.getText().toString(),
                        editText3.getText().toString(),editText4.getText().toString());
                if (isUpdate == true)
                    Toast.makeText(MainActivity.this,"Data Updated",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void addData(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               boolean isInserted = myDb.insertData(editText.getText().toString(),editText2.getText().toString(),editText3.getText().toString());

                if (isInserted = true)
                    Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
            }
        });
    }


    public void viewData(){
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=myDb.getAllData();
                if(res.getCount() ==0){

                    showMessage("Error","Nothing found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("ID :" + res.getString(0)+"\n");
                    buffer.append("Name :" + res.getString(1)+"\n");
                    buffer.append("Surname :" + res.getString(2)+"\n");
                    buffer.append("Marks :" + res.getString(3)+"\n\n");
                }
                showMessage("Data",buffer.toString());
            }
        });
    }


    public void showMessage(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }

    public void DeleteData(){
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Integer deletedRow=myDb.deleteData(editText4.getText().toString());
                if (deletedRow >0)
                    Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(MainActivity.this,"Data not Deleted",Toast.LENGTH_LONG).show();


            }
        });
    }

}
