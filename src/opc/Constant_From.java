package opc;

import java.awt.Toolkit;
/**
 *
 * @author 黄尚文
 */
public class Constant_From {
    public Constant_From(){
        
    }
    //开始运行标记
    public static int BEGIN=0;
    
    //Excel处理专用变量
    public static int SHEET_NUMBER=0,ROWS_NUMBER=-1;
    public int SHEET_NUMBER(int a){SHEET_NUMBER=a;return 0;}
    public int ROWS_NUMBER(int a){ROWS_NUMBER=a;return 0;}
    public static String PATH_CHOOSE="";
    public int PATH_CHOOSE(String a){PATH_CHOOSE=a;return 0;}
    
    //显示参数
    private static int Screen_X=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    private static int Screen_Y=(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    private static int Screen_H=460;
    public static int FileChoose_X=Screen_X-150,FileChoose_Y=Screen_H;
    public static int JProcessBar_X=Screen_X/2,JProcessBar_Y=Screen_H;
    public static int GetInformation_X=Screen_X/2-194,GetInformation_Y=Screen_H;
}
