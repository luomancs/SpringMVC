package com.luoman.repository;

import com.luoman.model.UserEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;


/**
 * Created by Mango on 2017/4/27.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Modifying      // 说明该方法是修改操作
    @Transactional  // 说明该方法是事务性操作
    // 定义查询
    // @Param注解用于提取参数
    @Query("update UserEntity us set us.userName=:userName, us.institution=:institution, us.password=:password, us.major=:major where us.id=:id")
    void updateUser(@Param("userName") String userName, @Param("institution") String institution, @Param("password") String password,@Param("major") String major,
                           @Param("id") Integer id);
    @Query("select us from UserEntity us where us.identity=:identity")
    List<UserEntity> getStudentByIndentity(@Param("identity") String identity);
}
