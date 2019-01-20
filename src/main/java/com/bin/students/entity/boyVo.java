package com.bin.students.entity;

import javax.persistence.Column;
import javax.persistence.Id;

public class boyVo {
    @Id
    private Integer id;

    private String 档案号;

    private String 学籍号;

    @Column(name = "` 姓名`")
    private String 姓名;

    @Column(name = "K性别")
    private String k性别;

    @Column(name = "M语文")
    private String m语文;

    @Column(name = "M数学")
    private String m数学;

    @Column(name = "M英语")
    private String m英语;

    @Column(name = "M总成绩")
    private String m总成绩;

    private String 是否注册;

    @Column(name = "K村居")
    private String k村居;

    private Integer 班级;



    private int[] 分数等级标识 = new int[3];

    public int[] get分数等级标识() {
        return 分数等级标识;
    }

    public void set分数等级标识(int[] 分数等级标识) {
        this.分数等级标识 = 分数等级标识;
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 档案号
     */
    public String get档案号() {
        return 档案号;
    }

    /**
     * @param 档案号
     */
    public void set档案号(String 档案号) {
        this.档案号 = 档案号;
    }

    /**
     * @return 学籍号
     */
    public String get学籍号() {
        return 学籍号;
    }

    /**
     * @param 学籍号
     */
    public void set学籍号(String 学籍号) {
        this.学籍号 = 学籍号;
    }

    /**
     * @return  姓名
     */
    public String get姓名() {
        return 姓名;
    }

    /**
     * @param 姓名
     */
    public void set姓名(String 姓名) {
        this.姓名 = 姓名;
    }

    /**
     * @return K性别
     */
    public String getK性别() {
        return k性别;
    }

    /**
     * @param k性别
     */
    public void setK性别(String k性别) {
        this.k性别 = k性别;
    }

    /**
     * @return M语文
     */
    public String getM语文() {
        return m语文;
    }

    /**
     * @param m语文
     */
    public void setM语文(String m语文) {
        this.m语文 = m语文;
    }

    /**
     * @return M数学
     */
    public String getM数学() {
        return m数学;
    }

    /**
     * @param m数学
     */
    public void setM数学(String m数学) {
        this.m数学 = m数学;
    }

    /**
     * @return M英语
     */
    public String getM英语() {
        return m英语;
    }

    /**
     * @param m英语
     */
    public void setM英语(String m英语) {
        this.m英语 = m英语;
    }

    /**
     * @return M总成绩
     */
    public String getM总成绩() {
        return m总成绩;
    }

    /**
     * @param m总成绩
     */
    public void setM总成绩(String m总成绩) {
        this.m总成绩 = m总成绩;
    }

    /**
     * @return 是否注册
     */
    public String get是否注册() {
        return 是否注册;
    }

    /**
     * @param 是否注册
     */
    public void set是否注册(String 是否注册) {
        this.是否注册 = 是否注册;
    }

    /**
     * @return K村居
     */
    public String getK村居() {
        return k村居;
    }

    /**
     * @param k村居
     */
    public void setK村居(String k村居) {
        this.k村居 = k村居;
    }

    /**
     * @return 班级
     */
    public Integer get班级() {
        return 班级;
    }

    /**
     * @param 班级
     */
    public void set班级(Integer 班级) {
        this.班级 = 班级;
    }
}