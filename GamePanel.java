package com.Takagi.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

//遊戲的面板
public class GamePanel extends JPanel implements KeyListener, ActionListener {

    int length;  //蛇的長度
    int[] snakeX = new int[600];  //蛇的座標X
    int[] snakeY = new int[500];  //蛇的座標Y
    String fx = "R";  // R:右  L:左  U:上  D:下
    boolean isStart = false;  //遊戲是否開始，默認為停止

    //delay 100毫秒刷新一次，即一秒10fps  1000毫秒刷新一次，即一秒1fps
    //listener 監聽對象this，即當前的窗口
    Timer timer = new Timer(100, this);

    //1.定義一個食物
    int foodX;
    int foodY;
    Random random = new Random();

    //死亡判斷
    boolean isFail = false;

    //積分系統
    int score;


    //構造器
    public GamePanel() {
        init();

        //獲取鍵盤的監聽事件
        this.setFocusable(true);   //讓鍵盤焦點聚集在遊戲上，不設置的話，鍵盤的指令傳不到遊戲
        this.addKeyListener(this);  //因為已經實施KeyListener，所以目前的GamePanel類是一個KeyListener實現類
    }

    //初始化
    public void init() {
        length = 3;
        snakeX[0] = 100; snakeY[0] = 100;  //頭部座標
        snakeX[1] = 75;  snakeY[1] = 100;  //第一節身體座標
        snakeX[2] = 50;  snakeY[2] = 100;  //第二節身體座標

        timer.start();

        foodX = 25 + 25* random.nextInt(34);  //產生的隨機數[0,34)
        foodY = 75 + 25* random.nextInt(24);

        score = 0;

    }


    //繪製面板，遊戲中的所有東西都使用這支畫筆來畫
    //畫筆會默認調用，即使不調該方法也會被默認調用
    //Graphics  畫筆
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  //清屏

        //繪製靜態的面板
        this.setBackground(Color.WHITE);
        Data.header.paintIcon(this, g, 25, 11);  //繪製header
        g.fillRect(25, 75, 850, 600);  //繪製遊戲區域

        //劃一條靜態的小蛇
        if (fx.equals("R")) {
            Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (fx.equals("L")) {
            Data.left.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (fx.equals("U")) {
            Data.up.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (fx.equals("D")) {
            Data.down.paintIcon(this, g, snakeX[0], snakeY[0]);
        }

        for (int i = 1; i < length; i++) {
            Data.body.paintIcon(this, g, snakeX[i], snakeY[i]);  //蛇的身體長度透過length來控制
        }

        //畫積分
        g.setColor(Color.BLACK);
        g.setFont(new Font("標楷體", Font.BOLD, 18));
        g.drawString("長度: "+length,750,35);
        g.drawString("分數: "+score,750,50);


        //畫食物
        Data.food.paintIcon(this,g,foodX,foodY);

        //遊戲提示: 是否開始
        if (isStart == false) {
            g.setColor(Color.WHITE);  //設置畫筆的顏色
            g.setFont(new Font("標楷體", Font.BOLD, 40));  //設置字體
            g.drawString("按下空格開始遊戲", 300, 300);
        }

        //失敗提醒
        if(isFail){
            g.setColor(Color.RED);  //設置畫筆的顏色
            g.setFont(new Font("標楷體", Font.BOLD, 40));  //設置字體
            g.drawString("遊戲結束，按下空格重新開始", 200, 300);
        }

    }

    //接收鍵盤的輸入:監聽
    //實現keyPressed的對象默認調用
    @Override
    public void keyPressed(KeyEvent e) {
        //獲取按下的鍵盤是哪個鍵
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_SPACE) {    //如果按下的是空格鍵
            if (isFail){  //失敗，遊戲再來一遍
                isFail=false;
                init();  //重新初始化遊戲
            }else{  //暫停遊戲
                isStart = !isStart;
            }
            this.repaint();  //刷新介面
        }

        //鍵盤控制走向
        if (keyCode==KeyEvent.VK_LEFT){
            fx="L";
        }else if (keyCode==KeyEvent.VK_RIGHT){
            fx="R";
        }else if (keyCode==KeyEvent.VK_UP) {
            fx = "U";
        }else if (keyCode==KeyEvent.VK_DOWN) {
            fx = "D";
        }
    }


    //定時器，監聽時間  fps  執行定時操作
    @Override
    public void actionPerformed(ActionEvent e) {
        //如果遊戲處於開始狀態，並且遊戲沒有結束
        if (isStart && isFail == false) {
            //右移
            for (int i = length - 1; i > 0; i--) {   //除了頭，身體都向前移動
                snakeX[i] = snakeX[i - 1];  //後面一節的身體給前面一節
                snakeY[i] = snakeY[i - 1];  //後面一節的身體給前面一節
            }

            //通過控制方向讓頭部移動
            if(fx.equals("R")){
                snakeX[0] = snakeX[0] + 25;
                //邊界判斷
                if (snakeX[0]>850){
                    snakeX[0] = 25;
                }
            }else if (fx.equals("L")){
                snakeX[0] = snakeX[0] - 25;
                if (snakeX[0]<25){
                    snakeX[0] = 850;
                }
            }else if (fx.equals("U")){
                snakeY[0] = snakeY[0] - 25;
                if (snakeY[0]<75){
                    snakeY[0] = 650;
                }
            }else if (fx.equals("D")) {
                snakeY[0] = snakeY[0] + 25;
                if (snakeY[0] > 650) {
                    snakeY[0] = 75;
                }
            }

            //如果小蛇的頭與食物座標重合
            if(snakeX[0] == foodX && snakeY[0] == foodY){
                //長度+1
                length++;

                score = score + 10;

                //重新生成食物
                foodX = 25 + 25* random.nextInt(34);  //產生的隨機數[0,34)
                foodY = 75 + 25* random.nextInt(24);

            }
            //結束判斷
            for (int i = 1; i < length; i++) {
                if (snakeX[0]==snakeX[i] && snakeY[0]==snakeY[i]){
                    isFail = true;
                    break;
                }
            }


            //每次移動完，都需要刷新介面
            repaint();
        }
        //timer.start();  //讓時間動起來
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}
