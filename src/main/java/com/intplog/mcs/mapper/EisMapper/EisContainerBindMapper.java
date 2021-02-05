package com.intplog.mcs.mapper.EisMapper;

import com.intplog.mcs.bean.model.EisModel.EisContainerBind;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author liaoliming
 * @Date 2019-10-31
 * @Version 1.0
 */
public interface EisContainerBindMapper {

    /**
     * 查询所有
     *
     * @return
     */
    @Select("select task_id as taskId,type ,stock_id as stockId,stock_id_sub as stockIdSub,source,target,create_time " +
            "as createTime,weight,detection,priority ,status from mcs_container_bind")
    public List<EisContainerBind> getAll();

    /**
     * status查询
     *
     * @param
     * @return
     */
    @Select("select task_id as taskId,type ,stock_id as stockId,stock_id_sub as stockIdSub,source,target,create_time " +
            "as createTime,weight,detection,priority ,status from mcs_container_bind where status = #{status}")
    public List<EisContainerBind> getMcsContainerBindByStatus(@Param("status") int status);

    /**
     * status查询
     *
     * @param
     * @return
     */
    @Select("select task_id as taskId,type ,stock_id as stockId,stock_id_sub as stockIdSub,source,target,create_time " +
            "as createTime,weight,detection,priority ,status from mcs_container_bind where status = #{status} " +
            "and source = #{source} order by create_time ")
    public List<EisContainerBind> getListByStatusAndSource(@Param("status") int status, @Param("source") String source);
    /**
     * status查询
     *
     * @param
     * @return
     */
    @Select("select task_id as taskId,type ,stock_id as stockId,source, stock_id_sub as stockIdSub,target,create_time " +
            "as createTime,weight,detection,priority ,status from mcs_container_bind where status = #{status}" +
            " and source=#{source} order by create_time LIMIT 0,1")
    public EisContainerBind getByStatusAndSource(@Param("status") int status, @Param("source") String source);

    /**
     * status查询
     *
     * @param
     * @return
     */
    @Select("select task_id as taskId,type ,stock_id as stockId, stock_id_sub as stockIdSub,source,target,create_time " +
            "as createTime,weight,detection,priority ,status from mcs_container_bind where status=#{status}"+
            " and task_id=#{taskId}")
    public EisContainerBind getByStatusAndCode(@Param("status") int taskStage, @Param("taskId") String taskCode);

    /**
     * 查询改出入口是否存在数据
     * @param code
     * @return
     */
    @Select("select * from  mcs_container_bind where source=#{source} and status <4")
    List<EisContainerBind> getCountBySource(String code);

    /**
     * 模糊查询
     * @param status
     * @param taskId
     * @return
     */
    @Select({"<script>",
            "select task_id as taskId,type,stock_id as stockId,source,target,stock_id_sub as stockIdSub," +
                    "create_time as createTime,weight,detection,priority,status" +
                    "from mcs_container_bind where 1=1",
            "<if test='taskId!=null'>",
            "and task_id=#{taskId}",
            "</if>",
            "<if test='status!=0'>",
            "and status=#{status}",
            "</if>",
            "</script>"
    })
    public List<EisContainerBind> getListByStatus(@Param("status") int status, @Param("taskId") String taskId);
    /**
     *
     * @param taskId
     * @return
     *
     */
    @Select("select * from mcs_container_bind where task_id = #{taskId}")
    public EisContainerBind getMcsContainerBindByTaskCode(@Param("taskId") String taskId);

    /**
     * 根据taskCode查询
     *
     * @param taskId
     * @return
     */
    @Select({"<script>",
            "select * from mcs_container_bind where 1=1",
            "<if test='taskId!=null'>",
            "and task_id=#{taskId}",
            "</if>",
            "</script>"
    })
    public EisContainerBind getMcsContainerBindByCode(@Param("taskId") String taskId);

    /**
     * 根据任务号更新数据
     *
     * @return
     */
    @Update("update mcs_container_bind set type=#{type},stock_id=#{stockId},source=#{source}," +
            "target=#{target},weight=#{weight},priority=#{priority},create_time=#{createTime}," +
            " status=#{status},stock_id_sub =#{stockIdSub},detection=#{detection} where task_id=#{taskId}"
    )
    public int updateTaskStageByCode(EisContainerBind eisContainerBind);
    /**
     * 根据任务号更新数据
     */
    @Update("update mcs_container_bind set status=#{status} where task_id=#{taskId}")
    int updateByReportStatus(@Param("status") int status,@Param("taskId") String taskId);

    /**
     * 添加
     *
     * @param mcsContainerBind
     * @return
     */
    @Insert("insert into mcs_container_bind (task_id, type, stock_id,stock_id_sub,source, target," +
            " weight,detection,priority,create_time, status) values " +
            "(#{taskId},#{type},#{stockId},#{stockIdSub},#{source}, #{target}, #{weight},#{detection},#{priority}" +
            ",#{createTime}, #{status})")
    public int insertMcsContainerBind(EisContainerBind mcsContainerBind);

    /**
     * 根据任务号删除数据
     * @param taskId
     * @return
     */
    @Delete("delete from mcs_container_bind where task_id= #{taskId}")
    public  int deleteByTaskId(@Param("taskId") String taskId);



}

