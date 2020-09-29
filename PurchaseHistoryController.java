package jp.co.internous.grapes.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.internous.grapes.model.domain.dto.PurchaseHistoryDto;
import jp.co.internous.grapes.model.mapper.TblPurchaseHistoryMapper;
import jp.co.internous.grapes.model.session.LoginSession;

@Controller
@RequestMapping("/grapes/history")
public class PurchaseHistoryController {
	
	//インスタンス生成し、利用できるようにする
	@Autowired
	private LoginSession loginSession;
	
	@Autowired
	private TblPurchaseHistoryMapper purchaseHistoryMapper;
	
	@RequestMapping("/")
	public String index(Model m) {
		
		//ログインセッションよりユーザーID取得
		int userId = loginSession.getUserId();
		
		//ユーザーに紐づく購入履歴情報を取得
		List<PurchaseHistoryDto> historyList = purchaseHistoryMapper.findByUserId(userId);
		
		m.addAttribute("historyList", historyList);
		m.addAttribute("loginSession",loginSession);
		
		return "purchase_history"; 
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public boolean delete() {
		//ログインセッションよりユーザーIDを取得
		int userId = loginSession.getUserId();
		int result = purchaseHistoryMapper.logicalDeleteByUserId(userId);
		
		return result > 0;
	}
}
