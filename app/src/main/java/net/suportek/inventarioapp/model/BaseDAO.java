package net.suportek.inventarioapp.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//Classe responsável pela criação do Banco de Dados e tabelas
public class BaseDAO extends SQLiteOpenHelper {

    public static final String TBL_INVENTARIO = "tbProduto";
    public static final String INVENTARIO_ID = "_id";
    public static final String INVENTARIO_CODEAN = "codean";
    public static final String INVENTARIO_PRODUTO = "produto";
    public static final String INVENTARIO_QTD = "qtd";
    public static final String INVENTARIO_OBS = "obs";

    private static final String DATABASE_NAME = "Inventario.db";
    private static final int DATABASE_VERSION = 1;

    //Estrutura da tabela de produtos (sql statement)
    private static final String CREATE_TBPRODUTO = "create table " +
            TBL_INVENTARIO + "( " + INVENTARIO_ID       + " integer primary key autoincrement, " +
            INVENTARIO_CODEAN     + " text not null, " +
            INVENTARIO_PRODUTO + " text not null, " +
            INVENTARIO_QTD + " text not null, " +
            INVENTARIO_OBS + " text not null);";

    public BaseDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        //Criação da tabela
        database.execSQL(CREATE_TBPRODUTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Caso seja necessário mudar a estrutura da tabela
        //deverá primeiro excluir a tabela e depois recriá-la
        db.execSQL("DROP TABLE IF EXISTS " + TBL_INVENTARIO);
        onCreate(db);
    }
}
