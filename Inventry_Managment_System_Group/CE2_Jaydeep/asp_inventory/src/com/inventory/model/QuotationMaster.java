package com.inventory.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="quotation_mst")
public class QuotationMaster {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int quotation_id;
	
	private String quotation_number;
	public String getQuotation_number() {
		return quotation_number;
	}

	public void setQuotation_number(String quotation_number) {
		this.quotation_number = quotation_number;
	}

	private Date quotation_date;
	
	private int total_amount;
	
	@Transient
	List<String> data = new ArrayList<>();
	

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}

	public int getQuotation_id() {
		return quotation_id;
	}

	public void setQuotation_id(int quotation_id) {
		this.quotation_id = quotation_id;
	}

	public Date getQuotation_date() {
		return quotation_date;
	}

	public void setQuotation_date(Date quotation_date) {
		this.quotation_date = quotation_date;
	}

	public int getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(int total_amount) {
		this.total_amount = total_amount;
	}
	
	

}
