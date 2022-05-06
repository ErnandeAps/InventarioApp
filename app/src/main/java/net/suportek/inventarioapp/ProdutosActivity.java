package net.suportek.inventarioapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

public class ProdutosActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.mnu_cadastrar:
                    Intent it_Cad = new Intent(ProdutosActivity.this, CadProdutoActivity.class);
                    startActivity(it_Cad);
                    return true;
                case R.id.mnu_importar:
                    Intent it_Imp = new Intent(ProdutosActivity.this, Import_prodActivity.class);
                    startActivity(it_Imp);
                    return true;
                case R.id.mnu_exportar:
                    Intent it_Exp = new Intent(ProdutosActivity.this, Export_prodActivity.class);
                    startActivity(it_Exp);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produtos);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
