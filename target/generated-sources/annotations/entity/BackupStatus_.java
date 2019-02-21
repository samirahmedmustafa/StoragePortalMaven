package entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-02-21T16:37:05")
@StaticMetamodel(BackupStatus.class)
public class BackupStatus_ { 

    public static volatile SingularAttribute<BackupStatus, String> server;
    public static volatile SingularAttribute<BackupStatus, Date> mydate;
    public static volatile SingularAttribute<BackupStatus, Double> sizeinGB;
    public static volatile SingularAttribute<BackupStatus, String> domains;
    public static volatile SingularAttribute<BackupStatus, String> groups;
    public static volatile SingularAttribute<BackupStatus, Integer> id;
    public static volatile SingularAttribute<BackupStatus, Integer> completed;
    public static volatile SingularAttribute<BackupStatus, Integer> failed;
    public static volatile SingularAttribute<BackupStatus, Integer> succeeded;

}