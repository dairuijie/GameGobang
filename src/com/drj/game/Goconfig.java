package com.drj.game;
import java.util.HashMap;
/**
 * 
 * @ClassName:  Goconfig   
 * @Description:TODO(公用的变量)   
 * @author: drj 
 * @date:   2018年9月8日 下午7:49:11   
 *     
 * @Copyright: 2018 
 *
 */
public interface Goconfig {
    public static final String TITLE = "简单五子棋";
    public static final String PC_TYPE="人机对战";
    public static final String PP_TYPE = "人人对站";
    public static final String START_GAME = "开始游戏";
    public static final String RETURN_GO = "悔棋";
    public static final String LOSER = "认输";
    public static final String WHITE_WIN = "白棋胜利！";
    public static final String BLACK_WIN = "黑棋胜利！";
    public static final String WARING_INFO = "请点击在棋盘内";
    public static final String DEUCE = "恭喜你们打成平手";
    public static final int ROW=6;  //棋谱行数
    public static final int COLUMN = 6; //棋谱列数
    public static final int SQUARE_SIZE=35;  //棋谱每个方格的大小
    public static final int RADIUS=12;  //棋子的半径长度
    public static Piece go[][]=new Piece[ROW][ROW];  //存放棋谱上棋子的颜色
    public static int weightarr[][]=new int[ROW][ROW];  //存放棋子的权重，也就是权重越大 优先级越高
    public static HashMap<String,Integer> map = new HashMap<String,Integer>();   //存放 白旗或是黑棋 存在的路线组合
    //举个例子： 0 代表空位 1 白色 棋子 2 黑色棋子  010 表示 只有一个白色棋子两边都是空位 0110  代表两个棋子相连两头是空位的 

}
