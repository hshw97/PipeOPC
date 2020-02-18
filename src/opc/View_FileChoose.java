package opc;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author 黄尚文 
 */
public class View_FileChoose extends JFrame{
    public static Object userObject;
    public View_FileChoose(String name,int xx,int yy){
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocation(xx,yy);
        JFileChooser jfc=new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );
        jfc.showDialog(new JLabel(),"选择");
        File file=jfc.getSelectedFile();
        new Constant_From().PATH_CHOOSE(file.getAbsolutePath());
    }
}