package com.byzx.vo;

public class GroupRole {
    private Integer goupRoleId;

    private Integer groupId;

    private Integer roleId;

    public Integer getGoupRoleId() {
        return goupRoleId;
    }

    public void setGoupRoleId(Integer goupRoleId) {
        this.goupRoleId = goupRoleId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

	@Override
	public String toString() {
		return "GroupRole [goupRoleId=" + goupRoleId + ", groupId=" + groupId + ", roleId=" + roleId + "]";
	}
    
}