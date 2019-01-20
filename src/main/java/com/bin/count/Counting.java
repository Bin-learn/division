package com.bin.count;

import com.bin.students.entity.boy;
import com.bin.students.entity.boyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by  bin
 * Time： 2018/7/14.
 */
@RestController
@RequestMapping("/aa/")
public  class Counting {

    @Autowired
    private data data;

    @Autowired
    private Compare compare;

    @RequestMapping(value = "count", method = RequestMethod.GET)
    @ResponseBody
    public double[][][] count(){


        //12个班级的集合（集合内含每个班级的各个学生）
        List<List<boyVo>>  DClass = data.firstDivision();

        double totalGrade = 0.0;

        /*
        * classData[][][]数组每个维度含义：
        * 1.班级数
        * 2.科目数
        * 3.档次数
        * */


        double[][][] classData = countClassData(DClass);

        System.out.println("各班平均分: ");
        for(int i=0; i<12; i++){
            System.out.println("第"+i+"个班");
            for(int j=0; j<DClass.get(i).size(); j++){
                totalGrade = totalGrade + Double.parseDouble(DClass.get(i).get(j).getM总成绩());
            }
            System.out.println(totalGrade/DClass.get(i).size());
            classData[i][3][4] = totalGrade/DClass.get(i).size();
            totalGrade = 0.0;
        }





       printClassData(classData);
        for(int i=0; i<10000; i++){
            DClass = compare.adjust(classData, DClass);
            classData = countClassData(DClass);
            if(i == 1 || i == 9999 || i == 5000){
                printClassData(classData);
                System.out.println("间隔符...........................................");
                System.out.println("间隔符...........................................");
                System.out.println("间隔符...........................................");
                System.out.println("间隔符...........................................");

            }

        }

        return classData;

    }

    //计算各班级数据
    public double[][][] countClassData(List<List<boyVo>> DClass){
        double classData[][][] = new double[13][4][5];

        //用于存储每个班的数据，用以跟平均值比较
        Map<String, Double> classDataListMap = new HashMap<>();


        Map<String, Double> map = data.getAve();
        Double TotalAve = map.get("totalave");
        Double ChineseAve = map.get("Cave");
        Double MathAve = map.get("Mave");
        Double EnglishAve = map.get("Eave");

        classDataListMap.put("总平均分",TotalAve);
        classDataListMap.put("语文平均分",ChineseAve);
        classDataListMap.put("数学平均分",MathAve);
        classDataListMap.put("英语平均分",EnglishAve);
        classData[12][0][4] = ChineseAve;
        classData[12][1][4] = MathAve;
        classData[12][2][4] = EnglishAve;
        classData[12][3][4] = TotalAve;

//        System.out.println("总平均分：" + TotalAve);
//        System.out.println("语文平均分：" + ChineseAve);
//        System.out.println("数学平均分：" + MathAve);
//        System.out.println("英语平均分：" + EnglishAve);

        List<Integer> totalExcellentNumMap = data.getExcellentNum();
        int TotalExcellentNum = totalExcellentNumMap.get(0);
        int ChineseExcellentNum = totalExcellentNumMap.get(1);
        int EnglishExcellentNum = totalExcellentNumMap.get(2);
        int MathExcellentNum = totalExcellentNumMap.get(3);

        double ChineseExcellentNumAve = (double)totalExcellentNumMap.get(1)/12;
        double EnglishExcellentNumAve = (double)totalExcellentNumMap.get(2)/12;
        double MathExcellentNumAve = (double)totalExcellentNumMap.get(3)/12;

        classDataListMap.put("语文平均优秀人数",ChineseExcellentNumAve);
        classDataListMap.put("英语平均优秀人数",EnglishExcellentNumAve);
        classDataListMap.put("数学平均优秀人数",MathExcellentNumAve);

        classData[12][0][0] = ChineseExcellentNumAve;
        classData[12][1][0] = EnglishExcellentNumAve;
        classData[12][2][0] = MathExcellentNumAve;


//        System.out.println("总分优秀人数：" + TotalExcellentNum);
//        System.out.println("语文优秀人数：" + ChineseExcellentNum);
//        System.out.println("英语优秀人数：" + EnglishExcellentNum);
//        System.out.println("数学优秀人数：" + MathExcellentNum);

        List<Integer> totalHighNumMap = data.getHighNum();
        int TotalHighNum = totalHighNumMap.get(0);
        int ChineseHighNum = totalHighNumMap.get(1);
        int EnglishHighNum = totalHighNumMap.get(2);
        int MathHighNum = totalHighNumMap.get(3);

        double ChineseHighNumAve = (double)totalHighNumMap.get(1)/12;
        double EnglishHighNumAve = (double)totalHighNumMap.get(2)/12;
        double MathHighNumAve = (double)totalHighNumMap.get(3)/12;

        classDataListMap.put("语文平均高分人数",ChineseHighNumAve);
        classDataListMap.put("英语平均高分人数",EnglishHighNumAve);
        classDataListMap.put("数学平均高分人数",MathHighNumAve);
        classData[12][0][1] = ChineseHighNumAve;
        classData[12][1][1] = EnglishHighNumAve;
        classData[12][2][1] = MathHighNumAve;


//        System.out.println("总分高分人数：" + TotalHighNum);
//        System.out.println("语文高分人数：" + ChineseHighNum);
//        System.out.println("英语高分人数：" + EnglishHighNum);
//        System.out.println("数学高分人数：" + MathHighNum);

        List<Integer> totalPassNumMap = data.getPassNum();
        int TotalPassNum = totalPassNumMap.get(0);
        int ChinesePassNum = totalPassNumMap.get(1);
        int EnglishPassNum = totalPassNumMap.get(2);
        int MathPassNum = totalPassNumMap.get(3);

        double ChinesePassNumAve = (double)totalPassNumMap.get(1)/12;
        double EnglishPassNumAve = (double)totalPassNumMap.get(2)/12;
        double MathPassNumAve = (double)totalPassNumMap.get(3)/12;

        classDataListMap.put("语文平均及格人数",ChinesePassNumAve);
        classDataListMap.put("英语平均及格人数",EnglishPassNumAve);
        classDataListMap.put("数学平均及格人数",MathPassNumAve);
        classData[12][0][2] = ChinesePassNumAve;
        classData[12][1][2] = EnglishPassNumAve;
        classData[12][2][2] = MathPassNumAve;


//        System.out.println("总分及格人数：" + TotalPassNum);
//        System.out.println("语文及格人数：" + ChinesePassNum);
//        System.out.println("英语及格人数：" + EnglishPassNum);
//        System.out.println("数学及格人数：" + MathPassNum);

        List<Integer> totalLowNumMap = data.getLowNum();
        int TotalLowNum = totalLowNumMap.get(0);
        int ChineseLowNum = totalLowNumMap.get(1);
        int EnglishLowNum = totalLowNumMap.get(2);
        int MathLowNum = totalLowNumMap.get(3);

        double ChineseLowNumAve = (double)totalLowNumMap.get(1)/12;
        double EnglishLowNumAve = (double)totalLowNumMap.get(2)/12;
        double MathLowNumAve = (double)totalLowNumMap.get(3)/12;

        classDataListMap.put("语文平均不及格人数",ChineseLowNumAve);
        classDataListMap.put("英语平均不及格人数",EnglishLowNumAve);
        classDataListMap.put("数学平均不及格人数",MathLowNumAve);
        classData[12][0][3] = ChineseLowNumAve;
        classData[12][1][3] = EnglishLowNumAve;
        classData[12][2][3] = MathLowNumAve;




//        System.out.println("总分不及格人数：" + TotalLowNum);
//        System.out.println("语文不及格人数：" + ChineseLowNum);
//        System.out.println("英语不及格人数：" + EnglishLowNum);
//        System.out.println("数学不及格人数：" + MathLowNum);


        for(int i=0; i<12; i++){
            double ChineseTotal = 0.0d;
            double MathTotal = 0.0d;
            double EnglishTotal = 0.0d;
            //语文
            for(int j=0; j<DClass.get(i).size(); j++){
                if(Double.parseDouble(DClass.get(i).get(j).getM语文()) >= 90.0){
                    classData[i][0][0]++;
                    DClass.get(i).get(j).get分数等级标识()[0] = 0;
                }else if(Double.parseDouble(DClass.get(i).get(j).getM语文()) >= 80.0){
                    classData[i][0][1]++;
                    DClass.get(i).get(j).get分数等级标识()[0] = 1;
                }else if(Double.parseDouble(DClass.get(i).get(j).getM语文()) >= 60.0){
                    classData[i][0][2]++;
                    DClass.get(i).get(j).get分数等级标识()[0] = 2;
                }else if(Double.parseDouble(DClass.get(i).get(j).getM语文()) <= 30.0){
                    classData[i][0][3]++;
                    DClass.get(i).get(j).get分数等级标识()[0] = 3;
                }else{
                    DClass.get(i).get(j).get分数等级标识()[0] = 5;
                }
                ChineseTotal = ChineseTotal + Double.parseDouble(DClass.get(i).get(j).getM语文());

            }
            classData[i][0][4] = ChineseTotal/DClass.get(i).size();

            //数学
            for(int j=0; j<DClass.get(i).size(); j++){
                if(Double.parseDouble(DClass.get(i).get(j).getM数学()) >= 90.0){
                    classData[i][1][0]++;
                    DClass.get(i).get(j).get分数等级标识()[1] = 0;
                }else if(Double.parseDouble(DClass.get(i).get(j).getM数学()) >= 80.0){
                    classData[i][1][1]++;
                    DClass.get(i).get(j).get分数等级标识()[1] = 1;
                }else if(Double.parseDouble(DClass.get(i).get(j).getM数学()) >= 60.0){
                    classData[i][1][2]++;
                    DClass.get(i).get(j).get分数等级标识()[1] = 2;
                }else if(Double.parseDouble(DClass.get(i).get(j).getM数学()) <= 30.0){
                    classData[i][1][3]++;
                    DClass.get(i).get(j).get分数等级标识()[1] = 3;
                }else{
                    DClass.get(i).get(j).get分数等级标识()[1] = 5;
                }
                MathTotal = MathTotal + Double.parseDouble(DClass.get(i).get(j).getM数学());

            }
            classData[i][1][4] = MathTotal/DClass.get(i).size();

            //英语
            for(int j=0; j<DClass.get(i).size(); j++){
                if(Double.parseDouble(DClass.get(i).get(j).getM英语()) >= 90.0){
                    classData[i][2][0]++;
                    DClass.get(i).get(j).get分数等级标识()[2] = 0;
                }else if(Double.parseDouble(DClass.get(i).get(j).getM英语()) >= 80.0){
                    classData[i][2][1]++;
                    DClass.get(i).get(j).get分数等级标识()[2] = 1;
                }else if(Double.parseDouble(DClass.get(i).get(j).getM英语()) >= 60.0){
                    classData[i][2][2]++;
                    DClass.get(i).get(j).get分数等级标识()[2] = 2;
                }else if(Double.parseDouble(DClass.get(i).get(j).getM英语()) <= 30.0){
                    classData[i][2][3]++;
                    DClass.get(i).get(j).get分数等级标识()[2] = 3;
                }else{
                    DClass.get(i).get(j).get分数等级标识()[2] = 5;
                }


                EnglishTotal = EnglishTotal + Double.parseDouble(DClass.get(i).get(j).getM英语());

            }
            classData[i][2][4] = EnglishTotal/DClass.get(i).size();
        }
        return classData;
    }

    //输出各班级数据
    public void printClassData(double[][][] classData){
        String subject = "语文";
        for (int i=0; i<13; i++){
            for (int j=0; j<5; j++){
                for (int k=0; k<3; k++){
                    switch (k){
                        case 0: subject = "语文";
                            break;
                        case 1: subject = "数学";
                            break;
                        case 2: subject = "英语";
                            break;
                    }

                    if(j < 4){
                        System.out.println(i+"班+"+subject+"第"+j+"档次："+classData[i][k][j]);
                    }else{
                        System.out.println(i+"班+"+subject+"平均分："+classData[i][k][j]);
                    }


                }

            }
        }
    }
}
