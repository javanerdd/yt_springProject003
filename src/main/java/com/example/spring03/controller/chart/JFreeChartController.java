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
	
	@RequestMapping("chart1.do") //view 에서 맵핑되는 메소트
	public void createChart1(HttpServletResponse response) { //화면에 바로 출력해야되기 때문에 HttpServlet 사용
		try {
			JFreeChart chart = chartService.createChart(); 
			//서비스에서 생성한 차트를 받아와 변수에 저장, 차트를 얻어온 다음에 바로 이미지파일로 보냄
			
			ChartUtils.writeChartAsPNG(response.getOutputStream(), chart, 900, 500);
			//차트를 받아와서 가로,세로, 길이를 설정해둔다. view 필요없이 화면에 곧바로 출력이 된다.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("chart2.do") //view에서 맵핑되는 메소드,pdf를 만들때 사용되는 메소트
	public ModelAndView createChar2(HttpServletResponse response) {
		String message="";
		
		try {
			JFreeChart chart = chartService.createChart(); 
			Document document = new Document(); //웹 페이지로 접근할 수 있는 객체를 생성
			
			PdfWriter.getInstance(document, new FileOutputStream("c:/sample.pdf")); //pdf 파일이 저장될 경로
			
			//pdf 파일로 변환하기 위한 과정
			document.open(); //pdf 파일을 연다
			Image png = Image.getInstance(
				  ChartUtils.encodeAsPNG(chart.createBufferedImage(500, 500))); // 차트 내용을 500x500 이미지로 만들기
			
			document.add(png);
			document.close();
			message="pdf파일이 생성되었습니다.";
			
		} catch (Exception e) {
			e.printStackTrace();
			message="pdf 파일 생성 실패..";
		}
		return new ModelAndView("chart/jchart02","message",message);
		 //보낼 페이지와 보낼 값을 지정해서 리턴한다.
        //jchart02 페이지에 값을 보내서 메시지를 출력한다. (pdf파일이 생성이 되었는지, 안되었는지..) 
	}
}
