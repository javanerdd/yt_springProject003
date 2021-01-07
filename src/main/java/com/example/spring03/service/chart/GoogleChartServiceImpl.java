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
	public JSONObject getChartData() { // json object를 리턴하는것
		//getChartData 메소드를 호출하면
		//db에서 리스트를 받아오고, 받아온걸로 json형식으로 만들어서 리턴해주게된다
		
		List<CartDTO> items = cartService.cartMoney();
		
		JSONObject data = new JSONObject(); //리턴할 json객체

		
		JSONObject col1 = new JSONObject(); //json의 컬럼 객체
		JSONObject col2 = new JSONObject();
		
		JSONArray title = new JSONArray(); //json 배열 객체 , 배열에 저장할때 JSONArray()객체 사용
		col1.put("label","상품명");
		col1.put("type","string");
		col2.put("label","금액");
		col2.put("type", "number");
		
		title.add(col1); //타이틀행에 컬럼 추가
		title.add(col2);
		
		data.put("cols", title);
		//{"cols": [{"label":"상품명","type":"string"}
		//,{"label":"금액","type":"number"}]}
		
		JSONArray body = new JSONArray(); //rows
		
		for(CartDTO dto:items) { //items에 저장된 값을 dto로 반복문을 돌려서 하나씩 저장
			
			JSONObject name = new JSONObject();
			name.put("v", dto.getProduct_name()); //상품명
			
			JSONObject money = new JSONObject();
			money.put("v", dto.getMoney()); //금액
			
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
