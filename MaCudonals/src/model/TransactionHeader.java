package model;

import java.sql.Timestamp;

public class TransactionHeader {
	private Integer transactionId;
	private Integer userId;
	private Timestamp date;
	
	public TransactionHeader(Integer transactionId, Integer userId, Timestamp date) {
		super();
		this.transactionId = transactionId;
		this.userId = userId;
		this.date = date;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}
}
