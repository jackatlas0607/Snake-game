import javax.swing.*;

//遊戲的主啟動類
public class StartGame {
    public static void main(String[] args) {
        //1.繪製一個靜態視窗  JFrame
        JFrame frame = new JFrame("Takagi的貪吃蛇小遊戲");
        frame.setBounds(10,10,900,720);  //設置介面的大小
        frame.setResizable(false);   //視窗大小不能改變
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //設置關閉事件，遊戲可以關閉了

        //2.面板JPanel  可已加入到JFrame
        frame.add(new GamePanel());

        frame.setVisible(true);  //讓視窗能夠展現出來
    }
}
