/*
 * XML resources
 *
 * License : The MIT License
 * Copyright(c) 2009 olyutorskii
 */

package jp.sourceforge.jindolf.corelib;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * このパッケージに含まれるXML関連の各種リソース、およびWeb上の原本。
 * 相対URIのベースはこのクラスのコードベース。
 */
public final class XmlResource{

    /** 独自XMLスキーマ 外部URI名の前置詞。 {@value} */
    public static final String O_XSDBASE =
            "http://jindolf.sourceforge.jp/xml/xsd/";
    /** 独自XMLスキーマ 内部リソース名の前置詞。 {@value} */
    public static final String I_XSDBASE = "resources/xsd/";
    /** XML 内部リソース名の前置詞。 {@value} */
    public static final String I_XMLBASE = "resources/xml/";

    /** パブリックXMLスキーマ 外部URI名。 {@value} */
    public static final String O_RES_EXTXML =
            "http://www.w3.org/2001/xml.xsd";
    /** 基本型定義XMLスキーマ 外部URI名。 {@value} */
    public static final String O_RES_CORETYPE =
            O_XSDBASE + "coreType-090829.xsd";
    /** 文書定義XMLスキーマ 外部URI名。 {@value} */
    public static final String O_RES_COREXML =
            O_XSDBASE + "coreXML-090829.xsd";

    /** パブリックXMLスキーマ 内部リソース名。 {@value} */
    public static final String I_RES_EXTXML =
            I_XSDBASE + "ext/xml-2009-01.xsd";
    /** 基本型定義XMLスキーマ 内部リソース名。 {@value} */
    public static final String I_RES_CORETYPE =
            I_XSDBASE + "private/coreType-090829.xsd";
    /** 文書定義XMLスキーマ 内部リソース名。 {@value} */
    public static final String I_RES_COREXML =
            I_XSDBASE + "private/coreXML-090829.xsd";

    /** 国定義 内部リソース名。 {@value} */
    public static final String I_RES_LANDDEF =
            I_XMLBASE + "landDefList.xml";
    /** キャラ定義 内部リソース名。 {@value} */
    public static final String I_RES_AVATARDEF =
            I_XMLBASE + "preDefAvatarList.xml";

    /** パブリックXMLスキーマ 外部URI。 */
    public static final URI O_URI_EXTXML;
    /** 基本型定義XMLスキーマ 外部URI。 */
    public static final URI O_URI_CORETYPE;
    /** 文書定義XMLスキーマ 外部URI。 */
    public static final URI O_URI_COREXML;

    /** パブリックXMLスキーマ 内部リソースURI。 */
    public static final URI I_URI_EXTXML;
    /** 基本型定義XMLスキーマ 内部リソースURI。 */
    public static final URI I_URI_CORETYPE;
    /** 文書定義XMLスキーマ 内部リソースURI。 */
    public static final URI I_URI_COREXML;

    /** 国定義 内部リソースURI。 */
    public static final URI I_URI_LANDDEF;
    /** キャラ定義 内部リソースURI。 */
    public static final URI I_URI_AVATARDEF;


    /** 文書定義XMLスキーマ 内部リソースURL。 */
    public static final URL I_URL_COREXML;
    /** 国定義 内部リソースURL。 */
    public static final URL I_URL_LANDDEF;
    /** キャラ定義 内部リソースURL。 */
    public static final URL I_URL_AVATARDEF;


    /**
     * Web上の原本からリソースへの変換を記述したマップ。
     */
    public static final Map<URI, URI> RESOLVE_MAP;


    private static final Class<?> THISCLASS = XmlResource.class;


    static{
        new XmlResource().hashCode();

        O_URI_EXTXML   = loadOuter(O_RES_EXTXML);
        O_URI_CORETYPE = loadOuter(O_RES_CORETYPE);
        O_URI_COREXML  = loadOuter(O_RES_COREXML);

        try{
            I_URI_EXTXML    = loadInner(I_RES_EXTXML);
            I_URI_CORETYPE  = loadInner(I_RES_CORETYPE);
            I_URI_COREXML   = loadInner(I_RES_COREXML);
            I_URI_LANDDEF   = loadInner(I_RES_LANDDEF);
            I_URI_AVATARDEF = loadInner(I_RES_AVATARDEF);
        }catch(FileNotFoundException | URISyntaxException e){
            throw new ExceptionInInitializerError(e);
        }

        try{
            I_URL_COREXML   = I_URI_COREXML.toURL();
            I_URL_LANDDEF   = I_URI_LANDDEF.toURL();
            I_URL_AVATARDEF = I_URI_AVATARDEF.toURL();
        }catch(MalformedURLException e){
            throw new ExceptionInInitializerError(e);
        }

        Map<URI, URI> map = new HashMap<>();
        map.put(O_URI_EXTXML,   I_URI_EXTXML);
        map.put(O_URI_CORETYPE, I_URI_CORETYPE);
        map.put(O_URI_COREXML,  I_URI_COREXML);
        RESOLVE_MAP = Collections.unmodifiableMap(map);
    }


    /**
     * 隠れコンストラクタ。
     */
    private XmlResource(){
        super();
        assert this.getClass().equals(THISCLASS);
        return;
    }


    /**
     * 内部リソースのURIを得る。
     * @param res リソース名
     * @return URI
     * @throws FileNotFoundException リソースが見つからない。
     * @throws URISyntaxException URI書式が変。
     */
    private static URI loadInner(String res)
            throws FileNotFoundException,
                   URISyntaxException {
        URL url = THISCLASS.getResource(res);
        if(url == null) throw new FileNotFoundException(res);
        URI result = url.toURI().normalize();
        return result;
    }

    /**
     * 外部リソースのURIを得る。
     * @param http 外部リソース名
     * @return URI
     */
    private static URI loadOuter(String http){
        URI uri = URI.create(http).normalize();
        return uri;
    }

}
