package bean.out;

import mybatisauto.bean.BaseOutBean;

public class TdTvcmBoardInfoOutBean extends BaseOutBean{
	private String createUser;

	private String createTime;

	private String boardName;

	private String boardId;

	public String getCreateUser(){return createUser;}
	public void setCreateUser(String createUser){this.createUser=createUser;}

	public String getCreateTime(){return createTime;}
	public void setCreateTime(String createTime){this.createTime=createTime;}

	public String getBoardName(){return boardName;}
	public void setBoardName(String boardName){this.boardName=boardName;}

	public String getBoardId(){return boardId;}
	public void setBoardId(String boardId){this.boardId=boardId;}

}