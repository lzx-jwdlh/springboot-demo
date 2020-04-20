package com.alzz.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alzz.demo.repository.UserMapper;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	UserMapper userMapper;

	@Test
	void A(){
//        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
//        userQueryWrapper.l
//		List<User> users = userMapper.selectList(null);

//        System.out.println(users);
	}














	@Test
	void contextLoads() throws IOException {
		Connection.Response res = Jsoup.connect("https://www.wukong.com/wenda/web/nativefeed/brow/?concern_id=6300775428692904450&t=1533714730319&_signature=DKZ7mhAQV9JbkTPBachKdgyme4")
				.header("Accept", "*/*")
				.header("Accept-Encoding", "gzip, deflate")
				.header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
				.header("Content-Type", "application/json;charset=UTF-8")
				.header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
				.timeout(10000).ignoreContentType(true).execute();//.get();
		String body = res.body();
		System.out.println(body);
		JSONObject jsonObject2 = JSON.parseObject(body);
		JSONArray jsonArray = jsonObject2.getJSONArray("data");

		//JSONArray jsonArray1 = JSONArray.parseArray(JSON_ARRAY_STR);//因为JSONArray继承了JSON，所以这样也是可以的

		//遍历方式1
		int size = jsonArray.size();
		for (int i = 0; i < size; i++){
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			if(jsonObject.containsKey("question"))
			{
				JSONObject jsonObject3 = jsonObject.getJSONObject("question");
				String qid = jsonObject3.getString("qid");
				System.out.println(qid);
			}

		}
	}


}
