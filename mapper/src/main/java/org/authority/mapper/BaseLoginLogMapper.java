package org.authority.mapper;

import org.authority.domain.Folder;
import org.authority.domain.LoginLog;

import com.github.pagehelper.Page;

public interface BaseLoginLogMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_log
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_log
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    int insert(LoginLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_log
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    int insertSelective(LoginLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_log
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    LoginLog selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_log
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    int updateByPrimaryKeySelective(LoginLog record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table login_log
     *
     * @mbg.generated Thu Nov 07 15:40:12 CST 2019
     */
    int updateByPrimaryKey(LoginLog record);
    
    Page<LoginLog> ListByPage(LoginLog record);
}