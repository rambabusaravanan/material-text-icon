package com.rambabusaravanan.texticon.example;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.rambabusaravanan.TextIcon;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Random random;
    private TextIcon[] icons = new TextIcon[8];
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        public void run() {
            int ind_icon = random.nextInt(Data.icons.length);
            int ind_view = random.nextInt(icons.length);
            int ind_colo = random.nextInt(Data.colors.length);
            icons[ind_view].setText(Data.icons[ind_icon]);
            icons[ind_view].setTextColor(Color.parseColor(Data.colors[ind_colo]));
            handler.postDelayed(runnable, 200);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.credit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl("http://rambabusaravanan.com");
            }
        });
        random = new Random();
        ArrayList<Model> dataSet = new ArrayList<>();
        for (int i = 0; i < Data.icons.length; i++) {
            dataSet.add(new Model(Data.icons[i], Data.names[i]));
        }

        // Recycler View
        final RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler.setLayoutManager(layoutManager);

        final IconAdapter adapter = new IconAdapter(this, dataSet);
        recycler.setAdapter(adapter);

        // Search and Filter
        EditText editText = (EditText) findViewById(R.id.search);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                adapter.getFilter().filter(editable.toString());
            }
        });

        // Randoms
        icons[0] = (TextIcon) findViewById(R.id.icon_1);
        icons[1] = (TextIcon) findViewById(R.id.icon_2);
        icons[2] = (TextIcon) findViewById(R.id.icon_3);
        icons[3] = (TextIcon) findViewById(R.id.icon_4);
        icons[4] = (TextIcon) findViewById(R.id.icon_5);
        icons[5] = (TextIcon) findViewById(R.id.icon_6);
        icons[6] = (TextIcon) findViewById(R.id.icon_7);
        icons[7] = (TextIcon) findViewById(R.id.icon_8);
        handler.postDelayed(runnable, 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_info) {
            openUrl("https://github.com/rambabusaravanan/material-text-icon");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openUrl(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }


}
