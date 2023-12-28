package com.example.myspringsecurityproject.common.domain;

import java.io.Serializable;

public class MyVO implements Serializable {

    private static final long serialVersionUID = 4906807137535316289L;

    private String userId;
    private String localUserName;
    private String creatorId;
    private String creatorName;
    private String createDttm;
    private String updaterId;
    private String updaterName;
    private String updateDttm;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLocalUserName() {
        return localUserName;
    }

    public void setLocalUserName(String localUserName) {
        this.localUserName = localUserName;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getCreateDttm() {
        return createDttm;
    }

    public void setCreateDttm(String createDttm) {
        this.createDttm = createDttm;
    }

    public String getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(String updaterId) {
        this.updaterId = updaterId;
    }

    public String getUpdaterName() {
        return updaterName;
    }

    public void setUpdaterName(String updaterName) {
        this.updaterName = updaterName;
    }

    public String getUpdateDttm() {
        return updateDttm;
    }

    public void setUpdateDttm(String updateDttm) {
        this.updateDttm = updateDttm;
    }
}
