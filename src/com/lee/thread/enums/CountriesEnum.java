package com.lee.thread.enums;

/**
 * @description 枚举的工程代码
 * @author Lee
 * @date 2018年3月18日
 */
public enum CountriesEnum {
	ONE(1, "楚"), TWO(2, "齐"), THREE(3, "韩"), FORE(4, "魏"), FIVE(5, "赵"), SIX(6, "燕");
	private Integer reCode;
	private String reMsg;

	private CountriesEnum(Integer reCode, String reMsg) {
		this.reCode = reCode;
		this.reMsg = reMsg;
	}

	public Integer getReCode() {
		return reCode;
	}

	public void setReCode(Integer reCode) {
		this.reCode = reCode;
	}

	public String getReMsg() {
		return reMsg;
	}

	public void setReMsg(String reMsg) {
		this.reMsg = reMsg;
	}

	// 遍历
	public static CountriesEnum getCountry(Integer index) {
		for (CountriesEnum country : values()) {
			if(index == country.getReCode()) {
				return country;
			}
		}
		return null;
	}
}
