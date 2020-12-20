package kr.thkim.jsonutil.to;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import kr.thkim.jsonutil.from.ItemDTO;

public class ToItemDTO implements Serializable {

	@SerializedName("id")
	private int id;

	@SerializedName("eng_name")
	private String engName;

	@SerializedName("eng_desc")
	private String engDesc;

	@SerializedName("desc")
	private String desc;

	@SerializedName("name")
	private String name;

	@SerializedName("grade")
	private String grade;

	@SerializedName("equip")
	private String equip;

	@SerializedName("kinds")
	private String kinds;

	@SerializedName("effect")
	private List<EffectDTO> effect;

	@SerializedName("drop")
	private List<DropDTO> drop;

	@SerializedName("acq_loc")
	private List<AcqDTO> acqLoc;

	@SerializedName("comb_mat")
	private List<CombDTO> combMat;

	@SerializedName("acqNumber")
	private int acqNumber; // 획득개수 (조합,습득 실행시 몇개가 나오는지)

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEngName() {
		return engName;
	}

	public void setEngName(String engName) {
		this.engName = engName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getEquip() {
		return equip;
	}

	public void setEquip(String equip) {
		this.equip = equip;
	}

	public String getKinds() {
		return kinds;
	}

	public void setKinds(String kinds) {
		this.kinds = kinds;
	}

	public List<EffectDTO> getEffect() {
		return effect;
	}

	public void setEffect(List<EffectDTO> effect) {
		this.effect = effect;
	}

	public List<DropDTO> getDrop() {
		return drop;
	}

	public void setDrop(List<DropDTO> drop) {
		this.drop = drop;
	}

	public List<AcqDTO> getAcqLoc() {
		return acqLoc;
	}

	public void setAcqLoc(List<AcqDTO> acqLoc) {
		this.acqLoc = acqLoc;
	}

	public List<CombDTO> getCombMat() {
		return combMat;
	}

	public void setCombMat(List<CombDTO> combMat) {
		this.combMat = combMat;
	}

	public int getAcqNumber() {
		return acqNumber;
	}

	public void setAcqNumber(int acqNumber) {
		this.acqNumber = acqNumber;
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

	public ToItemDTO(ItemDTO item){
		List<EffectDTO> effects = new ArrayList<>();
		List<DropDTO> drops = new ArrayList<>();
		List<AcqDTO> acqs = new ArrayList<>();
		List<CombDTO> combs = new ArrayList<>();

		this.effect = effects;
		this.drop = drops;
		this.acqLoc = acqs;
		this.combMat = combs;

		this.id = item.getId();
		this.engName = item.getEngName();
		this.name = item.getName();
		this.engDesc = item.getEngDesc();
		this.desc = item.getDesc();
		this.acqNumber = item.getAcqNumber();
		this.grade = item.getGrade();
		this.equip = item.getEquip();
		this.kinds = item.getKinds();
	}
}