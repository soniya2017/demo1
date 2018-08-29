package com.ojas.student.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

import com.ojas.student.entity.AccessToken;
import com.ojas.student.entity.Entity;
import com.ojas.student.entity.StudentInformation;

public class JpaDao<T extends Entity, I> implements Dao<T, I> {

	private EntityManager entityManager;

	private Class<T> entityClass;

	public JpaDao() {

	}

	public JpaDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public T save(T entity) {

		this.getEntityManager().persist(entity);
		return entity;
	}

	@Override
	@Transactional(readOnly = true)

	public T find(I id) {

		return getEntityManager().find(entityClass, id);
	}

	public T getStudentEntity(String userName) {
		/*Query query = this.getEntityManager().createQuery("Select student From StudentInformation as student where student.studentName=:studentName");
		query.setParameter("studentName", userName);
		
		/*CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		Query query = this.getEntityManager().createNamedQuery("studentDetails");
		query.setParameter("studentName", userName);*/

		CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<StudentInformation> studentCriteria = builder.createQuery(StudentInformation.class);
		Root<StudentInformation> rootStudent = studentCriteria.from(StudentInformation.class);
		Predicate condition = builder.equal(rootStudent.get("studentName"), userName);
		CriteriaQuery<StudentInformation> resultantCriteria = studentCriteria.select(rootStudent).where(condition);
		TypedQuery<StudentInformation> query = this.getEntityManager().createQuery(resultantCriteria);
		return (T) query.getSingleResult();
	}

	@Override
	public T getAccessTokenDetail(Long id) {

		CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<AccessToken> accessCritea = builder.createQuery(AccessToken.class);
		Root<AccessToken> rootAccess = accessCritea.from(AccessToken.class);
		Predicate condition = builder.equal(rootAccess.get("id"), id);
		CriteriaQuery<AccessToken> result = accessCritea.select(rootAccess).where(condition);
		TypedQuery<AccessToken> query = this.getEntityManager().createQuery(result);
		return (T) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public I getUpdateAccessTokenDetail(String expireDate, Long id) {

		Query query = this.getEntityManager()
				.createQuery("Update AccessToken as token set token.expiryDate=?1 where token.id=?2");
		query.setParameter(1, expireDate);
		query.setParameter(2, id);
		return (I) Integer.valueOf(query.executeUpdate());
		
	}
	

}
