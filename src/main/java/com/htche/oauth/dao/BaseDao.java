package com.htche.oauth.dao;

import java.util.List;

public interface BaseDao<T> {
	int deleteByPrimaryKey(String pk);

	int insert(T t);

	int insertSelective(T t);

	T selectByPrimaryKey(String pk);

	int updateByPrimaryKeySelective(T t);

	int updateByPrimaryKey(T t);

	List<T> queryByList(T t);


	int queryCountByPage(T t);
}
