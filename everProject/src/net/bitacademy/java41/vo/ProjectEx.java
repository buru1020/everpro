package net.bitacademy.java41.vo;


public class ProjectEx extends Project {
	private static final long serialVersionUID = 1L;
	
	protected int level;
	protected String plEmail;
	protected String plName;
	protected String plTel;
	
	
	
	public int getLevel() {
		return level;
	}

	public ProjectEx setLevel(int level) {
		this.level = level;
		return this;
	}
	public String getPlEmail() {
		return plEmail;
	}

	public ProjectEx setPlEmail(String plEmail) {
		this.plEmail = plEmail;
		return this;
	}

	public String getPlName() {
		return plName;
	}

	public ProjectEx setPlName(String plName) {
		this.plName = plName;
		return this;
	}

	public String getPlTel() {
		return plTel;
	}

	public ProjectEx setPlTel(String plTel) {
		this.plTel = plTel;
		return this;
	}

	public ProjectEx clone() {
		ProjectEx obj = new ProjectEx();
		obj.no = this.no;
		obj.title = this.title;
		obj.content = this.content;
		obj.startDate = this.startDate;
		obj.endDate = this.endDate;
		obj.tag = this.tag;
		obj.level = this.level;
		
		return obj;
	}
	
}
