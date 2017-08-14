package Tool.Forensics_Log_Analyzer;
/**
*
* @author g_chawla
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
 
 
public class Pie extends Application {
 
    @Override public void start(Stage stage) {
        stage.setTitle("Load Per Hour");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Hours");       
        
        final LineChart<String,Number> lineChart = 
                new LineChart<String,Number>(xAxis,yAxis);
                
        lineChart.setTitle("Load Per Hour");                                
        XYChart.Series series = new XYChart.Series();        
        series.setName("Load Per Hour");
        
//Testing        
        String FILENAME = "output/part-r-00000";        
        BufferedReader br = null;
		FileReader fr = null;
		try {
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);
			String sCurrentLine;
			br = new BufferedReader(new FileReader(FILENAME));
//			data.append("Data After Analysis:\n");
//			data.append("Token Details"+"   "+"Number Of Records");
			while ((sCurrentLine = br.readLine()) != null) {
			String delims = "[	]+";
			String[] tokens = sCurrentLine.split(delims);
			for (int i = 0; i < tokens.length; i++)
				{
//				data.append("\n");
//				data.append(tokens[i].toString());
//				data.append("               ");				
//				data.append(tokens[i].toString());
				series.getData().add(new XYChart.Data(tokens[i].toString(), Integer.parseInt(tokens[i+1].toString())));
				i++;
				}
			}
		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

//Testing		
        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);
       
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main() {
        launch();
    }
}