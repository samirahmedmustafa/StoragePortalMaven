package controller;

import com.google.gson.Gson;
import entity.AggrBackupReport;
import entity.BackupStatus;
import entity.Data;
import facade.AggrBackupReportFacade;
import facade.BackupStatusFacade;
import facade.DataFacade;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@Named(value = "controller")
@SessionScoped
public class Controller implements Serializable {

    @EJB
    private BackupStatusFacade backupStatusFacade;
    
    @EJB
    private AggrBackupReportFacade aggrBackupReportFacade;

    @Inject
    BackupStatus backupStatus;

    @EJB
    private DataFacade dataFacade;
    private String mv3lavun01prp;
    private String mv2lavun01prp;
    private String aggregated_mv3lavun01prp;
    private String aggregated_mv2lavun01prp;
    private String lastSevenDays;

    public BackupStatus getBackupStatus() {
        return backupStatus;
    }

    public BackupStatusFacade getBackupStatusFacade() {
        return backupStatusFacade;
    }

    public String findAll() {
        List<Data> dataList = dataFacade.findAll();
//        System.out.println("Data: " + dataList.get(0).getTotal());
//        System.out.println(dataList.get(0).toString());
        return dataList.get(0).toString();
    }

    public void toDashboard() throws IOException {
        mv3lavun01prp = new Gson().toJson(backupStatusFacade.findByDateStorage("mv3lavun01prp.smrc.sidra.org"));
        mv2lavun01prp = new Gson().toJson(backupStatusFacade.findByDateStorage("mv2lavun01prp.smrc.sidra.org"));
        aggregated_mv3lavun01prp = new Gson().toJson(backupStatusFacade.findAggreByGroup("mv3lavun01prp.smrc.sidra.org"));
        aggregated_mv2lavun01prp = new Gson().toJson(backupStatusFacade.findAggreByGroup("mv2lavun01prp.smrc.sidra.org"));
        lastSevenDays = new Gson().toJson(backupStatusFacade.findLast7("mv3lavun01prp.smrc.sidra.org"));
        System.out.println("lastSevenDays: " + backupStatusFacade.findLast7("mv3lavun01prp.smrc.sidra.org"));
        System.out.println("mv3lavun01prp: " + backupStatusFacade.findByDateStorage("mv3lavun01prp.smrc.sidra.org"));
        System.out.println("mv2lavun01prp: " + backupStatusFacade.findByDateStorage("mv2lavun01prp.smrc.sidra.org"));
        FacesContext.getCurrentInstance().getExternalContext().redirect("mydashboard.jsp");
    }

    public String getLastSevenDays() {
        return lastSevenDays;
    }

    public String fetchMv3lavun01prp() {
        return mv3lavun01prp;
    }

    public String fetchAggrMv3lavun01prp() {
        return aggregated_mv3lavun01prp;
    }
    
    public String fetchAggrMv2lavun01prp() {
        return aggregated_mv2lavun01prp;
    }

    public String fetchMv2lavun01prp() {
        return mv2lavun01prp;
    }
}
