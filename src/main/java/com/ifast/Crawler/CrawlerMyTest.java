package com.ifast.Crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CrawlerMyTest {

    private static String url = "http://blog.csdn.net";
    private static String blogName = "zhangcc233";

    public static void main(String[] args) {
        getArticleListFromUrl(url + "/" + blogName);
    }

    public static void getArticleListFromUrl(String listUrl) {
        Document doc = null;
        try {
            doc = Jsoup.connect(listUrl).userAgent("Mozilla/5.0").timeout(3000).post();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements elements = doc.getElementsByTag("a");
        for (Element element : elements) {
            String relHref = element.attr("href");
            System.out.println("----href-----"+relHref);
            String linkHref = element.text();
            if (!relHref.startsWith("http://") && relHref.contains("details")) {
                {
                    StringBuffer sb = new StringBuffer();
                    sb.append(relHref);
                    System.out.println("comment 链接data：" + sb.substring(0, sb.length() - 9));//去掉最后的#comment输出
                    getArticleFromUrl(sb.toString());//可以通过这个url获取文章了
                }
//			System.out.println(linkHref);
                if (linkHref.equals("下一页"))//如果有下一页
                {
                    getArticleListFromUrl(url + relHref);//获取下一页的列表
                }
            }
        }
    }

    /**
     * 获取文章内容
     *
     * @param detailurl
     */
    public static void getArticleFromUrl(String detailurl) {
        try {
            Document document = Jsoup.connect(detailurl).userAgent("Mozilla/5.0").timeout(3000).post();
            //Element elementTitle = document.getElementsByClass("link_title").first();//标题。 这边根据class的内容来过滤
            Element elementTitle = document.getElementsByClass("title-article").first();//标题。 这边根据class的内容来过滤
            System.out.println(elementTitle.text());
            String filename = elementTitle.text().replaceAll("/", "或");
            Element elementContent = document.getElementsByClass("article_content").first();//内容。
            saveArticle(filename, elementContent.text(), blogName);
            // String Content =elementContent.te  xt().replaceAll(" ", "\t");
            // System.out.println(elementContent.text()+"\n");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 保存文章到本地
     *
     * @param titile
     * @param content
     * @param blogName
     */
    public static void saveArticle(String titile, String content, String blogName) {
        String lujing = "d:\\MyLoadArticle\\" + blogName + "\\" + titile + ".txt";//保存到本地的路径和文件名
        File file = new File(lujing);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.flush();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
