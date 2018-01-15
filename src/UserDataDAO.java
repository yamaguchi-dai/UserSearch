import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import twitter4j.User;

public class UserDataDAO {

	/**
	 * ユーザー情報をDBに保存
	 * @param user
	 * @throws Exception
	 */
	public static void insertUser(User user) throws Exception {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();
			st = con.prepareStatement("INSERT INTO user_t(user_id,name,screen_name) VALUES(?,?,?)");

			st.setLong(1, user.getId());
			st.setString(2, user.getName());
			st.setString(3, user.getScreenName());

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
	 * すでに登録済みのユーザか判断
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public static Boolean isRegistered(Long userId) throws Exception {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = DBManager.getConnection();
			String sql = "SELECT user_id FROM user_t WHERE user_id = ?";
			st = con.prepareStatement(sql);
			st.setLong(1, userId);
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
