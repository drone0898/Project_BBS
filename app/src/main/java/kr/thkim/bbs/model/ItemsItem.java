package kr.thkim.bbs.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ItemsItem{

	@SerializedName("drop")
	private List<DropItem> drop;

	@SerializedName("acq_loc")
	private List<AcqLocItem> acqLoc;

	@SerializedName("eng_desc")
	private String engDesc;

	@SerializedName("equip")
	private String equip;

	@SerializedName("grade")
	private String grade;

	@SerializedName("effect")
	private List<EffectItem> effect;

	@SerializedName("name")
	private String name;

	@SerializedName("comb_mat")
	private List<Object> combMat;

	@SerializedName("acqNumber")
	private int acqNumber;

	@SerializedName("kinds")
	private String kinds;

	@SerializedName("id")
	private int id;

	@SerializedName("desc")
	private String desc;

	public List<DropItem> getDrop(){
		return drop;
	}

	public List<AcqLocItem> getAcqLoc(){
		return acqLoc;
	}

	public String getEngDesc(){
		return engDesc;
	}

	public String getEquip(){
		return equip;
	}

	public String getGrade(){
		return grade;
	}

	public List<EffectItem> getEffect(){
		return effect;
	}

	public String getName(){
		return name;
	}

	public List<Object> getCombMat(){
		return combMat;
	}

	public int getAcqNumber(){
		return acqNumber;
	}

	public String getKinds(){
		return kinds;
	}

	public int getId(){
		return id;
	}

	public String getDesc(){
		return desc;
	}
}