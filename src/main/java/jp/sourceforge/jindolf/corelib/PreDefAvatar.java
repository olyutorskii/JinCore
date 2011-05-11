/*
 * pre-defined avatar info
 *
 * License : The MIT License
 * Copyright(c) 2009 olyutorskii
 */

package jp.sourceforge.jindolf.corelib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * プリセット済みAvatarに関する情報。
 * F国運用の時点で20キャラクタ。
 */
public final class PreDefAvatar{

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
        List<Element> elemList = DomUtils.loadElemList(
                builder, XmlResource.I_URL_AVATARDEF, "preDefinedAvatar");

        List<PreDefAvatar> result =
                new ArrayList<PreDefAvatar>(elemList.size());

        for(Element elem : elemList){
            PreDefAvatar avatar = buildAvatar(elem);
            result.add(avatar);
        }

        result = Collections.unmodifiableList(result);

        return result;
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
