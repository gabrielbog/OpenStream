using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Server
{
    public class Messages
    {
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

        //upload messages
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
}
