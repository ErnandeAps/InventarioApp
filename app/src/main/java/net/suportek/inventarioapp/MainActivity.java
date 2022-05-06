package net.suportek.inventarioapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_inventario:
                    Intent it = new Intent(MainActivity.this, InventarioActivity.class);
                    startActivity(it);

                    return true;
                case R.id.nav_configuracao:
                    Intent it_C = new Intent(MainActivity.this, ConfigActivity.class);
                    startActivity(it_C);
                    return true;

                case R.id.nav_informacoes:
                    Intent it_Inf = new Intent(MainActivity.this, InformacoesActivity.class);
                    startActivity(it_Inf);
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

}
