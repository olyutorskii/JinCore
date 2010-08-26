/*
 * pre-defined avatar info
 *
 * Copyright(c) 2009 olyutorskii
 * $Id: PreDefAvatar.java 889 2009-11-04 06:44:30Z olyutorskii $
 */

package jp.sourceforge.jindolf.corelib;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * プリセット済みAvatarに関する情報。
 * F国運用の時点で20キャラクタ。
 */
public final class PreDefAvatar{

    /**
     * プリセット済みAvatar一覧リストを生成する。
     * @param builder DOMビルダ
     * @return プリセット済みAvatar一覧リスト
     * @throws IOException IOエラー
     * @throws SAXException パースエラー
     */
    public static List<PreDefAvatar> buildPreDefAvatarList(
            DocumentBuilder builder)
            throws IOException,
                   SAXException {
        Element list = loadAvatarList(builder);
        List<PreDefAvatar> result;
        result = elemToAvatarList(list);
        result = Collections.unmodifiableList(result);
        return result;
    }

    /**
     * プリセットAvatarに関する定義をロードする。
     * @see XmlResource#I_URL_AVATARDEF ロード対象となるXMLリソースのURL
     * @param builder DOMビルダ
     * @return Avatar定義情報のルート要素
     * @throws IOException IOエラー
     * @throws SAXException パースエラー
     */
    private static Element loadAvatarList(DocumentBuilder builder)
            throws IOException,
                   SAXException {
        InputStream istream = XmlResource.I_URL_AVATARDEF.openStream();
        Document document;
        try{
            document = builder.parse(istream);
        }finally{
            istream.close();
        }

        Element root = document.getDocumentElement();
        String tagName = root.getTagName();
        if( ! tagName.equals("preDefinedAvatarList") ){
            throw new SAXException("illegal root " + tagName);
        }

        return root;
    }

    /**
     * 要素内部を探索し、プリセットAvatarを登録する。
     * @param list ルート要素
     * @return プリセットAvatarが登録されたList
     * @throws SAXException パースエラー
     */
    private static List<PreDefAvatar> elemToAvatarList(Element list)
            throws SAXException {
        NodeList elems = list.getElementsByTagName("preDefinedAvatar");
        int avatarNum = elems.getLength();
        if(avatarNum <= 0){
            throw new SAXException("there is no <preDefinedAvatar>");
        }
        List<PreDefAvatar> avatarList =
                new ArrayList<PreDefAvatar>(avatarNum);

        for(int index = 0; index < avatarNum; index++){
            Node node = elems.item(index);
            Element elem = (Element) node;
            PreDefAvatar avatar = buildAvatar(elem);
            avatarList.add(avatar);
        }

        return avatarList;
    }

    /**
     * 個々のプリセットAvatar定義をオブジェクトに変換する。
     * @param avatarDef プリセットAvatar定義要素
     * @return プリセットAvatar定義オブジェクト
     * @throws SAXException パースエラー
     */
    private static PreDefAvatar buildAvatar(Element avatarDef)
            throws SAXException {
        String avatarId  = avatarDef.getAttribute("avatarId");
        String jobTitle  = avatarDef.getAttribute("jobTitle");
        String shortName = avatarDef.getAttribute("shortName");
        String serialNum = avatarDef.getAttribute("serialNum");

        String fullName = jobTitle + "\u0020" + shortName;

        int serialNo;
        try{
            serialNo = Integer.parseInt(serialNum);
        }catch(NumberFormatException e){
            throw new SAXException("illegal number form", e);
        }

        PreDefAvatar avatar = new PreDefAvatar(avatarId,
                                               fullName,
                                               jobTitle,
                                               shortName,
                                               serialNo );

        return avatar;
    }

    private final String avatarId;
    private final String fullName;
    private final String jobTitle;
    private final String shortName;
    private final int serialNo;

    /**
     * コンストラクタ。
     * @param avatarId Avatar識別子
     * @param fullName フルネーム
     * @param jobTitle 職業名
     * @param shortName 省略名
     * @param serialNo 通し番号
     */
    private PreDefAvatar(String avatarId,
                         String fullName,
                         String jobTitle,
                         String shortName,
                         int serialNo ){
        super();

        if(   avatarId  == null
           || fullName  == null
           || jobTitle  == null
           || shortName == null ){
            throw new NullPointerException();
        }

        if(   avatarId.length() <= 0
           || fullName.length() <= 0
           || serialNo < 0 ){
            throw new IllegalArgumentException();
        }

        this.avatarId  = avatarId.intern();
        this.fullName  = fullName.intern();
        this.jobTitle  = jobTitle.intern();
        this.shortName = shortName.intern();
        this.serialNo  = serialNo;

        return;
    }

    /**
     * Avatar識別子を返す。
     * @return Avatar識別子
     */
    public String getAvatarId(){
        return this.avatarId;
    }

    /**
     * フルネームを返す。
     * @return フルネーム
     */
    public String getFullName(){
        return this.fullName;
    }

    /**
     * 職業名を返す。
     * @return 職業名
     */
    public String getJobTitle(){
        return this.jobTitle;
    }

    /**
     * 省略名を返す。
     * @return 省略名
     */
    public String getShortName(){
        return this.shortName;
    }

    /**
     * 通し番号を返す。
     * @return 通し番号
     */
    public int getSerialNo(){
        return this.serialNo;
    }

}
