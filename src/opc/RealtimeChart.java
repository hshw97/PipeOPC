package opc;

import static java.lang.Thread.sleep;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafish.clients.opc.exception.ComponentNotFoundException;
import javafish.clients.opc.exception.SynchReadException;
import javax.swing.JFrame;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler.ChartTheme;
import org.knowm.xchart.style.Styler.LegendLayout;
import org.knowm.xchart.style.Styler.LegendPosition;
 
/**
 * Logarithmic Y-Axis
 *
 * <p>
 * Demonstrates the following:
 *
 * <ul>
 * <li>Logarithmic Y-Axis
 * <li>Building a Chart with ChartBuilder
 * <li>Place legend at Inside-NW position
 */
public class RealtimeChart extends Thread{
 
	private SwingWrapper<XYChart> swingWrapper;
	private XYChart chart;
	private JFrame frame;
        Opc_Operate MyOpc;
        private int item_num;
	private String title;// 标题
	private String seriesName;// 系列，此处只有一个系列。若存在多组数据，可以设置多个系列
        private LinkedList<Double> fifo;//FIFO数据
	private int size = 1000;// 最多显示多少数据，默认显示1000个数据
 
	public int getSize() {
		return size;
	}
 
	public void setSize(int size) {
		this.size = size;
	}
 
	public String getSeriesName() {
		return seriesName;
	}
 
	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}
 
	public String getTitle() {
		return title;
	}
 
	public void setTitle(String title) {
		this.title = title;
	}
 
	/**
	 * 实时绘图
	 * 
	 * @param seriesName
	 * @param title
	 */
	public RealtimeChart(String title, String seriesName) {
		super();
		this.seriesName = seriesName;
		this.title = title;
	}
 
	public RealtimeChart(String title, String seriesName, int size, Opc_Operate Opc,int item_num) {
		super();
		this.title = title;
		this.seriesName = seriesName;
		this.size = size;
                this.MyOpc = Opc;
                this.item_num=item_num;
                this.setTitle(title);
	}
 
	public void plot(double data) {
		
                if (fifo == null) {
			fifo = new LinkedList<Double>();
		}
                fifo.add(data);
                if (fifo.size() > this.size) {
			fifo.removeFirst();
		}               
                
		if (swingWrapper == null) {
 
			// Create Chart
			chart = new XYChartBuilder().width(600).height(450).theme(ChartTheme.Matlab).title(title).build();
			chart.addSeries(seriesName, null, fifo);
			chart.getStyler().setLegendPosition(LegendPosition.OutsideS);// 设置legend的位置为外底部
			chart.getStyler().setLegendLayout(LegendLayout.Horizontal);// 设置legend的排列方式为水平排列
                        chart.getStyler().setXAxisTicksVisible(false);
			swingWrapper = new SwingWrapper<XYChart>(chart);
			frame = swingWrapper.displayChart();
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);// 防止关闭窗口时退出程序
		} else {
 
			// Update Chart
			chart.updateXYSeries(seriesName, null, fifo, null);
			swingWrapper.repaintChart();
		}
	}

    @Override
    public void run() {
        //super.run();
        double dataPoint =0;        
        while (new Constant_From().BEGIN==1){
            try {         
                dataPoint=MyOpc.jopc.synchReadItem(MyOpc.group,MyOpc.group.getItemsAsArray()[item_num]).getValue().getDouble();
                //System.out.println(MyOpc.jopc.synchReadItem(MyOpc.group, MyOpc.item_Write).getValue().getDouble());                
            } catch (ComponentNotFoundException ex) {Logger.getLogger(RealtimeChart.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SynchReadException ex) {Logger.getLogger(RealtimeChart.class.getName()).log(Level.SEVERE, null, ex);}
            this.plot(dataPoint);
            try {
sleep(500);//此处更改绘图周期
            } catch (InterruptedException ex) {Logger.getLogger(RealtimeChart.class.getName()).log(Level.SEVERE, null, ex);}
        }
        super.interrupt();
        this.interrupt();
    }
}