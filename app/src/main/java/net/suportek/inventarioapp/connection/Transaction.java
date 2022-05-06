package net.suportek.inventarioapp.connection;

import net.suportek.inventarioapp.domain.RequestData;

import androidbootstrap.example.br.exampleonscrolllistener.domain.RequestData;

/**
 * Created by viniciusthiengo on 2/1/15.
 */
public interface Transaction {
    public void doBefore();
    public void doAfter(String answer);
    public RequestData getRequestData();
}
