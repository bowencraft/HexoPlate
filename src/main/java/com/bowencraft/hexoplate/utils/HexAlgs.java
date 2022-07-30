package com.bowencraft.hexoplate.utils;

import com.bowencraft.hexoplate.HexoPlate;

public class HexAlgs {

    private static HexoPlate plugin;

    public HexAlgs(HexoPlate plugin) {
        this.plugin = plugin;
    }



    // 从csv读取 偏移坐标X
//    public static int[][] readoffsetToPolarX(String fileName, int row){
//        int[][] polarX = new int[2*row][2*row];
//
//        StdIn.setFile("files/" + fileName);
//
//        for (int i=0;i<2*row;i++){
//            for(int j=0;j<2*row;j++){
//                polarX[i][j] = StdIn.readInt();
//            }
//        }
//
//        // for (int i=0;i<2*row;i++){
//        //     for (int j=0;j<2*row-1;j++){
//        //         StdOut.print(polarX[i][j] + " ");
//        //     }
//        //     StdOut.println(polarX[i][2*row-1]);
//        // }
//
//        return polarX;
//    }
//
//    // 从csv读取 偏移坐标Y
//    public static int[][] readoffsetToPolarY(String fileName, int row){
//        int[][] polarY = new int[2*row][2*row];
//
//        StdIn.setFile("files/" + fileName);
//
//        for (int i=0;i<2*row;i++){
//            for(int j=0;j<2*row;j++){
//                polarY[i][j] = StdIn.readInt();
//            }
//        }
//
//        // for (int i=0;i<2*row;i++){
//        //     for (int j=0;j<2*row-1;j++){
//        //         StdOut.print(polarY[i][j] + " ");
//        //     }
//        //     StdOut.println(polarY[i][2*row-1]);
//        // }
//
//        return polarY;
//    }


    // 新建 六边形数组输入草图
    public static int[][] createHexoInputFile(int radius){

        int[][] hexoinput = new int[2][6*(radius*(radius+1)/2) + 2];
        // StdOut.setFile("files/" + fileName);
        hexoinput[0][0] = radius +1;
        hexoinput[1][0] = 6*radius;

        hexoinput[0][1] = 0;
        hexoinput[1][1] = 0;
        // StdOut.println("0 0");

        for(int i = 1; i<= radius; i++){
            for(int j=0;j<6*i;j++){

                hexoinput[0][6*(i*(i-1)/2)+j+2] = i;
                hexoinput[1][6*(i*(i-1)/2)+j+2] = j;
            }
        }
        return hexoinput;
    }


    // 打印 - 从草图 转换为 偏移→极坐标，极坐标→偏移 csv 文件
    public static void setHexArray
    (int[][] hexoinput) {
        
        // StdIn.setFile("files/" + inputFile);
        
        int row = hexoinput[0][0];
        int col = hexoinput[1][0];

        int[][] offsetX = new int[row][col];
        int[][] offsetY = new int[row][col];

        int[][] polarX = new int[row * 2][2 * row];
        int[][] polarY = new int[row * 2][2 * row];

        for (int i = 1; i <hexoinput[0].length; i++) {

            int x = hexoinput[0][i];
            int y = hexoinput[1][i];
    
            int[] offset = polarToOffset(x, y);
            offsetX[x][y] = offset[0];
            offsetY[x][y] = offset[1];

            polarX[offset[0]+row][offset[1]+row] = x;
            polarY[offset[0]+row][offset[1]+row] = y;
            // StdOut.println(offset[0] + separator + offset[1]);

        }
//
//        StdOut.setFile("files/" + outputFileX);
//        for (int i=0;i<row;i++){
//            for (int j=0;j<col-1;j++){
//                StdOut.print(offsetX[i][j] + separator);
//            }
//            StdOut.println(offsetX[i][col-1]);
//        }
//
//        StdOut.setFile("files/" + outputFileY);
//        for (int i=0;i<row;i++){
//            for (int j=0;j<col-1;j++){
//                StdOut.print(offsetY[i][j] + separator);
//            }
//            StdOut.println(offsetY[i][col-1]);
//        }
//
//        StdOut.setFile("files/" + polarFileX);
//        for (int i=0;i<2*row;i++){
//            for (int j=0;j<2*row-1;j++){
//                StdOut.print(polarX[i][j] + separator);
//            }
//            StdOut.println(polarX[i][2*row-1]);
//        }
//        // 读取 x/y 正常位置 竖x+ 横y+
//
//        StdOut.setFile("files/" + polarFileY);
//        for (int i=0;i<2*row;i++){
//            for (int j=0;j<2*row-1;j++){
//                StdOut.print(polarY[i][j] + separator);
//            }
//            StdOut.println(polarY[i][2*row-1]);
//        }
//        // 读取： x/y各减 row 竖x+ 横y+
    }

//    // 打印 极坐标 至 偏移坐标
//    public static void printPolarToOffset(int x, int y) {
//        int[] offset = polarToOffset(x, y);
//        System.out.println(offset[0] + " " + offset[1]);
//    }

//    // 新建 PolarHex 状态 boolean表格
//    public static boolean[][] buildPolarHex(int radius) {
//
//        boolean[][] polarHex = new boolean[radius+1][radius*6];
//
//        return polarHex;
//    }
//
//    // 获得 PolarHex 状态 XY → boolean
//    public static boolean PolarHexGetter(boolean[][] polarHex, int theta, int radius) {
//
//        boolean status = polarHex[theta][radius];
//
//        return status;
//
//    }
//
//    // 设置 PolarHex 状态 XY → boolean
//    public static void PolarHexSetter(boolean[][] polarHex, int theta, int radius, boolean status) {
//
//        polarHex[theta][radius] = status;
//
//    }


    // 相对坐标 转换为 偏移坐标
    public static int[] relativeToOffset(double ix, double iy) {
        
        int[] offsetHex = new int[3];

        int[][] hexBorder = {
            {5,4,4,3,3,2,2,1,1,0,0,0,1,1,2,2,3,3,4,4}, 
            {6,6,5,5,4,4,3,3,2,2,1,2,2,3,3,4,4,5,5,6},
            {0,0,1,1,2,2,3,3,4,4,5,4,4,3,3,2,2,1,1,0}, 
            {1,2,2,3,3,4,4,5,5,6,6,6,5,5,4,4,3,3,2,2}
        };

        boolean inHex = true;

        int x = (int)Math.floor(ix);
        int y = (int)Math.floor(iy);

        int qx = x / 34; // x 商
        int rx = x % 34; // x 余数

        int qy = y / 20;
        int ry = y % 20;

        if (rx<0) {
            qx -= 1;
            rx += 34;
        }
        if (ry<0) {
            qy -= 1;
            ry += 20;
        }

        int qhx = 2*qx;
        int qhy = qy;


        if (rx<6) {
            if(ry>=10&&ry<11){
                inHex = false;
            } else {
                inHex = true;

                qhx += 0;
                if(ry<10) {
                    qhy +=0;
                } else if(ry>=11) {
                    qhy+=1;
                }
            }
        } else if (rx>=29) {
            if(ry>=10&&ry<11){
                inHex = false;
            } else {
                inHex = true;

                qhx += 2;
                if(ry<10) {
                    qhy +=0;
                } else if(ry>=11) {
                    qhy+=1;
                }
            }
        } else if (rx>=12&&rx<23) {
            if (ry>=0&&ry<1) {
                inHex = false;
            } else {
                inHex = true;
                qhx += 1;
            }
        } else if (rx>=6&&rx<12) {
            rx -= 6;
            if (rx<hexBorder[0][ry]) {
                inHex = true;
                qhx += 0;

                if(ry<10) {
                    qhy+=0;
                }
                else if(ry>=11) {
                    qhy+=1;
                }

            } else if (rx>=hexBorder[1][ry]) {
                qhx +=1;
                inHex = true;
            } else {
                inHex = false;
            }
        } else if(rx>=23&&rx<29) {
            rx -=23;
            if (rx<hexBorder[2][ry]) {
                inHex = true;
                qhx += 1;
            } else if (rx >= hexBorder[3][ry]) {
                qhx += 2;
                if (ry<10) {
                    qhy += 0;
                } else if (ry>=11) {
                    qhy += 1;

                }
            } else {
                inHex = false;
            }
        }

        if (inHex) {
            offsetHex[2] = 1;
        } else {
            offsetHex[2] = 0;
        }

        offsetHex[0] = qhx;
        offsetHex[1] = qhy;
        return offsetHex;
        
    }

    // 极坐标 转换为 偏移坐标
    public static int[] polarToOffset(int x, int y) {

        int[] offset = {0,0};
        if(x==0) {
            return offset;
        } 
        int quotient = y/x;
        int remainder = y%x;

        if (quotient == 0) {
            offset[0] = remainder;
            offset[1] = x - (int)Math.ceil(remainder/2.0);

        } else if (quotient == 1) {
            offset[0] = x;
            offset[1] = (int)Math.floor(x/2.0) - remainder;

        } else if (quotient == 2) {
            int redeem = 0;
            if (x%2==1 && y%2==0) redeem = 1;
            offset[0] = x - remainder;
            offset[1] = - (int)Math.floor((x)/2.0) - (int)Math.ceil((remainder)/2.0) - redeem;

        } else if (quotient == 3) {
            offset[0] = - remainder;
            offset[1] = -x + (int)Math.floor(remainder/2.0);

        } else if (quotient == 4) {
            offset[0] = -x;
            offset[1] = remainder - (int)Math.ceil(x/2.0);

        } else if (quotient == 5) {
            int redeem = 0;
            if (x%2==1 && y%2==1) redeem = 1;
            offset[0] = - x + remainder;
            offset[1] = (int)Math.ceil((x)/2.0) + (int)Math.floor((remainder)/2.0) - redeem;
        }
        return offset;
    }

    // 偏移坐标 转换为 极坐标
    public static int[] offsetToPolar(int x, int y) {

//        int row = hexoinput[0][0];
//        int[][] polarX = readoffsetToPolarX("OffsetToPolarX.csv", row);
//        int[][] polarY = readoffsetToPolarY("OffsetToPolarY.csv", row);
//
        int radius = plugin.getConfig().getInt("radius");;
        int[][] hexoinput = createHexoInputFile(radius);

        // setHexArray

        int row = hexoinput[0][0];
        int col = hexoinput[1][0];

        // int[][] offsetX = new int[row][col];
        // int[][] offsetY = new int[row][col];

        int[][] polarX = new int[row * 2][2 * row];
        int[][] polarY = new int[row * 2][2 * row];

        for (int i = 1; i <hexoinput[0].length; i++) {

            int px = hexoinput[0][i];
            int py = hexoinput[1][i];

            int[] offset = polarToOffset(px, py);
            // offsetX[px][py] = offset[0];
            // offsetY[px][py] = offset[1];

            polarX[offset[0] + row][offset[1] + row] = px;
            polarY[offset[0] + row][offset[1] + row] = py;

        }
        //

        int[] polar = {0,0};

        polar[0] = polarX[x+row][y+row];
        polar[1] = polarY[x+row][y+row];

        return polar;
    }

    // 极坐标 转换为 中心坐标
    public static int[] polarToCentralXY(int x, int y){
        int[] central = {0,0};
        int[] offset = {0,0};
        offset = polarToOffset(x, y);

        if (offset[0]%2==0){
            central[1]=offset[1]*20;
        } else {
            central[1]=offset[1]*20+10;
        }
        central[0] = offset[0]*17;

        return central;
    }

    // 偏移坐标 转换为 中心坐标
    public static int[] offsetToCentralXY(int x, int y){
        int[] central = {0,0};
        int[] offset = {x,y};

        if (offset[0]%2==0){
            central[1]=offset[1]*20;
        } else {
            central[1]=offset[1]*20+10;
        }
        central[0] = offset[0]*17;

        return central;
    }

    public static void main(String[] args) {

        // 相对XY → 偏移Hex
        // double Origin_x = 0; // Origin x
        // double Origin_y = 0; // Origin y
    
        // double Absolute_x = Double.parseDouble(args[0]); // Event position x
        // double Absolute_y = Double.parseDouble(args[1]); // Event position x
    
        // double Relative_x = Absolute_x - Origin_x;
        // double Relative_y = Absolute_y - Origin_y;

        // int[] pos = relativeToOffset(Relative_x,Relative_y);
        // StdOut.println(pos[0] + ", " + pos[1] +  ", " + pos[2]);

        // 偏移Hex → 中心XY
        // int x = StdIn.readInt();
        // int y = StdIn.readInt();
        // int[] pos = offsetToCentralXY(x, y);
        // StdOut.println(pos[0] + ", " + pos[1]);


        // 相对XY → 偏移Hex
        // int x = StdIn.readInt();
        // int y = StdIn.readInt();
        // int[] pos = relativeToOffset(x, y);
        // StdOut.println(pos[0] + ", " + pos[1] + ", " + pos[2]);

        // 打印 - 从草图 转换为 偏移→极坐标，极坐标→偏移 csv 文件
        // printToFile("hexinput.txt", "PolarToOffsetX.csv", "PolarToOffsetY.csv", "OffsetToPolarX.csv", "OffsetToPolarY.csv");


    }


}