package com.nomad.connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;
import com.nomad.entity.Book;
import com.nomad.entity.Lend;
import com.nomad.entity.Reader;
import com.nomad.entity.User;
import com.sun.org.apache.bcel.internal.classfile.ConstantString;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class Jdbc {
	
	private final String connUrl = new String("jdbc:mysql://localhost:3306/librarymanagementsystem");
	private final String connUser = new String("test");
	private final String connPassword = new String("test");
	
	private Connection connection;
	private String url;
	private String url1;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	
	public Jdbc(){
		initial();
	}
	
//	连接数据库
	void initial(){
		try {
	//		注册驱动程序
				Class.forName("com.mysql.jdbc.Driver");
	//			建立数据库连接对象(Connection)	
//				connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/librarymanagementsystem", "test", "123456");
//				connection = (Connection) DriverManager.getConnection("jdbc:mysql://218.199.185.104:3306/librarymanagementsystem", "test", "123456");
				
	//			创建Statement对象
	//			statement = (Statement) connection.createStatement();
	//			发送SQL语句
	//			int i = statement.executeUpdate("INSERT ... ");
	//			如果有结果集，处理结果集(ResultSet)
	/*			url = new String("SELECT * FROM Reader");
				resultSet = (ResultSet) statement.executeQuery(url);
				while (resultSet.next()) {
					String name = resultSet.getString("Rname");
					int id = resultSet.getInt(1);
					System.out.println("id:" + id + "姓名:" + name);
				} */
	//			关闭相应的流资源
	//			statement.close();
	// 			connection.close();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("连接失败");
				e.printStackTrace();
			} 
		}
/*
 * 	数据库操作	
 */
//	判断用户名是否已经存在
	public boolean isUserExist(String userName) {
		boolean flag = false;
		url = new String("select Uname from MUser");
		User user = new User();
		try {
			connection = (Connection) DriverManager.getConnection(connUrl, connUser, connPassword);
			statement = (Statement) connection.createStatement();
			resultSet = (ResultSet) statement.executeQuery(url);
			while (resultSet.next()){
				user.setUserName(resultSet.getString(1));
				if(userName.equals(user.getUserName())){
					flag = true;break;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag = false;
			}		
		}
		return flag;
	}
	
//	添加登录用户
	public boolean addUser(String name, String key){
		boolean flag = true;
		if (isUserExist(name)) {
			flag = false;
		}else {
			//url = new String("insert into MUser values(name, key)");
			url = new String("insert into MUser(Uname, Ukey, Uright) values(?, ?, ?)");
			try {
				connection = (Connection) DriverManager.getConnection(connUrl, connUser, connPassword);
				preparedStatement = (PreparedStatement) connection.prepareStatement(url);
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, key);
				preparedStatement.setInt(3, 0);
				preparedStatement.executeUpdate();
				
				resultSet = (ResultSet) preparedStatement.executeQuery("select * from MUser");
				while (resultSet.next()) {
					String name1 = resultSet.getString("Uname");
					String key1 = resultSet.getString("Ukey");
					System.out.println("用户名" + name1 + "密码" + key1);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag = false;
			}finally {
				//	关闭相应的流资源
				try {
					preparedStatement.close();
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					flag = false;
				}
			}
		}
		return flag;
	}
//	验证登录用户是否合法
	public boolean isLegal(String name1, String key1){
		boolean flag = false;
		url = new String("select Uname, Ukey from MUser");
		String name;
		String key;
		try {
			connection = (Connection) DriverManager.getConnection(connUrl, connUser, connPassword);
			statement = (Statement) connection.createStatement();
			resultSet = (ResultSet) statement.executeQuery(url);
			while (resultSet.next()){
				name = (String) resultSet.getString("Uname");
				key = (String) resultSet.getString("Ukey");
				if(name.equals(name1) && key.equals(key1)){
					flag = true;break;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag = false;
			}		
		}
		return flag;
	}
//	删除登录用户（没有使用）
	public boolean deleteUser(String userName){
		boolean flag = true;
		//url = new String("insert into MUser values(name, key)");
		url = new String("delete from MUser where Uname = '" + userName + "'");
		try {
			connection = (Connection) DriverManager.getConnection(connUrl, connUser, connPassword);
			statement = (Statement) connection.createStatement();
			statement.executeUpdate(url);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}finally {
			//	关闭相应的流资源
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}
//	所有图书信息集
	public Vector<String[]> selectAllBook(){
		
		int i = 0;
		Vector<String[]> vector = new Vector<String[]>();
		url = new String("select * from Book");
		try {
			connection = (Connection) DriverManager.getConnection(connUrl, connUser, connPassword);
			statement = (Statement) connection.createStatement();
			resultSet = (ResultSet) statement.executeQuery(url);
			while (resultSet.next()) {
				String[] result = new String[5];
				String[] bookInfo = new String[5];
				bookInfo[i] = (String) resultSet.getString("Bno");
				result[i] = bookInfo[i];++i;
				bookInfo[i] = (String) resultSet.getString("Bname");
				result[i] = bookInfo[i];++i;
				bookInfo[i] = (String) resultSet.getString("Bauthor");
				result[i] = bookInfo[i];++i;
				bookInfo[i] = (String) resultSet.getString("Bpublishment");
				result[i] = bookInfo[i];++i;
				bookInfo[i] = (String) resultSet.getString("Quantity");
				result[i] = bookInfo[i];++i;
				System.out.println("编号：" + bookInfo[0]);
				System.out.println("书名：" + bookInfo[1]);
				System.out.println("作者：" + bookInfo[2]);
				System.out.println("出版社：" + bookInfo[3]);
				System.out.println("数量：" + bookInfo[4]);
				i = 0;
				vector.add(result);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return vector;
	}
	//搜索图书信息（根据图书名）
	public Vector<String[]> selectBook(String name) {
		
		int i = 0;
		Vector<String[]> vector = new Vector<String[]>();
		url = new String("select * from Book where Bname like '%" + name + "%'");
		try {
			connection = (Connection) DriverManager.getConnection(connUrl, connUser, connPassword);
			statement = (Statement) connection.createStatement();
			resultSet = (ResultSet) statement.executeQuery(url);
			while (resultSet.next()) {
				String[] result = new String[5];
				result[i] = (String) resultSet.getString("Bno");
				++i;
				result[i] = (String) resultSet.getString("Bname");
				++i;
				result[i] = (String) resultSet.getString("Bauthor");
				++i;
				result[i] = (String) resultSet.getString("Bpublishment");
				++i;
				result[i] = (String) resultSet.getString("Quantity");
				++i;
				System.out.println("编号：" + result[0]);
				System.out.println("书名：" + result[1]);
				System.out.println("作者：" + result[2]);
				System.out.println("出版社：" + result[3]);
				System.out.println("数量：" + result[4]);
				i = 0;
				vector.add(result);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return vector;
	}
	//搜索图书信息（根据图书编号）   选择String[]而不是Book是为了在SelectBook.java中好foreach遍历
		public Vector<String[]> selectBook(int bookId) {
			
			int i = 0;
			Vector<String[]> vector = new Vector<String[]>();
			url = new String("select * from Book where Bno like '%" + bookId + "%'");
			try {
				connection = (Connection) DriverManager.getConnection(connUrl, connUser, connPassword);
				statement = (Statement) connection.createStatement();
				resultSet = (ResultSet) statement.executeQuery(url);
				while (resultSet.next()) {
					String[] result = new String[5];
					result[i] = (String) resultSet.getString("Bno");
					++i;
					result[i] = (String) resultSet.getString("Bname");
					++i;
					result[i] = (String) resultSet.getString("Bauthor");
					++i;
					result[i] = (String) resultSet.getString("Bpublishment");
					++i;
					result[i] = (String) resultSet.getString("Quantity");
					++i;
					System.out.println("编号：" + result[0]);
					System.out.println("书名：" + result[1]);
					System.out.println("作者：" + result[2]);
					System.out.println("出版社：" + result[3]);
					System.out.println("数量：" + result[4]);
					i = 0;
					vector.add(result);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					statement.close();
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return vector;
		}
		//搜索图书信息（根据图书编号）   选择String[]而不是Book是为了在SelectBook.java中好foreach遍历
		//根据图书号精确查询，被updateBook调用
		public String[] selectBook1(int bookId) {
			
			String[] result = new String[5];
			url = new String("select * from Book where Bno = " + bookId);
			try {
				connection = (Connection) DriverManager.getConnection(connUrl, connUser, connPassword);
				statement = (Statement) connection.createStatement();
				resultSet = (ResultSet) statement.executeQuery(url);
				while (resultSet.next()) {
					result[0] = (String) resultSet.getString("Bno");
					result[1] = (String) resultSet.getString("Bname");
					result[2] = (String) resultSet.getString("Bauthor");
					result[3] = (String) resultSet.getString("Bpublishment");
					result[4] = (String) resultSet.getString("Quantity");
					System.out.println("编号：" + result[0]);
					System.out.println("书名：" + result[1]);
					System.out.println("作者：" + result[2]);
					System.out.println("出版社：" + result[3]);
					System.out.println("数量：" + result[4]);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				try {
					statement.close();
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return result;
		}
//	图书是否存在
		public boolean isBookExist(int bookNo) {
			boolean flag = false;
			url = new String("select Bno from Book");
			Book book = new Book();
			try {
				connection = (Connection) DriverManager.getConnection(connUrl, connUser, connPassword);
				statement = (Statement) connection.createStatement();
				resultSet = (ResultSet) statement.executeQuery(url);
				while (resultSet.next()){
					book.setBookNo(resultSet.getInt(1));
					if(bookNo == book.getBookNo()){
						flag = true;break;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag = false;
			}finally {
				try {
					statement.close();
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					flag = false;
				}		
			}
			return flag;
		}

//	添加图书信息
	public boolean addBook(Book book){
		boolean flag = true;
		if (isBookExist(book.getBookNo()) || book.getBookQuantity() < 0) { 
			flag = false;
		}else {
			url = new String("insert into Book(Bno, Bname, Bauthor, Bpublishment, Quantity) values("
					+ "?, ?, ?, ?, ?)");
			try {
				connection = (Connection) DriverManager.getConnection(connUrl, connUser, connPassword);
				preparedStatement = (PreparedStatement) connection.prepareStatement(url);
				preparedStatement.setInt(1, book.getBookNo());
				preparedStatement.setString(2, book.getBookName());
				preparedStatement.setString(3, book.getBookAuthor());
				preparedStatement.setString(4, book.getBookPublishment());
				preparedStatement.setInt(5, book.getBookQuantity());
				preparedStatement.executeUpdate();
	//			resultSet = (ResultSet) preparedStatement.executeQuery("select * from MUser");
	//			while (resultSet.next()) {
	//				String name1 = resultSet.getString("Uname");
	//				String key1 = resultSet.getString("Ukey");
	//				System.out.println("用户名" + name1 + "密码" + key1);
	//			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag = false;
			}finally {
				try {
					preparedStatement.close();
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					flag = false;
				}
			}
		}
		return flag;
	}
//	删除图书信息(通过图书编号)
	public boolean deleteBook(int bookNo) {
		boolean flag = true;
		url = new String("delete from Lend where Bno =" + bookNo);
		url1 = new String("delete from Book where Bno =" + bookNo);
		try {
			connection = (Connection) DriverManager.getConnection(connUrl, connUser, connPassword);
			statement = (Statement) connection.createStatement();
			statement.addBatch(url);
			statement.addBatch(url1);
			statement.executeBatch();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}
	
//	修改图书信息（通过编号修改书的数量）
	public boolean updateBook(int bookNo, int quantity) {
		boolean flag = true;
		if (!isBookExist(bookNo)) {
			flag = false;
		}else {
			//此处有个要注意，需要bookNo输入正确，不能模糊查询；
			String[] strings = selectBook1(bookNo);
			if (Integer.parseInt(strings[4]) >= -quantity) {   // 防止要减掉的书数目大于库存
				url = new String("update Book set Quantity = Quantity + " + quantity + " where Bno =" + bookNo);
				try {
					connection = (Connection) DriverManager.getConnection(connUrl, connUser, connPassword);
					statement = (Statement) connection.createStatement();
					statement.executeUpdate(url);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					flag = false;
				}finally {
					try {
						statement.close();
						connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						flag = false;
					}
				}
			}else{
				flag = false;
			}
		}
		return flag;
	}
	
//	判断借阅信息是否存在
	public boolean isLendExist(int bookNo, int readerNo) {
		boolean flag = false;
		url = new String("select Bno, Rno from Lend");
		Lend lend = new Lend();
		try {
			connection = (Connection) DriverManager.getConnection(connUrl, connUser, connPassword);
			statement = (Statement) connection.createStatement();
			resultSet = (ResultSet) statement.executeQuery(url);
			while (resultSet.next()){
				lend.setBookNo(resultSet.getInt(1));
				lend.setReaderNo(resultSet.getInt(2));
				if(bookNo == lend.getBookNo() && readerNo == lend.getReaderNo()){
					flag = true;break;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag = false;
			}		
		}
		return flag;
	}
	
//	查询借阅信息(按照读者编号)
	public Vector<String[]> selectLend(int readerNo) {
		int i = 0;
		Vector<String[]> vector = new Vector<String[]>();
		url = new String("select Lend.*, Rname from Lend, Reader where Lend.Rno = Reader.Rno and "
				+ "Lend.Rno = " + readerNo);
		try {
			connection = (Connection) DriverManager.getConnection(connUrl, connUser, connPassword);
			statement = (Statement) connection.createStatement();
			resultSet = (ResultSet) statement.executeQuery(url);
			while (resultSet.next()) {
//				Lend lend = new Lend();
//				lend.setBookNo(resultSet.getInt(1));
//				lend.setReaderNo(resultSet.getInt(2));
//				lend.setLendTime(resultSet.getString(3));
//				lend.setReturnTime(resultSet.getString(4));
//				lend.setQuantity(resultSet.getInt(5));
//				
//				System.out.println("图书编号：" + lend.getBookNo());
//				System.out.println("读者编号：" + lend.getReaderNo());
//				System.out.println("借阅日期：" + lend.getLendTime());
//				System.out.println("归还日期：" + lend.getReturnTime());
//				System.out.println("借阅数量：" + lend.getQuantity());
//				vector.add(lend);
				String[] result = new String[6];
				result[i] = (String) resultSet.getString("Bno");
				++i;
				result[i] = (String) resultSet.getString("Rno");
				++i;
				result[i] = (String) resultSet.getString("Rname");
				++i;
				result[i] = (String) resultSet.getString("Ltime");
				++i;
				result[i] = (String) resultSet.getString("Rtime");
				++i;
				result[i] = (String) resultSet.getString("Quantity");
				++i;
				System.out.println("图书编号：" + result[0]);
				System.out.println("读者编号：" + result[1]);
				System.out.println("读者姓名：" + result[2]);
				System.out.println("借阅日期：" + result[3]);
				System.out.println("归还日期：" + result[4]);
				System.out.println("借阅数量：" + result[5]);
				i = 0;
				vector.add(result);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return vector;
	}
//	查询借阅信息(按照书编号)   选择String[]而不是Lend是为了在SelectLend.java中好foreach遍历
	public Vector<String[]> selectLend(int bookNo, int flag) {
		int i = 0;
		Vector<String[]> vector = new Vector<String[]>();
		url = new String("select Lend.*, Rname from Lend, Reader where Lend.Rno = Reader.Rno and "
				+ "Lend.Bno = " + bookNo);
		try {
			connection = (Connection) DriverManager.getConnection(connUrl, connUser, connPassword);
			statement = (Statement) connection.createStatement();
			resultSet = (ResultSet) statement.executeQuery(url);
			while (resultSet.next()) {
//				Lend lend = new Lend();
//				lend.setBookNo(resultSet.getInt(1));
//				lend.setReaderNo(resultSet.getInt(2));
//				lend.setLendTime(resultSet.getString(3));
//				lend.setReturnTime(resultSet.getString(4));
//				lend.setQuantity(resultSet.getInt(5));
//				
//				System.out.println("图书编号：" + lend.getBookNo());
//				System.out.println("读者编号：" + lend.getReaderNo());
//				System.out.println("借阅日期：" + lend.getLendTime());
//				System.out.println("归还日期：" + lend.getReturnTime());
//				System.out.println("借阅数量：" + lend.getQuantity());
//				vector.add(lend);
				String[] result = new String[6];
				result[i] = (String) resultSet.getString("Bno");
				++i;
				result[i] = (String) resultSet.getString("Rno");
				++i;
				result[i] = (String) resultSet.getString("Rname");
				++i;
				result[i] = (String) resultSet.getString("Ltime");
				++i;
				result[i] = (String) resultSet.getString("Rtime");
				++i;
				result[i] = (String) resultSet.getString("Quantity");
				++i;
				System.out.println("图书编号：" + result[0]);
				System.out.println("读者编号：" + result[1]);
				System.out.println("读者姓名：" + result[2]);
				System.out.println("借阅日期：" + result[3]);
				System.out.println("归还日期：" + result[4]);
				System.out.println("借阅数量：" + result[5]);
				i = 0;
				vector.add(result);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return vector;
	}
	
//	增加借阅信息
	public boolean addLend(Lend lend) {
		boolean flag = true;
		if (isLendExist(lend.getBookNo(), lend.getReaderNo()) ||
				!(isBookExist(lend.getBookNo()) && isReaderExist(lend.getReaderNo())) || lend.getQuantity() < 0) {
			flag = false;
		}else {
			updateBook(lend.getBookNo(), -lend.getQuantity());
			url = new String("insert into Lend(Bno, Rno, Ltime, Rtime, Quantity) values("
					+ "?, ?, ?, ?, ?)");
			try {
				connection = (Connection) DriverManager.getConnection(connUrl, connUser, connPassword);
				preparedStatement = (PreparedStatement) connection.prepareStatement(url);
				preparedStatement.setInt(1, lend.getBookNo());
				preparedStatement.setInt(2, lend.getReaderNo());//为了更加完善，此处最好还要判断书编号和读者编号是否都存在（写两个判断函数）		
				preparedStatement.setString(3, lend.getLendTime());
				preparedStatement.setString(4, lend.getReturnTime());
				preparedStatement.setInt(5, lend.getQuantity());
				preparedStatement.executeUpdate();
	//			resultSet = (ResultSet) preparedStatement.executeQuery("select * from MUser");
	//			while (resultSet.next()) {
	//				String name1 = resultSet.getString("Uname");
	//				String key1 = resultSet.getString("Ukey");
	//				System.out.println("用户名" + name1 + "密码" + key1);
	//			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag = false;
			}finally {
				try {
					preparedStatement.close();
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					flag = false;
				}
			}
		}
		return flag;
	}
//	删除借阅信息(学生离校)
	public boolean deleteLend(int readerNo){
		boolean flag = true;
		Vector<String[]> vector = selectLend(readerNo);
		for(String[] strings : vector){
			updateLend(Integer.parseInt(strings[0]), readerNo, -(Integer.parseInt(strings[5])));
		}
		url = new String("delete from Lend where Rno = " + readerNo);
		try {
			connection = (Connection) DriverManager.getConnection(connUrl, connUser, connPassword);
			statement = (Statement) connection.createStatement();
			statement.executeUpdate(url);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag = false;
			}
		}
		return flag;
	}

	
//	修改借阅信息（quantity可正可负，代表借书还书）
	public boolean updateLend(int bookNo, int readerNo, int quantity){
		boolean flag = true;
		if (!isLendExist(bookNo, readerNo)) {
			flag = false;
		}else {
			url1 = new String("select Quantity from Lend where Bno = " + bookNo + " and Rno = " + readerNo);
			try {
				connection = (Connection) DriverManager.getConnection(connUrl, connUser, connPassword);
				statement = (Statement) connection.createStatement();
				resultSet = (ResultSet) statement.executeQuery(url1);
				if(resultSet.next()){
					// 防止还的书数目大于数据库记录自己借阅该书的数目		
					if (resultSet.getInt("Quantity") < -quantity) {
						flag = false;
					}else{
						statement.close();
						connection.close();
						//防止借的书比存的书多
						if(updateBook(bookNo, -quantity)){
							url = new String("update Lend set Quantity = Quantity + " + quantity + " where Bno =" + bookNo + " and Rno = " + readerNo);
							connection = (Connection) DriverManager.getConnection(connUrl, connUser, connPassword);
							statement = (Statement) connection.createStatement();
							statement.executeUpdate(url);
						}else {
							flag = false;
						}
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag = false;
			} finally {
				try {
					statement.close();
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					flag = false;
				}
			}
		}
		
		return flag;
	}
	
	//搜索读者信息（根据读者名）
	public Vector<String[]> selectReader(String name) {
		
		int i = 0;
		Vector<String[]> vector = new Vector<String[]>();
		url = new String("select * from Reader where Rname like '%" + name + "%'");
		try {
			connection = (Connection) DriverManager.getConnection(connUrl, connUser, connPassword);
			statement = (Statement) connection.createStatement();
			resultSet = (ResultSet) statement.executeQuery(url);
			while (resultSet.next()) {
				String[] result = new String[3];
				result[i] = (String) resultSet.getString("Rno");
				++i;
				result[i] = (String) resultSet.getString("Rname");
				++i;
				result[i] = (String) resultSet.getString("Rsex");
				++i;
				System.out.println("编号：" + result[0]);
				System.out.println("姓名：" + result[1]);
				System.out.println("性别：" + result[2]);
				i = 0;
				vector.add(result);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return vector;
	}
	//搜索读者信息（根据读者编号）   选择String[]而不是Book是为了在SelectBook.java中好foreach遍历
		public Vector<String[]> selectReader(int readerId) {
			
			int i = 0;
			Vector<String[]> vector = new Vector<String[]>();
			url = new String("select * from Reader where Rno like '%" + readerId + "%'");
			try {
				connection = (Connection) DriverManager.getConnection(connUrl, connUser, connPassword);
				statement = (Statement) connection.createStatement();
				resultSet = (ResultSet) statement.executeQuery(url);
				while (resultSet.next()) {
					String[] result = new String[3];
					result[i] = (String) resultSet.getString("Rno");
					++i;
					result[i] = (String) resultSet.getString("Rname");
					++i;
					result[i] = (String) resultSet.getString("Rsex");
					++i;
					System.out.println("编号：" + result[0]);
					System.out.println("姓名：" + result[1]);
					System.out.println("性别：" + result[2]);
					i = 0;
					vector.add(result);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					statement.close();
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return vector;
		}
		//搜索读者信息（根据读者编号）   选择String[]而不是Book是为了在SelectBook.java中好foreach遍历
		//根据读者号精确查询，被updateBook调用
		public String[] selectReader1(int readerId) {
			
			String[] result = new String[3];
			url = new String("select * from Reader where Rno = " + readerId);
			try {
				connection = (Connection) DriverManager.getConnection(connUrl, connUser, connPassword);
				statement = (Statement) connection.createStatement();
				resultSet = (ResultSet) statement.executeQuery(url);
				while (resultSet.next()) {
					result[0] = (String) resultSet.getString("Rno");
					result[1] = (String) resultSet.getString("Rname");
					result[2] = (String) resultSet.getString("Rsex");
					System.out.println("编号：" + result[0]);
					System.out.println("姓名：" + result[1]);
					System.out.println("性别：" + result[2]);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				try {
					statement.close();
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return result;
		}

//	读者是否存在
		public boolean isReaderExist(int readerNo) {
			boolean flag = false;
			url = new String("select Rno from Reader");
			Reader reader = new Reader();
			try {
				connection = (Connection) DriverManager.getConnection(connUrl, connUser, connPassword);
				statement = (Statement) connection.createStatement();
				resultSet = (ResultSet) statement.executeQuery(url);
				while (resultSet.next()){
					reader.setReaderNo(resultSet.getInt(1));
					if(readerNo == reader.getReaderNo()){
						flag = true;break;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag = false;
			}finally {
				try {
					statement.close();
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					flag = false;
				}		
			}
			return flag;
		}
//	添加读者信息
	public boolean addReader(Reader reader){
		boolean flag = true;
		if (isReaderExist(reader.getReaderNo())) { 
			flag = false;
		}else {
			url = new String("insert into Reader(Rno, Rname, Rsex) values("
					+ "?, ?, ?)");
			try {
				connection = (Connection) DriverManager.getConnection(connUrl, connUser, connPassword);
				preparedStatement = (PreparedStatement) connection.prepareStatement(url);
				preparedStatement.setInt(1, reader.getReaderNo());
				preparedStatement.setString(2, reader.getReaderName());
				preparedStatement.setString(3, reader.getReaderSex());
				preparedStatement.executeUpdate();
	//			resultSet = (ResultSet) preparedStatement.executeQuery("select * from MUser");
	//			while (resultSet.next()) {
	//				String name1 = resultSet.getString("Uname");
	//				String key1 = resultSet.getString("Ukey");
	//				System.out.println("用户名" + name1 + "密码" + key1);
	//			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag = false;
			}finally {
				try {
					preparedStatement.close();
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					flag = false;
				}
			}
		}
		return flag;
	}
//	删除读者信息(通过读者编号)
	public boolean deleteReader(int readerId) {
		boolean flag = true;
		if (!deleteLend(readerId)) {
			flag = false;
		}else {
			url = new String("delete from Reader where Rno =" + readerId);
			try {
				connection = (Connection) DriverManager.getConnection(connUrl, connUser, connPassword);
				statement = (Statement) connection.createStatement();
				statement.executeUpdate(url);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag = false;
			}finally {
				try {
					statement.close();
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					flag = false;
				}
			}
		}
		return flag;
	}
	
//	修改读者信息（通过编号修改读者）
	public boolean updateReader(int readerId, String readerName) {
		boolean flag = true;
		if (!isReaderExist(readerId)) {
			flag = false;
		}else {
			String[] strings = selectReader1(readerId);
			url = new String("update Reader set Rname = '" + readerName + "' where Rno =" + readerId);
			try {
				connection = (Connection) DriverManager.getConnection(connUrl, connUser, connPassword);
				statement = (Statement) connection.createStatement();
				statement.executeUpdate(url);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag = false;
			}finally {
				try {
					statement.close();
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					flag = false;
				}
			}
		}
		return flag;
	}
}
