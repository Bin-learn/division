package com.bin.count;

import com.bin.students.dao.boyMapper;
import com.bin.students.entity.boy;
import com.bin.students.entity.boyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by  bin
 * Time： 2018/7/14.
 */
@Service("data")
public class data {

    @Autowired
    private  boyMapper boymapper;

    public  Map<String, Double> getAve(){
        return changeMap(boymapper.getToTalAve());
    }

    public  List<Integer>  getExcellentNum(){
        return boymapper.getNum(90.0,100.0);
    }

    public  List<Integer>  getHighNum(){
        return boymapper.getNum(80.0,90.0);
    }


    public  List<Integer>  getPassNum(){
        return boymapper.getNum(60.0,80.0);
    }

    public  List<Integer>  getLowNum(){
        return boymapper.getNum(0.0,30.0);
    }

    //初始分班(Z形排列分班)
    public List<List<boyVo>> firstDivision(){
        List<boy> allStudents = boymapper.selectStudentsOrderBytotal();//获取学生列表

        List<boyVo> allStudentsVo = new ArrayList<>();
        List<List<boyVo>>  DClass = new ArrayList<>();
        for(int i=0; i<12; i++){
            List<boyVo> initData = new ArrayList<>();
            DClass.add(initData); //初始化班级列表
        }
        boolean flag = true;

        for(int j=0; j<allStudents.size(); j++){
            boyVo ABoy = new boyVo();
            ABoy.setId(allStudents.get(j).getId());
            ABoy.setK性别(allStudents.get(j).getK性别());
            ABoy.setK村居(allStudents.get(j).getK村居());
            ABoy.setM总成绩(allStudents.get(j).getM总成绩());
            ABoy.setM数学(allStudents.get(j).getM数学());
            ABoy.setM英语(allStudents.get(j).getM英语());
            ABoy.setM语文(allStudents.get(j).getM语文());
            ABoy.set姓名(allStudents.get(j).get姓名());
            ABoy.set学籍号(allStudents.get(j).get学籍号());
            ABoy.set是否注册(allStudents.get(j).get是否注册());
            ABoy.set档案号(allStudents.get(j).get档案号());
            ABoy.set班级(allStudents.get(j).get班级());
            allStudentsVo.add(ABoy);
        }

        for(int j=0; j<allStudents.size();){
            if(flag){
                for(int i=0; i<12; i++){
                    if(j >=allStudents.size()){
                        break;
                    }
                    DClass.get(i).add(allStudentsVo.get(j++));//把学生添加到班级中
                }
                flag = false;
            }else{
                for(int i=11; i>=0; i--){
                    if(j >=allStudents.size()){
                        break;
                    }
                    DClass.get(i).add(allStudentsVo.get(j++));
                }
                flag =true;
            }

        }
        return DClass;
    }


    //从数据库获取到的数据是List<map>格式，但是数据只有一条，所以直接将其转化为map格式
    public Map<String, Double> changeMap(List<Map>  listM){
        Map<String, Double> map = new HashMap<>();
        try{
            map = (Map<String, Double>) listM.get(0);
        }catch (Exception e){
            System.out.println(e);
        }

        return map;
    }
}
