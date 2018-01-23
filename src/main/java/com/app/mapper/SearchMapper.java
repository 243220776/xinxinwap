package com.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.app.domain.Search;

@Mapper
public interface SearchMapper {
	
	@Select("select * from search")
	List<Search> getSearch();

}
