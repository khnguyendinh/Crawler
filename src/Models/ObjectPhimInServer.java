package Models;

/**
 * Created by KHOAND_ADMIN on 6/18/2017.
 */
public class ObjectPhimInServer {
    public String idRap;
    public String tenRap;
    public String urlRap;
    public String tenPhim;
    public String tenPhimTiengAnh;
    public String idPhim;
    public String urlRutGon;
    public String ngayChieu;
    public String idKhungGio;
    public String khungGio;


    public String ngayKhoiChieu;
    public String anhDaiDien;
    public String thoiLuong;
    public String daoDien;
    public String dienVien;
    public String ngonNgu;
    public String quocGia;
    public String theLoai;
    public String trailer;
    public String noiDung;
    public String idKhungGioClick;
    public String phongChieu;


    public String urlChiTietPhim = "";
    public String list_trailer;
    public int idLocation;
    public String tinhThanh;
    public String tenPhimChuanVN;

    public void showInfor(){
        System.out.println("Infor Film ");
        System.out.println("==================================================================");
        System.out.println(tenPhim);
        System.out.println(idPhim);
        System.out.println("urlRutGon "+urlRutGon);
        System.out.println(ngayChieu);
        System.out.println("idKhungGio "+idKhungGio);
        System.out.println("khungGio "+khungGio);
        System.out.println(ngayKhoiChieu);
        System.out.println(anhDaiDien);
        System.out.println(thoiLuong);
        System.out.println(daoDien);
        System.out.println(dienVien);
        System.out.println(quocGia);
        System.out.println(theLoai);
        System.out.println(trailer);
        System.out.println(noiDung);
    }

}
