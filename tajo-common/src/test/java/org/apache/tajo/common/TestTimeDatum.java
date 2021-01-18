package org.apache.tajo.common;

import static org.junit.Assert.*;

import org.apache.tajo.datum.Datum;
import org.apache.tajo.datum.DatumFactory;
import org.apache.tajo.datum.TimeDatum;
import org.apache.tajo.exception.InvalidOperationException;
import org.apache.tajo.exception.InvalidValueForCastException;
import org.apache.tajo.exception.TajoRuntimeException;
import org.junit.Assert;
import org.junit.Test;

public class TestTimeDatum {

	//Parametri
		Object[][] parametri = {
				                 {DatumFactory.createTime("07:15:00"), DatumFactory.createTime("07:15:00")},
								 {DatumFactory.createTime("07:15:00"), DatumFactory.createTime("01:16:00")},
								 {DatumFactory.createTime("07:15:00"),null}};
		@Test
		 public void equalsTest() {
			
			for(int i =0;i<3;i++) {
				switch (i) {
				case 0:
					Datum datum1 = (Datum) parametri[0][0];
					Datum datum2 = (Datum) parametri[0][1];
					Boolean res1 = datum1.equals(datum2);
					Assert.assertEquals(true,res1);
				case 1:
					Datum datum3 = (Datum) parametri[1][0];
					Datum datum4 = (Datum) parametri[1][1];
					Boolean res2 = datum3.equals(datum4);
					Assert.assertEquals(false,res2);
					
				case 2:
					Datum datum5 = (Datum) parametri[2][0];
					Datum datum6 = (Datum) parametri[2][1];
					try {
						Boolean date3 = datum5.equals(datum6);
			        } catch (Exception e) {
			            Assert.assertEquals(NullPointerException.class, e.getClass());
			        }
				}	
			}
			
		}
		
		@Test
		 public void compareToTest() {
			
			for(int i =0;i<3;i++) {
				switch (i) {
				case 0:
					Datum datum1 = (Datum) parametri[0][0];
					Datum datum2 = (Datum) parametri[0][1];
					int result1 = datum1.compareTo(datum2);
					Assert.assertEquals(0,result1);
				case 1:
					Datum datum3 = (Datum) parametri[1][0];
					Datum datum4 = (Datum) parametri[1][1];
					int result2 = datum3.compareTo(datum4);
					Assert.assertEquals(1,result2);
					
				case 2:
					Datum datum5 = (Datum) parametri[2][0];
					Datum datum6 = (Datum) parametri[2][1];
					try {
						int result3 = datum5.compareTo(datum6);
			        } catch (Exception e) {
			            Assert.assertEquals(NullPointerException.class, e.getClass());
			        }
				}	
			}
			//scritto per incremantare coverage
			Datum datum = (Datum) parametri[0][0];
			Datum null_datum = DatumFactory.createNullDatum();
			Assert.assertEquals(-1,datum.compareTo(null_datum));
			
			//scritto per incremantare coverage

			Datum datum_bool = DatumFactory.createBool(1);
			try{
				datum.compareTo(datum_bool);
			}catch(Exception e) {
				Assert.assertEquals(InvalidOperationException.class,e.getClass());
			}
		}
		
		@Test
		 public void equalsToTest() {
			
			for(int i =0;i<3;i++) {
				switch (i) {
				case 0:
					Datum datum1 = (Datum) parametri[0][0];
					Datum datum2 = (Datum) parametri[0][1];
					Datum date1 = datum1.equalsTo(datum2);
					Assert.assertEquals(true,date1.asBool());
				case 1:
					Datum datum3 = (Datum) parametri[1][0];
					Datum datum4 = (Datum) parametri[1][1];
					Datum date2 = datum3.equalsTo(datum4);
					Assert.assertEquals(false,date2.asBool());
					
				case 2:
					Datum datum5 = (Datum) parametri[2][0];
					Datum datum6 = (Datum) parametri[2][1];
					try {
						Datum date3 = datum5.equalsTo(datum6);
			        } catch (Exception e) {
			            Assert.assertEquals(NullPointerException.class, e.getClass());
			        }
				}	
			}
			//scritto per incremantare coverage
			Datum datum = (Datum) parametri[0][0];
			Datum null_datum = DatumFactory.createNullDatum();
			Assert.assertEquals(null_datum,datum.equalsTo(null_datum));
			
			Datum datum_bool = DatumFactory.createBool(1);
			try{
				datum.equalsTo(datum_bool);
			}catch(Exception e) {
				Assert.assertEquals(InvalidOperationException.class,e.getClass());
			}
			
			
			
		}
		@Test
		public void minusTest() {
			for(int i =0;i<3;i++) {
				switch (i) {
				case 0:
					Datum datum1 = (Datum) parametri[0][0];
					Datum datum2 = DatumFactory.createInterval("07:15:00");
					Datum res1 = datum1.minus(datum2);
					Assert.assertEquals(DatumFactory.createTime("00:00:00"),res1);
				case 1:
					Datum datum3 = (Datum) parametri[1][0];
					Datum datum4 = DatumFactory.createInterval("01:16:00");
					Datum res2 = datum3.minus(datum4);
					Assert.assertEquals(DatumFactory.createTime("05:59:00"),res2);
					
				case 2:
					Datum datum5 = (Datum) parametri[2][0];
					Datum datum6 = (Datum) parametri[2][1];
					try {
						Datum date3 = datum5.minus(datum6);
			        } catch (Exception e) {
			            Assert.assertEquals(NullPointerException.class, e.getClass());
			        }
				}	
			}
			
		}
		
		@Test
		public void plusTest() {
			for(int i =0;i<3;i++) {
				switch (i) {
				case 0:
					Datum datum1 = (Datum) parametri[0][0];
					Datum datum2 = DatumFactory.createInterval("07:15:00");
					Datum res1 = datum1.plus(datum2);
					Assert.assertEquals(DatumFactory.createTime("14:30:00"),res1);
				case 1:
					Datum datum3 = (Datum) parametri[1][0];
					Datum datum4 = DatumFactory.createInterval("01:16:00");
					Datum res2 = datum3.plus(datum4);
					Assert.assertEquals(DatumFactory.createTime("08:31:00"),res2);
					
				case 2:
					Datum datum5 = (Datum) parametri[2][0];
					Datum datum6 = (Datum) parametri[2][1];
					try {
						Datum date3 = datum5.plus(datum6);
			        } catch (Exception e) {
			            Assert.assertEquals(NullPointerException.class, e.getClass());
			        }
				}	
			}
			
		}
		//scritto per incremantare coverage
		@Test
		public void TestCoveragePlus() {
			
			Datum datum0 = (Datum) parametri[2][0];
			Datum datum1= (Datum) DatumFactory.createDate(2020,4,18);
			Datum res1= (Datum) datum0.plus(datum1);
			Assert.assertEquals("2020-04-18 07:15:00", res1.toString());
			
			Datum datum2= (Datum) DatumFactory.createTimestamp("11:00:00");
			Datum res2= (Datum) datum0.plus(datum2);
			Assert.assertEquals(DatumFactory.createTimestamp("18:15:00"), res2);
			
			Datum datum = (Datum) parametri[0][0];
			Datum bool_datum = DatumFactory.createBool(1);
			try{
				datum.plus(bool_datum);
			}catch(Exception e) {
				Assert.assertEquals(InvalidOperationException.class,e.getClass());
			}
			
		}
		//scritto per incremantare coverage
		@Test
		public void TestCoverageMinus() {
			Datum datum0 = (Datum) parametri[2][0];
			Datum datum1= (Datum) DatumFactory.createTime("05:00:00");
			Datum res = datum0.minus(datum1);
			Assert.assertEquals(DatumFactory.createInterval("02:15:00"), res);
			Datum datum = (Datum) parametri[0][0];
			Datum bool_datum = DatumFactory.createBool(1);
			try{
				datum.minus(bool_datum);
			}catch(Exception e) {
				Assert.assertEquals(InvalidOperationException.class,e.getClass());
			}
		}
		
		//scritto per incremantare coverage
		@Test
		public void TestGeneralCoverage() {
			//Per prendere le informazioni da TIMEDATUM 
			//ORE MINUTI SECONDI E MILLISECONDI
			TimeDatum time = DatumFactory.createTime("07:15:36.678");
			Assert.assertEquals(8, time.size());
			Assert.assertEquals(7, time.getHourOfDay());
			Assert.assertEquals(15, time.getMinuteOfHour());
			Assert.assertEquals(36, time.getSecondOfMinute());
			Assert.assertEquals(678, time.getMillisOfSecond());
			Assert.assertEquals("07:15:36.678", time.toString());
			
			TimeDatum time1 = new TimeDatum(1);
			Assert.assertEquals(1,time1.hashCode());

		}
		//scritto per incremantare coverage
		@Test(expected = TajoRuntimeException.class)
		public void CoverageFloat4() {
			TimeDatum time = DatumFactory.createTime("07:15:36");
			time.asFloat4();
		}
		
		@Test(expected = TajoRuntimeException.class)
		public void CoverageFloat8() {
			TimeDatum time = DatumFactory.createTime("07:15:36");
			time.asFloat8();
		}
		@Test(expected = TajoRuntimeException.class)
		public void CoverageInt4() {
			TimeDatum time = DatumFactory.createTime("07:15:36");
			time.asInt4();
		}
		@Test
		public void CoverageAsInt8() {
			TimeDatum time = new TimeDatum(1000);
			Assert.assertEquals(1000,time.asInt8());
		}
}
