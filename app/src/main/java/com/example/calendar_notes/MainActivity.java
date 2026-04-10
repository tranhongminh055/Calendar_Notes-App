package com.example.calendar_notes;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    // Khai bao cac bien
    ListView lv;
    ArrayList<String> arraywork;
    ArrayAdapter<String> arrAdapater;
    EditText edtwork,edth,edtm;
    TextView txtdate;
    Button btnwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edth = findViewById(R.id.edthour);
        edtm = findViewById(R.id.edtmi);
        edtwork = findViewById(R.id.edtwork);
        btnwork = findViewById(R.id.btnadd);
        lv = findViewById(R.id.ListView1);
        txtdate = findViewById(R.id.txtdate);

        // o day chung ta khong su dung du lieu co dinh, ma du lieu cua listview duoc cap nhat trong qua trinh chay ne o day
        // ta khai bao mang arraylist kieu string rong
        arraywork = new ArrayList<>();
        // khai bao ArrayAdapter, dua mang du lieu vao ArrayAdapter
        arrAdapater = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arraywork);
        // Dua Adapter co du lieu len ListView
        lv.setAdapter(arrAdapater);
        // lay ngay gio he thong
        Date currentDay = Calendar.getInstance().getTime();
        // Format theo dinh dang dd/mm/yyyy
        java.text.SimpleDateFormat simpleDateFormat = new
                java.text.SimpleDateFormat("dd/MM/yyyy");
        // Hien thi len TextView
        txtdate.setText("Hom nay: " + simpleDateFormat.format(currentDay));
        //viet phuong thuc khi click vao button btnwork
        btnwork.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            // neu 1 trong 3 edit text khong co noi dung thi hien len thong bao bang hop thoai dialog

            if (edtwork.getText().toString().isEmpty() || edth.getText().toString().isEmpty()
                    || edtm.getText().toString().isEmpty()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Info missing");
                builder.setMessage("Please enter all information of the work");
                builder.setPositiveButton("Continue", (dialog, which) -> {
                    // TODO Auto-generated method stub
                });
                builder.show();
            }
            // lay noi dung cong viec va thoi gian tu edit text va dua vao mang arraywork, cap nhat lai adapter
            else {
                String str = edtwork.getText().toString() + " - " +
                        edth.getText().toString() + ":" + edtm.getText().toString();
                // de them du lieu cho ListView, chung ta can 2 buoc:
                // cap them lieu cho mang
                arraywork.add(str); // xoa:arraywork.remove(i)
                // cap nhat lai Adapter
                arrAdapater.notifyDataSetChanged();
                edth.setText("");
                edtm.setText("");
                edtwork.setText("");
            }
        });
    }
}
