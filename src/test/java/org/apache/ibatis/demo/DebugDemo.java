package org.apache.ibatis.demo;

import org.apache.ibatis.demo.domain.Role;
import org.apache.ibatis.demo.mapper.RoleMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author lilimin
 * @since 2021-04-04
 */
public class DebugDemo {

  public static void main(String[] args) throws IOException {
    String resource = "mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    SqlSession sqlSession = null;
    try {
      // 返回的是 DefaultSqlSession
      sqlSession = sqlSessionFactory.openSession();
      Role role1 = (Role) sqlSession.selectOne("org.apache.ibatis.demo.mapper.RoleMapper.selectById", 2L);
      System.out.println(role1);
      RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
      Role role = roleMapper.selectById(2L);
      System.out.println(role);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (sqlSession != null) {
        sqlSession.close();
      }
    }
  }
}
