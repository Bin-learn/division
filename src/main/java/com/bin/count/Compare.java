package com.bin.count;

import com.bin.students.entity.boyVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("Compare")
public class Compare {


    List<List<boyVo>> AllClass;

    double[][][] AllclassData;
    //根据初次分班的结果及各班数据进行调节
    public List<List<boyVo>> adjust(double[][][] classData, List<List<boyVo>> Dclass) {

        AllClass = Dclass;
        AllclassData = classData;

        double N = 1.5;

        int[][] defferentNum = new int[12][12];
        int defferentNumMax = 0;
        int compareClassA = 0;
        int compareClassB = 0;

        //表示每个班各科缺失的档次
        int[][][] lack = new int[12][3][4];
        int[][][] surplus = new int[12][3][4];

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                for(int k=0; k<4; k++) {
                    lack[i][j][k] = 0;
                    surplus[i][j][k] = 0;
                }
            }
        }

        //1代表缺少，2代表多余
        for(int i=0; i<12; i++){
            for(int j=0; j<3; j++){
                for(int k=0; k<4; k++){
//                    System.out.println(classData[i][j][k]+","+classData[12][j][k]);
                    if(classData[i][j][k] < classData[12][j][k] - N){
                        lack[i][j][k] = 1;
                    }else if(classData[i][j][k] > classData[12][j][k] + N){
                        lack[i][j][k] = 2;
                    }
                }
            }
        }

        for(int i=0; i<12; i++){
            for(int l=i+1; l<12; l++) {
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 4; k++) {
                        if (lack[i][j][k] + lack[l][j][k] == 3) {
                            defferentNum[i][l]++;
                        }
                    }
                }
            }
        }

        for(int i=0; i<12; i++){
            for(int l=i+1; l<12; l++) {
               if(defferentNum[i][l] > defferentNumMax){
                   defferentNumMax = defferentNum[i][l];
                   compareClassA = i;
                   compareClassB = l;
               }
            }
        }

        AllClass = changeTwoClass(compareClassA, compareClassB, lack);
        return Dclass;
    }

    private List<List<boyVo>> changeTwoClass(int compareClassA, int compareClassB, int[][][] lack) {

        int matchingNum = 3;
        int matchingB = 0, matchingA = 0;

        List<boyVo> classA = AllClass.get(compareClassA);
        List<boyVo> classB = AllClass.get(compareClassB);

        boyVo changeB = classB.get(0);
        int changeBID = 100;



        for(int i=0; i<classB.size(); i++){
            int[] b = classB.get(i).get分数等级标识();
            for(int j=0; j<3; j++){
                if(b[j]!=5 && lack[compareClassA][j][b[j]] == 1 ){
                    matchingB++;
                    if(matchingB == matchingNum){
                        changeB = classB.get(i);
                        changeBID = i;
                        i = 1000;
                        j = 1000;
                    }else if(j == 2){
                        matchingB = 0;
                    }
                }
            }
            if(i == classA.size() - 1 && matchingNum>0){
                i = 0;
                matchingNum--;
            }
        }

        matchingNum = 3;

        for(int i=0; i<classA.size(); i++){
            int[] a = classA.get(i).get分数等级标识();
            for(int j=0; j<3; j++){
                if(a[j]!=5 && lack[compareClassB][j][a[j]] == 1){
                    matchingA++;
                    if(matchingA == matchingNum){
                        if(changeBID != 100){
                            classB.set(changeBID, classA.get(i));
                            classA.set(i, changeB);
                        }
                        i = 1000;
                        j = 1000;
                    }else if(j == 2){
                        matchingA = 0;
                    }
                }
            }
            if(i == classB.size() - 1 && matchingNum>0){
                i = 0;
                matchingNum--;
            }
        }


        return AllClass;
    }

}
