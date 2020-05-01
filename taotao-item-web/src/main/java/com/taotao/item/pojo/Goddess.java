package com.taotao.item.pojo;

import java.util.Date;

/**
 * 
 * @author zhaoyuan
 * @date 2019年5月1日 下午10:13:44
 */
public class Goddess {

	private int id;
	private String name;
	private String address;
	private Date date;
	private String work;

	public Goddess(){
		super();
	}
	
	public Goddess(int id, String name, String address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
	}

	public Goddess(int id, String name, String address,Date date) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.date = date;
	}
	
	public Goddess(int id, String name, String address, Date date, String work) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.date = date;
		this.work = work;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}
}
