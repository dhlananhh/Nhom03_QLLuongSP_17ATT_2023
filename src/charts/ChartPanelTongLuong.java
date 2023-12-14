package charts;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import entity.TongLuong;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ChartPanelTongLuong extends JPanel {
    private List<TongLuong> data;

    public ChartPanelTongLuong(List<TongLuong> data) {
        this.data = data;
        createChart();
    }

    private void createChart() {
        CategoryDataset dataset = createDataset();
        JFreeChart chart = ChartFactory.createBarChart(
                "Biểu đồ tổng lương qua từng tháng",
                "Tháng",
                "Tổng lương",
                dataset
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(600, 400));
        add(chartPanel);
    }

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (TongLuong tongLuong : data) {
            dataset.addValue(tongLuong.getTongLuong(), "Tổng lương", "" + tongLuong.getThang());
        }

        return dataset;
    }
}
