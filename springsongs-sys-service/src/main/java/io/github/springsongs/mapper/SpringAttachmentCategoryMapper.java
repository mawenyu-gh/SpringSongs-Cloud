package io.github.springsongs.mapper;

import io.github.springsongs.domain.SpringAttachmentCategory;
import java.util.List;

public interface SpringAttachmentCategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring_attachment_category
     *
     * @mbg.generated Mon Dec 14 16:39:39 CST 2020
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring_attachment_category
     *
     * @mbg.generated Mon Dec 14 16:39:39 CST 2020
     */
    int insert(SpringAttachmentCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring_attachment_category
     *
     * @mbg.generated Mon Dec 14 16:39:39 CST 2020
     */
    SpringAttachmentCategory selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring_attachment_category
     *
     * @mbg.generated Mon Dec 14 16:39:39 CST 2020
     */
    List<SpringAttachmentCategory> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring_attachment_category
     *
     * @mbg.generated Mon Dec 14 16:39:39 CST 2020
     */
    int updateByPrimaryKey(SpringAttachmentCategory record);
}