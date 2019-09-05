package controller;

import com.google.gson.Gson;
import entity.BackupStatus;
import entity.StorageReport;
import facade.AggrBackupReportFacade;
import facade.BackupStatusFacade;
import facade.BackuptopFacade;
import facade.DataFacade;
import facade.StorageReportFacade;
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
    private BackuptopFacade backuptopFacade;

    @EJB
    private AggrBackupReportFacade aggrBackupReportFacade;
    @EJB
    private StorageReportFacade storageReportFacade;

    @Inject
    BackupStatus backupStatus;

    @EJB
    private DataFacade dataFacade;
    private String mv3lavun01prp;
    private String mv2lavun01prp;
    private String aggregated_mv3lavun01prp;
    private String aggregated_mv2lavun01prp;
    private String lastSevenDays;
    private String topTen;
    private String totalUsed_MV3;
    private String totalUsed_MV2;
    private String total_MV3;
    private String total_MV2;
    private List<StorageReport> top10VMAX;

    public BackupStatus getBackupStatus() {
        return backupStatus;
    }

    public String getTotal_MV3() {
        return total_MV3;
    }

    public String getTotal_MV2() {
        return total_MV2;
    }

    public String getTotalUsed_MV3() {
        return totalUsed_MV3;
    }

    public String getTotalUsed_MV2() {
        return totalUsed_MV2;
    }

    public BackupStatusFacade getBackupStatusFacade() {
        return backupStatusFacade;
    }

    public List<StorageReport> getTop10VMAX() {
        for(StorageReport s: top10VMAX) {
            System.out.println(s.getSite() + " " + s.getAllocated());
        }
        return top10VMAX;
    }

//    public String findAll() {
//        List<Data> dataList = dataFacade.findAll();
////        System.out.println("Data: " + dataList.get(0).getTotal());
////        System.out.println(dataList.get(0).toString());
//        return dataList.get(0).toString();
//    }
    public void toDashboard() throws IOException {
        mv3lavun01prp = new Gson().toJson(backupStatusFacade.findByDateStorage("mv3lavun01prp.smrc.sidra.org"));
        mv2lavun01prp = new Gson().toJson(backupStatusFacade.findByDateStorage("mv2lavun01prp.smrc.sidra.org"));
        aggregated_mv3lavun01prp = new Gson().toJson(backupStatusFacade.findAggreByGroup("mv3lavun01prp.smrc.sidra.org"));
        aggregated_mv2lavun01prp = new Gson().toJson(backupStatusFacade.findAggreByGroup("mv2lavun01prp.smrc.sidra.org"));
        lastSevenDays = new Gson().toJson(backupStatusFacade.findLast7("mv3lavun01prp.smrc.sidra.org"));
        totalUsed_MV3 = new Gson().toJson(backupStatusFacade.findTotalUsed("MV3-VMAX"));
        totalUsed_MV2 = new Gson().toJson(backupStatusFacade.findTotalUsed("MV2-VMAX"));
        total_MV3 = new Gson().toJson(backupStatusFacade.findTotal("MV3-VMAX"));
        total_MV2 = new Gson().toJson(backupStatusFacade.findTotal("MV2-VMAX"));
        topTen = new Gson().toJson(backuptopFacade.findTopTen());
//        top10VMAX = new Gson().toJson(backupStatusFacade.findTopTenVMAX("MV3-VMAX"));
        top10VMAX = storageReportFacade.findTopTenVMAX("MV3-VMAX");
        System.out.println(top10VMAX);
//        for (Object[] o : backuptopFacade.findTopTen()) {
//            System.out.println("findTopTen: " + o[1]);
//        }
//        System.out.println("lastSevenDays: " + backupStatusFacade.findLast7("mv3lavun01prp.smrc.sidra.org"));
//        System.out.println("mv3lavun01prp: " + backupStatusFacade.findByDateStorage("mv3lavun01prp.smrc.sidra.org"));
//        System.out.println("mv2lavun01prp: " + backupStatusFacade.findByDateStorage("mv2lavun01prp.smrc.sidra.org"));
        FacesContext.getCurrentInstance().getExternalContext().redirect("mydashboard.jsp");
    }

    public String getLastSevenDays() {
        return lastSevenDays;
    }

    public String fetchTopTen() {
        return topTen;
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
