package org.example.tasks.interfaceChallenge;

public interface Browser {
    static final String ENGINE = "WebKit";
     default String getEngine(){
         return Browser.ENGINE;
     }
     void gotoPage(String url);
     void refreshPage();
     void bookmarkPage();
}
