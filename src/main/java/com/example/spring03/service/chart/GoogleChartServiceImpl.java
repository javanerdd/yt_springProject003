package com.example.spring03.service.chart;

import java.util.List;

import javax.inject.Inject;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.example.spring03.model.shop.dto.CartDTO;
import com.example.spring03.model.shop.service.CartService;

@Service
public class GoogleChartServiceImpl implements GoogleChartService {
	
	@Inject
	CartService cartService;
	
	@Override
	public JSONObject getChartData() { // json object�� �����ϴ°�
		//getChartData �޼ҵ带 ȣ���ϸ�
		//db���� ����Ʈ�� �޾ƿ���, �޾ƿ°ɷ� json�������� ���� �������ְԵȴ�
		
		List<CartDTO> items = cartService.cartMoney();
		
		JSONObject data = new JSONObject(); //������ json��ü

		
		JSONObject col1 = new JSONObject(); //json�� �÷� ��ü
		JSONObject col2 = new JSONObject();
		
		JSONArray title = new JSONArray(); //json �迭 ��ü , �迭�� �����Ҷ� JSONArray()��ü ���
		col1.put("label","��ǰ��");
		col1.put("type","string");
		col2.put("label","�ݾ�");
		col2.put("type", "number");
		
		title.add(col1); //Ÿ��Ʋ�࿡ �÷� �߰�
		title.add(col2);
		
		data.put("cols", title);
		//{"cols": [{"label":"��ǰ��","type":"string"}
		//,{"label":"�ݾ�","type":"number"}]}
		
		JSONArray body = new JSONArray(); //rows
		
		for(CartDTO dto:items) { //items�� ����� ���� dto�� �ݺ����� ������ �ϳ��� ����
			
			JSONObject name = new JSONObject();
			name.put("v", dto.getProduct_name()); //��ǰ��
			
			JSONObject money = new JSONObject();
			money.put("v", dto.getMoney()); //�ݾ�
			
			JSONArray row = new JSONArray();
			row.add(name);
			row.add(money);
			
			JSONObject cell = new JSONObject();
			cell.put("c", row);
			body.add(cell);
		}
		data.put("rows", body);
		
		
		return data;
	}
}
