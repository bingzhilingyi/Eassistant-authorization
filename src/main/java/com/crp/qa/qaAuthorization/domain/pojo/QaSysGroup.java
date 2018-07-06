package com.crp.qa.qaAuthorization.domain.pojo;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * @author huangyue
 * @date 20180702
 * @description this is the pojo of group
 */
@Entity
@Table(name="qa_sys_group",schema="qa")
public class QaSysGroup {

	private Integer groupId;
	private String groupName; //group name
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
	
	private Set<QaSysGroupRights> qaSysGroupRights;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="group_id")
    public Integer getGroupId(){
        return groupId;
    }
    public void setGroupId(Integer groupId){
        this.groupId = groupId;
    }

    @Column(name="group_name",length=40,nullable=false,unique=true)
    public String getGroupName(){
        return groupName;
    }
    public void setGroupName(String groupName){
        this.groupName= groupName; 
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
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "rights_group_id", referencedColumnName = "group_id")
	public Set<QaSysGroupRights> getQaSysGroupRights() {
		return qaSysGroupRights;
	}
	public void setQaSysGroupRights(Set<QaSysGroupRights> qaSysGroupRights) {
		this.qaSysGroupRights = qaSysGroupRights;
	}
	
	
}

