import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class Main {

    public Main() {
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        String myFilm = "https://chieuphimquocgia.com.vn/vet-co-phieu-luu-ky-(2d-lt)---p-chi-tiet-film-7403";
        String myRoom = "https://chieuphimquocgia.com.vn/PlanScreenings/BookTicket?pId=144494";
        String linkRap = "https://chieuphimquocgia.com.vn";
        main.processPage(myFilm,myRoom,linkRap);

    }

    private void processPage(String urlFilm,String urlRoom,String linkRap) throws IOException {
        Document docFilm = Jsoup.connect(urlFilm).get();
        Elements elements = docFilm.getElementsByClass("wrapper");
        Element element = elements.get(2);
        Element content_film = element.getElementsByClass("content_movie").get(0);

        Document docRoom = Jsoup.connect(urlRoom).get();

        String idRap;
        String tenRap;
        String urlRap ;
        String tenPhim = content_film.select("a[href]").get(0).text().toString();
        String tenPhimTiengAnh;
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
        String ngonNgu;
        String quocGia = content_film.select("p").get(4).text().replace("Xuất xứ:","");
        String theLoai = content_film.select("p").get(0).text().replace("Loại phim:","");
        String trailer = content_film.select("p").get(7).getElementsByClass("trainer").attr("href");
        String noiDung = content_film.select("p").get(6).text().replace("Xuất xứ:","");
        String idKhungGioClick;
        String phongChieu;
        String urlChiTietPhim = "";
        String list_trailer;
        int idLocation;
        String tinhThanh;
        String tenPhimChuanVN;

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
