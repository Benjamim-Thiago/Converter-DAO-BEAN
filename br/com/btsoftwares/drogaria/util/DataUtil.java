package br.com.btsoftwares.drogaria.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DataUtil {
	public static final String TIME_ZONE = "America/Sao_Paulo" ;

	public Date dataServidor() {
		TimeZone.setDefault(TimeZone.getTimeZone(TIME_ZONE));
		Calendar calendar = Calendar.getInstance();

		Date dt = calendar.getTime();

		return dt;
	}
}
