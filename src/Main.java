import Models.ItemListFilm;
import Models.ObjectPhimInServer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public Main() {
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        String listFilm = "https://chieuphimquocgia.com.vn/showtimes-lich-chieu-phim";
        String myFilm = "https://chieuphimquocgia.com.vn/vet-co-phieu-luu-ky-(2d-lt)---p-chi-tiet-film-7403";
        String myRoom = "https://chieuphimquocgia.com.vn/PlanScreenings/BookTicket?pId=144494";
        String linkRap = "https://chieuphimquocgia.com.vn";
//        main.processPage(myFilm,myRoom,linkRap);
        main.getListFilm(listFilm);
//        insertDB();
    }

    private void getListFilm(String listFilm) throws IOException {
//        Document docListFilm = Jsoup.connect(listFilm).get();
        File input = new File("assets/Trung Tâm Chiếu Phim Quốc Gia.html");
        Document docListFilm =  Jsoup.parse(input, "UTF-8", listFilm);
        Elements show_times = docListFilm.getElementsByClass("showtime");

        //danh sách phim today
        Element show_time_0 = show_times.get(0);
        //danh sách phim tomorow
        Element show_time_1 = show_times.get(1);

        //lấy danh sách phim ngày hôm nay
        List<ItemListFilm> listFilmToDay = new ArrayList<>();
        listFilmToDay = getListFilmOneDay(show_time_0);
    }

    private List<ItemListFilm> getListFilmOneDay(Element show_time_0) {
        List<ItemListFilm> result = new ArrayList<>();

        return null;
    }

    private void insertDB() {
    }

    private void processPage(String urlFilm,String urlRoom,String linkRap) throws IOException {
        Document docFilm = Jsoup.connect(urlFilm).get();
        Elements elements = docFilm.getElementsByClass("wrapper");
        Element element = elements.get(2);
        Element content_film = element.getElementsByClass("content_movie").get(0);

        Document docRoom = Jsoup.connect(urlRoom).get();

        String idRap = "1";
        String tenRap = "Quốc gia";
        String urlRap = linkRap;
        String tenPhim = content_film.select("a[href]").get(0).text().toString();
        String tenPhimTiengAnh = tenPhim;
        String idPhim = content_film.select("p").get(7).select("a[href]").get(0).attr("href").replace("/PlanScreenings/Sessiontimes?filmId=","");
        String urlRutGon = linkRap + docFilm.getElementsByClass("screen_showtime").select("a[href]").get(0).attr("href");
        String ngayChieu = docFilm.getElementsByClass("showtime").text();
        ngayChieu = ngayChieu.substring(ngayChieu.indexOf("-")+1,ngayChieu.indexOf("-")+11);
        String idKhungGio = docFilm.getElementsByClass("screen_showtime").select("a[href]").get(0).attr("href").replace("/PlanScreenings/BookTicket?pId=","");
        String khungGio = docFilm.getElementsByClass("screen_showtime").select("a[href]").get(0).text();
        String ngayKhoiChieu = content_film.select("p").get(5).text().replace("Khởi chiếu :","");
        String anhDaiDien = content_film.select("img[src$=.jpg]").get(0).absUrl("src").toString();
        String thoiLuong = content_film.select("p").get(1).text().replace("Thời lượng: ","");
        String daoDien = content_film.select("p").get(3).text().replace("Đạo diễn:","");
        String dienVien = content_film.select("p").get(2).text().replace("Diễn viên: ","");
        String ngonNgu = "VN";
        String quocGia = content_film.select("p").get(4).text().replace("Xuất xứ:","");
        String theLoai = content_film.select("p").get(0).text().replace("Loại phim:","");
        String trailer = content_film.select("p").get(7).getElementsByClass("trainer").attr("href");
        String noiDung = content_film.select("p").get(6).text().replace("Xuất xứ:","");
        String idKhungGioClick = "idClick";
        String phongChieu = "phongCHieu";
        String urlChiTietPhim = "";
        String list_trailer= trailer;
        int idLocation = 0;
        String tinhThanh = "Ha noi";
        String tenPhimChuanVN = tenPhim;



        ObjectPhimInServer objectPhimInServer = new ObjectPhimInServer();
        objectPhimInServer.idRap = idRap;
        objectPhimInServer.tenRap = tenRap;
        objectPhimInServer.urlRap = urlRap;
        objectPhimInServer.tenPhim = tenPhim;
        objectPhimInServer.tenPhimTiengAnh= tenPhimTiengAnh;
        objectPhimInServer.idPhim= idPhim;
        objectPhimInServer.urlRutGon= urlRutGon;
        objectPhimInServer.ngayChieu= ngayChieu;
        objectPhimInServer.idKhungGio= idKhungGio;
        objectPhimInServer.khungGio= khungGio;


        objectPhimInServer.ngayKhoiChieu= ngayKhoiChieu;
        objectPhimInServer.anhDaiDien= anhDaiDien;
        objectPhimInServer.thoiLuong= thoiLuong;
        objectPhimInServer.daoDien= daoDien;
        objectPhimInServer.dienVien= dienVien;
        objectPhimInServer.ngonNgu= ngonNgu;
        objectPhimInServer.quocGia= quocGia;
        objectPhimInServer.theLoai= theLoai;
        objectPhimInServer.trailer= trailer;
        objectPhimInServer.noiDung= noiDung;
        objectPhimInServer.idKhungGioClick= idKhungGioClick;
        objectPhimInServer.phongChieu= phongChieu;


        objectPhimInServer.urlChiTietPhim = urlChiTietPhim;
        objectPhimInServer.list_trailer= list_trailer;
        objectPhimInServer.idLocation= idLocation;
        objectPhimInServer.tinhThanh= tinhThanh;
        objectPhimInServer.tenPhimChuanVN= tenPhimChuanVN;

       objectPhimInServer.showInfor();

    }
}
