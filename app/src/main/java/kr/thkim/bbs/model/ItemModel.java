package kr.thkim.bbs.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

// 아이템 db
public class ItemModel {

	@SerializedName("drop")
	private List<AnimalDropModel> drop;

	@SerializedName("acq_loc")
	private List<AcqLocModel> acqLoc;

	@SerializedName("eng_desc")
	private String engDesc;

	@SerializedName("equip")
	private String equip;

	@SerializedName("grade")
	private String grade;

	@SerializedName("effect")
	private List<EffectModel> effect;

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

	public List<AnimalDropModel> getDrop(){
		return drop;
	}

	public List<AcqLocModel> getAcqLoc(){
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

	public List<EffectModel> getEffect(){
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