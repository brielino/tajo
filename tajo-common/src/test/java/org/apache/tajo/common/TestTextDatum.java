package org.apache.tajo.common;

import static org.junit.Assert.*;

import org.apache.tajo.datum.Datum;
import org.apache.tajo.datum.DatumFactory;
import org.apache.tajo.datum.TextDatum;
import org.apache.tajo.datum.TimeDatum;
import org.apache.tajo.exception.InvalidOperationException;
import org.apache.tajo.exception.TajoRuntimeException;
import org.junit.Assert;
import org.junit.Test;

public class TestTextDatum {
	//Parametri
	Object[][] parametri = {
			                 {DatumFactory.createText(new byte[]{1, 2, 3}), DatumFactory.createText(new byte[]{1, 2, 3})},
							 {DatumFactory.createText(new byte[]{1, 2, 5}), DatumFactory.createText(new byte[]{1, 2, 4})},
							 {DatumFactory.createText(new byte[]{1, 2, 3}),null}};
	@Test
	 public void equalsToTest() {
		
		for(int i =0;i<3;i++) {
			switch (i) {
			case 0:
				Datum datum1 = (Datum) parametri[0][0];
				Datum datum2 = (Datum) parametri[0][1];
				Datum date = datum1.equalsTo(datum2);
				Assert.assertEquals(true,date.asBool());
			case 1:
				Datum datum3 = (Datum) parametri[1][0];
				Datum datum4 = (Datum) parametri[1][1];
				Datum date1 = datum3.equalsTo(datum4);
				Assert.assertEquals(false,date1.asBool());
				
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
		//scritto per incrementare coverage
		Datum datum = (Datum) parametri[0][0];
		Datum null_datum = DatumFactory.createNullDatum();
		Assert.assertEquals(null_datum,datum.equalsTo(null_datum));
		
		//scritto per incrementare coverage

		Datum datum_bool = DatumFactory.createBool(1);
		try{
			datum.equalsTo(datum_bool);
		}catch(Exception e) {
			Assert.assertEquals(InvalidOperationException.class,e.getClass());
		}
	}
	
	@Test
	 public void equalsTest() {
		
		for(int i =0;i<3;i++) {
			switch (i) {
			case 0:
				Datum datum1 = (Datum) parametri[0][0];
				Datum datum2 = (Datum) parametri[0][1];
				Boolean result1 = datum1.equals(datum2);
				Assert.assertEquals(true,result1);
			case 1:
				Datum datum3 = (Datum) parametri[1][0];
				Datum datum4 = (Datum) parametri[1][1];
				Boolean result2 = datum3.equals(datum4);
				Assert.assertEquals(false,result2);
				
			case 2:
				Datum datum5 = (Datum) parametri[2][0];
				Datum datum6 = (Datum) parametri[2][1];
				try {
					Boolean result3 = datum5.equals(datum6);
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
		//scritto per incrementare coverage
		Datum datum = (Datum) parametri[0][0];
		Datum null_datum = DatumFactory.createNullDatum();
		Assert.assertEquals(-1,datum.compareTo(null_datum));
		Datum datum_bool = DatumFactory.createBool(1);
		try{
			datum.compareTo(datum_bool);
		}catch(Exception e) {
			Assert.assertEquals(InvalidOperationException.class,e.getClass());
		}
	}
	
	
	@Test
	public void TestGeneralCoverage() {
		TextDatum texttime = new TextDatum("11:00:00");
		Assert.assertEquals("11:00:00", texttime.toString());
		Assert.assertEquals(8, texttime.size());
		
		TextDatum text_datum = new TextDatum("1");
		Assert.assertEquals(1, text_datum.asInt2());
		Assert.assertEquals(1, text_datum.asInt4());
		Assert.assertEquals(1, text_datum.asInt8());
		Assert.assertEquals("1.0", Float.toString(text_datum.asFloat4()));
		Assert.assertEquals("1.0", Double.toString(text_datum.asFloat8()));
	}
	
	@Test(expected = TajoRuntimeException.class)
	public void CoverageAsBool() {
		TextDatum time = new TextDatum("11:00:00");
		time.asBool();
	}
	
	@Test(expected = TajoRuntimeException.class)
	public void CoverageAsByte() {
		TextDatum time = new TextDatum("11:00:00");
		time.asByte();
	}
}
