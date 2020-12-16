package io.github.springsongs.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import io.github.springsongs.domain.SpringDictionaryDetail;
import io.github.springsongs.dto.SpringDictionaryDetailDTO;

public interface SpringDictionaryDetailMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring_dictionary_detail
     *
     * @mbg.generated Mon Dec 14 16:39:39 CST 2020
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring_dictionary_detail
     *
     * @mbg.generated Mon Dec 14 16:39:39 CST 2020
     */
    int insert(SpringDictionaryDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring_dictionary_detail
     *
     * @mbg.generated Mon Dec 14 16:39:39 CST 2020
     */
    SpringDictionaryDetail selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring_dictionary_detail
     *
     * @mbg.generated Mon Dec 14 16:39:39 CST 2020
     */
    List<SpringDictionaryDetail> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring_dictionary_detail
     *
     * @mbg.generated Mon Dec 14 16:39:39 CST 2020
     */
    int updateByPrimaryKey(SpringDictionaryDetail record);

	List<SpringDictionaryDetail> listByPage(SpringDictionaryDetailDTO springDictionaryDetailQuery);

	void setDelete(@Param("ids") List<String> ids);

	void setDeleteByCode(@Param("dictionaryCode") String dictionaryCode);

	List<SpringDictionaryDetail> listSpringDictionaryDetailByDictionaryCode(@Param("dictionaryCode") String dictionaryCode);

	void setDeleteByDictionCode(@Param("codes") List<String> codes);

}