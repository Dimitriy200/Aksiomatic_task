package com.axmtk_task.axmtk_task.services;

import com.axmtk_task.axmtk_task.models.SolutionStatus;

public class CreditSolutionService {
    Solution solution;
    private String approved;
    private String denied;

    public CreditSolutionService(Solution solution){
        this.approved = "approved";
        this.denied = "denied";
        this.solution = solution;
    }

    public CreditSolutionService(String approved,
                                 String denied){
        this.approved = approved;
        this.denied = denied;
    }

     public SolutionStatus getSolutionStatus(){
        if(this.solution.getSolution_status()){
            return SolutionStatus.approved;
        }
        else{
            return SolutionStatus.denied;
        }

     }
}