import java.sql.SQLException;

import twitter4j.Status;
import twitter4j.User;

public class Main {
	public static void main(String[] args) {

		try {
			// 検索結果を見てみる
			for (Status tweet : MainLogic.getTwewtList("別れました -セフレ exclude:replies exclude:retweets")) {
				User user = tweet.getUser();

				try {

				//未登録なら登録
				if (!UserDataDAO.isRegistered(user.getId())) {
					UserDataDAO.insertUser(user);
				}

				if (!TweetDataDAO.isRegistered(tweet.getId())) {
					TweetDataDAO.insertTwitter(tweet);
				}
				}catch(SQLException se) {
					System.err.println("【警告】"+se.toString());

				}


			}
			System.out.println("バグなく終了");

		} catch (Exception e) {//エラーのキャッチを行う
			System.out.println("不明エラー" + e);
			e.printStackTrace();
		}

	}

}
