package com.inventory.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "item_location")
public class ItemLocation {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int itemlocation_id;
	
	private int item_id, section_number, item_quant;
	
	private String section_name;

	public int getItemlocation_id() {
		return itemlocation_id;
	}

	public void setItemlocation_id(int itemlocation_id) {
		this.itemlocation_id = itemlocation_id;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public int getSection_number() {
		return section_number;
	}

	public void setSection_number(int section_number) {
		this.section_number = section_number;
	}

	public String getSection_name() {
		return section_name;
	}
	

	public int getItem_quant() {
		return item_quant;
	}

	public void setItem_quant(int item_quant) {
		this.item_quant = item_quant;
	}

	public void setSection_name(String section_name) {
		this.section_name = section_name;
	}
	
	
}
