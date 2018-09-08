package com.drj.game;
/**
 * 
 * @ClassName:  Piece   
 * @Description:TODO(描述棋子的颜色、判断是否成功、计算ai权值 )   
 * @author: drj 
 * @date:   2018年9月8日 下午7:45:01   
 *     
 * @Copyright: 2018 
 *
 */
class Piece implements Goconfig {
    public Piece(int color) {
        this.color = 0;
    }

    public int color = 0;// 0 代表空位
    // 权值重置

    public static void weightReset() {
        for (int i = 0; i < weightarr.length; i++) {
            for (int j = 0; j < weightarr[i].length; j++) {
                weightarr[i][j] = 0;
            }
        }
    }

    /**
     * 计算权重
     * 
     * @return
     */
    public static Chess weight() {
        int maxWeight = 0;
        int x = 0, y = 0;
        for (int i = 0; i < go.length; i++) {
            for (int j = 0; j < go[i].length; j++) {
                if (go[i][j].color == 0) {// 只有空位的 才去计算权值
                    String key1 = "0";
                    String key2 = "0";
                    int upx = i, upy = j, downx = i, downy = j;
                    // 左斜方向判断 西北方
                    int first1 = 0, first2 = 0;
                    while ((upx - 1) >= 0 && (upy - 1) >= 0) {
                        int first = go[i - 1][j - 1].color;
                        first1 = first;
                        if (first == 0)
                            break;
                        else {
                            --upx;
                            --upy;
                            if (go[upx][upy].color == first) {
                                key1 += go[upx][upy].color;
                            } else {
                                key1 += go[upx][upy].color;
                                break;
                            }
                        }
                    }
                    // 东南方
                    while ((downx + 1) <= (ROW - 1) && (downy + 1) <= (ROW - 1)) {
                        int first = go[i + 1][j + 1].color;
                        first2 = first;
                        if (first == 0)
                            break;
                        else {
                            ++downx;
                            ++downy;
                            if (go[downx][downy].color == first) {
                                key2 += go[downx][downy].color;
                            } else {
                                key2 += go[downx][downy].color;
                                break;
                            }
                        }
                    }
                    System.out.println("one" + key1 + "===" + key2);

                    Integer res1 = map.get(key1);
                    Integer res2 = map.get(key2);
                    int value1 = 0, value2 = 0;
                    if (res1 != null)
                        value1 = res1.intValue();
                    if (res2 != null)
                        value2 = res2.intValue();
                    int value = value1 + value2;
                    if ((first1 == first2) && first1 != 0) {
                        value *= 2;
                    }
                    key1 = "0";
                    key2 = "0";
                    upx = i;
                    upy = j;
                    downx = i;
                    downy = j;
                    // 绔栫洿鏂瑰悜鍒ゆ柇
                    first1 = 0;
                    first2 = 0;
                    while ((upy - 1) >= 0) {
                        int first = go[i][j - 1].color;
                        first1 = first;
                        if (first == 0)
                            break;
                        else {
                            --upy;
                            if (go[upx][upy].color == first) {
                                key1 += go[upx][upy].color;
                            } else {
                                key1 += go[upx][upy].color;
                                break;
                            }
                        }
                    }
                    while ((downy + 1) <= (ROW - 1)) {
                        int first = go[i][j + 1].color;
                        first2 = first;
                        if (first == 0)
                            break;
                        else {
                            ++downy;
                            if (go[downx][downy].color == first) {
                                key2 += go[downx][downy].color;
                            } else {
                                key2 += go[downx][downy].color;
                                break;
                            }
                        }
                    }
                    System.out.println("tow" + key1 + "===" + key2);
                    res1 = map.get(key1);
                    res2 = map.get(key2);
                    value1 = 0;
                    value2 = 0;
                    if (res1 != null)
                        value1 = res1.intValue();
                    if (res2 != null)
                        value2 = res2.intValue();
                    value += value1 + value2;
                    if ((first1 == first2) && first1 != 0) {
                        value *= 2;
                    }
                    key1 = "0";
                    key2 = "0";
                    upx = i;
                    upy = j;
                    downx = i;
                    downy = j;
                    first1 = 0;
                    first2 = 0;
                    while ((upx - 1) >= 0) {
                        int first = go[i - 1][j].color;
                        first1 = first;
                        if (first == 0)
                            break;
                        else {
                            --upx;
                            if (go[upx][upy].color == first) {
                                key1 += go[upx][upy].color;
                            } else {
                                key1 += go[upx][upy].color;
                                break;
                            }
                        }
                    }
                    while ((downx + 1) <= (ROW - 1)) {
                        int first = go[i + 1][j].color;
                        first2 = first;
                        if (first == 0)
                            break;
                        else {
                            ++downx;
                            if (go[downx][downy].color == first) {
                                key2 += go[downx][downy].color;
                            } else {
                                key2 += go[downx][downy].color;
                                break;
                            }
                        }
                    }
                    System.out.println("three" + key1 + "===" + key2);
                    res1 = map.get(key1);
                    res2 = map.get(key2);
                    value1 = 0;
                    value2 = 0;
                    if (res1 != null)
                        value1 = res1.intValue();
                    if (res2 != null)
                        value2 = res2.intValue();
                    value += value1 + value2;
                    if ((first1 == first2) && first1 != 0)
                        value *= 2;

                    key1 = "0";
                    key2 = "0";
                    upx = i;
                    upy = j;
                    downx = i;
                    downy = j;
                    first1 = 0;
                    first2 = 0;
                    while ((upx + 1) <= (ROW - 1) && (upy - 1) >= 0) {
                        int first = go[i + 1][j - 1].color;
                        first1 = first;
                        if (first == 0)
                            break;
                        else {
                            ++upx;
                            --upy;
                            if (go[upx][upy].color == first) {
                                key1 += go[upx][upy].color;
                            } else {
                                key1 += go[upx][upy].color;
                                break;
                            }
                        }
                    }
                    while ((downx - 1) >= 0 && (downy + 1) <= (ROW - 1)) {
                        int first = go[i - 1][j + 1].color;
                        first2 = first;
                        if (first == 0)
                            break;
                        else {
                            --downx;
                            ++downy;
                            if (go[downx][downy].color == first) {
                                key2 += go[downx][downy].color;
                            } else {
                                key2 += go[downx][downy].color;
                                break;
                            }
                        }
                    }
                    System.out.println("four" + key1 + "===" + key2);
                    res1 = map.get(key1);
                    res2 = map.get(key2);
                    value1 = 0;
                    value2 = 0;
                    if (res1 != null)
                        value1 = res1.intValue();
                    if (res2 != null)
                        value2 = res2.intValue();
                    value += value1 + value2;
                    if ((first1 == first2) && first1 != 0)
                        value *= 2; 
                    weightarr[i][j] += value;
                    if (weightarr[i][j] >= maxWeight) {
                        maxWeight = weightarr[i][j];
                        x = i;
                        y = j;
                    }
                    
                }
            }
        }
        System.out.println(maxWeight);
        return new Chess(x, y);
    }

    /**
     * 判断是否五子连接成功
     * 
     * @param x
     * @param y
     * @return
     */
    public int judge(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int upx = x, upy = y, downx = x, downy = y;
            int count = 1;
            if (i == 0) {
                while ((upx - 1) >= 0 && (upy - 1) >= 0) {
                    if (go[--upx][--upy].color == this.color)
                        count++;
                    else
                        break;
                }
                while ((downx + 1) <= (ROW - 1) && (downy + 1) <= (ROW - 1)) {
                    if (go[++downx][++downy].color == this.color)
                        count++;
                    else
                        break;
                }
                if (count >= 5) {
                    System.out.print(this.color + "胜利");
                    return this.color;
                }
            } else if (i == 1) {
                while ((upy - 1) >= 0) {
                    if (go[upx][--upy].color == this.color)
                        count++;
                    else
                        break;
                }
                while ((downy + 1) <= (ROW - 1)) {
                    if (go[downx][++downy].color == this.color)
                        count++;
                    else
                        break;
                }
                if (count >= 5) {
                    System.out.print(this.color + "胜利");
                    return this.color;
                }
            } else if (i == 2) {
                while ((upx + 1) <= (ROW - 1) && (upy - 1) >= 0) {
                    if (go[++upx][--upy].color == this.color)
                        count++;
                    else
                        break;
                }
                while ((downx - 1) >= 0 && (downy + 1) <= (ROW - 1)) {
                    if (go[--downx][++downy].color == this.color)
                        count++;
                    else
                        break;
                }
                if (count >= 5) {
                    System.out.print(this.color + "胜利");
                    return this.color;
                }
            } else if (i == 3) {
                while ((downx - 1) >= 0) {
                    if (go[--downx][downy].color == this.color)
                        count++;
                    else
                        break;
                }
                while ((upx + 1) <= (ROW - 1)) {
                    if (go[++upx][upy].color == this.color)
                        count++;
                    else
                        break;
                }
                if (count >= 5) {
                    System.out.print(this.color + "胜利");
                    return this.color;
                }
            }
        }
        return 0;
    }

}
