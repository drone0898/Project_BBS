package kr.thkim.jsonutil.from;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ItemDTO implements Serializable {

	@SerializedName("id")
	private int id;

	@SerializedName("eng_name")
	private String engName;

	@SerializedName("name")
	private String name;

	@SerializedName("eng_desc")
	private String engDesc;

	@SerializedName("desc")
	private String desc;

	@SerializedName("grade")
	private String grade;

	@SerializedName("equip")
	private String equip;

	@SerializedName("kinds")
	private String kinds;

	@SerializedName("effect")
	private String effect;

	@SerializedName("drop")
	private String drop;

	@SerializedName("acq_loc")
	private String acqLoc;

	@SerializedName("comb_mat")
	private String combMat;

	@SerializedName("number")
	private int acqNumber;

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setEngName(String engName){
		this.engName = engName;
	}

	public String getEngName(){
		return engName;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setGrade(String grade){
		this.grade = grade;
	}

	public String getGrade(){
		return grade;
	}

	public void setEquip(String equip){
		this.equip = equip;
	}

	public String getEquip(){
		return equip;
	}

	public void setKinds(String kinds){
		this.kinds = kinds;
	}

	public String getKinds(){
		return kinds;
	}

	public void setEffect(String effect){
		this.effect = effect;
	}

	public String getEffect(){
		return effect;
	}

	public void setDrop(String drop){
		this.drop = drop;
	}

	public String getDrop(){
		return drop;
	}

	public void setAcqLoc(String acqLoc){
		this.acqLoc = acqLoc;
	}

	public String getAcqLoc(){
		return acqLoc;
	}

	public void setCombMat(String combMat){
		this.combMat = combMat;
	}

	public String getCombMat(){
		return combMat;
	}

	public void setAcqNumber(int acqNumber){
		this.acqNumber = acqNumber;
	}

	public int getAcqNumber(){
		return acqNumber;
	}

	public String getEngDesc() {
		return engDesc;
	}

	public void setEngDesc(String engDesc) {
		this.engDesc = engDesc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
 	public String toString(){
		return 
			"ItemsDTO{" + 
			"id = '" + id + '\'' + 
			",eng_name = '" + engName + '\'' + 
			",name = '" + name + '\'' + 
			",grade = '" + grade + '\'' + 
			",equip = '" + equip + '\'' + 
			",kinds = '" + kinds + '\'' + 
			",effect = '" + effect + '\'' + 
			",drop = '" + drop + '\'' + 
			",acq_loc = '" + acqLoc + '\'' + 
			",comb_mat = '" + combMat + '\'' + 
			",acqNumber = '" + acqNumber + '\'' +
			"}";
		}
}