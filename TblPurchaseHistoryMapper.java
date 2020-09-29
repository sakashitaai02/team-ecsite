package jp.co.internous.grapes.model.mapper;

import java.util.List;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import jp.co.internous.grapes.model.domain.dto.PurchaseHistoryDto;

@Mapper
public interface TblPurchaseHistoryMapper {
	int insert(Map<String, Object> parameter);
	
	//TO DO userId によって履歴を検索し、PurchaseHistoryDto のリスト返却するメソッドの宣言
	List<PurchaseHistoryDto> findByUserId(@Param("userId") int userId);
	
	//TO DO 購入履歴をDBの購入履歴情報テーブルから論理削除(tbl_purchase_history.statusを0に更新)
	@Update("UPDATE tbl_purchase_history SET status = 0, updated_at = now() WHERE user_id = #{userId}")
	
	//Controllerから呼び出すためのメソッドの定義
	int logicalDeleteByUserId(@Param("userId") int userId);
}
