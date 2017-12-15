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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mango on 2017/5/4.
 */
@Controller
public class SubjectController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ExperienceRepository experienceRepository;
    @Autowired
    SubjectRepository subjectRepository;


    @RequestMapping(value = "/pages/Experience/get/subjectAdd/{id}", method = RequestMethod.GET)
    public String Subject(@PathVariable("id") Integer experienceId, ModelMap modelMap) {
        ExperienceEntity experienceEntity = experienceRepository.findOne(experienceId);
        List<SubjectEntity> subjectList = subjectRepository.findSubjectByExperienceId(experienceId);
        modelMap.addAttribute("experienceEntity", experienceEntity);
        modelMap.addAttribute("subjectList", subjectList);
        return "pages/Subject/add";
    }

    @RequestMapping(value = "/addSubject/{id}", method = {RequestMethod.POST, RequestMethod.GET})
    public String addSubject(@PathVariable("id") Integer experienceId, @ModelAttribute("subject") SubjectEntity subjectEntity, HttpServletRequest request, RedirectAttributes attr) {
        ExperienceEntity experienceEntity = experienceRepository.findOne(experienceId);
        subjectEntity.setExperienceByExperienceId(experienceEntity);
        subjectEntity.setState('N');
        subjectEntity.setSubjectId(0);
        subjectEntity.setRightAnswer(request.getParameter("rightAnswer"));
        subjectRepository.saveAndFlush(subjectEntity);
        attr.addFlashAttribute("{id}", experienceId);
        return "redirect:/pages/Experience/get/subjectAdd/{id}";
    }

    @RequestMapping(value = "/pages/Experience/get/subjectJudge/{id}", method = RequestMethod.GET)
    public String findSubjectJudge(@PathVariable("id") Integer experienceId, ModelMap modelMap) {
        List<SubjectEntity> subjectList = subjectRepository.findSubjectByExperienceId(experienceId);
        modelMap.addAttribute("subjectList", subjectList);
        return "/pages/Subject/judge";
    }

    @RequestMapping(value = "/findMark/{id}", method = RequestMethod.GET)
    public String findMark(@PathVariable("id") Integer subjectId, ModelMap modelMap) {
        SubjectEntity subjectEntity = subjectRepository.findOne(subjectId);
        modelMap.addAttribute(subjectEntity);
        return "pages/Subject/mark";
    }

    @RequestMapping(value = "/mark/{subjectId}", method = RequestMethod.POST)
    public String Mark(@PathVariable("subjectId") Integer subjectId, RedirectAttributes attr, HttpServletRequest request) {
        Double mark = Double.valueOf(request.getParameter("mark"));
        SubjectEntity subjectEntity = subjectRepository.findOne(subjectId);
        ExperienceEntity experienceEntity = subjectEntity.getExperienceByExperienceId();
        subjectRepository.updateMark(mark, subjectId);
        //attr.addFlashAttribute("{id}", experienceEntity.getExperienceId());
        return "redirect:/pages/Experience/get/subjectJudge/"+experienceEntity.getExperienceId()+"";


    }

    @RequestMapping(value = "/pages/Subject/update/{id}", method = RequestMethod.GET)
    public String findSubjectUpdate(@PathVariable("id") Integer subjectId, ModelMap modelMap) {
        SubjectEntity subjectEntity = subjectRepository.findOne(subjectId);
        modelMap.addAttribute("subjectEntity",subjectEntity);
        return "/pages/Subject/update";
    }

    @RequestMapping(value = "/updateSubject/{subjectId}", method = RequestMethod.POST)
    public String updateSubject(@PathVariable("subjectId") Integer subjectId, HttpServletRequest request, RedirectAttributes attr) {
        subjectRepository.updateSubject(request.getParameter("question"),request.getParameter("rightAnswer"),request.getParameter("gist"),subjectId);
        attr.addAttribute("{id}",subjectId);
        return "redirect:/pages/Subject/detail/{id}";
    }

    @RequestMapping(value = "/pages/Subject/detail/{id}", method = RequestMethod.GET)
    public String findSubjectDetail(@PathVariable("id") Integer subjectId, ModelMap modelMap) {
        SubjectEntity subjectEntity = subjectRepository.findOne(subjectId);
        modelMap.addAttribute(subjectEntity);
        return "/pages/Subject/detail";
    }
    @RequestMapping(value = "/pages/Subject/delete/{id}",method = RequestMethod.GET)
    public String deleteSubject(@PathVariable("id") Integer subjectId,RedirectAttributes attr)
    {
        SubjectEntity subjectEntity= subjectRepository.findOne(subjectId);
        ExperienceEntity experienceEntity=subjectEntity.getExperienceByExperienceId();
        attr.addAttribute("{id}",experienceEntity.getExperienceId());
        subjectRepository.delete(subjectId);
        return "redirect:/pages/Experience/detail/{id}";
    }
    @RequestMapping(value="/studentCheckSubject/{id}",method = RequestMethod.GET)
    public String studentCheckSubject(@PathVariable("id") Integer subjectId,ModelMap modelMap)
    {
        SubjectEntity subjectEntity=subjectRepository.findOne(subjectId);
        ExperienceEntity experienceEntity=subjectEntity.getExperienceByExperienceId();
        modelMap.addAttribute("subjectEntity",subjectEntity);
        modelMap.addAttribute(("experienceEntity"),experienceEntity);
        return "/pages/Subject/studentSubjectDetail";
    }
    @RequestMapping(value="/answerSubject/{id}",method = RequestMethod.GET)
    public String answerSubjetc(@PathVariable("id") Integer subjectId, ModelMap modelMap){
        SubjectEntity subjectEntity=subjectRepository.findOne(subjectId);
        ExperienceEntity experienceEntity=subjectEntity.getExperienceByExperienceId();
        modelMap.addAttribute("subjectEntity",subjectEntity);
        modelMap.addAttribute(("experienceEntity"),experienceEntity);
        return "/pages/Subject/answerSubject";
    }
    @RequestMapping(value = "/studentAnswer/{id}",method = RequestMethod.POST)
    public String studentAnswer(@PathVariable("id") Integer subjectId,HttpServletRequest request,RedirectAttributes attr){
        String answer=request.getParameter("studentAnswer");
        subjectRepository.updateStudent(answer,subjectId);
        attr.addAttribute("{id}",subjectId);
        return "redirect:/studentCheckSubject/{id}";
    }
    @RequestMapping(value = "/pages/student/Score",method = RequestMethod.GET)
    public String studentCheckScore(HttpSession session,ModelMap modelMap){
        UserEntity userEntity=(UserEntity) session.getAttribute("userEntity");
        List <SubjectEntity> subjectList = subjectRepository.findByStudentId(userEntity.getId());
        Double ave=0.0;
        int amount=0;
        for(int i=0;i<subjectList.size();i++){
            if(subjectList.get(i).getMark()!=null) {
                ave += subjectList.get(i).getMark();
                amount++;
            }
        }
        if(ave!=0 && amount!=0){
            ave=ave/amount;
        }
        modelMap.addAttribute("subjectList",subjectList);
        modelMap.addAttribute("ave",ave);
        return "/pages/Subject/studentCheckMark";
    }
    @RequestMapping(value ="/pages/teacher/Score",method = RequestMethod.GET)
    public String teacherCheckMark(HttpSession session,ModelMap modelMap){
        UserEntity userEntity=(UserEntity) session.getAttribute("userEntity");
        Map mapList=new HashMap();
        List <ExperienceEntity> experienceList=experienceRepository.findByUserByUserId(userEntity);
        for(int i=0;i<experienceList.size();i++){
            ExperienceEntity experience= experienceList.get(i);
            List <SubjectEntity> subjectEntityList=subjectRepository.findSubjectByExperienceId(experience.getExperienceId());
            mapList.put(experience.getObjective(),subjectEntityList);
        }
        modelMap.addAttribute("mapList",mapList);
        return "/pages/Subject/teacherCheckMark";
    }
    //teacher

    @RequestMapping(value = "tGetAllSubject", method = RequestMethod.GET)
    public String tGetAllSubject(){
        return "view/tGetAllSubject";
    }

    @RequestMapping(value = "tSubjectDetail{subjectId}", method = RequestMethod.GET)
    public String tSubjectDetail(@PathVariable("subjectId") Integer subjectId, ModelMap modelMap, HttpSession session){
        List<SubjectEntity> subjectList = (List<SubjectEntity>) session.getAttribute("subjectList");
        for (int i=0; i<subjectList.size();i++){
            if (subjectId==subjectList.get(i).getSubjectId())
                modelMap.addAttribute("subject",subjectList.get(i));
        }
        return "view/tSubjectDetail";
    }
    @RequestMapping(value = "tFindSubjectUpdate", method = RequestMethod.GET)
    public String tFindSubjectUpdate(){
        return "view/tFindSubjectUpdate";
    }
    @RequestMapping(value = "tUpdateSubject{subjectId}", method = RequestMethod.GET)
    public String tUpdateSubject(@PathVariable("subjectId") Integer subjectId, ModelMap modelMap, HttpSession session){
        List<SubjectEntity> subjectList = (List<SubjectEntity>) session.getAttribute("subjectList");
        for (int i=0; i<subjectList.size();i++){
            if (subjectId==subjectList.get(i).getSubjectId())
                modelMap.addAttribute("subject",subjectList.get(i));
        }
        return "view/tUpdateSubject";
    }
    @RequestMapping(value = "tUpdateSubjectInfo{subjectId}", method = RequestMethod.POST)
    public String tUpdateSubjectInfo(@PathVariable("subjectId") Integer subjectId, HttpServletRequest request,HttpSession session) {
        String question = request.getParameter("question");
        String rightAnswer = request.getParameter("rightAnswer");
        String gist = request.getParameter("gist");
        subjectRepository.updateSubject(question, rightAnswer, gist, subjectId);
        List<SubjectEntity> subjectList = (List<SubjectEntity>) session.getAttribute("subjectList");
        Map<SubjectEntity, ExperienceEntity> experienceSubject = (Map<SubjectEntity, ExperienceEntity>) session.getAttribute("experienceSubject");
        for (int i = 0; i < subjectList.size(); i++) {
            if (subjectId == subjectList.get(i).getSubjectId()) {
                subjectList.get(i).setQuestion(question);
                subjectList.get(i).setRightAnswer(rightAnswer);
                subjectList.get(i).setGist(gist);
            }
        }
        for (SubjectEntity subjectEntity: experienceSubject.keySet()){
            if(subjectEntity.getSubjectId() == subjectId)
                subjectEntity.setQuestion(question);
                subjectEntity.setRightAnswer(rightAnswer);
                subjectEntity.setGist(gist);
        }
        session.setAttribute("experienceSubject",experienceSubject);
        session.setAttribute("subjectList",subjectList);
        return "view/tFindSubjectUpdate";
    }
    @RequestMapping(value = "tFindSubjectAdd", method = RequestMethod.GET)
    public String tFindSubjectAdd(){
        return "view/tFindSubjectAdd";
    }
    @RequestMapping(value = "tSubjectAdd{experienceId}", method = RequestMethod.GET)
    public String tSubjectAdd(@PathVariable("experienceId") Integer experienceId,HttpSession session){
        session.setAttribute("subjectAddExperienceId",experienceId);
        return "view/tSubjectAdd";
    }
    @RequestMapping(value = "addSubject",method = RequestMethod.POST)
    public String addSubject(HttpSession session, HttpServletRequest request, @ModelAttribute("subject") SubjectEntity subjectEntity){
        Integer experienceId = (Integer) session.getAttribute("subjectAddExperienceId");
        ExperienceEntity experienceEntity = experienceRepository.findOne(experienceId);
        subjectEntity.setExperienceByExperienceId(experienceEntity);
        subjectEntity.setState('N');
        subjectEntity.setSubjectId(0);
        //subjectEntity.setRightAnswer(request.getParameter("rightAnswer"));
        subjectRepository.saveAndFlush(subjectEntity);
        List<SubjectEntity> subjectList = (List<SubjectEntity>) session.getAttribute("subjectList");
        Map<SubjectEntity, ExperienceEntity> experienceSubject = (Map<SubjectEntity, ExperienceEntity>) session.getAttribute("experienceSubject");
        subjectList.add(subjectEntity);
        experienceSubject.put(subjectEntity,experienceEntity);
        return "view/tFindSubjectAdd";
    }
    @RequestMapping(value ="tFindSubjectDelete", method = RequestMethod.GET)
    public String tFindSubjectDelete(){
        return "view/tFindSubjectDelete";
    }
    @RequestMapping(value = "tSubjectDelete{subjectId}",method = RequestMethod.GET)
    public String tSubjectDelete(@PathVariable("subjectId") Integer subjectId, HttpSession session){
        List<SubjectEntity> subjectList = (List<SubjectEntity>) session.getAttribute("subjectList");
        Map<SubjectEntity, ExperienceEntity> experienceSubject = (Map<SubjectEntity, ExperienceEntity>) session.getAttribute("experienceSubject");
        for(SubjectEntity subjectEntity: subjectList){
            if (subjectEntity.getSubjectId()== subjectId )
                subjectList.remove(subjectEntity);
        }
        for(SubjectEntity subjectEntity: experienceSubject.keySet()){
            if (subjectEntity.getSubjectId()==subjectId)
                experienceSubject.remove(subjectEntity);
        }
        subjectRepository.delete(subjectId);
        return "view/tFindSubjectDelete";
    }
}