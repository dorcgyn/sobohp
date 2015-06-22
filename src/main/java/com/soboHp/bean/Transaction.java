package com.soboHp.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

public class Transaction {
	
	public Transaction() {
		
	}
	
	public Transaction(ResultSet rs, Map<Integer, User>userMap) throws SQLException {
		setId(rs.getInt("id"));
        setLender_id(rs.getInt("lender_id"));
        setLender(userMap.get(lender_id));
        setBorrow_id(rs.getInt("borrow_id"));
        setBorrower(userMap.get(borrow_id));
        setGenerator_id(rs.getInt("generator_id"));
        setGenerator(userMap.get(generator_id));
        setType(rs.getString("type"));
        
        setLocation(rs.getString("location"));
        setAmount(rs.getFloat("amount"));
        setComment(rs.getString("comment"));
        setStatus(rs.getString("status"));
	
        setCreated_when(rs.getTimestamp("created_when"));
        setTran_date(rs.getDate("tran_date"));
	}
	
	public Date getCreated_when() {
		return created_when;
	}

	public void setCreated_when(Date created_when) {
		this.created_when = created_when;
	}

	private int id;
	private int lender_id;
	private User lender;
	
	private int borrow_id;
	private User borrower;
	
	private int generator_id;
	private User generator;
	
	
	private String type;
	private Date tran_date;
	private Date created_when;
	private String location;
	private float amount;
	private String comment;
	private String status;
	private int rid;
	
	public User getLender() {
		return lender;
	}
	public void setLender(User lender) {
		this.lender = lender;
	}
	public User getBorrower() {
		return borrower;
	}
	public void setBorrower(User borrower) {
		this.borrower = borrower;
	}

	
	public int getGenerator_id() {
		return generator_id;
	}
	public void setGenerator_id(int generator_id) {
		this.generator_id = generator_id;
	}
	public User getGenerator() {
		return generator;
	}
	public void setGenerator(User generator) {
		this.generator = generator;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLender_id() {
		return lender_id;
	}
	public void setLender_id(int lender_id) {
		this.lender_id = lender_id;
	}
	public int getBorrow_id() {
		return borrow_id;
	}
	public void setBorrow_id(int borrow_id) {
		this.borrow_id = borrow_id;
	}
	public Date getTran_date() {
		return tran_date;
	}
	public void setTran_date(Date tran_date) {
		this.tran_date = tran_date;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	
	public int getTargetUserId() {
		User user = getTargetUser();
		if (user == null) {
			return -1;
		}
		else {
			return user.getId();
		}
	}
	public User getTargetUser() {
		if (generator_id == borrow_id) {
			return lender;
		}
		else if (generator_id == lender_id) {
			return borrower;
		}
		else {
			// left for future use
			return null;
		}
	}
	
	public String toEmail() {
		return "\n"
				+ "Lender:\t" + lender.getUsername() + "\n"
				+ "Borrower:\t" + borrower.getUsername() + "\n"
				+ "Location:\t" + location + "\n"
				+ "Amount:\t" + amount + "\n"
				+ "Generator:\t" + generator.getUsername() + "\n"
				+ "Date:\t" + tran_date + "\n";
	}
}
