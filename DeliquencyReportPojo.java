package com.javainuse.step;

import java.sql.Date;

import org.springframework.stereotype.Component;



@Component("deliquencyReportPojo")
public class DeliquencyReportPojo {
	
	public int id;
	public String rpt_cycle;
	public Date date;
	public String flag_hldy;
	public String dd_seq;
	public String fnl_seq;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRpt_cycle() {
		return rpt_cycle;
	}
	public void setRpt_cycle(String rpt_cycle) {
		this.rpt_cycle = rpt_cycle;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getFlag_hldy() {
		return flag_hldy;
	}
	public void setFlag_hldy(String flag_hldy) {
		this.flag_hldy = flag_hldy;
	}
	public String getDd_seq() {
		return dd_seq;
	}
	public void setDd_seq(String dd_seq) {
		this.dd_seq = dd_seq;
	}
	public String getFnl_seq() {
		return fnl_seq;
	}
	public void setFnl_seq(String fnl_seq) {
		this.fnl_seq = fnl_seq;
	}
	@Override
	public String toString() {
		return "DeliquencyReportBO [id=" + id + ", rpt_cycle=" + rpt_cycle + ", date=" + date + ", flag_hldy="
				+ flag_hldy + ", dd_seq=" + dd_seq + ", fnl_seq=" + fnl_seq + "]";
	}
	

}
