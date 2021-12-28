package Model;

import Model.TblNhanvien;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-08T15:44:45")
@StaticMetamodel(TblChucvu.class)
public class TblChucvu_ { 

    public static volatile SingularAttribute<TblChucvu, String> tenCV;
    public static volatile SingularAttribute<TblChucvu, Long> maCV;
    public static volatile SingularAttribute<TblChucvu, Float> heSo;
    public static volatile CollectionAttribute<TblChucvu, TblNhanvien> tblNhanvienCollection;

}