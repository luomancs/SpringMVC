package com.luoman.controller;

import com.luoman.model.ExperienceEntity;
import com.luoman.model.SubjectEntity;
import com.luoman.repository.ExperienceRepository;
import com.luoman.repository.SubjectRepository;
import com.luoman.repository.UserRepository;
import com.luoman.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;


import java.io.InputStream;


/**
 * Created by Mango on 2017/4/22.
 */
@Controller
public class MainController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ExperienceRepository experienceRepository;
    @Autowired
    SubjectRepository subjectRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getUsers() {
        return "view/login";
    }


    @RequestMapping(value = "registration", method = RequestMethod.GET)
    public String addUser() {
        return "view/registration";
    }

    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") UserEntity userEntity) {

        Date now = new Date();
        userEntity.setPubDate(now);
        userRepository.saveAndFlush(userEntity);
        // 重定向到用户管理页面，方法为 redirect:url
        return "redirect:/";
    }

    @RequestMapping(value = "/pages/users/login", method = RequestMethod.GET)
    public String login() {
        return "pages/login";
    }

    @RequestMapping(value = "/loginUser", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpSession session) {

        // 找到user所表示的用户

        UserEntity User = userRepository.findOne(Integer.valueOf(request.getParameter("id")));

        // 传递给请求页面
        if (User != null && User.getPassword().equals(request.getParameter("password"))) {
            session.setAttribute("userEntity", User);
            if (User.getIdentity().equals("teacher")) {
                List<ExperienceEntity> experienceList= experienceRepository.findByUserByUserId(User);
                List<SubjectEntity> subjectList = new LinkedList<SubjectEntity>();
                Map <SubjectEntity, ExperienceEntity> experienceSubject = new HashMap<SubjectEntity, ExperienceEntity>();
                for (int i=0;i<experienceList.size();i++) {
                    List<SubjectEntity> addList = subjectRepository.findSubjectByExperienceId(experienceList.get(i).getExperienceId());
                    for (int j = 0; j < addList.size(); j++) {
                        subjectList.add(addList.get(j));
                        experienceSubject.put(addList.get(j), experienceList.get(i));
                    }
                }
                session.setAttribute("eAmount",experienceList.size());
                session.setAttribute("sAmount",subjectList.size());
                session.setAttribute("experienceList",experienceList);
                session.setAttribute("subjectList",subjectList);
                session.setAttribute("experienceSubject", experienceSubject);
                return "view/tDashboard";
                //return "pages/teacher";
            } else {
                List<SubjectEntity> subjectList = subjectRepository.findByStudentId(User.getId());
                session.setAttribute("subjectList",subjectList);
                return "pages/student";
            }
        } else
            return "pages/login";
    }

    @RequestMapping(value = "/pages/student/updateInfo", method = RequestMethod.GET)
    public String findUpdateInfo() {
        return "/pages/userUpdate";
    }


    @RequestMapping(value = "tAddStudent",method = RequestMethod.GET)
    public String tAddStudent(){
        return "view/tStudentAdd";
    }

    @RequestMapping(value = "addStudent",method = RequestMethod.POST)
    public String addStudent(@ModelAttribute("userAdd") UserEntity userEntity){
        Date now = new Date();
        userEntity.setPubDate(now);
        userEntity.setPassword("123456");
        userEntity.setIdentity("student");
        userRepository.saveAndFlush(userEntity);
        return "view/tStudentAdd";
    }
    @RequestMapping(value = "tFindStudentUpdate",method = RequestMethod.GET)
    public String tFindStudentUpdate(HttpSession session){
        List<UserEntity> studentList= userRepository.getStudentByIndentity("student");
        session.setAttribute("studentList",studentList);
        return "view/tFindStudentUpdate";
    }
    @RequestMapping(value = "tStudentUpdate{studentId}",method = RequestMethod.GET)
    public String updateStudent(@PathVariable("studentId") Integer studentId, ModelMap modelMap, HttpSession session){
        List<UserEntity> studentList = (List<UserEntity>) session.getAttribute("studentList");
        for (UserEntity student :studentList){
            if(student.getId().equals(studentId)){
               //modelMap.addAttribute("studentUpdate",student);
               session.setAttribute("studentUpdate",student);
               System.out.println("#");
               System.out.println(student.getUserName());
                break;
            }
        }
        return "view/tStudentUpdate";
    }
    @RequestMapping(value = "updateStudent{id}", method = RequestMethod.POST)
    public String updateStudent(@PathVariable("id") Integer studentId, HttpSession session, @ModelAttribute("studentUpdate") UserEntity studentUpdate){
        System.out.println(studentUpdate.getId());
        System.out.println(studentUpdate.getUserName());
        List<UserEntity> studentList = (List<UserEntity>) session.getAttribute("studentList");
        for (UserEntity student :studentList){
            if(student.getId().equals(studentId)) {
                userRepository.updateUser(studentUpdate.getUserName(), studentUpdate.getInstitution(), studentUpdate.getPassword(), studentUpdate.getMajor(), studentUpdate.getId());
                student.setUserName(studentUpdate.getUserName());
                student.setInstitution(studentUpdate.getInstitution());
                student.setMajor(studentUpdate.getMajor());
                student.setPassword(studentUpdate.getPassword());
            }
        }
        return "view/tFindStudentUpdate";
    }
    @RequestMapping(value = "tFindStudentDelete", method = RequestMethod.GET)
    public String tFindStudentDelete(){
        return "view/tFindStudentDelete";
    }
    @RequestMapping(value = "tStudentDelete{studentId}",method = RequestMethod.GET)
    public String tStudentDelete(@PathVariable("studentId") Integer studentId,HttpSession session){
        userRepository.delete(studentId);
        List<UserEntity> studentList = (List<UserEntity>) session.getAttribute("studentList");
        for (UserEntity student: studentList){
            if (student.getId().equals(studentId)){
                studentList.remove(student);
                break;
            }
        }
        return "view/tFindStudentDelete";
    }
    @RequestMapping(value = "tFindStudentScore",method = RequestMethod.GET)
    public String tFindStudentScore(){
        return "view/tFindStudentScore";
    }
    @RequestMapping(value = "tFindExperienceScore",method = RequestMethod.GET)
    public String tFindExperienceScore() {
        return "view/tFindExperienceScore";
    }

}