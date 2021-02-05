package com.intplog.mcs.mapper.McsMapper;

import com.intplog.mcs.bean.model.McsModel.McsCrossLayerTask;
import com.intplog.mcs.bean.model.McsModel.McsRgvChangeStoryTask;
import groovy.transform.SelfType;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @author quqingmao
 * @date 2020/6/14
 */
public interface McsRgvChangeStoryTaskMapper {

    @Select({"<script>",
            "select * from mcs_rgv_change_story_task where 1=1",
            "<if test='taskId!=null'>",
            "and task_id = #{taskId}",
            "</if>",
            "order by create_time desc",
            "</script>"
    })
    List<McsRgvChangeStoryTask> getList(@Param("taskId") String taskId);

    @Select({"<script>",
            "select * from mcs_rgv_change_story_task where 1=1",
            "<if test='taskId!=null'>",
            "and id = #{taskId}",
            "</if>",
            "<if test='hoistId!=null'>",
            "and hoist_id = #{hoistId}",
            "</if>",
            "order by status,create_time desc",
            "</script>"
    })
    List<McsRgvChangeStoryTask> getLists(@Param("taskId") String taskId, @Param("hoistId") String hoistId);


    @Select("select * from mcs_rgv_change_story_task")
    public List<McsRgvChangeStoryTask> getAll();

    @Select("select * from mcs_rgv_change_story_task where id=#{id}")
    McsRgvChangeStoryTask getId(@Param("id") String id);
    @Select("select * from mcs_rgv_change_story_task where status = #{status}  order by create_time Limit 0,1 ")
    List<McsRgvChangeStoryTask> getStatus(@Param("status") int status );


    @Select("select * from mcs_rgv_change_story_task where status in(1,2,3,4,5,6,7,8,9,10,88)  order by create_time Limit 0,1 ")
    List<McsRgvChangeStoryTask> getGcsStatus();

    @Select(" select * from  mcs_rgv_change_story_task where status=#{status}  and hoist_id=#{hoistId}")
    public List<McsRgvChangeStoryTask> getByStatusAndHoist(@Param("status") int status, @Param("hoistId") String hoistId);
    @Select("select * from mcs_rgv_change_story_task where task_id=#{taskId} order by create_time Limit 0,1")
    public McsRgvChangeStoryTask getByTaskId(@Param("taskId")String taskId);
    @Select("select * from mcs_rgv_change_story_task where task_id=#{taskId}")
    public List<McsRgvChangeStoryTask>  getByTaskIdAndStatus(@Param("taskId")String taskId);

    @Select("select * from mcs_rgv_change_story_task where status=#{status} and gcs_id=#{gcsId} limit 0,1")
    McsRgvChangeStoryTask getGcsId( @Param("status") int status , @Param("gcsId")String gcsId );


    @Select("select * from mcs_rgv_change_story_task where task_id=#{id} and status=#{status}")
    McsRgvChangeStoryTask getIdStatus(@Param("id")String id,@Param("status")int status);
    @Insert("insert into mcs_rgv_change_story_task(id,task_id,source, rgv_id,target ,create_time,update_time," +
            "status,hoist_id,gcs_id)values(#{id},#{taskId},#{source},#{rgvId},#{target},#{createTime}," +
            "#{updateTime},#{status},#{hoistId},#{gcsId})")
    public int insert(McsRgvChangeStoryTask mcsRgvChangeStoryTask);

    @Select("select * from  mcs_rgv_change_story_task where status<10 limit 0,1")
    List<McsCrossLayerTask> getLayerTask();

    @Update("update mcs_rgv_change_story_task set task_id=#{taskId},rgv_id=#{rgvId},source=#{source},target=#{target},create_time=#{createTime}," +
            "update_time=#{updateTime},status=#{status},hoist_id=#{hoistId} where id=#{id}")
    public int update(McsRgvChangeStoryTask mcsRgvChangeStoryTask);

    @Delete("delete from mcs_rgv_change_story_task where id={#id}")
    public int deleteById(@Param("id") String id);

    @Delete("delete from mcs_rgv_change_story_task where rq_time < #{date}")
    public int deleteLog(@Param("date") Date date);

    @Delete("delete from mcs_rgv_change_story_task where status=#{status}")
    int deleteStatus(@Param("status") int status);
}


