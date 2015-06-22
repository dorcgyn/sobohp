package com.soboHp.util;

import java.util.ArrayList;
import java.util.List;

import com.soboHp.bean.Transaction;
import com.soboHp.bean.User;
import com.soboHp.dao.TranDao;

public class TranUtil {
	public static int calculateBalance(ArrayList<Transaction> trans, User current) {
		int totalBalance = 0;
		int currentId = current.getId();
		for (Transaction tran : trans) {
			if (tran.getLender_id() == currentId) {
				totalBalance += tran.getAmount();
			}
			else if (tran.getBorrow_id() == currentId) {
				totalBalance -= tran.getAmount();
			}
		}
		return totalBalance;
	}
	
	public static ArrayList<Transaction> filterByLimit(List<Transaction> trans, int limit) {
		ArrayList<Transaction> display = new ArrayList<Transaction>();
		for (int i = trans.size() - limit; i < trans.size(); i++) {
			display.add(trans.get(i));
		}
		return display;
	}
}
