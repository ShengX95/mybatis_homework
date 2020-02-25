package com.sx;

import com.sx.dao.IUserDao;
import com.sx.pojo.User;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * @author shengx
 * @date 2020/2/22 19:32
 */
public class TestDemo {
    SqlSession sqlSession;
    @Before
    public void before() throws Exception {
        InputStream in = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryBuilder.build(in);
        sqlSession = sqlSessionFactory.openSession();
    }

    @Test
    public void test() {
        try {

//            List<User> list = sqlSession.selectList("user.findAll");
//            for (User user : list) {
//                System.out.println(user);
//            }
            User user = new User();
            user.setId(1);
            user.setUser_name("测试");
            User u = sqlSession.selectOne("user.findById", user);
            System.out.println(u);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() throws Exception {
        User iu = new User();
        iu.setId(4);
        iu.setUser_name("iu");
        iu.setPassword("1111");
        iu.setAge(11);
        sqlSession.update("com.sx.dao.IUserDao.addUser", iu);
    }

    @Test
    public void test3() throws Exception {
        User iu = new User();
        iu.setId(4);
        iu.setUser_name("iutt");
        iu.setPassword("11121");
        iu.setAge(113);
        sqlSession.update("com.sx.dao.IUserDao.updateUser", iu);
    }

    @Test
    public void test4() throws Exception {
        User iu = new User();
        iu.setId(4);
        sqlSession.update("com.sx.dao.IUserDao.deleteUserById", iu);
    }

    @Test
    public void test5() throws Exception {
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);
        System.out.println(userDao.findAll());
        User iu = new User();
        iu.setId(6);
        iu.setUser_name("iutt66");
        iu.setPassword("11121");
        iu.setAge(113);
        userDao.addUser(iu);
    }
}
