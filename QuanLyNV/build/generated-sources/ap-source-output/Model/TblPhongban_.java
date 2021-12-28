package Model;

import Model.TblNhanvien;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-08T15:44:45")
@StaticMetamodel(TblPhongban.class)
public class TblPhongban_ { 

    public static volatile SingularAttribute<TblPhongban, Long> maPB;
    public static volatile SingularAttribute<TblPhongban, String> truongPhong;
    public static volatile SingularAttribute<TblPhongban, String> tenPB;
    public static volatile CollectionAttribute<TblPhongban, TblNhanvien> tblNhanvienCollection;

}