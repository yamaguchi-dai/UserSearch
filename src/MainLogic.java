import java.util.List;
import java.util.ResourceBundle;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class MainLogic {
	static final ResourceBundle TWITTER_KEY_RB = ResourceBundle.getBundle("twitter_key");
	static final String CON_KEY = TWITTER_KEY_RB.getString("CON_KEY");
	static final String CON_SEC = TWITTER_KEY_RB.getString("CON_SEC");
	static final String AC_TOK = TWITTER_KEY_RB.getString("AC_TOK");
	static final String AC_SEC = TWITTER_KEY_RB.getString("AC_SEC");

	private static Twitter getTwitter() {
		Twitter twitter = new TwitterFactory().getInstance();//ツイッターオブジェクトの作成
		twitter.setOAuthConsumer(CON_KEY, CON_SEC);//ツイッターオブジェクトにコンシューマーキーをセット
		AccessToken accessToken = new AccessToken(AC_TOK, AC_SEC);//ユーザーを定義
		twitter.setOAuthAccessToken(accessToken);//ツイッターオブジェクトにアクセストークンをセット
		return twitter;
	}

	public static List<Status> getTwewtList(String queryWord) throws TwitterException {
		Twitter twitter = getTwitter();
		Query query = new Query();

		// 検索ワードをセット
		query.setQuery(queryWord);

		// 1度のリクエストで取得するTweetの数（100が最大）
		query.setCount(10);

		// 検索実行
		QueryResult result = twitter.search(query);

		System.out.println("ヒット数 : " + result.getTweets().size());

		return result.getTweets();
	}

}
