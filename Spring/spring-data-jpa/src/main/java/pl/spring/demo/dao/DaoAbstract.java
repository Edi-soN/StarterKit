package pl.spring.demo.dao;

import java.util.List;

public interface DaoAbstract <T> {

	List<T> findAll();

	T save(T book);
}
