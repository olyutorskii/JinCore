/*
 * DOM Utilities
 *
 * License : The MIT License
 * Copyright(c) 2011 olyutorskii
 */

package jp.sourceforge.jindolf.corelib;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * DOM ユーティリティ。
 */
final class DomUtils{

    /**
     * 隠しコンストラクタ。
     */
    private DomUtils(){
        assert false;
        return;
    }


    /**
     * DOM要素のリストをXMLリソースからロードする。
     * @param builder DOMビルダ
     * @param url URL
     * @param childName 要素名
     * @return DOM要素のリスト
     * @throws IOException 入力エラー
     * @throws SAXException XMLパースエラー
     */
    static List<Element> loadElemList(
            DocumentBuilder builder,
            URL url,
            String childName )
            throws IOException, SAXException{
        Element root = loadElement(builder, url);
        List<Element> result = getChildElemList(root, childName);
        return result;
    }

    /**
     * root要素をXMLリソースからロードする。
     * @param builder DOMビルダ
     * @param url URL
     * @return root要素
     * @throws IOException 入力エラー
     * @throws SAXException XMLパースエラー
     */
    private static Element loadElement(DocumentBuilder builder, URL url)
            throws IOException, SAXException{
        InputStream istream = url.openStream();

        Document document;
        try{
            document = builder.parse(istream);
        }finally{
            istream.close();
        }

        Element root = document.getDocumentElement();

        return root;
    }

    /**
     * root要素から子要素のリストを得る。
     * @param root root要素
     * @param childName 子要素のタグ名
     * @return 子要素のリスト
     */
    private static List<Element> getChildElemList(
            Element root, String childName){
        NodeList nodeList = root.getElementsByTagName(childName);
        int childNum = nodeList.getLength();

        List<Element> result = new ArrayList<Element>(childNum);

        for(int index = 0; index < childNum; index++){
            Node node = nodeList.item(index);
            Element element = (Element) node;
            result.add(element);
        }

        return result;
    }

    /**
     * XMLタグの必須属性値を得る。
     * @param elem XML要素
     * @param attrName 属性名
     * @return 属性値
     * @throws SAXException 必須属性が無かった。
     */
    static String attrRequired(Element elem, String attrName)
            throws SAXException{
        Attr attr = elem.getAttributeNode(attrName);
        if(attr == null){
            throw new SAXException("no attribute[" + attrName + "]");
        }
        String result = attr.getValue();
        if(result == null){
            throw new SAXException("no value[" + attrName + "]");
        }
        return result;
    }

    /**
     * XMLタグの属性値を得る。
     * @param elem XML要素
     * @param attrName 属性名
     * @return 属性値。なければnull
     */
    static String attrValue(Element elem, String attrName){
        Attr attr = elem.getAttributeNode(attrName);
        if(attr == null) return null;

        String result = attr.getValue();

        return result;
    }

    /**
     * XML属性値からURIを展開する。
     * @param elem XML国定義要素
     * @param attrName 属性名
     * @return URI
     * @throws SAXException 属性が未定義もしくはURI形式を満たさない。
     */
    static URI attrToUri(Element elem, String attrName)
            throws SAXException{
        Attr attr = elem.getAttributeNode(attrName);
        if(attr == null) return null;

        String uriText = attr.getValue();
        if(uriText == null) return null;

        URI uri;
        try{
            uri = new URI(uriText).normalize();
        }catch(URISyntaxException e){
            throw new SAXException("illegal URI " + uriText, e);
        }

        return uri;
    }

}
