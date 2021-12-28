package Model;

import Model.TblLuong;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-08T15:44:45")
@StaticMetamodel(TblKyluong.class)
public class TblKyluong_ { 

    public static volatile CollectionAttribute<TblKyluong, TblLuong> tblLuongCollection;
    public static volatile SingularAttribute<TblKyluong, Integer> thang;
    public static volatile SingularAttribute<TblKyluong, Long> maKL;
    public static volatile SingularAttribute<TblKyluong, Float> tienLuong;

}