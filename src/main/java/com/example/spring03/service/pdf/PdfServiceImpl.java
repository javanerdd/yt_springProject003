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
	CartService cartService; //��ٱ��̳� �ִ� ��������� pdf���Ϸ� ����� ���� ��ٱ��� ����ð�ü�� ����ϱ� ���� ������ ����
	
	@Override
	public String createPdf() {
		String result=""; //�ʱⰪ�� null�� ���� ������ �߻��ɼ� �ֱ� ������ ������ ����
		
		try {
			Document document = new Document(); //pdf������ ó���ϴ� ��ü
			
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:/sample.pdf"));
			//pdf ������ �����θ�  c����̺��� sample.pdf�� �Ѵٴ� ��
			
			document.open(); // ���������� �����ϴ� ��ü�� ����.
			
			BaseFont baseFont = BaseFont.createFont("c:/windows/fonts/malgun.ttf",BaseFont.IDENTITY_H,BaseFont.EMBEDDED);
			//pdf�� �⺻������ �ѱ�ó���� �ȵǱ� ������ �ѱ���Ʈ ó���� ����� �Ѵ�.
			//createFont �޼ҵ忡 ����� ��Ʈ�� ��� (batang.ttc �ü�ü) ���� ��θ� �������ش�.
			
			Font font = new Font(baseFont,12); //��Ʈ ������ 12�ȼ�
			PdfPTable table = new PdfPTable(4); //4���� ���� ���� ���̺� ��ü�� ���� (pdf���Ͽ� ��Ÿ�� ���̺�)
			Chunk chunk = new Chunk("��ٱ���", font); // Ÿ��Ʋ�� �̸��� ����ٱ��Ϸ� �ϰ� ���� �ִ� font�� ���
			Paragraph ph = new Paragraph(chunk); //���� ��ü�� ����
			ph.setAlignment(Element.ALIGN_CENTER); //���� ��� ����
			document.add(ph); //������ ���� ��� ����
			
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE); // �ٹٲ�. ���񿡼� ���� �����Ŀ� ���̺��� �ۼ��ǰ�
			
			PdfPCell cell1 = new PdfPCell(new Phrase("��ǰ��",font));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cell2 = new PdfPCell(new Phrase("�ܰ�",font));
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cell3 = new PdfPCell(new Phrase("����",font));
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cell4 = new PdfPCell(new Phrase("�ݾ�",font));
			cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			table.addCell(cell1);
			table.addCell(cell2);
			table.addCell(cell3);
			table.addCell(cell4); //�� 4���� ���� ���̺� �� ���̱�
			
			List<CartDTO> items = cartService.listCart("kang"); // ���񽺷� ���� id���� �Ű������� �־ ��ٱ��ϸ���� �ҷ��´�
			for(int i=0; i<items.size(); i++) {
				CartDTO dto = items.get(i); //���ڵ��� ���� ���� dto�� ����
				
				PdfPCell cellProductName = new PdfPCell(new Phrase(dto.getProduct_name(),font)); //��ǰ������ �ϳ��� ����ؼ� ���� �ְ� ���̺� ����
				PdfPCell cellPrice = new PdfPCell(new Phrase(""+dto.getPrice(),font)); //PharseŸ���� ������(int)���� �ϸ� ������ �߻��Ǳ� ������ ("")������ �־� String Ÿ������ ����
				PdfPCell cellAmountCell = new PdfPCell(new Phrase(""+dto.getAmount(),font));
				PdfPCell cellMoney = new PdfPCell(new Phrase(""+dto.getMoney(),font));
				
				table.addCell(cellProductName);
				table.addCell(cellPrice);
				table.addCell(cellAmountCell);
				table.addCell(cellMoney);
			}
			document.add(table); // ������ ��ü�� table�� �����Ѵ�.
			document.close(); // ��ü�� �ݴ´�.
			result ="pdf ������ �����Ǿ����ϴ�.";
		}catch (Exception e) {
			e.printStackTrace();
			result ="pdf���� ���� ����...";
		}
		return result;
	}

}
