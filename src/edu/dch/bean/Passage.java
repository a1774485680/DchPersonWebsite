package edu.dch.bean;

import org.springframework.stereotype.Component;

@Component("Passage")
public class Passage {
	public int pid;
	public int uid;
	public String Iid;
	public String path;
	public String ptitle;
	public String pdate;
	public String pclassify;
	public String pbrief;
	public int	plike;
	public int	pvisit;
	private String username;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Passage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Passage(int uid, String iid, String path, String ptitle, String pdate, String pclassify,String pbrief, int plike,
			int pvisit) {
		super();
		this.pbrief=pbrief;
		this.uid = uid;
		Iid = iid;
		this.path = path;
		this.ptitle = ptitle;
		this.pdate = pdate;
		this.pclassify = pclassify;
		this.plike = plike;
		this.pvisit = pvisit;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getIid() {
		return Iid;
	}
	public void setIid(String iid) {
		Iid = iid;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getPtitle() {
		return ptitle;
	}
	public void setPtitle(String ptitle) {
		this.ptitle = ptitle;
	}
	public String getPdate() {
		return pdate;
	}
	public void setPdate(String pdate) {
		this.pdate = pdate;
	}
	public String getPclassify() {
		return pclassify;
	}
	public void setPclassify(String pclassify) {
		this.pclassify = pclassify;
	}
	public int getPlike() {
		return plike;
	}
	public void setPlike(int plike) {
		this.plike = plike;
	}
	public int getPvisit() {
		return pvisit;
	}
	public void setPvisit(int pvisit) {
		this.pvisit = pvisit;
	}
	public String getPbrief() {
		return pbrief;
	}
	public void setPbrief(String pbrief) {
		this.pbrief = pbrief;
	}
	@Override
	public String toString() {
		return "Passage [pid=" + pid + ", uid=" + uid + ", Iid=" + Iid + ", path=" + path + ", ptitle=" + ptitle
				+ ", pdate=" + pdate + ", pclassify=" + pclassify + ", pbrief=" + pbrief + ", plike=" + plike
				+ ", pvisit=" + pvisit + "]";
	}	
}
