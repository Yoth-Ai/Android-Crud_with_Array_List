package com.mardikh.android_crude_with_array_list;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
public class MainActivity extends AppCompatActivity {

    EditText etName;
    RadioGroup radioGender;
    Spinner spinnerRole;
    Button btnAddUpdate;
    ListView listView;

    ArrayList<User> userList = new ArrayList<>();
    UserAdapter adapter;

    String[] roles = {"Student", "Teacher", "Admin"};
    int selectedIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
        setupSpinner();
        setupListView();

        btnAddUpdate.setOnClickListener(v -> {
            String name = etName.getText().toString();
            int genderId = radioGender.getCheckedRadioButtonId();
            String gender = ((RadioButton)findViewById(genderId)).getText().toString();
            String role = spinnerRole.getSelectedItem().toString();

            if (selectedIndex == -1) {
                // Add
                userList.add(new User(name, gender, role));
            } else {
                // Update
                User u = userList.get(selectedIndex);
                u.setName(name);
                u.setGender(gender);
                u.setRole(role);
                selectedIndex = -1;
                btnAddUpdate.setText("Add User");
            }

            adapter.notifyDataSetChanged();
            clearForm();
        });
    }

    private void setupViews() {
        etName = findViewById(R.id.etName);
        radioGender = findViewById(R.id.radioGender);
        spinnerRole = findViewById(R.id.spinnerRole);
        btnAddUpdate = findViewById(R.id.btnAddUpdate);
        listView = findViewById(R.id.listView);
    }

    private void setupSpinner() {
        ArrayAdapter<String> roleAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roles);
        roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRole.setAdapter(roleAdapter);
    }

    private void setupListView() {
        adapter = new UserAdapter(this, userList, new UserAdapter.OnUserActionListener() {
            @Override
            public void onEdit(int position) {
                User u = userList.get(position);
                etName.setText(u.getName());
                if (u.getGender().equals("Male")) {
                    radioGender.check(R.id.rbMale);
                } else {
                    radioGender.check(R.id.rbFemale);
                }
                spinnerRole.setSelection(Arrays.asList(roles).indexOf(u.getRole()));
                selectedIndex = position;
                btnAddUpdate.setText("Update User");
            }

            @Override
            public void onDelete(int position) {
                userList.remove(position);
                adapter.notifyDataSetChanged();
            }
        });

        listView.setAdapter(adapter);
    }

    private void clearForm() {
        etName.setText("");
        radioGender.clearCheck();
        spinnerRole.setSelection(0);
    }
}

