package com.taskapp.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.taskapp.App;
import com.taskapp.FormActivity;
import com.taskapp.Interfaces.OnItemClickListener;
import com.taskapp.R;
import com.taskapp.Task;
import com.taskapp.TaskAdapter;

import java.util.HashMap;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private List<Task> list;
    private int pos;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        initList();
        return root;
    }

    private void initList() {

        list = App.getInstance().getDatabase().taskDao().getAll();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TaskAdapter(list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                pos = position;
                Task task = list.get(position);
                Intent intent = new Intent(getContext(), FormActivity.class);
                intent.putExtra("task", task);
                startActivityForResult(intent, 101);
            }

            @Override
            public void onItemLongClick(int position) {
                final Task task = list.get(position);
                App.getInstance().getDatabase().taskDao().delete(task);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Подтвердите действие");
                builder.setMessage("Вы хотите удалить памятку?");

                builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        list.remove(task);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Нет", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public void sortList() {
        list.clear();
        list.addAll(App.getInstance().getDatabase().taskDao().getAllsorted());
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("TAG", "onActivityResult fragment");
        if (resultCode == Activity.RESULT_OK) {
            Task task = (Task) data.getSerializableExtra("task");
            if (requestCode == 100) {
                list.add(task);
            } else if (requestCode == 101) {
                list.set(pos, task);
            }
            adapter.notifyDataSetChanged();
        }
    }
}