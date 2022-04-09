package com.gabrielbog.openstream.threads;

public final class Constants {
    //server connection
    public static int PORT = 8000;
    public static String TAG = "OpenStream";
    public static String IP = "192.168.0.103";

    public static boolean DEBUG = true;

    //login messages
    public static String sLoginReq = "login";
    public static String sLoginOK = "login-ok";
    public static String sLoginInv = "login-inv";

    //register messages
    public static String sRegisterReq = "regis";
    public static String sRegisterOK = "regis-ok";
    public static String sRegisterInv = "regis-inv";

    //download messages
    public static String sDownloadReq = "downl";
    public static String sDownloadOK = "downl-ok";
    public static String sDownloadInv = "downl-inv";

    //upload
    public static String sUploadReq = "uplod";
    public static String sUploadOK = "uplod-ok";
    public static String sUploadInv = "uplod-inv";

    //folder content messages
    public static String sFolderReq = "foldr";
    public static String sFolderOK = "foldr-ok";
    public static String sFolderInv = "foldr-inv";

    //generic
    public static String sRequest = "request";

    //generic error
    public static String sError = "error";
}
