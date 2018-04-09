/**
 * 
 */
package com.bupt.qrj.unifyum.dal.dataobject;

/**
 * @author renjun.qrj 2015年10月31日:下午5:52:08 com.bupt.qrj.dataobject.UserMeta
 *         unifyum-dal 用途: - 用户账户基本信息
 *
 */
public class MissionReturnDO {

	public String getColumn_key() {
		return column_key;
	}

	public void setColumn_key(String column_key) {
		this.column_key = column_key;
	}

	public String getNote_name() {
		return note_name;
	}

	public void setNote_name(String note_name) {
		this.note_name = note_name;
	}

	public String getNote_content() {
		return note_content;
	}

	public void setNote_content(String note_content) {
		this.note_content = note_content;
	}

	public String getFont_color() {
		return font_color;
	}

	public void setFont_color(String font_color) {
		this.font_color = font_color;
	}

	public String getFont_size() {
		return font_size;
	}

	public void setFont_size(String font_size) {
		this.font_size = font_size;
	}

	private String column_key;
	private String note_name;
	private String note_content;
	private String font_color;
	private String font_size;

    public String getLevel_one() {
        return level_one;
    }

    public void setLevel_one(String level_one) {
        this.level_one = level_one;
    }

    public String getLevel_two() {
        return level_two;
    }

    public void setLevel_two(String level_two) {
        this.level_two = level_two;
    }

    private String level_one;
    private String level_two;



	
}
