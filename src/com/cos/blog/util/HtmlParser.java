package com.cos.blog.util;

import org.jsoup.Jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParser {
	public static String getContentYoutube(String content) {

		
		Document doc = Jsoup.parse(content);
		Elements aTags = doc.select("a");
		//https://www.youtube.com/watch?v=GAy3my6Yroc 	
		
		// elements랑 element 차이?
		
		for(Element aTag : aTags) {
				String href = aTag.attr("href" );
				String youtubeId = null;
				if(href.contains("http://www.youtube.com")) {
					String[] hrefArr = href.split("/");
					youtubeId = hrefArr[3];
					
				} 
				System.out.println(youtubeId);
				System.out.println("JSOUP 파싱 : YOUTUBE: " + youtubeId);
				String video = "<br/><iframe src='http://www.youtube.com/embed/"+youtubeId+"' width='400px' height='400px' frameborder='0' allowfullscreen></iframe>";
				System.out.println("video : "+video);
				aTag.after(video);
		}
		return doc.toString();
		
	}
		
		
		
		
		
		
		
		
		
		public static String getContentPreview(String content) {

			Document doc = Jsoup.parse(content);
		
		
		
		Elements pTag = doc.select("p");
		
			String text = pTag.text();
			if(text.length() > 0) {
				if(text.length() < 11) {
					return pTag.text();
				}else {
					return pTag.text().substring(0, 10)+"...";
				}	
			}
		
		return "내용 없음...";
		}
}


