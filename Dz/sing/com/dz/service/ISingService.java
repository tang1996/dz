package com.dz.service;

import java.util.List;

import com.dz.entity.Sing;

/**
 * 通用dao，包括基本的CRUD方法
 * 
 * @author Ljh
 *
 */

public interface ISingService {

	List<Sing> singList(Sing sing);
	
	Sing getSing(int companyId);

	public void delete(final String id);

	public void saveORupdate(final Sing sing);

}
