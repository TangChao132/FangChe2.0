package com.fangche.service2;


import org.joda.time.Days;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAmount;
import java.util.UUID;

@SpringBootTest
class Service2ApplicationTests {

	@Test
	void contextLoads() {

//		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
//		DateTime dateTime=new DateTime();//当前时间
//
//
//		String time = formatter.print(dateTime);
//		System.out.println(time);
//
//		DateTime minus = dateTime.minus(Days.days(3));
//		String print = formatter.print(minus);
//		System.out.println(print);
    /*	String timeString1 = "2024-06-20 16:24:24";
		String timeString2 = "2024-06-21 16:24:24";
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

		// 解析字符串为DateTime对象
		DateTime dateTime1 = formatter.parseDateTime(timeString1);
		DateTime dateTime2 = formatter.parseDateTime(timeString2);*/

		// 创建一个 LocalDateTime 对象，默认为当前时间
//		LocalDateTime dateTime = LocalDateTime.now();
//
//		System.out.println(dateTime);
//
//
//		LocalDateTime minus = dateTime.minusDays(3);
//		System.out.println(minus);

		// 创建一个 DateTimeFormatter 对象，指定格式为 "yyyy-MM-dd HH:mm:ss"
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		// 使用 formatter 将 LocalDateTime 转换为字符串
//		String formattedDateTime = dateTime.format(formatter);

		// 输出格式化后的字符串
//		System.out.println(formattedDateTime);
		for (int i =0;i<10;i++){
			String string = UUID.randomUUID().toString();
			System.out.println(string);
		}

	}

}
