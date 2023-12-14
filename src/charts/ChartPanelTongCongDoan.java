package charts;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import entity.TongCongDoan;
import entity.TongLuong;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ChartPanelTongCongDoan extends JPanel {
    private List<TongCongDoan> data;

    public ChartPanelTongCongDoan(List<TongCongDoan> data) {
        this.data = data;
        createChart();
    }

    private void createChart() {
        CategoryDataset dataset = createDataset();
        JFreeChart chart = ChartFactory.createBarChart(
                "Biểu đồ tổng sản phẩm hoàn thành qua từng tháng",
                "Tháng",
                "Tổng sản phẩm",
                dataset
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(600, 400));
        add(chartPanel);
    }

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (TongCongDoan tongCD : data) {
            dataset.addValue(tongCD.getSoLuongHoanThanh(), "Số lượng", "" + tongCD.getThang());
        }

        return dataset;
    }
}
