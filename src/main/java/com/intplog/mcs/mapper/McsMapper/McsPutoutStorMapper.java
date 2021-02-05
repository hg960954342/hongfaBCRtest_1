package com.intplog.mcs.mapper.McsMapper;

import com.intplog.mcs.bean.model.McsModel.McsPutoutStor;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @author quqingmao
 * @date 2020/8/13
 */
public interface McsPutoutStorMapper {
    /**
     * 查询所有数据
     *
     * @return
     */
    @Select("select *" +
            "from mcs_putout_stor order by create_time desc")
    public List<McsPutoutStor> getAll();

    /**
     *
     * @param status
     * @return
     */
    @Select("select *" +
            " from mcs_putout_stor where status=#{status} order by create_time")
    public List<McsPutoutStor> getListByStatus(@Param("status") int status);

    @Select("select  * " +
            " from mcs_putout_stor where status=#{status} and task_id=#{taskId} order by create_time limit 0,1")
    McsPutoutStor getListByTaskStatus(@Param("status") int status,@Param("taskId") String taskId);

    /**
     *根据状态和提升机编号
     * @param status
     * @return
     */
    @Select("select * " +
            "from mcs_putout_stor where status=#{status} and hoist_id=#{hoistId} and type=#{type} order by create_time Limit 0,1")
    public List<McsPutoutStor> getListByStatusAndHoist(@Param("hoistId")String hoistId, @Param("status") int status , @Param("type") int type);

    @Select("select  *" +
            "from mcs_putout_stor where status=#{status}  and type=#{type} order by create_time ")
    public List<McsPutoutStor> getListMove(@Param("status") int status , @Param("type") int type);



    /**
     *根据状态和原位置
     * @param source
     * @return
     */
    @Select("select * from mcs_putout_stor where status=#{status} and source=#{source}  order by create_time Limit 0,1 ")
    public McsPutoutStor getListByStatusAndSource(@Param("source")String source, @Param("status") int status);

    /**
     *根据状态和提升机编号
     * @param status
     * @return
     */
    @Select("select count(*) from mcs_putout_stor where status=#{status} and hoist_id=#{hoistId}  ")
    public int getCountByStatusAndHoist(@Param("hoistId")String hoistId, @Param("status") int status);
    /**
     *根据状态和提升机编号和PLC编号查询
     * @param status
     * @return
     */
    @Select("select * " +
            " from mcs_putout_stor where status=#{status} and hoist_id=#{hoistId} and source=#{source} order by create_time LIMIT 0, 1")
    public McsPutoutStor getListByStatusAndHoistAndPlcId(@Param("hoistId")String hoistId, @Param("source")String source,@Param("status") int status);
    /**
     *根据任务号查询
     * @param taskId
     * @return
     */
    @Select("select *" +
            " from mcs_putout_stor where task_id=#{taskId} order by create_time ")
    public McsPutoutStor getByTaskId(@Param("taskId")String taskId);

    /**
     * 根据Mcs流水号查询
     * @param mcsId
     * @return
     */
    @Select("select  *" +
            " from mcs_putout_stor where mcs_id=#{mcsId} order by create_time ")
    public McsPutoutStor getByMcsId(@Param("mcsId")String mcsId);

    /**
     *根据任务号和状态查询
     * @param mcsId
     * @return
     */
    @Select("select  *" +
            " from mcs_putout_stor where mcs_id=#{mcsId} and status=#{status} order by create_time ")
    public McsPutoutStor getByStatusAndMcsId(@Param("mcsId")String mcsId,@Param("status")int status);

    @Select("select * from mcs_task_info where status<70 and layer=#{layer} order by create_time")
    List<McsPutoutStor> getRefuseList(@Param("layer")int layer);
    @Select({"<script>",
            "select  *" ,
            "from mcs_putout_stor where 1=1",
            "<if test='mcsId!=null'>",
            "and hoist_id = #{mcsId}",
            "</if>","<if test='taskId!=null'>",
            "and task_id = #{taskId}",
            "</if>",
            "order by create_time desc",
            "</script>"

    })
    List<McsPutoutStor>getList(@Param("mcsId")String mcsId,@Param("taskId") String taskId);

    /**
     * 添加数据
     * @param mcsTaskInfo
     * @return
     */
    @Insert("insert into mcs_putout_stor (rgv_id , layer,task_id,mcs_id,type,hoist_id,stock_id,source,target,priority,weight," +
            "status,create_time,update_time,plc_name,address_type ,address )values" +
            "(#{rgvId},#{layer},#{taskId},#{mcsId},#{type},#{hoistId},#{stockId},#{source},#{target},#{priority},#{weight},#{status}" +
            ",#{createTime},#{updateTime},#{plcName},#{addressType} ,#{address} )")
    public int insert(McsPutoutStor mcsTaskInfo);

    /**
     * 更新数据
     * @param mcsTaskInfo
     * @return
     */
    @Update("update mcs_putout_stor set rgv_id =#{rgvId} ,layer=#{layer}, mcs_id=#{mcsId},type=#{type},stock_id=#{stockId},source=#{source}," +
            "target=#{target},priority=#{priority},weight=#{weight},status=#{status},create_time=#{createTime}" +
            ",update_time=#{updateTime},hoist_id=#{hoistId},plc_name=#{plcName},address_type=#{addressType} " +
            "where task_id=#{taskId}")
    public int update(McsPutoutStor mcsTaskInfo);

    /**
     * 删除指定日期之前的日志记录
     *
     * @param date
     * @return
     */
    @Delete("delete from mcs_putout_stor where create_time < #{date}")
    int deleteTask(@Param("date") Date date);

    @Delete("delete from mcs_putout_stor where id=#{id}")
    int deleteId(@Param("id") String id);

    @Delete("delete from mcs_putout_stor where status=#{status}")
    int deleteStatus(@Param("status") int status);
}
