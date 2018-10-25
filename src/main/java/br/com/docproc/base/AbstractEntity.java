package br.com.docproc.base;

import org.springframework.data.jpa.domain.AbstractPersistable;

import java.io.Serializable;

public abstract class AbstractEntity<ID extends Serializable> extends AbstractPersistable<ID> {
}
