package Model;

import Model.TblChamcong;
import Model.TblLuong;
import Model.TblTaikhoan;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-12-08T15:44:45")
@StaticMetamodel(TblNhanvien.class)
public class TblNhanvien_ { 

    public static volatile SingularAttribute<TblNhanvien, Integer> sdt;
    public static volatile SingularAttribute<TblNhanvien, String> tenCV;
    public static volatile SingularAttribute<TblNhanvien, Date> ngayVaoLam;
    public static volatile CollectionAttribute<TblNhanvien, TblLuong> tblLuongCollection;
    public static volatile CollectionAttribute<TblNhanvien, TblChamcong> tblChamcongCollection;
    public static volatile SingularAttribute<TblNhanvien, String> gioiTinh;
    public static volatile SingularAttribute<TblNhanvien, Long> maNV;
    public static volatile CollectionAttribute<TblNhanvien, TblTaikhoan> tblTaikhoanCollection;
    public static volatile SingularAttribute<TblNhanvien, String> diaChi;
    public static volatile SingularAttribute<TblNhanvien, String> hinhAnh;
    public static volatile SingularAttribute<TblNhanvien, Long> maCV;
    public static volatile SingularAttribute<TblNhanvien, String> trangThai;
    public static volatile SingularAttribute<TblNhanvien, Long> maPB;
    public static volatile SingularAttribute<TblNhanvien, Date> ngaySinh;
    public static volatile SingularAttribute<TblNhanvien, String> hoTen;
    public static volatile SingularAttribute<TblNhanvien, String> tenPB;

}