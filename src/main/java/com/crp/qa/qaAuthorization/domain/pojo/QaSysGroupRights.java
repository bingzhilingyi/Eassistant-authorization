package com.crp.qa.qaAuthorization.domain.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="qa_sys_group_rights",schema="qa")
public class QaSysGroupRights {

	private Integer rightsId;
	private Integer rightsGroupId;
	private String rightsCode;
	private String rightsCreate;
	private String rightsUpdate;
	private String rightsSearch;
	private String rightsDelete;
	private Integer createdBy;
	private Date creationDate;
	private Integer lastUpdatedBy;
	private Date lastUpdateDate;
	private String attribute1;
	private String attribute2;
	private String attribute3;
	private String attribute4;
	private String attribute5;
	private String attribute6;
	private String attribute7;
	private String attribute8;
	private String attribute9;
	private String attribute10;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="rights_id")
	public Integer getRightsId() {
		return rightsId;
	}

	public void setRightsId(Integer rightsId) {
		this.rightsId = rightsId;
	}
	
	@Column(name="rights_group_id")
	public Integer getRightsGroupId() {
		return rightsGroupId;
	}
	public void setRightsGroupId(Integer rightsGroupId) {
		this.rightsGroupId = rightsGroupId;
	}
	
	@Column(name="rights_code")
	public String getRightsCode() {
		return rightsCode;
	}
	public void setRightsCode(String rightsCode) {
		this.rightsCode = rightsCode;
	}
	
	@Column(name="rights_create")
	public String getRightsCreate() {
		return rightsCreate;
	}
	public void setRightsCreate(String rightsCreate) {
		this.rightsCreate = rightsCreate;
	}
	
	@Column(name="rights_update")
	public String getRightsUpdate() {
		return rightsUpdate;
	}
	public void setRightsUpdate(String rightsUpdate) {
		this.rightsUpdate = rightsUpdate;
	}
	
	@Column(name="rights_search")
	public String getRightsSearch() {
		return rightsSearch;
	}
	public void setRightsSearch(String rightsSearch) {
		this.rightsSearch = rightsSearch;
	}
	
	@Column(name="rights_delete")
	public String getRightsDelete() {
		return rightsDelete;
	}
	public void setRightsDelete(String rightsDelete) {
		this.rightsDelete = rightsDelete;
	}

	@Column(name="created_by")
	public Integer getCreatedBy() {
		return createdBy;
	}
	
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name="creation_date")
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	@Column(name="last_updated_by")
	public Integer getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(Integer lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	
	@Column(name="last_update_date")
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	
	@Column(name="attribute_1",length=200)
	public String getAttribute1() {
		return attribute1;
	}
	public void setAttribute1(String attribute1) {
		this.attribute1 = attribute1;
	}
	
	@Column(name="attribute_2",length=200)
	public String getAttribute2() {
		return attribute2;
	}
	public void setAttribute2(String attribute2) {
		this.attribute2 = attribute2;
	}
	
	@Column(name="attribute_3",length=200)
	public String getAttribute3() {
		return attribute3;
	}
	public void setAttribute3(String attribute3) {
		this.attribute3 = attribute3;
	}
	
	@Column(name="attribute_4",length=200)
	public String getAttribute4() {
		return attribute4;
	}
	public void setAttribute4(String attribute4) {
		this.attribute4 = attribute4;
	}
	
	@Column(name="attribute_5",length=200)
	public String getAttribute5() {
		return attribute5;
	}
	public void setAttribute5(String attribute5) {
		this.attribute5 = attribute5;
	}
	
	@Column(name="attribute_6",length=200)
	public String getAttribute6() {
		return attribute6;
	}
	public void setAttribute6(String attribute6) {
		this.attribute6 = attribute6;
	}
	
	@Column(name="attribute_7",length=200)
	public String getAttribute7() {
		return attribute7;
	}
	public void setAttribute7(String attribute7) {
		this.attribute7 = attribute7;
	}
	
	@Column(name="attribute_8",length=200)
	public String getAttribute8() {
		return attribute8;
	}
	public void setAttribute8(String attribute8) {
		this.attribute8 = attribute8;
	}
	
	@Column(name="attribute_9",length=200)
	public String getAttribute9() {
		return attribute9;
	}
	public void setAttribute9(String attribute9) {
		this.attribute9 = attribute9;
	}
	
	@Column(name="attribute_10",length=200)
	public String getAttribute10() {
		return attribute10;
	}
	public void setAttribute10(String attribute10) {
		this.attribute10 = attribute10;
	}
}
