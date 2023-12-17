package cn.edu.swu.zc.common;

public class PageTool {

public static String template = """
 <!DOCTYPE html>
 <html lang="en">
 <head>
     <meta charset="UTF-8">
     <title>网上书城</title>
 </head>
 <body>
 <br><br>
 <div style="float:right">
     <a href="./logout">退出系统</a>
                 
 </div>
 <center>
   <h1>哼哼啊啊啊啊啊啊啊啊啊</h1>
   %s
 </center>>
 </body>
 </html>
                """;
        public static String wrap(String body){
            return String.format(template, body);
        }
    }

