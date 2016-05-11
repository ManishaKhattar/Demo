package com.codingblocks.lifecycle;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    ArrayList<String> dataList;
     ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Main Activity", "Created");
        listView = (ListView)findViewById(R.id.listView);
        dataList  = new ArrayList<String>();

        for(int i=0;i<5;i++)
            dataList.add("Names " + i);
         adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,
                dataList);
        listView.setAdapter(adapter);

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

             //  ArrayAdapter<String> adapter =(ArrayAdapter<String>) parent.getAdapter();
               String name = adapter.getItem(position);
               Toast.makeText(MainActivity.this, name, Toast.LENGTH_LONG).show();
           }
       });
       // listView.setOnL
      //  listView.setOnItemClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Main Activity", "Started");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Main Activity", "Resumed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Main Activity", "Paused");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Main Activity", "Stopped");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Main Activity", "Destroyed");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.add_item){
            dataList.add("New Name");
            adapter.notifyDataSetChanged();
        }else if(item.getItemId() == R.id.delete_item){

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Confirm");
            //builder.setMessage("Are you sure you want to delete the item?");

            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.dialog_layout, null);

            TextView dialogTextView = (TextView) view.findViewById(R.id.dialogtextView);
            dialogTextView.setText("Delete "+ dataList.get(dataList.size()-1)+" ? ");
            builder.setView(view);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dataList.remove(dataList.size() - 1);
                    adapter.notifyDataSetChanged();
                }
            });
            builder.create().show();
        }
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ArrayAdapter<String> adapter =(ArrayAdapter<String>) parent.getAdapter();
               String name = adapter.getItem(position);
               Toast.makeText(MainActivity.this, name, Toast.LENGTH_LONG).show();
    }
}
