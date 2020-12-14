package io.github.springsongs.mapper;

import io.github.springsongs.domain.SpringActProcessRouter;
import java.util.List;

public interface SpringActProcessRouterMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring_act_process_router
     *
     * @mbg.generated Mon Dec 14 16:20:59 CST 2020
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring_act_process_router
     *
     * @mbg.generated Mon Dec 14 16:20:59 CST 2020
     */
    int insert(SpringActProcessRouter record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring_act_process_router
     *
     * @mbg.generated Mon Dec 14 16:20:59 CST 2020
     */
    SpringActProcessRouter selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring_act_process_router
     *
     * @mbg.generated Mon Dec 14 16:20:59 CST 2020
     */
    List<SpringActProcessRouter> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spring_act_process_router
     *
     * @mbg.generated Mon Dec 14 16:20:59 CST 2020
     */
    int updateByPrimaryKey(SpringActProcessRouter record);
}