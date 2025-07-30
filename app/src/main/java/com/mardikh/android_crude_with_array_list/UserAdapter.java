package com.mardikh.android_crude_with_array_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<User> userList;
    private LayoutInflater inflater;
    private OnUserActionListener listener;

    public interface OnUserActionListener {
        void onEdit(int position);
        void onDelete(int position);
    }

    public UserAdapter(Context context, ArrayList<User> userList, OnUserActionListener listener) {
        this.context = context;
        this.userList = userList;
        this.listener = listener;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() { return userList.size(); }

    @Override
    public Object getItem(int i) { return userList.get(i); }

    @Override
    public long getItemId(int i) { return i; }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.list_item_user, null);

        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvGender = view.findViewById(R.id.tvGender);
        TextView tvRole = view.findViewById(R.id.tvRole);
        Button btnEdit = view.findViewById(R.id.btnEdit);
        Button btnDelete = view.findViewById(R.id.btnDelete);

        User user = userList.get(i);

        tvName.setText("Name: " + user.getName());
        tvGender.setText("Gender: " + user.getGender());
        tvRole.setText("Role: " + user.getRole());

        btnEdit.setOnClickListener(v -> listener.onEdit(i));
        btnDelete.setOnClickListener(v -> listener.onDelete(i));

        return view;
    }
}

