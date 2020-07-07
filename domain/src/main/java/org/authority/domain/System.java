package org.authority.domain;

import java.util.Date;

public class System {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system.id
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    private String id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system.code
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    private String code;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system.title
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system.enable_delete
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    private Boolean enableDelete;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system.enable_edit
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    private Boolean enableEdit;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system.deleted_flag
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    private Boolean deletedFlag;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system.created_by
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    private String createdBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system.created_user_id
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    private String createdUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system.created_on
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    private Date createdOn;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system.created_ip
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    private String createdIp;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system.updated_user_id
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    private String updatedUserId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system.updated_by
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    private String updatedBy;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system.updated_on
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    private Date updatedOn;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column system.updated_ip
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    private String updatedIp;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system.id
     *
     * @return the value of system.id
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system.id
     *
     * @param id the value for system.id
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system.code
     *
     * @return the value of system.code
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system.code
     *
     * @param code the value for system.code
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system.title
     *
     * @return the value of system.title
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system.title
     *
     * @param title the value for system.title
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system.enable_delete
     *
     * @return the value of system.enable_delete
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    public Boolean getEnableDelete() {
        return enableDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system.enable_delete
     *
     * @param enableDelete the value for system.enable_delete
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    public void setEnableDelete(Boolean enableDelete) {
        this.enableDelete = enableDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system.enable_edit
     *
     * @return the value of system.enable_edit
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    public Boolean getEnableEdit() {
        return enableEdit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system.enable_edit
     *
     * @param enableEdit the value for system.enable_edit
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    public void setEnableEdit(Boolean enableEdit) {
        this.enableEdit = enableEdit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system.deleted_flag
     *
     * @return the value of system.deleted_flag
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    public Boolean getDeletedFlag() {
        return deletedFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system.deleted_flag
     *
     * @param deletedFlag the value for system.deleted_flag
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    public void setDeletedFlag(Boolean deletedFlag) {
        this.deletedFlag = deletedFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system.created_by
     *
     * @return the value of system.created_by
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system.created_by
     *
     * @param createdBy the value for system.created_by
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system.created_user_id
     *
     * @return the value of system.created_user_id
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    public String getCreatedUserId() {
        return createdUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system.created_user_id
     *
     * @param createdUserId the value for system.created_user_id
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    public void setCreatedUserId(String createdUserId) {
        this.createdUserId = createdUserId == null ? null : createdUserId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system.created_on
     *
     * @return the value of system.created_on
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    public Date getCreatedOn() {
        return createdOn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system.created_on
     *
     * @param createdOn the value for system.created_on
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system.created_ip
     *
     * @return the value of system.created_ip
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    public String getCreatedIp() {
        return createdIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system.created_ip
     *
     * @param createdIp the value for system.created_ip
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    public void setCreatedIp(String createdIp) {
        this.createdIp = createdIp == null ? null : createdIp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system.updated_user_id
     *
     * @return the value of system.updated_user_id
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    public String getUpdatedUserId() {
        return updatedUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system.updated_user_id
     *
     * @param updatedUserId the value for system.updated_user_id
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    public void setUpdatedUserId(String updatedUserId) {
        this.updatedUserId = updatedUserId == null ? null : updatedUserId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system.updated_by
     *
     * @return the value of system.updated_by
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system.updated_by
     *
     * @param updatedBy the value for system.updated_by
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy == null ? null : updatedBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system.updated_on
     *
     * @return the value of system.updated_on
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    public Date getUpdatedOn() {
        return updatedOn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system.updated_on
     *
     * @param updatedOn the value for system.updated_on
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column system.updated_ip
     *
     * @return the value of system.updated_ip
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    public String getUpdatedIp() {
        return updatedIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column system.updated_ip
     *
     * @param updatedIp the value for system.updated_ip
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    public void setUpdatedIp(String updatedIp) {
        this.updatedIp = updatedIp == null ? null : updatedIp.trim();
    }
}