package com.luoman.model;


import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;



/**
 * Created by Mango on 2017/4/29.
 */
@Entity
@Table(name = "ExperienceEntity", schema = "springMVC")
public class ExperienceEntity {
    private Integer experienceId;
    private Integer subjectAmount;
    private Integer selectAmount;
    private String objective;
    private String period;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date begin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deadline;
    private UserEntity userByUserId;

    ExperienceEntity(){}

    @Id
    @GeneratedValue
    @Column(name = "experienceId", nullable = false)
    public Integer getExperienceId() {return this.experienceId;}

    public void setExperienceId(Integer experienceId){ this.experienceId=experienceId;}


    @Basic
    @Column(name = "selectAmount", nullable = false)
    public Integer getSelectAmount(){
        return this.selectAmount;
    }
    public void setSelectAmount(Integer selectAmount){
        this.selectAmount=selectAmount;
    }

    @Basic
    @Column(name = "subjectAmount", nullable = false)
    public Integer getSubjectAmount(){
        return this.subjectAmount;
    }
    public void setSubjectAmount(Integer subjectAmount){
        this.subjectAmount=subjectAmount;
    }


    @Basic
    @Column(name = "objective", nullable = false)
    public String getObjective(){
        return this.objective;
    }
    public void setObjective(String objective){
        this.objective=objective;
    }

    @Basic
    @Column(name = "period", nullable = false)
    public String getPeriod(){
        return this.period;
    }
    public void setPeriod(String period){
        this.period=period;
    }

    @Basic
    @Column(name = "begin", nullable = false)
    public Date getBegin(){
        return this.begin;
    }
    public void setBegin(Date begin){
        this.begin=begin;
    }

    @Basic
    @Column(name = "deadline", nullable = false)
    public Date getDeadline(){
        return this.deadline;
    }
    public void setDeadline(Date deadline){
        this.deadline=deadline;
    }

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id", nullable = false)
    @Cascade(value=org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    public UserEntity getUserByUserId() {
        return userByUserId;
    }
    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }
}

