package com.ojas.student.dao;

import com.ojas.student.entity.Entity;

public interface Dao<T extends Entity, I> {

	T save(T entity);

	T find(I id);
	
	public T getStudentEntity(String userName);
	
	public T getAccessTokenDetail(Long id);
	
	public I getUpdateAccessTokenDetail(String expireDate,Long id);
}
