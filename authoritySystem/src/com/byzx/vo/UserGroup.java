package com.byzx.vo;

import java.util.List;

public class UserGroup {
    private Integer groupId;

    private String groupName;

    private String groupCode;

    private String groupDesc;

    private String groupState;
    
    private List<GroupRole> groupRole;

    public List<GroupRole> getGroupRole() {
		return groupRole;
	}

	public void setGroupRole(List<GroupRole> groupRole) {
		this.groupRole = groupRole;
	}

	public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode == null ? null : groupCode.trim();
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc == null ? null : groupDesc.trim();
    }

    public String getGroupState() {
        return groupState;
    }

    public void setGroupState(String groupState) {
        this.groupState = groupState == null ? null : groupState.trim();
    }
}