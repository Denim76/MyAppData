package com.example.mydataapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    private TextView tvWelcome;
    private EditText etNim, etNama, etProdi, etKelas, etAlamat, etEmail;
    private Button btnTambah, btnLogout;
    private ListView lvData;
    private ArrayList<String> dataList;
    private ArrayAdapter<String> adapter;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Inisialisasi View
        tvWelcome = findViewById(R.id.tvWelcome);
        etNim = findViewById(R.id.etNim);
        etNama = findViewById(R.id.etNama);
        etProdi = findViewById(R.id.etProdi);
        etKelas = findViewById(R.id.etKelas);
        etAlamat = findViewById(R.id.etAlamat);
        etEmail = findViewById(R.id.etEmail);
        btnTambah = findViewById(R.id.btnTambah);
        btnLogout = findViewById(R.id.btnLogout);
        lvData = findViewById(R.id.lvData);

        // SharedPreferences
        sharedPreferences = getSharedPreferences("UserSession", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "User");
        tvWelcome.setText("Selamat Datang " + username);

        // List & Adapter
        dataList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        lvData.setAdapter(adapter);

        // Tombol Tambah Data
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nim = etNim.getText().toString();
                String nama = etNama.getText().toString();
                String prodi = etProdi.getText().toString();
                String kelas = etKelas.getText().toString();
                String alamat = etAlamat.getText().toString();
                String email = etEmail.getText().toString();

                if (nim.isEmpty() || nama.isEmpty() || prodi.isEmpty() || kelas.isEmpty() || alamat.isEmpty() || email.isEmpty()) {
                    Toast.makeText(DashboardActivity.this, "Harap isi semua data", Toast.LENGTH_SHORT).show();
                } else {
                    String hasil = "NIM: " + nim + "\n" +
                                   "Nama: " + nama + "\n" +
                                   "Prodi: " + prodi + "\n" +
                                   "Kelas: " + kelas + "\n" +
                                   "Alamat: " + alamat + "\n" +
                                   "Email: " + email;
                    
                    dataList.add(hasil);
                    adapter.notifyDataSetChanged();
                    clearFields();
                    Toast.makeText(DashboardActivity.this, "Data ditambahkan", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Tombol Logout
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void clearFields() {
        etNim.setText("");
        etNama.setText("");
        etProdi.setText("");
        etKelas.setText("");
        etAlamat.setText("");
        etEmail.setText("");
    }
}