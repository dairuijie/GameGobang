package com.drj.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 * 
 * @ClassName: GameListener
 * @Description:TODO(监听窗体事件)
 * @author: drj
 * @date: 2018年9月8日 下午7:47:16
 * 
 * @Copyright: 2018
 *
 */
public class GameListener extends MouseAdapter implements ActionListener, Goconfig {
    private GameMain main;// 窗体对象
    private Graphics2D g;// 画图对象
    private String gameModel = PP_TYPE;// 游戏模式
    private boolean flag = true;// 默认 黑棋
    private JComboBox<String> cbItem;// 存放游戏选择 人人 还是人机 数组对象
    private ArrayList<Chess> list = new ArrayList<Chess>();

    public GameListener(GameMain main, JComboBox<String> cbItem) {
        this.main = main;
        this.cbItem = cbItem;
    }

    /**
     * 鼠标点击事件
     */
    public void mouseClicked(MouseEvent e) {
        if (g == null) {// 获取 画棋子的对象
            g = (Graphics2D) main.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
        int x = e.getX() - 30;// 获取鼠标点击的x 坐标
        int y = e.getY() - 30;// 获取鼠标点击的y 坐标
        int length = (ROW - 1) * SQUARE_SIZE + 30;
        if (x < 0 || y < 0 || x > length || y > length) {
            System.out.println(555);
            JOptionPane.showMessageDialog(main, WARING_INFO);

        } else {
            int cx = x - (x / SQUARE_SIZE) * SQUARE_SIZE;
            int cy = y - (y / SQUARE_SIZE) * SQUARE_SIZE;
            // 当 点击 x在方格中间 位置 按离他最近的点 放置
            if (cx > (SQUARE_SIZE / 2)) {
                x = (x / SQUARE_SIZE + 1) * SQUARE_SIZE;
            } else {
                x = (x / SQUARE_SIZE) * SQUARE_SIZE;
            }
            // 当 点击在 y 在方格中间 位置 按离他最近的点 放置
            if (cy > (SQUARE_SIZE / 2)) {
                y = (y / SQUARE_SIZE + 1) * SQUARE_SIZE;
            } else {
                y = (y / SQUARE_SIZE) * SQUARE_SIZE;
            }
            int zx = x / SQUARE_SIZE;
            int zy = y / SQUARE_SIZE;
            System.out.println(zx + "===" + zy);
            Chess tmpchess = new Chess(zx, zy);
            if (go[zx][zy].color == 0) {// 是空位就是画棋子
                draw(tmpchess);
                if (list.size() == Math.pow(ROW, 2)) {
                    gameOverClear(main, cbItem);
                    JOptionPane.showMessageDialog(main, DEUCE);
                    return;
                }
                if (PC_TYPE.equals(gameModel)) {// 人机模式
                    Piece.weightReset();
                    tmpchess = Piece.weight();
                    draw(tmpchess); // 画出该子，并判断输赢
                }
            }
        }
    }

    /**
     * 绘画黑白棋子
     * 
     * @param tmpchess
     */
    public void draw(Chess tmpchess) {
        int zx = tmpchess.x;
        int zy = tmpchess.y;
        int x = zx * SQUARE_SIZE + 30;
        int y = zy * SQUARE_SIZE + 30;
        if (flag) {
            g.setColor(Color.BLACK);
        } else {
            g.setColor(Color.WHITE);
        }
        g.fillOval(x - RADIUS, y - RADIUS, RADIUS * 2, RADIUS * 2);
        Color col = g.getColor();
        if (col == Color.BLACK) {
            go[zx][zy].color = 1;
        } else if (col == Color.WHITE) {
            go[zx][zy].color = 2;
        }
        if (go[zx][zy].judge(zx, zy) != 0) {
            if (flag) {
                JOptionPane.showMessageDialog(main, BLACK_WIN);
            } else {
                JOptionPane.showMessageDialog(main, WHITE_WIN);
            }
            gameOverClear(main, cbItem);
            return;
        }
        flag = !flag;
        list.add(tmpchess);
    }

    /**
     * 针对窗体上事件的拦截
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(START_GAME)) {
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COLUMN; j++) {
                    go[i][j].color = 0;
                }
            }
            Piece.weightReset(); // 开始前重置棋盘的权重
            flag = true; // 黑色 定义为 true
            cbItem.setEnabled(false);// 下拉框 置为不可选
            list.clear(); // 清空棋子数组
            main.repaint();// 窗体重绘
            // main.removeMouseListener(this); //移除监听对象
            main.addMouseListener(this);// 添加监听对象
            main.setButton(true); // 设置悔棋、认输可点
        } else if (e.getActionCommand().equals(RETURN_GO)) {
            if (list.size() >= 1) {
                Chess chess = list.remove(list.size() - 1);// 删除最后加入的棋子对象
                go[chess.x][chess.y].color = 0;// 将最后棋子 位置 颜色置为 0
                flag = !flag;// 悔棋后 需要是悔棋重新下
                main.repaint();// 棋谱重新画
            }
        } else if (e.getActionCommand().equals(LOSER)) {
            if (flag) {
                JOptionPane.showMessageDialog(main, WHITE_WIN);
            } else {
                JOptionPane.showMessageDialog(main, BLACK_WIN);
            }
            gameOverClear(main, cbItem);
        } else if (e.getSource() instanceof JComboBox) {
            @SuppressWarnings("unchecked")
            JComboBox<String> cbItem = (JComboBox<String>) e.getSource();// 获取事件源对象
            gameModel = cbItem.getSelectedItem().toString(); // 获取选择的对战模式
            System.out.println("select is" + gameModel);
        }
    }

    /**
     * 游戏结束 处理公共代码
     * 
     * @param main
     * @param cbItem
     */
    public void gameOverClear(GameMain main, JComboBox<String> cbItem) {
        main.removeMouseListener(this); // 移除棋盘面板上的鼠标动作监听方法和事件处理类对象
        cbItem.setEnabled(true); // 游戏模式可以操作
        main.setButton(false); // 悔棋 禁止操作
    }
}
