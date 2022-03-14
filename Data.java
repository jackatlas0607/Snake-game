package com.Takagi.snake;

import javax.swing.*;
import java.net.URL;

//數據中心，存放外部數據
public class Data {

    //圖片若直接放在snake目錄下，則路徑  圖片.png
    //若在snake目錄下新增一個statics目錄存放圖片，則路徑為statics/圖片.png
    // .代表當前目錄  ，   ..代表上一層目錄
    /*
    text.html「表同一層目錄下的 text.html 檔案」。
    ./text.html「表同一層目錄下的 text.html 檔案 」。
    image/text.html「表示當前目錄下的image子目錄的text.html 檔案 」。
    ../index.html「表示上一層目錄下的 index.html 檔案 」。
    ../html40/cover.html「表示上一層目錄下 html40 子目錄的 index.html 檔案 」
     */

    //URL  定義圖片的地址
    public static URL headerURL = Data.class.getResource("statics/kakashi.png");
    public static URL upURL = Data.class.getResource("statics/up.png");
    public static URL downURL = Data.class.getResource("statics/down.png");
    public static URL leftURL = Data.class.getResource("statics/left.png");
    public static URL rightURL = Data.class.getResource("statics/right.png");
    public static URL bodyURL = Data.class.getResource("statics/body.png");
    public static URL foodURL = Data.class.getResource("statics/food.png");

    //ImageIcon 圖片
    public static ImageIcon header = new ImageIcon(headerURL);
    public static ImageIcon up = new ImageIcon(upURL);
    public static ImageIcon down = new ImageIcon(downURL);
    public static ImageIcon left = new ImageIcon(leftURL);
    public static ImageIcon right = new ImageIcon(rightURL);
    public static ImageIcon body = new ImageIcon(bodyURL);
    public static ImageIcon food = new ImageIcon(foodURL);



}
