/*
 * land information definition
 *
 * License : The MIT License
 * Copyright(c) 2009 olyutorskii
 */

package jp.sourceforge.jindolf.corelib;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TimeZone;
import java.util.TreeSet;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 * 人狼BBSの各国設定。
 */
public final class LandDef{

    /** 各種イメージの相対なベースURI。 */
    public static final String IMAGE_RELPATH = "./plugin_wolf/img/";

    /** 顔アイコンURIのテンプレート。 */
    public static final String DEF_FACE_URI_TMPL =
        IMAGE_RELPATH + "face{0,number,#00}.jpg";
    /** デカキャラURIのテンプレート。 */
    public static final String DEF_BODY_URI_TMPL =
        IMAGE_RELPATH + "body{0,number,#00}.jpg";

    /** 墓小アイコンのデフォルト相対URI。 */
    public static final URI DEF_TOMBFACE_URI =
            URI.create(IMAGE_RELPATH + "face99.jpg").normalize();
    /** 墓大アイコンのデフォルト相対URI。 */
    public static final URI DEF_TOMBBODY_URI =
            URI.create(IMAGE_RELPATH + "body99.jpg").normalize();

    private static final Map<String, LandState> STATE_MAP;

    /** space or tab. */
    private static final String REG_POSIXBLANK = "\\p{Blank}";

    private static final char HYPHEN_CH = '-';
    private static final String HYPHEN = "-";
    private static final String COMMA = ",";


    static{
        STATE_MAP = new HashMap<>();
        STATE_MAP.put("closed",     LandState.CLOSED);
        STATE_MAP.put("historical", LandState.HISTORICAL);
        STATE_MAP.put("active",     LandState.ACTIVE);
    }


    private String landName;
    private String landId;
    private String formalName;
    private String landPrefix;
    private LandState landState;
    private int minMembers;
    private int maxMembers;
    private URI webURI;
    private URI cgiURI;
    private URI tombFaceIconURI;
    private URI tombBodyIconURI;
    private String faceURITemplate;
    private String bodyURITemplate;
    private Locale locale;
    private Charset encoding;
    private TimeZone timeZone;
    private long startDateTime;
    private long endDateTime;
    private String description;
    private String contactInfo;
    private int[] invalidVid;


    /**
     * コンストラクタ。
     */
    private LandDef(){
        super();
        return;
    }


    /**
     * ハイフンで区切られた整数範囲をパースする。
     * 「1-3」なら1,2,3を結果に格納する。
     * @param intSet 格納先Set
     * @param seq パース対象
     * @throws IllegalArgumentException 形式が変
     */
    private static void parseIntPair(Set<Integer> intSet, CharSequence seq)
            throws IllegalArgumentException{
        String token = seq.toString();

        String[] ivalues = token.split(HYPHEN);
        assert ivalues.length >= 1;
        if(ivalues.length >= 3){
            throw new IllegalArgumentException(token);
        }

        int ivalStart;
        int ivalEnd;
        try{
            ivalStart = Integer.parseInt(ivalues[0]);
            if(ivalues.length >= 2) ivalEnd = Integer.parseInt(ivalues[1]);
            else                    ivalEnd = ivalStart;
        }catch(NumberFormatException e){
            throw new IllegalArgumentException(token, e);
        }

        if(ivalStart > ivalEnd){
            int dummy = ivalStart;
            ivalStart = ivalEnd;
            ivalEnd = dummy;
            assert ivalStart <= ivalEnd;
        }

        for(int ival = ivalStart; ival <= ivalEnd; ival++){
            intSet.add(ival);
        }

        return;
    }

    /**
     * コンマとハイフンで区切られた整数の羅列をパースする。
     * 「10,23-25」なら10,23,24,25を結果に返す。
     * @param seq パース対象文字列
     * @return ソートされたIntegerのList
     * @throws IllegalArgumentException 形式が変。
     */
    public static SortedSet<Integer> parseIntList(CharSequence seq)
            throws IllegalArgumentException{
        SortedSet<Integer> result = new TreeSet<>();

        if(seq.length() <= 0 ) return result;
        String str = seq.toString();
        str = str.replaceAll(REG_POSIXBLANK, "");

        String[] tokens = str.split(COMMA);
        assert tokens.length >= 1;
        for(String token : tokens){
            if(token.length() <= 0) continue;
            if(token.charAt(0) == HYPHEN_CH || token.endsWith(HYPHEN)){
                throw new IllegalArgumentException(token);
            }
            parseIntPair(result, token);
        }

        return result;
    }

    /**
     * 国設定のListを返す。
     * @param builder DOMビルダ
     * @return List 国設定リスト
     * @throws IOException IOエラー
     * @throws SAXException パースエラー
     */
    public static List<LandDef> buildLandDefList(DocumentBuilder builder)
            throws IOException,
                   SAXException{
        List<Element> elemList = DomUtils.loadElemList(
                builder, XmlResource.I_URL_LANDDEF, "landDef");

        List<LandDef> result = new ArrayList<>(elemList.size());

        for(Element elem : elemList){
            LandDef landDef = buildLandDef(elem);
            result.add(landDef);
        }

        result = Collections.unmodifiableList(result);

        return result;
    }

    /**
     * ハイフンをデリミタに持つロケール指定文字列からLocaleを生成する。
     * @param attrVal ロケール指定文字列
     * @return Locale
     */
    public static Locale buildLocale(CharSequence attrVal){
        String lang    = "";
        String country = "";
        String variant = "";

        String[] lcstr = attrVal.toString().split(HYPHEN, 3);
        if(lcstr.length >= 1) lang    = lcstr[0];
        if(lcstr.length >= 2) country = lcstr[1];
        if(lcstr.length >= 3) variant = lcstr[2];

        Locale locale = new Locale(lang, country, variant);

        return locale;
    }

    /**
     * XML属性を使って国定義の識別子情報を埋める。
     * @param result 国定義
     * @param elem 個別のXML国定義要素
     * @throws SAXException XML属性の記述に関する異常系
     */
    private static void fillIdInfo(LandDef result, Element elem)
            throws SAXException{
        String landName   = DomUtils.attrRequired(elem, "landName");
        String landId     = DomUtils.attrRequired(elem, "landId");
        String formalName = DomUtils.attrRequired(elem, "formalName");
        String landPrefix = DomUtils.attrRequired(elem, "landPrefix");

        if(    landName  .length() <= 0
            || landId    .length() <= 0
            || formalName.length() <= 0 ){
            throw new SAXException("no identification info");
        }

        result.landName   = landName;
        result.landId     = landId;
        result.formalName = formalName;
        result.landPrefix = landPrefix;

        return;
    }

    /**
     * XML属性を使って国定義の定員情報を埋める。
     * @param result 国定義
     * @param elem 個別のXML国定義要素
     * @throws SAXException XML属性の記述に関する異常系
     */
    private static void fillMemberInfo(LandDef result, Element elem)
            throws SAXException{
        String minStr = DomUtils.attrRequired(elem, "minMembers");
        String maxStr = DomUtils.attrRequired(elem, "maxMembers");

        int minMembers = Integer.parseInt(minStr);
        int maxMembers = Integer.parseInt(maxStr);

        if(    minMembers <= 0
            || minMembers > maxMembers ){
            throw new SAXException("invalid member limitation");
        }

        result.minMembers = minMembers;
        result.maxMembers = maxMembers;

        return;
    }

    /**
     * XML属性を使って国定義のURI情報を埋める。
     * @param result 国定義
     * @param elem 個別のXML国定義要素
     * @throws SAXException XML属性の記述に関する異常系
     */
    private static void fillUriInfo(LandDef result, Element elem)
            throws SAXException{
        URI webURI = DomUtils.attrToUri(elem, "webURI");
        URI cgiURI = DomUtils.attrToUri(elem, "cgiURI");
        if(webURI == null || cgiURI == null){
            throw new SAXException("no URI");
        }
        if(    ! webURI.isAbsolute()
            || ! cgiURI.isAbsolute() ){
            throw new SAXException("relative URI");
        }

        URI tombFaceIconURI = DomUtils.attrToUri(elem, "tombFaceIconURI");
        URI tombBodyIconURI = DomUtils.attrToUri(elem, "tombBodyIconURI");
        if(tombFaceIconURI == null) tombFaceIconURI = DEF_TOMBFACE_URI;
        if(tombBodyIconURI == null) tombBodyIconURI = DEF_TOMBBODY_URI;

        result.webURI          = webURI;
        result.cgiURI          = cgiURI;
        result.tombFaceIconURI = tombFaceIconURI;
        result.tombBodyIconURI = tombBodyIconURI;

        return;
    }

    /**
     * XML属性を使って国定義のURIテンプレート情報を埋める。
     * @param result 国定義
     * @param elem 個別のXML国定義要素
     * @throws SAXException XML属性の記述に関する異常系
     */
    private static void fillTemplateInfo(LandDef result, Element elem)
            throws SAXException{
        String faceURITemplate;
        String bodyURITemplate;

        faceURITemplate = DomUtils.attrValue(elem, "faceIconURITemplate");
        bodyURITemplate = DomUtils.attrValue(elem, "bodyIconURITemplate");

        if(faceURITemplate == null) faceURITemplate = DEF_FACE_URI_TMPL;
        if(bodyURITemplate == null) bodyURITemplate = DEF_BODY_URI_TMPL;

        result.faceURITemplate = faceURITemplate;
        result.bodyURITemplate = bodyURITemplate;

        return;
    }

    /**
     * XML属性を使って国定義の国際化情報を埋める。
     * @param result 国定義
     * @param elem 個別のXML国定義要素
     * @throws SAXException XML属性の記述に関する異常系
     */
    private static void fillI18NInfo(LandDef result, Element elem)
            throws SAXException{
        String localeText   = DomUtils.attrRequired(elem, "locale");
        String encodingText = DomUtils.attrRequired(elem, "encoding");
        String timeZoneText = DomUtils.attrRequired(elem, "timeZone");

        Locale locale = buildLocale(localeText);
        Charset encoding = Charset.forName(encodingText);
        TimeZone timeZone = TimeZone.getTimeZone(timeZoneText);

        result.locale   = locale;
        result.encoding = encoding;
        result.timeZone = timeZone;

        return;
    }

    /**
     * XML属性を使って国定義の日付情報を埋める。
     * @param result 国定義
     * @param elem 個別のXML国定義要素
     * @throws SAXException XML属性の記述に関する異常系
     */
    private static void fillDateInfo(LandDef result, Element elem)
            throws SAXException{
        long startDateTime;
        long endDateTime;

        String startDateText = DomUtils.attrRequired(elem, "startDate");
        String endDateText = elem.getAttribute("endDate");

        startDateTime = DateUtils.parseISO8601(startDateText);

        if(endDateText.length() > 0){
            endDateTime = DateUtils.parseISO8601(endDateText);
        }else{
            endDateTime = -1;
        }

        if(startDateTime < 0){
            throw new SAXException("illegal start date " + startDateText);
        }

        if(endDateTime >= 0 && startDateTime > endDateTime){
            throw new SAXException("start date is too old " + startDateText);
        }

        result.startDateTime = startDateTime;
        result.endDateTime   = endDateTime;

        return;
    }

    /**
     * XML属性を使って国定義の各種ステータス情報を埋める。
     * @param result 国定義
     * @param elem 個別のXML国定義要素
     * @throws SAXException XML属性の記述に関する異常系
     */
    private static void fillLandInfo(LandDef result, Element elem)
            throws SAXException{
        String state = DomUtils.attrRequired(elem, "landState");
        LandState landState = STATE_MAP.get(state);
        if(landState == null){
            throw new SAXException("illegal land status " + state);
        }

        String description = DomUtils.attrRequired(elem, "description");
        String contactInfo = DomUtils.attrRequired(elem, "contactInfo");

        String invalidVid = elem.getAttribute("invalidVid");
        SortedSet<Integer> invalidSet = parseIntList(invalidVid);
        int[] invalidArray = new int[invalidSet.size()];
        int pos = 0;
        for(int vid : invalidSet){
            invalidArray[pos++] = vid;
        }

        result.landState   = landState;
        result.description = description;
        result.contactInfo = contactInfo;
        result.invalidVid  = invalidArray;

        return;
    }

    /**
     * 個々の国設定をオブジェクトに変換する。
     * @param elem 国設定要素
     * @return 国設定オブジェクト
     * @throws SAXException パースエラー
     */
    private static LandDef buildLandDef(Element elem)
            throws SAXException{
        LandDef result = new LandDef();

        fillLandInfo    (result, elem);
        fillIdInfo      (result, elem);
        fillMemberInfo  (result, elem);
        fillUriInfo     (result, elem);
        fillTemplateInfo(result, elem);
        fillI18NInfo    (result, elem);
        fillDateInfo    (result, elem);

        return result;
    }


    /**
     * 国名を得る。
     * @return 国名
     */
    public String getLandName(){
        return this.landName;
    }

    /**
     * 国識別子を得る。
     * @return 識別子
     */
    public String getLandId(){
        return this.landId;
    }

    /**
     * 正式名称を得る。
     * @return 正式名称
     */
    public String getFormalName(){
        return this.formalName;
    }

    /**
     * 各村の前置文字。
     * F国なら「F」
     * @return 前置文字
     */
    public String getLandPrefix(){
        return this.landPrefix;
    }

    /**
     * 国の状態を得る。
     * @return 状態
     */
    public LandState getLandState(){
        return this.landState;
    }

    /**
     * 最小定員を得る。
     * @return 最小定員
     */
    public int getMinMembers(){
        return this.minMembers;
    }

    /**
     * 最大定員を得る。
     * @return 最大定員
     */
    public int getMaxMembers(){
        return this.maxMembers;
    }

    /**
     * Webアクセス用の入り口URIを得る。
     * @return 入り口URI
     */
    public URI getWebURI(){
        return this.webURI;
    }

    /**
     * クエリーを投げるCGIのURIを得る。
     * @return CGIのURI
     */
    public URI getCgiURI(){
        return this.cgiURI;
    }

    /**
     * 墓画像のURIを得る。
     * @return 墓URI
     */
    public URI getTombFaceIconURI(){
        return this.tombFaceIconURI;
    }

    /**
     * 大きな墓画像のURIを得る。
     * @return 墓URI
     */
    public URI getTombBodyIconURI(){
        return this.tombBodyIconURI;
    }

    /**
     * 顔アイコンURIのテンプレートを得る。
     * @return Formatter用テンプレート
     */
    public String getFaceURITemplate(){
        return this.faceURITemplate;
    }

    /**
     * 全身像アイコンURIのテンプレートを得る。
     * @return Formatter用テンプレート
     */
    public String getBodyURITemplate(){
        return this.bodyURITemplate;
    }

    /**
     * この国のロケールを得る。
     * @return ロケール
     */
    public Locale getLocale(){
        return this.locale;
    }

    /**
     * この国が使うエンコーディングを得る。
     * @return エンコーディング
     */
    public Charset getEncoding(){
        return this.encoding;
    }

    /**
     * この国の時刻表記で使うタイムゾーンのコピーを得る。
     * @return タイムゾーン
     */
    public TimeZone getTimeZone(){
        Object copy = this.timeZone.clone();
        TimeZone result = (TimeZone) copy;
        return result;
    }

    /**
     * この国の始まった時刻を得る。
     * @return 始まった時刻(エポックミリ秒)。
     */
    public long getStartDateTime(){
        return this.startDateTime;
    }

    /**
     * この国が発言を打ち切った時刻を得る。
     * @return 打ち切った時刻(エポックミリ秒)。まだ打ち切っていない場合は負。
     */
    public long getEndDateTime(){
        return this.endDateTime;
    }

    /**
     * この国の説明を得る。
     * @return 説明文字列
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * この国の連絡先を得る。
     * @return 連絡先文字列
     */
    public String getContactInfo(){
        return this.contactInfo;
    }

    /**
     * 有効な村IDか否か判定する。
     * @param vid 村ID
     * @return 無効な村ならfalse
     */
    public boolean isValidVillageId(int vid){
        int pos = Arrays.binarySearch(this.invalidVid, vid);
        if(pos >= 0) return false;
        return true;
    }

}
