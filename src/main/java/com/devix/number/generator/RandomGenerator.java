package com.devix.number.generator;

import java.util.Random;

import com.devix.number.controller.MessageController;
import com.devix.number.dao.MyBatisUtil;
import com.devix.number.dao.ReadHistData;
import com.devix.number.dao.ServiceMapper;
import org.apache.ibatis.session.SqlSession;

public class RandomGenerator {

    private static Random rnd = new Random();

    public static String generate() {

        SqlSession sqlSession;
        com.devix.number.dao.ServiceMapper mapper;

        String alphabet = "АЕТОРНУКХСВМ";
        Random random = new Random();

        String num = generateNumbers(1000);
        char char1 = alphabet.charAt(random.nextInt(12));
        char char2 = alphabet.charAt(random.nextInt(12));
        char char3 = alphabet.charAt(random.nextInt(12));

        sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        mapper = sqlSession.getMapper(ServiceMapper.class);
        ReadHistData histData = mapper.getHistData(char1 + num + char2 + char3 + " 116 RUS");

        Integer countHist = histData.getCount();
        System.out.println("countHist: " + countHist);

        if (countHist > 0) {

            throw new MessageController.NumberNotFoundException();

        } else {

            mapper.insertGosAutoNumber(char1 + num + char2 + char3 + " 116 RUS");
            sqlSession.commit();

        }

        return char1 + num + char2 + char3 + " 116 RUS";
    }

    private static String generateNumbers(int i) {

        return ("" + (rnd.nextInt(i) + i)).substring(1);

    }
}