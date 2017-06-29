package com.inventory.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "quotation_items")
public class QuotationItems {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int quotation_item_id;
	
	private int quotation_id;
	
	private int item_id;
	
	private int quantity;
	
	private int ppi;
	
	private int total;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getQuotation_item_id() {
		return quotation_item_id;
	}

	public void setQuotation_item_id(int quotation_item_id) {
		this.quotation_item_id = quotation_item_id;
	}

	public int getQuotation_id() {
		return quotation_id;
	}

	public void setQuotation_id(int quotation_id) {
		this.quotation_id = quotation_id;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPpi() {
		return ppi;
	}

	public void setPpi(int ppi) {
		this.ppi = ppi;
	}
	
	

}
