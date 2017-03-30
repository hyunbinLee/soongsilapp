package com.example.hblee.map2;

import java.util.ArrayList;

class Noti_webview_parsing {
    String data;
    ArrayList<String> da=new ArrayList<String>();
    Noti_webview_parsing()
    {
    }
    public String parsing()
    {
        return data; }
    public String creHtml(String source)
    {
        StringBuffer sb= new StringBuffer("<HTML>");
        sb.append("<HEAD>");
        sb.append("<HEAD>");
        sb.append("<BODY style='margin:5;'>");
        sb.append(source);
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }
}
