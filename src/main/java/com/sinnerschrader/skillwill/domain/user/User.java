package com.sinnerschrader.skillwill.domain.user;

import com.sinnerschrader.skillwill.domain.skills.KnownSkill;
import com.sinnerschrader.skillwill.domain.skills.UserSkill;
import com.sinnerschrader.skillwill.domain.skills.SkillUtils;
import com.sinnerschrader.skillwill.exceptions.SkillNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;

/**
 * Class holding all information about a person
 *
 * @author torree
 */
public class User {

  @Id
  private String id;
  private List<UserSkill> skills;
  private Role role;
  private String ldapDN;

  @Transient
  private FitnessScore fitnessScore;

  @Version
  private Long version;

  // LDAP Details will be updates regularly
  private UserLdapDetails ldapDetails;

  public User(String id) {
    this.id = id;
    this.skills = new ArrayList<>();
    this.ldapDetails = null;
    this.fitnessScore = null;
    this.role = Role.USER;
    this.ldapDN = null;
  }

  public String getId() {
    return this.id;
  }

  public List<UserSkill> getSkillsExcludeHidden() {
    return this.skills.stream()
      .filter(s -> !s.isHidden())
      .collect(Collectors.toList());
  }

  public List<UserSkill>  getSkills() {
    return this.skills;
  }

  public UserSkill getSkill(String name) {
    return this.skills.stream()
        .filter(s -> s.getName().equals(name))
        .findFirst()
        .orElse(null);
  }

  public boolean hasSkill(String skill) {
    return this.getSkill(skill) != null;
  }

  public UserSkill getSkillExcludeHidden(String name) {
    UserSkill skill = this.getSkill(name);
    return skill == null || skill.isHidden() ? null : skill;
  }

  public UserLdapDetails getLdapDetails() {
    return this.ldapDetails;
  }

  public void setLdapDetails(UserLdapDetails ldapDetails) {
    this.ldapDetails = ldapDetails;
  }

  public void addUpdateSkill(String name, int skillLevel, int willLevel, boolean hidden, boolean mentor) {
    // Remove old skill if existing...
    Optional<UserSkill> existing = skills.stream()
        .filter(s -> s.getName().equals(SkillUtils.sanitizeName(name)))
        .findFirst();
    if (existing.isPresent()) {
      existing.get().setSkillLevel(skillLevel);
      existing.get().setWillLevel(willLevel);
      existing.get().setMentor(mentor);
      existing.get().setHidden(hidden);
    } else {
      this.skills.add(new UserSkill(name, skillLevel, willLevel, hidden, mentor));
    }
  }

  public void removeSkill(String name) throws SkillNotFoundException {
    UserSkill skill = skills.stream()
        .filter(s -> s.getName().equals(name))
        .findAny()
        .orElseThrow(() -> new SkillNotFoundException("user does not have skill"));
    skills.remove(skill);
  }

  public void setFitnessScore(Collection<KnownSkill> searchedSkills, FitnessScoreProperties props) {
    this.fitnessScore = new FitnessScore(this, searchedSkills, props);
  }

  public double getFitnessScoreValue() {
    if (this.fitnessScore == null) {
      throw new IllegalStateException("no fitness score set");
    }

    return this.fitnessScore.getValue();
  }

  public JSONObject toJSON() {
    JSONObject obj = new JSONObject();
    obj.put("id", this.id);
    obj.put("role", this.role.toString());

    if (this.ldapDetails != null) {
      obj.put("firstName", ldapDetails.getFirstName());
      obj.put("lastName", ldapDetails.getLastName());
      obj.put("mail", ldapDetails.getMail());
      obj.put("phone", ldapDetails.getPhone());
      obj.put("location", ldapDetails.getLocation());
      obj.put("title", ldapDetails.getTitle());
      obj.put("company", ldapDetails.getCompany());
    }

    if (this.fitnessScore != null) {
      obj.put("fitness", this.fitnessScore.getValue());
    }

    JSONArray skillsArr = new JSONArray();
    this.skills.stream()
      .filter(s -> !s.isHidden())
      .sorted(Comparator.comparing(UserSkill::getName))
      .map(UserSkill::toJSON)
      .forEach(skillsArr::put);

    obj.put("skills", skillsArr);
    return obj;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public String getLdapDN() {
    return this.ldapDN;
  }

  public void setLdapDN(String ldapDN) {
    this.ldapDN = ldapDN;
  }

}