package com.drj.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @ClassName: GameMain
 * @Description:TODO(启动主类、初始化窗体)
 * @author: drj
 * @date: 2018年9月8日 下午7:48:17
 * qq：3343217807
 * @Copyright: 2018
 *
 */
public class GameMain extends JPanel implements Goconfig {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 存放棋子可能的组合 活一连 两头都是空位 就是活几连 有几个一起相连的就是几 活二连 眠一连 一头是对方棋子 一头是空位
     * 
     */
    static {
        map.put("01", 20);
        map.put("02", 20);
        map.put("010", 42);
        map.put("020", 42);
        map.put("011", 300);
        map.put("022", 300);
        map.put("0110", 400);
        map.put("0220", 400);
        map.put("0111", 400);
        map.put("0222", 400);
        map.put("01111", 3000);
        map.put("02222", 3000);
        map.put("01110", 3000);
        map.put("02220", 3000);
        map.put("011110", 10000);
        map.put("022220", 10000);
        map.put("012", 23);
        map.put("021", 23);
        map.put("0112", 200);
        map.put("0221", 200);
        map.put("01112", 500);
        map.put("02221", 500);
        map.put("022221", 3000);
        map.put("011112", 3000);
    }

    JButton buttonStart = new JButton(START_GAME);
    JButton buttonregret = new JButton(RETURN_GO);
    JButton buttonlose = new JButton(LOSER);

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        GameMain main = new GameMain();
        main.Init();
        main.PieceInit();
        main.setButton(false);
    }

    /**
     * 初始化窗体 1窗体大小 2窗体中布局 3按钮、下拉框 4添加事件监听
     */
    public void Init() {
        JFrame jf = new JFrame();
        jf.setTitle(TITLE);
        jf.setSize(650, 580);
        jf.setLocationRelativeTo(null);
        jf.setResizable(true);
        jf.setDefaultCloseOperation(3);
        jf.setLayout(new BorderLayout());
        jf.add(this);
        JPanel eastp = new JPanel();
        eastp.setPreferredSize(new Dimension(100, 0));
        String[] itemArray = { PP_TYPE, PC_TYPE };
        JComboBox<String> cbItem = new JComboBox<String>(itemArray);
        buttonStart.setPreferredSize(new Dimension(90, 40));
        buttonregret.setPreferredSize(new Dimension(90, 40));
        buttonlose.setPreferredSize(new Dimension(90, 40));
        cbItem.setPreferredSize(new Dimension(90, 40));
        JPanel eastp_tmp = new JPanel();
        eastp_tmp.setPreferredSize(new Dimension(90, 30));
        eastp.add(eastp_tmp);
        eastp.add(buttonStart);
        eastp.add(buttonregret);
        eastp.add(buttonlose);
        eastp.add(cbItem);
        jf.add(eastp, BorderLayout.EAST);
        jf.setVisible(true);
        GameListener cl = new GameListener(this, cbItem);
        buttonStart.addActionListener(cl);
        buttonregret.addActionListener(cl);
        buttonlose.addActionListener(cl);
        cbItem.addActionListener(cl);
    }

    /**
     * 存放棋子的数组 开始都是空位
     */
    private void PieceInit() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < ROW; j++) {
                go[i][j] = new Piece(0);
            }
        }
    }

    /**
     * 画棋谱格子
     */
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < ROW; i++) {
            g.drawLine(30, 30 + i * SQUARE_SIZE, 30 + SQUARE_SIZE * (ROW - 1), 30 + i * SQUARE_SIZE);
            g.drawLine(30 + i * SQUARE_SIZE, 30, 30 + i * SQUARE_SIZE, 30 + SQUARE_SIZE * (ROW - 1));
        }
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < ROW; j++) {
                if (go[i][j].color != 0) {
                    int x = 30 + i * SQUARE_SIZE;
                    int y = 30 + j * SQUARE_SIZE;
                    if (go[i][j].color == 1)
                        g.setColor(Color.BLACK);
                    else
                        g.setColor(Color.WHITE);
                    g.fillOval(x - 12, y - 12, 24, 24);
                }
            }
        }
    }

    public void setButton(boolean flag) {
        buttonregret.setEnabled(flag);
        buttonlose.setEnabled(flag);
    }
}
