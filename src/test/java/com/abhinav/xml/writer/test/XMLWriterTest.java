/*
 * Created By: Abhinav Kumar Mishra
 * Copyright &copy; 2015. Abhinav Kumar Mishra. 
 * All rights reserved.
 */
package com.abhinav.xml.writer.test;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

import com.abhinav.xml.writer.XMLWriter;
import com.abhinav.xml.writer.impl.XMLWriterImpl;

/**
 * The Class XMLWriterTest.
 */
public class XMLWriterTest {

	/** The xml writer. */
	private XMLWriter xmlWriter;

	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		xmlWriter = new XMLWriterImpl();
	}

	/**
	 * Test upload xml document.
	 */
	@Test
	public void testUploadXmlDocument() {
		final InputStream docStream = XMLWriter.class.getClassLoader().getResourceAsStream("data/testUpload.xml");
		boolean isUploaded = xmlWriter.uploadXmlDocument("localhost", 8004, "admin", "admin", docStream, "mycollection",
				"/test/xyz");
		assertEquals(true, isUploaded);
	}

}
