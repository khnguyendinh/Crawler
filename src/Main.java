import Models.ItemListFilm;
import Models.ObjectPhimInServer;
import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    String linkDatVe = "https://chieuphimquocgia.com.vn/PlanScreenings/BookTicket?pId=";
    String linkRap = "http://www.chieuphimquocgia.com.vn/";

    public Main() {
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        long startTime = System.currentTimeMillis();
        String lich_chieu = "https://chieuphimquocgia.com.vn/showtimes-lich-chieu-phim";
        String myFilm = "https://chieuphimquocgia.com.vn/vet-co-phieu-luu-ky-(2d-lt)---p-chi-tiet-film-7403";
        String myRoom = "https://chieuphimquocgia.com.vn/PlanScreenings/BookTicket?pId=144494";


        List<ItemListFilm> listFilms = new ArrayList<>();
        List<ObjectPhimInServer> objectPhimInServerList = new ArrayList<>();
        listFilms = main.getListFilm(lich_chieu);

        objectPhimInServerList = main.processPage(listFilms);
//        System.out.println("listFilms Count "+listFilms.size());
//        insertDB();
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("total TIme " +totalTime);
    }

    private List<ObjectPhimInServer> processPage(List<ItemListFilm> listFilms) throws IOException {
        List<ObjectPhimInServer> result = new ArrayList<>();
        for (ItemListFilm item : listFilms) {
            Document docFilm = null;
            try {
                docFilm = Jsoup.connect(item.urlChiTietVN).get();
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("Error "+item.urlChiTietVN);
            }
            if(docFilm.getElementsByClass("txt_error").size() > 0){
                continue;
            }
            Elements elements = docFilm.getElementsByClass("wrapper");
            Element element = elements.get(2);
            Element content_film = element.getElementsByClass("content_movie").get(0);


            String idRap = "TTCPQG_NCC";
            String tenRap = "Trung tâm chiếu phim quốc gia";
            String urlRap = linkRap;
//              String tenPhim = content_film.select("a[href]").get(0).text().toString();
            String tenPhim = item.tenPhimVn;
            String tenPhimTiengAnh = tenPhim;
            String idPhim = "";
            Elements allTagP = content_film.select("p");
            if (allTagP.size() > 8) {
//                    System.out.println("Main.processPage " +content_film.select("p").get(8));
                if (allTagP.select("a[href]").size() > 0) {
//                        System.out.println("Main.processPage " +content_film.select("p").get(7).select("a[href]").get(0).attr("href"));
                    idPhim = allTagP.get(8).select("a[href]").get(0).attr("href").replace("/PlanScreenings/Sessiontimes?filmId=", "");
                }
            }
//                String idPhim = content_film.select("p").get(7).select("a[href]").get(0).attr("href").replace("/PlanScreenings/Sessiontimes?filmId=", "");
            String urlRutGon = "";
            try {
                urlRutGon = linkRap + docFilm.getElementsByClass("screen_showtime").select("a[href]").get(0).attr("href");
            }catch (Exception e){
                System.out.println("Error "+item.tenPhimVn);
                System.out.println("Error2 "+item.urlChiTietVN);
            }
            String ngayChieu = "";
            if(docFilm.getElementsByClass("showtime").size() > 0){
                ngayChieu = docFilm.getElementsByClass("showtime").text();
                ngayChieu = ngayChieu.substring(ngayChieu.indexOf("-") + 1, ngayChieu.indexOf("-") + 11);
            }

            String ngayKhoiChieu = allTagP.get(5).text().replace("Khởi chiếu :", "");
            String anhDaiDien = content_film.select("img[src$=.jpg]").get(0).absUrl("src").toString();
            String thoiLuong = allTagP.get(1).text().replace("Thời lượng: ", "");
            String daoDien = allTagP.get(3).text().replace("Đạo diễn:", "");
            String dienVien = allTagP.get(2).text().replace("Diễn viên: ", "");
            String ngonNgu = "VN";
            String quocGia = allTagP.get(4).text().replace("Xuất xứ:", "");
            String theLoai = allTagP.get(0).text().replace("Loại phim:", "");
            String trailer = allTagP.get(7).getElementsByClass("trainer").attr("href");
            String noiDung = allTagP.get(6).text().replace("Xuất xứ:", "");
            String idKhungGioClick = "idClick";
            String phongChieu = "phongCHieu";
            String urlChiTietPhim = "";
            String list_trailer = trailer;
            int idLocation = 0;
            String tinhThanh = "Hà Nội";
            String tenPhimChuanVN = tenPhim;


            ObjectPhimInServer objectPhimInServer = new ObjectPhimInServer();
            objectPhimInServer.idRap = idRap;
            objectPhimInServer.tenRap = tenRap;
            objectPhimInServer.urlRap = urlRap;
            objectPhimInServer.tenPhim = tenPhim;
            objectPhimInServer.tenPhimTiengAnh = tenPhimTiengAnh;
            objectPhimInServer.idPhim = idPhim;
            objectPhimInServer.urlRutGon = urlRutGon;
            objectPhimInServer.ngayChieu = ngayChieu;



            objectPhimInServer.ngayKhoiChieu = ngayKhoiChieu;
            objectPhimInServer.anhDaiDien = anhDaiDien;
            objectPhimInServer.thoiLuong = thoiLuong;
            objectPhimInServer.daoDien = daoDien;
            objectPhimInServer.dienVien = dienVien;
            objectPhimInServer.ngonNgu = ngonNgu;
            objectPhimInServer.quocGia = quocGia;
            objectPhimInServer.theLoai = theLoai;
            objectPhimInServer.trailer = trailer;
            objectPhimInServer.noiDung = noiDung;
            objectPhimInServer.idKhungGioClick = idKhungGioClick;
            objectPhimInServer.phongChieu = phongChieu;


            objectPhimInServer.urlChiTietPhim = urlChiTietPhim;
            objectPhimInServer.list_trailer = list_trailer;
            objectPhimInServer.idLocation = idLocation;
            objectPhimInServer.tinhThanh = tinhThanh;
            objectPhimInServer.tenPhimChuanVN = tenPhimChuanVN;
            for (int i = 0; i < item.iD_show_times.size(); i++) {
//                Document docRoom = Jsoup.connect(linkDatVe + item.iD_show_times.get(i)).get();
//              String idKhungGio = docFilm.getElementsByClass("screen_showtime").select("a[href]").get(0).attr("href").replace("/PlanScreenings/BookTicket?pId=", "");

                String idKhungGio = item.iD_show_times.get(i);
//                String khungGio = docFilm.getElementsByClass("screen_showtime").select("a[href]").get(0).text();
                String khungGio = item.show_times.get(i);
                objectPhimInServer.idKhungGio = idKhungGio;
                objectPhimInServer.khungGio = khungGio;
                result.add(objectPhimInServer);
//                objectPhimInServer.showInfor();
                System.out.println("Infor "+new Gson().toJson(objectPhimInServer));
            }
        }
        return result;

    }

    private List<ItemListFilm> getListFilm(String listFilm) throws IOException {
//        Document docListFilm = Jsoup.connect(listFilm).get();
        File input = new File("assets/cinema.html");
        Document docListFilm = Jsoup.parse(input, "UTF-8", listFilm);
        Elements show_times = docListFilm.getElementsByClass("showtime");

        //danh sách phim today
        Element show_time_0 = show_times.get(0);
        //danh sách phim tomorow
        Element show_time_1 = show_times.get(1);

        List<ItemListFilm> listFilmAllDay = new ArrayList<>();
        //lấy danh sách phim ngày hôm nay
        List<ItemListFilm> listFilmToDay = new ArrayList<>();
        listFilmToDay = getListFilmOneDay(show_time_0);
        if (listFilmToDay != null && listFilmToDay.size() > 0) {
            listFilmAllDay.addAll(listFilmToDay);
//            System.out.println(new Gson().toJson(listFilmToDay));
        }
        if (listFilmAllDay.size() > 0) {
            return listFilmAllDay;
        }
        return null;
    }

    private List<ItemListFilm> getListFilmOneDay(Element show_time) {
        List<ItemListFilm> result = new ArrayList<>();
        Elements movie_showtimes = show_time.getElementsByClass("movie_showtime");
        Elements timeInfors = show_time.getElementsByClass("screen_showtime");
//        String name1 = itemFilms.att.get(0).text();
        for (int i = 0; i < movie_showtimes.size(); i++) {
            String tenPhimVn = "";
            String tenPhimEn = "";
            String urlChiTietVN = "";
            String urlChiTietEN = "";
            List<String> show_times = new ArrayList<>();
            List<String> iD_show_times = new ArrayList<>();
            //get tenPhim Viet nam
            tenPhimVn = getTenPhimVnFromLichChieu(movie_showtimes.get(i));
            //get tenPhim English
            tenPhimEn = getTenPhimEnFromLichChieu(movie_showtimes.get(i));
            //get urlChiTiet Viet Nam
            urlChiTietVN = getUrlChiTietVnFromLichChieu(movie_showtimes.get(i));
            //get urlChiTiet English
            urlChiTietEN = getUrlChiTietEnFromLichChieu(movie_showtimes.get(i));
            //get show_times
            show_times = getShow_timesFromLichChieu(timeInfors.get(i));
            //get ID khung giờ
            iD_show_times = getID_Show_timesFromLichChieu(timeInfors.get(i));
//            System.out.println("========================================");
            ItemListFilm itemListFilm = new ItemListFilm();
            itemListFilm.tenPhimVn = tenPhimVn;
            itemListFilm.tenPhimEn = tenPhimEn;
            itemListFilm.urlChiTietVN = urlChiTietVN;
            itemListFilm.urlChiTietEN = urlChiTietEN;
            itemListFilm.show_times = show_times;
            itemListFilm.iD_show_times = iD_show_times;
            result.add(itemListFilm);
        }
        if (result.size() > 0) {
            return result;
        }
        return null;
    }

    private List<String> getID_Show_timesFromLichChieu(Element element) {
        List<String> id_Show_time = new ArrayList<>();
        for (Element item : element.getElementsByTag("a")) {
            String id = item.attr("href");
            id = id.replace("https://chieuphimquocgia.com.vn/PlanScreenings/BookTicket?pId=", "");
            id_Show_time.add(id);
        }
//        System.out.println("getID_Show_timesFromLichChieu " + id_Show_time);
        return id_Show_time;
    }

    private List<String> getShow_timesFromLichChieu(Element element) {
        List<String> show_times = new ArrayList<>();
        for (Element item : element.getElementsByTag("a")) {
            String show_time = item.text();
            show_times.add(show_time);
        }
//        System.out.println("getShow_timesFromLichChieu " + show_times);
        return show_times;
    }

    private String getUrlChiTietVnFromLichChieu(Element element) {
        String urlChiTietVN = "";
        if (element.getElementsByTag("td").size() > 3) {
            if (element.getElementsByTag("td").get(3).getElementsByTag("a").size() > 0) {
                urlChiTietVN = element.getElementsByTag("td").get(3).getElementsByTag("a").get(0).attr("href");
            }
        } else if (element.getElementsByTag("td").size() == 3) {
            if (element.getElementsByTag("td").get(2).getElementsByTag("a").size() > 0) {
                urlChiTietVN = element.getElementsByTag("td").get(2).getElementsByTag("a").get(0).attr("href");
            }
        }
//        System.out.println("urlChiTietVN " + urlChiTietVN);
        return urlChiTietVN;
    }

    private String getUrlChiTietEnFromLichChieu(Element element) {
        String urlChiTietEN = "";
        if (element.getElementsByTag("td").size() > 3) {
            if (element.getElementsByTag("td").get(3).getElementsByTag("a").size() > 1) {
                urlChiTietEN = element.getElementsByTag("td").get(3).getElementsByTag("a").get(1).attr("href");
            }
        } else if (element.getElementsByTag("td").size() == 3) {
            if (element.getElementsByTag("td").get(2).getElementsByTag("a").size() > 1) {
                urlChiTietEN = element.getElementsByTag("td").get(2).getElementsByTag("a").get(1).attr("href");
            }
        }
//        System.out.println("urlChiTietEN " + urlChiTietEN);
        return urlChiTietEN;
    }

    private String getTenPhimVnFromLichChieu(Element element) {
        String tenPhimVn = "";
        if (element.getElementsByTag("td").size() > 3) {
            if (element.getElementsByTag("td").get(3).getElementsByTag("a").size() > 0) {
                tenPhimVn = element.getElementsByTag("td").get(3).getElementsByTag("a").get(0).text();
            }
        } else if (element.getElementsByTag("td").size() == 3) {
            if (element.getElementsByTag("td").get(2).getElementsByTag("a").size() > 0) {
                tenPhimVn = element.getElementsByTag("td").get(2).getElementsByTag("a").get(0).text();
            }
        }
//        System.out.println("TenPhimVN " + tenPhimVn);
        return tenPhimVn;
    }

    private String getTenPhimEnFromLichChieu(Element element) {
        String tenPhimEn = "";
        if (element.getElementsByTag("td").size() > 3) {
            if (element.getElementsByTag("td").get(3).getElementsByTag("a").size() > 1) {
                tenPhimEn = element.getElementsByTag("td").get(3).getElementsByTag("a").get(1).text();
            }
        } else if (element.getElementsByTag("td").size() == 3) {
            if (element.getElementsByTag("td").get(2).getElementsByTag("a").size() > 1) {
                tenPhimEn = element.getElementsByTag("td").get(2).getElementsByTag("a").get(1).text();
            }
        }
//        System.out.println("TenPhimEN " + tenPhimEn);
        return tenPhimEn;
    }

    private void insertDB() {
    }

}
