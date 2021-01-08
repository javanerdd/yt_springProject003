package com.example.spring03.controller.chart;

import java.io.FileOutputStream;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.spring03.service.chart.JFreeChartService;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
@RequestMapping("/jchart/*")
public class JFreeChartController {
	
	@Inject
	JFreeChartService chartService;
	
	@RequestMapping("chart1.do") //view ���� ���εǴ� �޼�Ʈ
	public void createChart1(HttpServletResponse response) { //ȭ�鿡 �ٷ� ����ؾߵǱ� ������ HttpServlet ���
		try {
			JFreeChart chart = chartService.createChart(); 
			//���񽺿��� ������ ��Ʈ�� �޾ƿ� ������ ����, ��Ʈ�� ���� ������ �ٷ� �̹������Ϸ� ����
			
			ChartUtils.writeChartAsPNG(response.getOutputStream(), chart, 900, 500);
			//��Ʈ�� �޾ƿͼ� ����,����, ���̸� �����صд�. view �ʿ���� ȭ�鿡 ��ٷ� ����� �ȴ�.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("chart2.do") //view���� ���εǴ� �޼ҵ�,pdf�� ���鶧 ���Ǵ� �޼�Ʈ
	public ModelAndView createChar2(HttpServletResponse response) {
		String message="";
		
		try {
			JFreeChart chart = chartService.createChart(); 
			Document document = new Document(); //�� �������� ������ �� �ִ� ��ü�� ����
			
			PdfWriter.getInstance(document, new FileOutputStream("c:/sample.pdf")); //pdf ������ ����� ���
			
			//pdf ���Ϸ� ��ȯ�ϱ� ���� ����
			document.open(); //pdf ������ ����
			Image png = Image.getInstance(
				  ChartUtils.encodeAsPNG(chart.createBufferedImage(500, 500))); // ��Ʈ ������ 500x500 �̹����� �����
			
			document.add(png);
			document.close();
			message="pdf������ �����Ǿ����ϴ�.";
			
		} catch (Exception e) {
			e.printStackTrace();
			message="pdf ���� ���� ����..";
		}
		return new ModelAndView("chart/jchart02","message",message);
		 //���� �������� ���� ���� �����ؼ� �����Ѵ�.
        //jchart02 �������� ���� ������ �޽����� ����Ѵ�. (pdf������ ������ �Ǿ�����, �ȵǾ�����..) 
	}
}
