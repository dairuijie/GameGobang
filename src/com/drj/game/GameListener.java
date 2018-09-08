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
 * @Description:TODO(���������¼�)
 * @author: drj
 * @date: 2018��9��8�� ����7:47:16
 * 
 * @Copyright: 2018
 *
 */
public class GameListener extends MouseAdapter implements ActionListener, Goconfig {
    private GameMain main;// �������
    private Graphics2D g;// ��ͼ����
    private String gameModel = PP_TYPE;// ��Ϸģʽ
    private boolean flag = true;// Ĭ�� ����
    private JComboBox<String> cbItem;// �����Ϸѡ�� ���� �����˻� �������
    private ArrayList<Chess> list = new ArrayList<Chess>();

    public GameListener(GameMain main, JComboBox<String> cbItem) {
        this.main = main;
        this.cbItem = cbItem;
    }

    /**
     * ������¼�
     */
    public void mouseClicked(MouseEvent e) {
        if (g == null) {// ��ȡ �����ӵĶ���
            g = (Graphics2D) main.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }
        int x = e.getX() - 30;// ��ȡ�������x ����
        int y = e.getY() - 30;// ��ȡ�������y ����
        int length = (ROW - 1) * SQUARE_SIZE + 30;
        if (x < 0 || y < 0 || x > length || y > length) {
            System.out.println(555);
            JOptionPane.showMessageDialog(main, WARING_INFO);

        } else {
            int cx = x - (x / SQUARE_SIZE) * SQUARE_SIZE;
            int cy = y - (y / SQUARE_SIZE) * SQUARE_SIZE;
            // �� ��� x�ڷ����м� λ�� ����������ĵ� ����
            if (cx > (SQUARE_SIZE / 2)) {
                x = (x / SQUARE_SIZE + 1) * SQUARE_SIZE;
            } else {
                x = (x / SQUARE_SIZE) * SQUARE_SIZE;
            }
            // �� ����� y �ڷ����м� λ�� ����������ĵ� ����
            if (cy > (SQUARE_SIZE / 2)) {
                y = (y / SQUARE_SIZE + 1) * SQUARE_SIZE;
            } else {
                y = (y / SQUARE_SIZE) * SQUARE_SIZE;
            }
            int zx = x / SQUARE_SIZE;
            int zy = y / SQUARE_SIZE;
            System.out.println(zx + "===" + zy);
            Chess tmpchess = new Chess(zx, zy);
            if (go[zx][zy].color == 0) {// �ǿ�λ���ǻ�����
                draw(tmpchess);
                if (list.size() == Math.pow(ROW, 2)) {
                    gameOverClear(main, cbItem);
                    JOptionPane.showMessageDialog(main, DEUCE);
                    return;
                }
                if (PC_TYPE.equals(gameModel)) {// �˻�ģʽ
                    Piece.weightReset();
                    tmpchess = Piece.weight();
                    draw(tmpchess); // �������ӣ����ж���Ӯ
                }
            }
        }
    }

    /**
     * �滭�ڰ�����
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
     * ��Դ������¼�������
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals(START_GAME)) {
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COLUMN; j++) {
                    go[i][j].color = 0;
                }
            }
            Piece.weightReset(); // ��ʼǰ�������̵�Ȩ��
            flag = true; // ��ɫ ����Ϊ true
            cbItem.setEnabled(false);// ������ ��Ϊ����ѡ
            list.clear(); // �����������
            main.repaint();// �����ػ�
            // main.removeMouseListener(this); //�Ƴ���������
            main.addMouseListener(this);// ��Ӽ�������
            main.setButton(true); // ���û��塢����ɵ�
        } else if (e.getActionCommand().equals(RETURN_GO)) {
            if (list.size() >= 1) {
                Chess chess = list.remove(list.size() - 1);// ɾ������������Ӷ���
                go[chess.x][chess.y].color = 0;// ��������� λ�� ��ɫ��Ϊ 0
                flag = !flag;// ����� ��Ҫ�ǻ���������
                main.repaint();// �������»�
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
            JComboBox<String> cbItem = (JComboBox<String>) e.getSource();// ��ȡ�¼�Դ����
            gameModel = cbItem.getSelectedItem().toString(); // ��ȡѡ��Ķ�սģʽ
            System.out.println("select is" + gameModel);
        }
    }

    /**
     * ��Ϸ���� ����������
     * 
     * @param main
     * @param cbItem
     */
    public void gameOverClear(GameMain main, JComboBox<String> cbItem) {
        main.removeMouseListener(this); // �Ƴ���������ϵ���궯�������������¼����������
        cbItem.setEnabled(true); // ��Ϸģʽ���Բ���
        main.setButton(false); // ���� ��ֹ����
    }
}
