package com.devix.number.generator;

import com.devix.number.controller.MessageController;

public class Converter {

    public static String convert(int i) {

        String param;

        switch (i) {
            case 1:
                param = "А";
                break;
            case 2:
                param = "В";
                break;
            case 3:
                param = "Е";
                break;
			case 4:
				param = "K";
				break;
			case 5:
				param = "М";
				break;
			case 6:
				param = "Н";
				break;
			case 7:
				param = "О";
				break;
			case 8:
				param = "Р";
				break;
			case 9:
				param = "С";
				break;
			case 10:
				param = "Т";
				break;
			case 11:
				param = "У";
				break;
			case 12:
				param = "Х";
				break;
            default:
                System.out.println("Error! Некорректное значение: " + i);
                throw new MessageController.NumberNotFoundException();
        }

        return param;
    }
}