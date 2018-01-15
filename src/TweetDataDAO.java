import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import twitter4j.Status;

public class TweetDataDAO {

	/**
	 * 別れたツイートをDBに保存
	 * @param tweet
	 * @throws Exception
	 */
	public static void insertTwitter(Status tweet) throws Exception {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();
			st = con.prepareStatement("INSERT INTO break_tweet_t(tweet_id,user_id,break_tweet_text,break_tweet_create_date) VALUES(?,?,?,?)");

			st.setLong(1, tweet.getId());
			st.setLong(2, tweet.getUser().getId());
			st.setString(3, tweet.getText());
			st.setTimestamp(4, new Timestamp(tweet.getCreatedAt().getTime()));

			st.executeUpdate();
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	/**
	 * tweet_idで重複チェック
	 * @param tweetId
	 * @return
	 * @throws Exception
	 */
	public static Boolean isRegistered(Long tweetId) throws Exception {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();
			String sql = "SELECT tweet_id FROM break_tweet_t WHERE tweet_id = ?";
			st = con.prepareStatement(sql);
			st.setLong(1, tweetId);
			ResultSet rs = st.executeQuery();
			return rs.next() ? true : false;
		} catch (SQLException e) {
			throw new SQLException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}
}
