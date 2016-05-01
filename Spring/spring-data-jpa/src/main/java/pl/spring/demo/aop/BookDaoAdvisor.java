package pl.spring.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.DaoAbstract;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.IdAware;

@Aspect
public class BookDaoAdvisor {

	@Autowired
	private Sequence sequence;
	private DaoAbstract<? extends IdAware> daoAbstract;

	@SuppressWarnings("unchecked")
	@Before("@annotation(nullableId)")
	public void before(JoinPoint joinPoint, NullableId nullableId) throws BookNotNullIdException {
		checkNotNullId(joinPoint.getArgs()[0]);
		daoAbstract = (DaoAbstract<? extends IdAware>) joinPoint.getThis();
		if (daoAbstract instanceof DaoAbstract) {
			addNextId(joinPoint.getArgs()[0]);
		}
	}

	private void addNextId(Object o) {
		if (o instanceof IdAware) {
			((IdAware) o).setId(sequence.nextValue(daoAbstract.findAll()));
		}
	}

	private void checkNotNullId(Object o) {
		if (o instanceof IdAware && (((IdAware) o).getId() != null)) {
			throw new BookNotNullIdException();
		}
	}
}
