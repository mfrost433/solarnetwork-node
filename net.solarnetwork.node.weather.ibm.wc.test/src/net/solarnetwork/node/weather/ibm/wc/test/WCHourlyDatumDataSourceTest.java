package net.solarnetwork.node.weather.ibm.wc.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.solarnetwork.node.domain.GeneralAtmosphericDatum;
import net.solarnetwork.node.weather.ibm.wc.BasicWCClient;
import net.solarnetwork.node.weather.ibm.wc.WCClient;
import net.solarnetwork.node.weather.ibm.wc.WCHourlyDatum;
import net.solarnetwork.node.weather.ibm.wc.WCHourlyDatumDataSource;

/**
 * Tests the parsing for hourly forecasts using downloaded JSON 
 * 
 * @author matt frost
 *
 */
public class WCHourlyDatumDataSourceTest {
	
	private static final String TEST_SOURCE_ID = "Test";
	private static final String RESOURCE_NAME = "2day_hourly.json";
	private WCHourlyDatumDataSource hourlyService;

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private WCClient createClient() {
		URL url = getClass().getResource(RESOURCE_NAME);
		String urlString = url.toString();
		String baseDirectory = urlString.substring(0, urlString.lastIndexOf('/'));
		if ( baseDirectory.startsWith("file:") ) {
			baseDirectory = "file://" + baseDirectory.substring(5);
		}
		BasicWCClient client = new BasicWCClient();
		client.setBaseUrl(baseDirectory);
		client.setTest(true);
		client.setHourlyForecastUrl("%s_hourly.json");
		
		return client;
	}
	
	@Before
	public void setup() {
		hourlyService = new WCHourlyDatumDataSource();
		hourlyService.setClient(createClient());
		hourlyService.setDatumPeriod("2day");
		hourlyService.setObjectMapper(new ObjectMapper());
	}
	
	@Test
	public void readDatum() {
		Collection<WCHourlyDatum> datum = hourlyService.readMultipleDatum();
		log.debug(datum.toString());
		WCHourlyDatum first = (WCHourlyDatum) datum.toArray()[0];
		SimpleDateFormat day = new SimpleDateFormat("d MMMM yyyy");
		SimpleDateFormat hour = new SimpleDateFormat("HH:mm EE d MMMM yyyy");
		assertNotNull(first);
		
		assertNotNull(first.getCreated());
		
		//log.debug(hour.format(first.getCreated()), "12:00 Tue 10 July 2018");
		
		assertEquals(hour.format(first.getCreated()), "12:00 Tue 10 July 2018");
		
		assertEquals(first.getTemperature(),new BigDecimal(86));
		
		assertEquals(first.getVisibility().intValue(),10);
		
		assertEquals(first.getWindDirection().intValue(),135);
		
		assertEquals(first.getWindSpeed().intValue(),12);
		
		assertEquals(first.getHumidity().intValue(),16);
		
		assertEquals(first.getCloudCover().intValue(),28);
	}
	
	
	
	
}
