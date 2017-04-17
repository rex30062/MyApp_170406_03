package com.cclz.myapp_170406_03;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by YVTC on 2017/4/17.
 */

public class MyDataHandler extends DefaultHandler {
    boolean isTitle=false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        isTitle=true;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        isTitle=false;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if(isTitle){
            String str=new String(ch).substring(start, length);
            Log.d("TITLE", str);
        }
    }
}
