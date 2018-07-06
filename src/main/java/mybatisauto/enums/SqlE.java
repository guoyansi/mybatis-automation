package mybatisauto.enums;

public enum SqlE {
	or(" or "),
	and(" and "),
	orderBy(" order by "),
	desc(" desc "),
	asc(" asc "),
	where("where ");
	
	private String value;
	private SqlE(String value) {
		this.value=value;
	}
	public String getValue() {
		return value;
	}
}
