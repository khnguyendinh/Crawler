package Models;

import java.util.List;

/**
 * Created by KHOAND_ADMIN on 6/19/2017.
 */
public class ItemListFilm {

    public String tenPhimVn;
    public String tenPhimEn;
    //link trỏ đến chi tiết phim Việt nam
    public String urlChiTietVN;
    //link trỏ đến chi tiết phim English
    public String urlChiTietEN;
    //thời gian chiếu
    public List<String> show_times;
    //ID thời gian chiếu
    public List<String> iD_show_times;
}
