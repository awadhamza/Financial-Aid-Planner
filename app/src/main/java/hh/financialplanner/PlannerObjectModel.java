package hh.financialplanner;

import java.util.List;

public class PlannerObjectModel {

    private List<PlannerObject> plannerObjectList;

    public PlannerObjectModel(List<PlannerObject> plannerObjectList) {
        this.plannerObjectList = plannerObjectList;
    }

    @Override
    public String toString() {
        return "PlannerObjectModel{" +
                "plannerObjectList=" + plannerObjectList +
                '}';
    }

    public List<PlannerObject> getPlannerObjectList() {
        return plannerObjectList;
    }

    public void setPlannerObjectList(List<PlannerObject> plannerObjectList) {
        this.plannerObjectList = plannerObjectList;
    }
}
