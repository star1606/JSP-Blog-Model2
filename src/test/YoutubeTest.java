package test;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class YoutubeTest {

	@Test
	public void preview() {
		String content = "<a href=\"https://www.youtube.com/watch?v=GAy3my6Yroc</a>";
		//System.out.println(content);

		Document doc = Jsoup.parse(content);
		//System.out.println(doc.toString());
		Elements els = doc.select("a");
		Element el = els.get(0);
		//System.out.println(el.toString());
		String value = el.attr("href");
		System.out.println(value);

		String arr[] = value.split("/");
		for(String s : arr) {
			System.out.println(s);
		}
		System.out.println(doc);
	}
}	