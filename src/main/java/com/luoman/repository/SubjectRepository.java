package com.luoman.repository;

import com.luoman.model.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;
import com.luoman.model.UserEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Mango on 2017/4/29.
 */
public interface SubjectRepository extends JpaRepository<SubjectEntity, Integer> {

    List<SubjectEntity> findByStudentId(Integer studentId);

    @Query("select s from SubjectEntity s where s.experienceByExperienceId.id=:id")
    List<SubjectEntity> findSubjectByExperienceId(@Param("id")Integer id);

    @Query("select s from SubjectEntity s where s.experienceByExperienceId.id=:id and s.state=:state")
    List<SubjectEntity> findSubjectByState(@Param("id")Integer id,@Param("state") char state);


    @Modifying      // 说明该方法是修改操作
    @Transactional  // 说明该方法是事务性操作
    // 定义查询
    // @Param注解用于提取参数
    @Query("update SubjectEntity se set se.question=:question,se.rightAnswer=:rightAnswer,se.gist=:gist where se.id=:id")
    void updateSubject(@Param("question") String question,@Param("rightAnswer") String rightAnswer,@Param("gist") String gist, @Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("update SubjectEntity se set se.mark=:mark where se.id=:id")
    void updateMark(@Param("mark") Double mark, @Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("update SubjectEntity se set se.studentAnswer=:studentAnswer where se.id=:id")
    void updateStudent(@Param("studentAnswer") String studentAnswer, @Param("id") Integer id);

    @Transactional  // 说明该方法是事务性操作
    @Modifying      // 说明该方法是修改操作
    @Query("update SubjectEntity se set se.state=:state, se.studentId=:student_id where se.id=:id")
    void subjectSelect(@Param("state") char state, @Param("student_id") Integer student_id, @Param("id") Integer id);
}

