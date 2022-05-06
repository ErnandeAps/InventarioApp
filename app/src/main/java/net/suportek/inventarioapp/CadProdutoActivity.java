package net.suportek.inventarioapp;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import net.suportek.inventarioapp.model.BaseDAO;


public class CadProdutoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private EditText edtCodEan, edtProduto, edtQtd,edtObs;
    ListView listView;
    BaseDAO helper;
    private ListView mListView;
    private SQLiteDatabase database;
    private CursorAdapter dataSource;
    Cursor  mCursor;
    //Atenção: é necessário inserir o PK (chave primária _id) como último campo
    private static final String campos[] = {"codean", "produto", "qtd","obs", "_id"};

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.mnu_salvar    :

                    SalvarRegistro();
                    return true;

                case R.id.mnu_excluir :
                    Excluirregistro();
                    return true;

                case R.id.mnu_zerar  :
                    ZerarTabela();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadprodutos );
        Window w = getWindow();
        w.setTitle("Cadastro de Produtos");
        BottomNavigationView navView = findViewById( R.id.cad_view  );
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        listView = (ListView) findViewById(R.id.listProdutos);
        listView.setOnItemClickListener(this);

        //cria instância da classe BaseDAO, responsável pela criação do Banco de Dados
        helper = new BaseDAO(this);
        //executa rotinas internas para abrir/utilizar o banco de dados
        database = helper.getWritableDatabase();

        CarregarDados();
    }

    public void CarregarDados(){
        //executa consulta geral de todos os registros cadastrados no banco de dados
        Cursor contatos = database.query("tbProduto", campos, null, null, null, null, null);

        if (contatos.getCount() > 0){
            //cria cursor que será exibido na tela, nele serão exibidos
            //todos os contatos cadastrados
            dataSource = new SimpleCursorAdapter(this, R.layout.row, contatos,
                    campos, new int[] { R.id.CodEan , R.id.Produto , R.id.Qtd });

            //relaciona o dataSource ao próprio listview
            listView.setAdapter(dataSource);
        }else{
            Toast.makeText(this, "Nenhum registro encontrado", Toast.LENGTH_SHORT).show();
        }
    }

    public void SalvarRegistro(){
        //Inserir_dados();
        edtCodEan = (EditText)findViewById(R.id.edtCodEan);
        edtProduto  = (EditText)findViewById(R.id.edtProduto );
        edtQtd  = (EditText)findViewById(R.id.edtQtd);
        edtObs = (EditText)findViewById(R.id.edtObs);

        EditText strCodEan = (EditText) findViewById(R.id.edtCodEan );
        String textoCodEan = strCodEan.getText().toString();
        EditText strProduto = (EditText) findViewById(R.id.edtProduto );
        String textoProduto = strProduto.getText().toString();
        EditText strQtd = (EditText) findViewById(R.id.edtQtd );
        String textoQtd = strQtd.getText().toString();
        EditText strObs = (EditText) findViewById(R.id.edtObs );
        String textoObs = strObs.getText().toString();

        if(textoCodEan == null ||textoCodEan.isEmpty()){
            Toast.makeText(this, "Obrigatório o preenchimento dos campos.", Toast.LENGTH_SHORT).show();
        }
        else {

            //mCursor = database.rawQuery("SELECT COUNT(_id) as quantidade FROM tbProduto where codean="+textoCodEan.toString()  , null);
            mCursor = database.rawQuery("SELECT * FROM tbProduto where codean="+textoCodEan.toString()  , null);
            mCursor.moveToLast() ;
            mCursor.moveToFirst() ;

            Integer  quantidade = mCursor.getCount() ;

            if (quantidade > 0) {
                //Atualiza dados no banco de dados
                database.execSQL( "UPDATE tbProduto SET produto ='" + textoProduto + "', qtd ='" + textoQtd + "', obs ='" + textoObs + "' where codean=" + textoCodEan );
                Toast.makeText( this, "Registros Atualizados com sucesso", Toast.LENGTH_SHORT ).show();

            } else {

                //insere dados no banco de dados
                database.execSQL( "INSERT INTO tbProduto (codean, produto, qtd, obs) VALUES " +
                        "('" + textoCodEan + "', '" + textoProduto + "', '" + textoQtd + "','" + textoObs + "')" );
                Toast.makeText( this, "Registros inseridos com sucesso", Toast.LENGTH_SHORT ).show();
            };
        };

        edtCodEan.setText("");
        edtProduto.setText("");
        edtQtd.setText("");
        edtObs.setText("");
        edtCodEan.requestFocus();
        CarregarDados();
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        database.execSQL("Select * from tbProduto where _id="+ position );

        edtCodEan = (EditText) findViewById(R.id.edtCodEan );
        edtProduto = (EditText) findViewById(R.id.edtProduto );
        edtQtd = (EditText) findViewById(R.id.edtQtd );
        edtObs  = (EditText) findViewById(R.id.edtObs );
        edtCodEan.setText(dataSource.getCursor().getString(0) .toString());
        edtProduto.setText(dataSource.getCursor().getString(1) .toString());
        edtQtd.setText(dataSource.getCursor().getString(2) .toString());
        edtObs.setText(dataSource.getCursor().getString(3) .toString());
        edtCodEan.setEnabled(false);

    }

    public void Excluirregistro(){
        EditText strCodEan = (EditText) findViewById(R.id.edtCodEan );
        String textoCodEan = strCodEan.getText().toString();
        if(textoCodEan == null ||textoCodEan.isEmpty()){
            Toast.makeText(this, "Obrigatório selecionar um produto.", Toast.LENGTH_SHORT).show();
        }
        else {

            database.execSQL( "DELETE FROM tbProduto where codean=" + textoCodEan);
            edtCodEan.setText("");
            edtProduto.setText("");
            edtQtd.setText("");
            edtObs.setText("");
            edtCodEan.requestFocus();
            Toast.makeText( this, "Registros excluido com sucesso", Toast.LENGTH_SHORT ).show();
        }
        CarregarDados();
    }

    public void ZerarTabela(){
        database.execSQL( "DELETE FROM tbProduto");
        edtCodEan.setText("");
        edtProduto.setText("");
        edtQtd.setText("");
        edtObs.setText("");
        edtCodEan.requestFocus();
        Toast.makeText( this, "Registros excluidos com sucesso", Toast.LENGTH_SHORT ).show();
        CarregarDados();
    }
}
