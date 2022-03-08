package com.yjxxt.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.base.BaseService;
import com.yjxxt.bean.Stage;
import com.yjxxt.mapper.StageMapper;
import com.yjxxt.query.StageQuery;
import com.yjxxt.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StageService extends BaseService<Stage,Integer> {

    @Autowired(required = false)
    private StageMapper stageMapper;

    //查询所有的阶段课程信息
    public List<Map<String,Object>> findStage(Integer stageId){
        return stageMapper.selectStage(stageId);
    }

    //查询所有课程
    public Map<String,Object> findAllStage(StageQuery query){
        //实例化map
        Map<String, Object> map = new HashMap<>();
        //准备分页
        PageHelper.startPage(query.getPage(),query.getLimit());
        //开始分页
        PageInfo<Stage> plist = new PageInfo<>(stageMapper.selectAllStage(query));
        //设定默认值
        map.put("code",0);
        map.put("msg","success");
        map.put("count",plist.getTotal());
        map.put("data",plist.getList());
        //返回map
        return map;
    }

    //根据id查找
    public Stage findById(Integer id){
        return stageMapper.selectAllStageTeacher(id);
    }

    //将t_stage表中的信息选择出来
    public List<Map<String,Object>> findStage()  {
        return stageMapper.selectStage();
    }

    //添加课程
    public void addStage(Stage stage){
        //校验
        checkStage(stage);
        //isValid:默认有效数据(1-有效 0-无效)
        stage.setIsValid(1);
        //添加失败还是成功
        AssertUtil.isTrue(stageMapper.insertSelective(stage)<1,"阶段添加失败");
        AssertUtil.isTrue(setTeaher(stage)<0,"添加授课老师失败");
    }


    //更新课程
    public void updateStage(Stage stage){
        //校验数据
        checkStage(stage);
        //isValid:默认有效数据(1-有效 0-无效)
        stage.setIsValid(1);
        //判断更新是否成功
        AssertUtil.isTrue(stageMapper.updateByPrimaryKeySelective(stage)<1,"课程更新失败");
        AssertUtil.isTrue(stageMapper.updateTeacher(stage.getId(),stage.getTId(),new Date())<0,"老师更新失败");
    }

    //设置授课老师
    private Integer setTeaher(Stage stage){
        AssertUtil.isTrue(stage.getStageName()==null,"未选择授课老师");
        Integer stageId = stageMapper.selectStageByName(stage.getStageName());
        Integer teacherId = stage.getTId();
        Date createDate = new Date();
        Date updateDate = new Date();
        return stageMapper.insertTeacherStage(stageId,teacherId,createDate,updateDate);
    }


    /**
     * 校验
     * 阶段名非空
     * 阶段时间非空
     * 阶段开始时间不能大于结束时间
     * 阶段名不能重复
     * 授课老师非空
     * @param stage
     */
    private void checkStage(Stage stage) {
        AssertUtil.isTrue(StringUtils.isBlank(stage.getStageName()),"阶段名为空");
        AssertUtil.isTrue(stage.getCreateDate()==null,"请设置阶段开课时间");
        AssertUtil.isTrue(stage.getUpdateDate().getTime()<stage.getCreateDate().getTime(),"阶段课程结束时间不能早于阶段课程开始时间");
        //判断阶段名是否重复
        if (stage.getId()!=null){
            AssertUtil.isTrue(!(stageMapper.selectStageName(stage.getStageName()).equals(stage.getId())),"阶段名重复");
        }else {
            AssertUtil.isTrue(stageMapper.selectStageName(stage.getStageName())!=null,"阶段名不能重复");
        }
        AssertUtil.isTrue(stage.getTId()==null,"请设置授课老师");
    }

    public List<Map<String,Object>> findAllTeacher(Integer clazzId){
        return stageMapper.selectAllTeacher(clazzId);
    }


    //根据id删除
    public void deleteByIds(Integer []ids){
        AssertUtil.isTrue(stageMapper.deleteBatch(ids)!=ids.length,"删除失败");
    }

    public List<Map<String,Object>> findSC(){
        return stageMapper.findSC();
    }

    public List<Map<String,Object>> findStudentStage(){
        return stageMapper.selectStudentStage();
    }


    public List<Map<String,Object>> findAll(){
        return stageMapper.findAll();
    }
}
