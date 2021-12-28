package Model;

import Model.TblChitietchamcong;
import Model.TblLuong;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-08T15:44:45")
@StaticMetamodel(TblChamcong.class)
public class TblChamcong_ { 

    public static volatile CollectionAttribute<TblChamcong, TblLuong> tblLuongCollection;
    public static volatile SingularAttribute<TblChamcong, Integer> thang;
    public static volatile SingularAttribute<TblChamcong, Long> maCC;
    public static volatile SingularAttribute<TblChamcong, String> tenNV;
    public static volatile SingularAttribute<TblChamcong, Integer> soGioLam;
    public static volatile SingularAttribute<TblChamcong, Integer> nam;
    public static volatile CollectionAttribute<TblChamcong, TblChitietchamcong> tblChitietchamcongCollection;
    public static volatile SingularAttribute<TblChamcong, Long> maNV;

}