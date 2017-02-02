package org.maxwu.jrefresh.HttpApi;

/**
 * Created by maxwu on 2/2/17.
 */
public class Constants {

    private Constants(){}
    public static String Freegeoip_Url  = "https://freegeoip.net/";
    public static String JSON           = "json";
    public static String XML            =  "xml";
    public static String Freegeoio_Url_Json = Freegeoip_Url + JSON + "/";
    public static String Freegeoio_Url_Xml = Freegeoip_Url + XML + "/";

    public static String Ipify_Url      = "https://api.ipify.org/";

    public static String HTTP_CONTENT_TYPE_JSON = "application/json";
    public static String HTTP_CONTENT_TYPE_TEXT = "text/plain";
}
