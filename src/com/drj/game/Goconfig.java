package com.drj.game;
import java.util.HashMap;
/**
 * 
 * @ClassName:  Goconfig   
 * @Description:TODO(���õı���)   
 * @author: drj 
 * @date:   2018��9��8�� ����7:49:11   
 *     
 * @Copyright: 2018 
 *
 */
public interface Goconfig {
    public static final String TITLE = "��������";
    public static final String PC_TYPE="�˻���ս";
    public static final String PP_TYPE = "���˶�վ";
    public static final String START_GAME = "��ʼ��Ϸ";
    public static final String RETURN_GO = "����";
    public static final String LOSER = "����";
    public static final String WHITE_WIN = "����ʤ����";
    public static final String BLACK_WIN = "����ʤ����";
    public static final String WARING_INFO = "������������";
    public static final String DEUCE = "��ϲ���Ǵ��ƽ��";
    public static final int ROW=6;  //��������
    public static final int COLUMN = 6; //��������
    public static final int SQUARE_SIZE=35;  //����ÿ������Ĵ�С
    public static final int RADIUS=12;  //���ӵİ뾶����
    public static Piece go[][]=new Piece[ROW][ROW];  //������������ӵ���ɫ
    public static int weightarr[][]=new int[ROW][ROW];  //������ӵ�Ȩ�أ�Ҳ����Ȩ��Խ�� ���ȼ�Խ��
    public static HashMap<String,Integer> map = new HashMap<String,Integer>();   //��� ������Ǻ��� ���ڵ�·�����
    //�ٸ����ӣ� 0 �����λ 1 ��ɫ ���� 2 ��ɫ����  010 ��ʾ ֻ��һ����ɫ�������߶��ǿ�λ 0110  ������������������ͷ�ǿ�λ�� 

}
