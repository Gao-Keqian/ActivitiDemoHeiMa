package com.example.activitidemoheima.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Participant implements Serializable {
    private String applicant;
    private String dptManager;
    private String techDeveloper;
    private String techManager;
    private String dptManagerCheck;
    private boolean techDeveloperCheck;
    private boolean techManagerCheck;
    private boolean transferToOther;
}
