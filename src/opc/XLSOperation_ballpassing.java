package opc;

/**
 *
 * @author 黄尚文
 */
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.*;
import jxl.read.biff.BiffException;
public class XLSOperation_ballpassing{
    File XLS_file;
    Workbook XLS_book;
    Sheet XLS_sheet;
    Constant_From con;
    
    XLSOperation_ballpassing(){
        con = new Constant_From();
    }
    public void XLS_OPEN(){
        try {
            this.XLS_file= new File(con.PATH_CHOOSE);
            this.XLS_book = Workbook.getWorkbook(XLS_file);
            this.XLS_sheet=XLS_book.getSheet(con.SHEET_NUMBER);
        } catch (IOException ex) {
            Logger.getLogger(XLSOperation_ballpassing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BiffException ex) {
            Logger.getLogger(XLSOperation_ballpassing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void XLS_CLOSE(){
        XLS_book.close();
    }
    public int getRowsNumber(){
        int Rows_number=-1;
        try{
            Rows_number=XLS_sheet.getRows();
            con.ROWS_NUMBER(Rows_number);
        }catch(Exception e){System.out.println(e);}
        return Rows_number;
    }
    public String getText(int row){
        String ElevationValue = new String();
        try{
            Cell cell1=XLS_sheet.getCell(0,row-1);
            ElevationValue=cell1.getContents();
           
        }catch(Exception e){System.out.println(e);}
        return ElevationValue;
    }
    public String[] getTexts(){
        String[] name;
        this.XLS_OPEN();
        this.getRowsNumber();
        name=new String[con.ROWS_NUMBER];
        for(int i=1;i<=con.ROWS_NUMBER;i++){
            name[i-1]=this.getText(i);
        }
        this.XLS_CLOSE();
        return name;
    }
}