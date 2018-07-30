package bean.in;

import java.util.List;
import mybatisauto.bean.BaseInBean;

public class GysInBean extends BaseInBean{
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String roleName;

	private String note;

	private Double price3;

	private String tdate;

	public Integer getId(){return id;}
	public void setId(Integer id){this.id=id;}

	public String getRoleName(){return roleName;}
	public void setRoleName(String roleName){this.roleName=roleName;}

	public String getNote(){return note;}
	public void setNote(String note){this.note=note;}

	public Double getPrice3(){return price3;}
	public void setPrice3(Double price3){this.price3=price3;}

	public String getTdate(){return tdate;}
	public void setTdate(String tdate){this.tdate=tdate;}


	//  id====================
	private Boolean sqlIdLike=false;

	private Boolean sqlIdLeftLike=false;

	private Boolean sqlIdRightLike=false;

	private Boolean sqlIdD=false;

	private Boolean sqlIdDd=false;

	private Boolean sqlIdX=false;

	private Boolean sqlIdXd=false;

	private List<Integer> sqlIdInList;

	public Boolean getSqlIdLike(){return sqlIdLike;}
	public void setSqlIdLike(Boolean sqlIdLike){this.sqlIdLike=sqlIdLike;}

	public Boolean getSqlIdLeftLike(){return sqlIdLeftLike;}
	public void setSqlIdLeftLike(Boolean sqlIdLeftLike){this.sqlIdLeftLike=sqlIdLeftLike;}

	public Boolean getSqlIdRightLike(){return sqlIdRightLike;}
	public void setSqlIdRightLike(Boolean sqlIdRightLike){this.sqlIdRightLike=sqlIdLeftLike;}

	public Boolean getSqlIdD(){return sqlIdD;}
	public void setSqlIdD(Boolean sqlIdD){this.sqlIdD=sqlIdD;}

	public Boolean getSqlIdDd(){return sqlIdDd;}
	public void setSqlIdDd(Boolean sqlIdDd){this.sqlIdDd=sqlIdDd;}

	public Boolean getSqlIdX(){return sqlIdX;}
	public void setSqlIdX(Boolean sqlIdX){this.sqlIdX=sqlIdX;}

	public Boolean getSqlIdXd(){return sqlIdXd;}
	public void setSqlIdXd(Boolean sqlIdXd){this.sqlIdXd=sqlIdXd;}

	public List<Integer> getSqlIdInList(){return sqlIdInList;}
	public void setSqlIdInList(List<Integer> sqlIdInList){this.sqlIdInList=sqlIdInList;}

	//  roleName====================
	private Boolean sqlRoleNameLike=false;

	private Boolean sqlRoleNameLeftLike=false;

	private Boolean sqlRoleNameRightLike=false;

	private Boolean sqlRoleNameD=false;

	private Boolean sqlRoleNameDd=false;

	private Boolean sqlRoleNameX=false;

	private Boolean sqlRoleNameXd=false;

	private List<String> sqlRoleNameInList;

	public Boolean getSqlRoleNameLike(){return sqlRoleNameLike;}
	public void setSqlRoleNameLike(Boolean sqlRoleNameLike){this.sqlRoleNameLike=sqlRoleNameLike;}

	public Boolean getSqlRoleNameLeftLike(){return sqlRoleNameLeftLike;}
	public void setSqlRoleNameLeftLike(Boolean sqlRoleNameLeftLike){this.sqlRoleNameLeftLike=sqlRoleNameLeftLike;}

	public Boolean getSqlRoleNameRightLike(){return sqlRoleNameRightLike;}
	public void setSqlRoleNameRightLike(Boolean sqlRoleNameRightLike){this.sqlRoleNameRightLike=sqlRoleNameLeftLike;}

	public Boolean getSqlRoleNameD(){return sqlRoleNameD;}
	public void setSqlRoleNameD(Boolean sqlRoleNameD){this.sqlRoleNameD=sqlRoleNameD;}

	public Boolean getSqlRoleNameDd(){return sqlRoleNameDd;}
	public void setSqlRoleNameDd(Boolean sqlRoleNameDd){this.sqlRoleNameDd=sqlRoleNameDd;}

	public Boolean getSqlRoleNameX(){return sqlRoleNameX;}
	public void setSqlRoleNameX(Boolean sqlRoleNameX){this.sqlRoleNameX=sqlRoleNameX;}

	public Boolean getSqlRoleNameXd(){return sqlRoleNameXd;}
	public void setSqlRoleNameXd(Boolean sqlRoleNameXd){this.sqlRoleNameXd=sqlRoleNameXd;}

	public List<String> getSqlRoleNameInList(){return sqlRoleNameInList;}
	public void setSqlRoleNameInList(List<String> sqlRoleNameInList){this.sqlRoleNameInList=sqlRoleNameInList;}

	//  note====================
	private Boolean sqlNoteLike=false;

	private Boolean sqlNoteLeftLike=false;

	private Boolean sqlNoteRightLike=false;

	private Boolean sqlNoteD=false;

	private Boolean sqlNoteDd=false;

	private Boolean sqlNoteX=false;

	private Boolean sqlNoteXd=false;

	private List<String> sqlNoteInList;

	public Boolean getSqlNoteLike(){return sqlNoteLike;}
	public void setSqlNoteLike(Boolean sqlNoteLike){this.sqlNoteLike=sqlNoteLike;}

	public Boolean getSqlNoteLeftLike(){return sqlNoteLeftLike;}
	public void setSqlNoteLeftLike(Boolean sqlNoteLeftLike){this.sqlNoteLeftLike=sqlNoteLeftLike;}

	public Boolean getSqlNoteRightLike(){return sqlNoteRightLike;}
	public void setSqlNoteRightLike(Boolean sqlNoteRightLike){this.sqlNoteRightLike=sqlNoteLeftLike;}

	public Boolean getSqlNoteD(){return sqlNoteD;}
	public void setSqlNoteD(Boolean sqlNoteD){this.sqlNoteD=sqlNoteD;}

	public Boolean getSqlNoteDd(){return sqlNoteDd;}
	public void setSqlNoteDd(Boolean sqlNoteDd){this.sqlNoteDd=sqlNoteDd;}

	public Boolean getSqlNoteX(){return sqlNoteX;}
	public void setSqlNoteX(Boolean sqlNoteX){this.sqlNoteX=sqlNoteX;}

	public Boolean getSqlNoteXd(){return sqlNoteXd;}
	public void setSqlNoteXd(Boolean sqlNoteXd){this.sqlNoteXd=sqlNoteXd;}

	public List<String> getSqlNoteInList(){return sqlNoteInList;}
	public void setSqlNoteInList(List<String> sqlNoteInList){this.sqlNoteInList=sqlNoteInList;}

	//  price3====================
	private Boolean sqlPrice3Like=false;

	private Boolean sqlPrice3LeftLike=false;

	private Boolean sqlPrice3RightLike=false;

	private Boolean sqlPrice3D=false;

	private Boolean sqlPrice3Dd=false;

	private Boolean sqlPrice3X=false;

	private Boolean sqlPrice3Xd=false;

	private List<Double> sqlPrice3InList;

	public Boolean getSqlPrice3Like(){return sqlPrice3Like;}
	public void setSqlPrice3Like(Boolean sqlPrice3Like){this.sqlPrice3Like=sqlPrice3Like;}

	public Boolean getSqlPrice3LeftLike(){return sqlPrice3LeftLike;}
	public void setSqlPrice3LeftLike(Boolean sqlPrice3LeftLike){this.sqlPrice3LeftLike=sqlPrice3LeftLike;}

	public Boolean getSqlPrice3RightLike(){return sqlPrice3RightLike;}
	public void setSqlPrice3RightLike(Boolean sqlPrice3RightLike){this.sqlPrice3RightLike=sqlPrice3LeftLike;}

	public Boolean getSqlPrice3D(){return sqlPrice3D;}
	public void setSqlPrice3D(Boolean sqlPrice3D){this.sqlPrice3D=sqlPrice3D;}

	public Boolean getSqlPrice3Dd(){return sqlPrice3Dd;}
	public void setSqlPrice3Dd(Boolean sqlPrice3Dd){this.sqlPrice3Dd=sqlPrice3Dd;}

	public Boolean getSqlPrice3X(){return sqlPrice3X;}
	public void setSqlPrice3X(Boolean sqlPrice3X){this.sqlPrice3X=sqlPrice3X;}

	public Boolean getSqlPrice3Xd(){return sqlPrice3Xd;}
	public void setSqlPrice3Xd(Boolean sqlPrice3Xd){this.sqlPrice3Xd=sqlPrice3Xd;}

	public List<Double> getSqlPrice3InList(){return sqlPrice3InList;}
	public void setSqlPrice3InList(List<Double> sqlPrice3InList){this.sqlPrice3InList=sqlPrice3InList;}

	//  tdate====================
	private Boolean sqlTdateLike=false;

	private Boolean sqlTdateLeftLike=false;

	private Boolean sqlTdateRightLike=false;

	private Boolean sqlTdateD=false;

	private Boolean sqlTdateDd=false;

	private Boolean sqlTdateX=false;

	private Boolean sqlTdateXd=false;

	private List<String> sqlTdateInList;

	public Boolean getSqlTdateLike(){return sqlTdateLike;}
	public void setSqlTdateLike(Boolean sqlTdateLike){this.sqlTdateLike=sqlTdateLike;}

	public Boolean getSqlTdateLeftLike(){return sqlTdateLeftLike;}
	public void setSqlTdateLeftLike(Boolean sqlTdateLeftLike){this.sqlTdateLeftLike=sqlTdateLeftLike;}

	public Boolean getSqlTdateRightLike(){return sqlTdateRightLike;}
	public void setSqlTdateRightLike(Boolean sqlTdateRightLike){this.sqlTdateRightLike=sqlTdateLeftLike;}

	public Boolean getSqlTdateD(){return sqlTdateD;}
	public void setSqlTdateD(Boolean sqlTdateD){this.sqlTdateD=sqlTdateD;}

	public Boolean getSqlTdateDd(){return sqlTdateDd;}
	public void setSqlTdateDd(Boolean sqlTdateDd){this.sqlTdateDd=sqlTdateDd;}

	public Boolean getSqlTdateX(){return sqlTdateX;}
	public void setSqlTdateX(Boolean sqlTdateX){this.sqlTdateX=sqlTdateX;}

	public Boolean getSqlTdateXd(){return sqlTdateXd;}
	public void setSqlTdateXd(Boolean sqlTdateXd){this.sqlTdateXd=sqlTdateXd;}

	public List<String> getSqlTdateInList(){return sqlTdateInList;}
	public void setSqlTdateInList(List<String> sqlTdateInList){this.sqlTdateInList=sqlTdateInList;}

}