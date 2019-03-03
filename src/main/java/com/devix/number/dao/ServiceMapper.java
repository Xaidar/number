package com.devix.number.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface ServiceMapper {

	// Проверка, "не сгенерирован ли ранее этот номер?"
	@Results({ @Result(property = "count", column = "count")
	})
	@Select("SELECT count(*) as count FROM gosAutoNumber WHERE AutoNumber = #{newGosAutoNumber}")
	ReadHistData getHistData(@Param("newGosAutoNumber") String newGosAutoNumber);

	// Запись номера в БД
	int insertGosAutoNumber(@Param("newGosAutoNumber") String newGosAutoNumber);

}
