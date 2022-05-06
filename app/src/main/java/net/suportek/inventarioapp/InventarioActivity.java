package net.suportek.inventarioapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

public class InventarioActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.mnu_inventariar:
                    Intent it_I = new Intent(InventariarActivity.this, InventariarActivity.class);
                    startActivity(it_I);
                    return true;

                case R.id.mnu_produto:
                    Intent it_P = new Intent(ProdutosActivity .this, ProdutosActivity.class);
                    startActivity(it_P);
                    return true;
                case R.id.mnu_dispositivo:
                    Intent it_D = new Intent(DispositivosActivity .this, DispositivosActivity.class);
                    startActivity(it_D);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
