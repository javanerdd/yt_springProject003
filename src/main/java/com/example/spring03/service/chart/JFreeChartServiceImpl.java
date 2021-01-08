package com.example.spring03.service.chart;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.inject.Inject;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Service;

import com.example.spring03.model.shop.dao.CartDAO;
import com.example.spring03.model.shop.dto.CartDTO;

@Service
public class JFreeChartServiceImpl implements JFreeChartService {
	
	@Inject
	CartDAO cartDao; //DAO�� ȣ���ϱ� ���� �������� ���Խ�Ų��.

	@Override
	public JFreeChart createChart() {
		List<CartDTO> list = cartDao.cartMoney(); // DAO�� ����� ��ٱ��Ͽ� ���� �ݾ��� ����Ʈ�� ����
		
		//������Ʈ�� �ƴѰ����� ������Ʈ�϶��ʹ� Ŭ������ �ٸ���.
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(CartDTO dto :list) {
			dataset.setValue(dto.getMoney(), "����", dto.getProduct_name());
			//�ݾ�, ����, ��ǰ�̸��� ���� (�� 3���� ���� ����)
			
//		���� ��Ʈ�ΰ��
//		DefaultPieDataset dataset = new DefaultPieDataset();
//		for(CartDTO dto: list) {
//			dataset.setValue(dto.getProduct_name(), dto.getMoney());
//			//������Ʈ���� x,y���� ���� ������ ���� 2���� ����.
//		}
		}
		JFreeChart chart = null;
		String title="��ٱ��� ���"; // ��ٱ��� ����
			try {
				
//				�� �׷���
//				chart = ChartFactory.createLineChart(
//				title, "��ǰ��", "�ݾ�", dataset,
//				PlotOrientation.VERTICAL,true,true,false);
				
//				���� �׷���
				chart = ChartFactory.createBarChart( // ���������� ���� �׷��� 
						title,
						"��ǰ��",
						"�ݾ�",
						dataset,
						PlotOrientation.VERTICAL, //���η� ��Ʈ�� ����ٴ� �ǹ�
						true, true, false);

//				������Ʈ
//				chart =ChartFactory.createPieChart(
//						title,
//						dataset,
//						true, true, false);
				
//				����, Ÿ��Ʋ�� ��Ʈ�� �۾� ũ�⸦ ����
				chart.getTitle().setFont(
						new Font("����",Font.BOLD,15));
				
//				����, ������ ��Ʈ�� �۾� ũ�⸦ ����
				chart.getLegend().setItemFont(
						new Font("����",Font.PLAIN,10));
				
				Font font = new Font("����",Font.PLAIN,12);
				Color color = new Color(0,0,0);
				StandardChartTheme chartTheme =
						(StandardChartTheme) StandardChartTheme.createJFreeTheme(); //�׸� ����
				
				chartTheme.setExtraLargeFont(font); // ��Ʈ ũ�⿡ ���� �׸��� �ٸ��� ����
				chartTheme.setLargeFont(font);
				chartTheme.setRegularFont(font);
				chartTheme.setSmallFont(font);
				
				chartTheme.setAxisLabelPaint(color);// ��, ���� ������ ������ ����
				chartTheme.setLegendItemPaint(color);
				chartTheme.setItemLabelPaint(color);
				chartTheme.apply(chart);
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		
		return chart;
	}

}
