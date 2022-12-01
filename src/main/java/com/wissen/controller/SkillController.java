package com.wissen.controller;

import com.wissen.entity.Department;
import com.wissen.entity.Designation;
import com.wissen.entity.EmployeeSkill;
import com.wissen.entity.Skill;
import com.wissen.service.EmployeeSkillService;
import com.wissen.service.SkillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


@Validated
@Slf4j
@RestController
@RequestMapping("/api/skill")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @Autowired
    private EmployeeSkillService employeeSkillService;

    @PostMapping
    public ResponseEntity<List<Skill>> saveSkill(@RequestBody @NotEmpty(message = "Skill list is empty") final List<String> skills){
        log.info("Starting to save Skills");
        List<Skill> savedSkills = this.skillService.saveSkills(skills);
        return ResponseEntity.status(HttpStatus.OK)
                .body(savedSkills);
    }

    @PostMapping("/skillEmployeeMapping")
    public ResponseEntity<EmployeeSkill> saveSkillEmployeeMapping(@RequestParam @NotNull(message = "Skill id is null") final int skillId,
                                                                  @RequestParam @NotNull(message = "Employee id is null") final int employeeId,
                                                                  @RequestParam @NotNull(message = "Level is null") final int levels){
        log.info("START: Saving skill employee mapping");
        log.info("Skill id: {}, Employee id: {}, level: {}", skillId, employeeId, levels);
        EmployeeSkill employeeSkill = this.employeeSkillService.saveSkillEmployeeMapping(skillId, employeeId, levels);
        return ResponseEntity.status(HttpStatus.OK)
                .body(employeeSkill);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Skill>> searchSkills(@RequestParam final String skill) {
        log.info("START: Getting skills");
        List<Skill> skills = this.skillService.searchSkills(skill);
        log.info("START: Getting skills");
        return ResponseEntity.status(HttpStatus.OK)
                .body(skills);
    }

    @GetMapping
    public List<Skill> getAllSkills(){
        log.info("Starting to fetch the skills");
        return skillService.getAllSkills();
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Skill> getSkillById(@PathVariable int id){
        return new ResponseEntity<>(skillService.getSkillById(id),HttpStatus.OK);
    }
}
