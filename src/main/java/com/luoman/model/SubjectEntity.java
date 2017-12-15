package com.luoman.model;



import javax.persistence.*;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Cascade;

/**
 * Created by Mango on 2017/4/29.
 */
@Entity
@Table(name = "SubjectEntity", schema = "springMVC")
public class SubjectEntity {

    private Integer subjectId;
    private ExperienceEntity experienceByExperienceId;
    private Integer studentId;
    private Double mark;
    private String question;
    private String studentAnswer;
    private String rightAnswer;
    private String gist;
    private char state;

    @Id
    @GeneratedValue
    @Column(name = "subjectId", nullable = false)
    public Integer getSubjectId(){return this.subjectId;}

    public void setSubjectId(Integer id){this.subjectId=id;}


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "experience_id", referencedColumnName = "experienceId", nullable = false)
    @Cascade(value= CascadeType.DELETE)
    public ExperienceEntity getExperienceByExperienceId(){
        return this.experienceByExperienceId;
    }
    public void setExperienceByExperienceId(ExperienceEntity experienceByExperienceId){
        this.experienceByExperienceId=experienceByExperienceId;
    }

    @Basic
    @Column(name = "studentId")
    public Integer getStudentId() {
        return this.studentId;
    }
    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "mark")
    public Double getMark(){return this.mark;}

    public void setMark(Double mark){this.mark=mark;}

    @Basic
    @Column(name = "question", nullable = false)
    public String getQuestion(){return this.question;}

    public void setQuestion(String question){this.question=question;}

    @Basic
    @Column(name = "rightAnswer")
    public String getRightAnswer(){return this.rightAnswer;}

    public void setRightAnswer(String rightAnswer){this.rightAnswer=rightAnswer;}

    @Basic
    @Column(name = "studentAnswer")
    public String getStudentAnswer(){return this.studentAnswer;}

    public void setStudentAnswer(String studentAnswer){this.studentAnswer=studentAnswer;}

    @Basic
    @Column(name = "gist")
    public String getGist(){return this.gist;}

    public void setGist(String gist){this.gist=gist;}

    @Basic
    @Column(name="state")
    public char getState(){return this.state;}
    public void setState(char state){this.state=state;}

}
