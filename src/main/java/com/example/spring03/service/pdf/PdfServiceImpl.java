package com.example.spring03.service.pdf;

import java.io.FileOutputStream;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.spring03.model.shop.dto.CartDTO;
import com.example.spring03.model.shop.service.CartService;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PdfServiceImpl implements PdfService {
	
	@Inject
	CartService cartService; //장바구이네 있느 ㄴ내용들을 pdf파일로 만들기 위해 장바구니 서브시객체를 사용하기 위해 의존성 주입
	
	@Override
	public String createPdf() {
		String result=""; //초기값이 null이 들어가면 오류가 발생될수 있기 때문에 공백을 지정
		
		try {
			Document document = new Document(); //pdf문서를 처리하는 객체
			
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:/sample.pdf"));
			//pdf 파일의 저장경로를  c드라이브의 sample.pdf로 한다는 뜻
			
			document.open(); // 웹페이지에 접근하는 객체를 연다.
			
			BaseFont baseFont = BaseFont.createFont("c:/windows/fonts/malgun.ttf",BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
			//pdf가 기본적으로 한글처리가 안되기 때문에 한글폰트 처리를 해줘야 한다.
			//createFont 메소드에 사용할 폰트의 경로 (batang.ttc 궁서체) 파일 경로를 지정해준다.
			
			Font font = new Font(baseFont,12); //폰트 사이즈 12픽셀
			PdfPTable table = new PdfPTable(4); //4개의 셀을 가진 테이블 객체를 생성 (pdf파일에 나타날 테이블)
			Chunk chunk = new Chunk("장바구니", font); // 타이틀의 이름으 ㄹ장바구니로 하고 위에 있는 font를 사용
			Paragraph ph = new Paragraph(chunk); //문단 객체를 만들어서
			ph.setAlignment(Element.ALIGN_CENTER); //문단 가운데 정렬
			document.add(ph); //문단을 만들어서 가운데 정렬
			
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE); // 줄바꿈. 제목에서 두줄 내린후에 테이블이 작성되게
			
			PdfPCell cell1 = new PdfPCell(new Phrase("상품명",font));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cell2 = new PdfPCell(new Phrase("단가",font));
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cell3 = new PdfPCell(new Phrase("수량",font));
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cell4 = new PdfPCell(new Phrase("금액",font));
			cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			table.addCell(cell1);
			table.addCell(cell2);
			table.addCell(cell3);
			table.addCell(cell4); //셀 4개로 만든 테이블에 셀 붙이기
			
			List<CartDTO> items = cartService.listCart("kang"); // 서비스로 부터 id값을 매개값으로 주어서 장바구니목록을 불러온다
			for(int i=0; i<items.size(); i++) {
				CartDTO dto = items.get(i); //레코드의 값을 꺼내 dto에 저장
				
				PdfPCell cellProductName = new PdfPCell(new Phrase(dto.getProduct_name(),font)); //상품정보를 하나씩 출력해서 셀에 넣고 테이블에 저장
				PdfPCell cellPrice = new PdfPCell(new Phrase(""+dto.getPrice(),font)); //Pharse타입은 숫자형(int)으로 하면 에러가 발생되기 때문에 ("")공백을 주어 String 타입으로 변경
				PdfPCell cellAmountCell = new PdfPCell(new Phrase(""+dto.getAmount(),font));
				PdfPCell cellMoney = new PdfPCell(new Phrase(""+dto.getMoney(),font));
				
				table.addCell(cellProductName);
				table.addCell(cellPrice);
				table.addCell(cellAmountCell);
				table.addCell(cellMoney);
			}
			document.add(table); // 웹접근 객체에 table를 저장한다.
			document.close(); // 객체를 닫는다.
			result ="pdf 파일이 생성되었습니다.";
		}catch (Exception e) {
			e.printStackTrace();
			result ="pdf파일 생성 실패...";
		}
		return result;
	}

}
