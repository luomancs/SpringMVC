package com.luoman.controller;

import com.luoman.model.ExperienceEntity;
import com.luoman.model.SubjectEntity;
import com.luoman.repository.ExperienceRepository;
import com.luoman.repository.SubjectRepository;
import com.luoman.repository.UserRepository;
import com.luoman.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.*;

import java.text.SimpleDateFormat;
import java.text.ParseException;


/**
 * Created by Mango on 2017/5/4.
 */
@Controller
public class ExperienceController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ExperienceRepository experienceRepository;
    @Autowired
    SubjectRepository subjectRepository;

    //Teacher
    @RequestMapping(value = "tGetAllExperience",method = RequestMethod.GET)
    public String tGetAllExperience(){
        return "view/tGetAllExperience";
        //return "pages/Experience/get";
    }
    @RequestMapping(value="tExperienceDetail{experienceId}",method = RequestMethod.GET)
    //@RequestMapping(value="/pages/Experience/detail/{experienceId}",method = RequestMethod.GET)
    public String tExperienceDetail(@PathVariable("experienceId") Integer experienceId,ModelMap modelMap,HttpSession session){
        //ExperienceEntity experienceEntity=experienceRepository.findOne(experienceId);
        List<ExperienceEntity> experienceList = (List<ExperienceEntity>) session.getAttribute("experienceList");
        for(int i=0;i<experienceList.size();i++){
            if(experienceList.get(i).getExperienceId()==experienceId)
                modelMap.addAttribute("experience",experienceList.get(i));
        }
        //modelMap.addAttribute("experience",experienceEntity);
        //modelMap.addAttribute("subjectList",subjectList);
        return "view/tExperienceDetail";
    }

    @RequestMapping(value = "tFindExperienceAdd",method = RequestMethod.GET)
    public String tFindExperienceAdd(){
        return "view/tFindAddExperience";
    }

    @RequestMapping(value = "tAddExperience", method = RequestMethod.POST)
    public String tAddExperience(HttpSession session,@ModelAttribute("experience") ExperienceEntity experienceEntity){
        UserEntity User = (UserEntity) session.getAttribute("userEntity");
        experienceEntity.setUserByUserId(User);
        userRepository.saveAndFlush(User);
        experienceEntity.setExperienceId(0);
        experienceEntity.setSelectAmount(0);
        System.out.println(User.getId()+"  "+experienceEntity.getUserByUserId().getId()+"   "+experienceEntity.getExperienceId());
        experienceRepository.saveAndFlush(experienceEntity);
        List<ExperienceEntity> experienceList = (List<ExperienceEntity>) session.getAttribute("experienceList");
        experienceList.add(experienceEntity);
        session.setAttribute("experienceEntity", experienceList);
        return "view/tFindAddExperience";}

    @RequestMapping(value = "tFindExperienceUpdate",method = RequestMethod.GET)
    public String tFindExperienceUpdate(){return "view/tFindExperienceUpdate";}

    @RequestMapping(value = "tUpdateExperience{experienceId}",method = RequestMethod.GET)
    public String tUpdateExperience(@PathVariable("experienceId") Integer experienceId, HttpSession session, ModelMap modelMap){
        //Integer selectAmount=Integer.valueOf(request.getParameter("subjectAmount"));
        List<ExperienceEntity> experienceList = (List<ExperienceEntity>) session.getAttribute("experienceList");
        for(int i=0;i<experienceList.size();i++) {
            if (experienceList.get(i).getExperienceId() == experienceId) {
                modelMap.addAttribute("experience", experienceList.get(i));
            }
        }
        return "view/tUpdateExperience";
    }
    @RequestMapping(value = "tUpdateExperienceInfo${experienceId}",method = {RequestMethod.GET, RequestMethod.POST})
    public String tUpdateExperienceInfo(@PathVariable("experienceId") Integer experienceId, HttpSession session, HttpServletRequest request){
        Integer selectAmount=Integer.valueOf(request.getParameter("subjectAmount"));
        List<ExperienceEntity> experienceList = (List<ExperienceEntity>) session.getAttribute("experienceList");
        String objective = request.getParameter("objective");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date begin = null, deadline = null;
        try {
            begin = sdf.parse(request.getParameter("begin"));
            deadline = sdf.parse(request.getParameter("deadline"));
        }catch (Exception e){}
        String period = request.getParameter("period");
        for(int i=0;i<experienceList.size();i++) {
            if (experienceList.get(i).getExperienceId() == experienceId) {
                experienceList.get(i).setSelectAmount(selectAmount);
                experienceList.get(i).setBegin(begin);
                experienceList.get(i).setObjective(objective);
                experienceList.get(i).setDeadline(deadline);
                experienceList.get(i).setPeriod(period);
            }
        }
        session.setAttribute("experienceList",experienceList);
        experienceRepository.updateExperience(selectAmount,objective,period,begin,deadline,experienceId);
        return "view/tFindExperienceUpdate";
    }

    @RequestMapping(value = "tFindExperienceDelete", method = RequestMethod.GET)
    public String tFindExperienceDelete(){return "view/tFindExperienceDelete";}


    @RequestMapping(value = "tExperienceDelete{experienceId}", method = RequestMethod.GET)
    public String tExperienceDelete(@PathVariable("experienceId") Integer experienceId, HttpSession session){
        List<ExperienceEntity> experienceList= (List<ExperienceEntity>) session.getAttribute("experienceList");
        Map<SubjectEntity, ExperienceEntity> experienceSubject = (Map<SubjectEntity, ExperienceEntity>) session.getAttribute("experienceSubject");
        for (ExperienceEntity experienceEntity: experienceList) {
            if (experienceEntity.getExperienceId() == experienceId)
                experienceList.remove(experienceEntity);
                break;
        }
        for (SubjectEntity subjectEntity:experienceSubject.keySet()){
            if(experienceSubject.get(subjectEntity).getExperienceId() ==experienceId)
                experienceSubject.remove(subjectEntity);
        }
        experienceRepository.delete(experienceId);
        return "view/tFindExperienceDelete";
    }
    @RequestMapping(value="/pages/Experience/update/{experienceId}",method = RequestMethod.GET)
    public String findExperienceUpdate(@PathVariable("experienceId") Integer experienceId,ModelMap modelMap){
        ExperienceEntity experienceEntity=experienceRepository.findOne(experienceId);
        modelMap.addAttribute("experience",experienceEntity);
        return "/pages/Experience/update";
    }

    //Student
    @RequestMapping(value="pages/student/checkAllExperience/{studentId}",method = RequestMethod.GET)
    public String allExperience(@PathVariable("studentId") Integer studentId, ModelMap modelMap){
        UserEntity userEntity=userRepository.findOne(studentId);
        List<ExperienceEntity> experienceList=experienceRepository.findAll();
        System.out.println(experienceList.size());
        Map mapList=new HashMap();
        for(int i=0;i<experienceList.size();i++)
        {
            if(experienceList.get(i).getSelectAmount()<experienceList.get(i).getSubjectAmount()){
                mapList.put(experienceList.get(i),"allow");
            }
            else
                mapList.put(experienceList.get(i),"occupy");
        }
        modelMap.addAttribute("mapList",mapList);
        modelMap.addAttribute("userEntity",userEntity);
        return "pages/Experience/studentAllExperience";
    }
    @RequestMapping(value="/pages/student/checkAllExperience",method = RequestMethod.GET)
    public String allExperience(HttpSession session){
        List<ExperienceEntity> experienceList=experienceRepository.findAll();
        System.out.println(experienceList.size());
        Map mapList=new HashMap();
        for(int i=0;i<experienceList.size();i++)
        {
            if(experienceList.get(i).getSelectAmount()<experienceList.get(i).getSubjectAmount()){
                mapList.put(experienceList.get(i),"allow");
            }
            else
                mapList.put(experienceList.get(i),"occupy");
        }
        session.setAttribute("mapList",mapList);
        return "pages/Experience/studentAllExperience";
    }
    @RequestMapping(value="/pages/student/MyExperience",method = RequestMethod.GET)
    public String studentExperience(HttpSession session){
        UserEntity userEntity=(UserEntity) session.getAttribute("userEntity");
        List<SubjectEntity> subjectList=subjectRepository.findByStudentId(userEntity.getId());
        session.setAttribute("subjectList",subjectList);
        return "pages/Subject/studentSubject";
    }
    @RequestMapping(value="/pages/Experience/student/detail/{experienceId}",method = RequestMethod.GET)
    public String studentExperienceDetail(@PathVariable("experienceId") Integer experienceId,HttpSession session){
        ExperienceEntity experienceEntity=experienceRepository.findOne(experienceId);
        List<SubjectEntity> subjectList=subjectRepository.findSubjectByExperienceId(experienceId);
        System.out.println(subjectList.size());
        session.setAttribute("experienceEntity",experienceEntity);
        session.setAttribute("subjectList",subjectList);
        return "pages/Experience/studentExperienceDetail";
    }
    @RequestMapping(value = "/findSelectExperience/{experienceId}",method = {RequestMethod.GET,RequestMethod.POST})
    public String studentSelectExperience(@PathVariable("experienceId") Integer experienceId,HttpSession session){
        List<SubjectEntity> subjectList=subjectRepository.findSubjectByExperienceId(experienceId);
        UserEntity userEntity=(UserEntity) session.getAttribute("userEntity");
        for(int i=0;i<subjectList.size();i++) {
            if (subjectList.get(i).getState() == 'N') {
                //更新subjectEntity与ExperienceEntity;
                subjectRepository.subjectSelect('Y',userEntity.getId(),subjectList.get(i).getSubjectId());
                ExperienceEntity experienceEntity=experienceRepository.findOne(experienceId);
                Integer selectAmount=experienceEntity.getSelectAmount()+1;
                experienceRepository.subjectSelect(selectAmount,experienceId);
                break;
            }
        }
        return "redirect:/pages/student/MyExperience";
    }
}
