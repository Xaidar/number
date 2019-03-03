package com.devix.number.controller;

import com.devix.number.dao.MyBatisUtil;
import com.devix.number.dao.ReadHistData;
import com.devix.number.dao.ServiceMapper;
import com.devix.number.generator.RandomGenerator;
import org.apache.ibatis.session.SqlSession;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.devix.number.generator.Converter;

@RestController
public class MessageController {

	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Oh! Something went wrong!")
	public static class NumberNotFoundException extends RuntimeException {

	}

	@RequestMapping(value= "/number/random", method = RequestMethod.GET)
	public String random() {

		return RandomGenerator.generate();
	}

	private Integer m = 0;
	private Integer i = 1;
	private Integer j = 1;
	private Integer k = 1;
	private String char1 = null;
	private String char2 = null;
	private String char3 = null;
	private String counter = null;

	@RequestMapping(value= "/number/next", method = RequestMethod.GET)
	public String next() {

		try {

			String iOut = null;

			if (i <= 12) {

				if (j <= 12) {

					if (k <= 12) {

						if (m <= 999) {

							char1 = Converter.convert(i);
							char2 = Converter.convert(j);
							char3 = Converter.convert(k);

							counter = ("" + (m + 1000)).substring(1);
							iOut = char1 + "" + counter + "" + char2 + "" + char3 + " 116 RUS";

							if (m == 999) {
								k++;
							}

							m++;

						} else {

							m = 0;

							char1 = Converter.convert(i);
							char2 = Converter.convert(j);
							char3 = Converter.convert(k);

							counter = ("" + (m + 1000)).substring(1);
							iOut = char1 + "" + counter + "" + char2 + "" + char3 + " 116 RUS";

							m++;

						}

					} else {

						m = 0;
						k = 1;
						j++;

						if (j > 12) {
							j = 1;
							i++;
						}

						char1 = Converter.convert(i);
						char2 = Converter.convert(j);
						char3 = Converter.convert(k);

						counter = ("" + (m + 1000)).substring(1);
						iOut = char1 + "" + counter + "" + char2 + "" + char3 + " 116 RUS";

						m++;

					}

				} else {

					m = 0;
					k = 1;
					j = 1;
					i++;

					char1 = Converter.convert(i);
					char2 = Converter.convert(j);
					char3 = Converter.convert(k);

					counter = ("" + (m + 1000)).substring(1);
					iOut = char1 + "" + counter + "" + char2 + "" + char3 + " 116 RUS";

					m++;

				}


				SqlSession sqlSession;
				com.devix.number.dao.ServiceMapper mapper;

				sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
				mapper = sqlSession.getMapper(ServiceMapper.class);
				ReadHistData histData = mapper.getHistData(iOut);

				Integer countHist = histData.getCount();
				System.out.println("countHist: " + countHist);

				if (countHist > 0) {

					throw new MessageController.NumberNotFoundException();

				} else {

					mapper.insertGosAutoNumber(iOut);
					sqlSession.commit();

				}

				System.out.println(iOut);
				return iOut;

			} else {

				throw new NumberNotFoundException();
			}

		} catch (RuntimeException ex) {

			throw new NumberNotFoundException();
		}

	}

}
