package table.bean;

public class FieldBean {
	private String field;
	private String type;
	private String key;
	private String defaults;
	private String extra;
	//oracle小数位数
	private Integer data_scale;
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKey() {
		if(key!=null){
			return key.toLowerCase();
		}
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getDefaults() {
		return defaults;
	}
	public void setDefaults(String defaults) {
		this.defaults = defaults;
	}
	public String getExtra() {
		return extra;
	}
	public void setExtra(String extra) {
		this.extra = extra;
	}
	public Integer getData_scale() {
		return data_scale;
	}
	public void setData_scale(Integer data_scale) {
		this.data_scale = data_scale;
	}
	
	
}
