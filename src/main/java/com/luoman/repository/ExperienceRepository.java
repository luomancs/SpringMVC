package com.luoman.repository;

import com.luoman.model.ExperienceEntity;
import com.luoman.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;

/**
 * Created by Mango on 2017/4/29.
 */
public interface ExperienceRepository extends JpaRepository<ExperienceEntity, Integer> {

   //@Query("select e from ExperienceEntity e where e.userByUserId=:user")
   //List<ExperienceEntity> findByUserByUser(@Param("user") UserEntity user);
   List<ExperienceEntity> findByUserByUserId(UserEntity user);
   ExperienceEntity findByExperienceId(Integer experienceId);
   //后期要删除
   //@Query("select e from ExperienceEntity e where e.userByUserId.id=:user")
   //List<ExperienceEntity> findByUserByUserId(@Param("user") Integer user);


   @Modifying
   @Transactional
   @Query("update ExperienceEntity e set e.selectAmount=:selectAmount where e.id=:id")
   void subjectSelect(@Param("selectAmount") Integer selectAmount,@Param("id") Integer id);

   @Modifying
   @Transactional
   @Query("update ExperienceEntity e set e.subjectAmount=:subjectAmount,e.objective=:objective,e.begin=:begin,e.deadline=:deadline,e.period=:period where e.id=:id")
   void updateExperience(@Param("subjectAmount") Integer subjectAmount,
                         @Param("objective") String objective, @Param("period") String period,
                         @Param("begin") Date begin,@Param("deadline") Date deadline,@Param("id") Integer id);

}
