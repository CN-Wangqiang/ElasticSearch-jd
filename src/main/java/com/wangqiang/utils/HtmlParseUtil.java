package com.wangqiang.utils;

import com.wangqiang.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @version : V1.0
 * @ClassName: HtmlParseUtil
 * @Description: TODO
 * @Auther: wangqiang
 * @Date: 2020/4/22 16:26
 */
@Component
public class HtmlParseUtil {
    public static void main(String[] args) throws Exception {

        new HtmlParseUtil().parseJD("编程").forEach(System.out::println);

    }

    public List<Content> parseJD(String keyWords)throws Exception{
        //获取请求
        String url = "https://search.jd.com/Search?keyword=" + keyWords +"&enc=utf-8";
        //解析网页  返回Dom对象
        Document document = Jsoup.parse(new URL(url), 30000);
        Element element = document.getElementById("J_goodsList");
        Elements elements = element.getElementsByTag("li");

//        System.out.println(elements);

        ArrayList<Content> goodList = new ArrayList<>();
        for (Element el : elements) {
            String img = el.getElementsByTag("img").eq(0).attr("source-data-lazy-img");
//            String price = el.getElementsByClass("p-price").eq(0).text(); //bug 同时会取出两个价格 正常价格和会员价格
            String price = el.select("div.p-price > strong").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();
            String shop = el.getElementsByClass("p-shop").eq(0).text();
            if (shop.isEmpty()){  //bug 有些商品的店铺标签不为p-shop
                shop = el.getElementsByClass("p-shopnum").eq(0).text();

            }
            Content content = new Content();
            content.setTitle(title);
            content.setPrice(price);
            content.setImg(img);
            content.setShop(shop);
            goodList.add(content);


        }
        return goodList;
    }
}
