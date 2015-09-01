/*
 * Created By: Abhinav Kumar Mishra
 * Copyright &copy; 2015. Abhinav Kumar Mishra. 
 * All rights reserved.
 */
package com.abhinav.xml.writer;

import java.io.InputStream;

/**
 * The Class XMLWriterImpl.
 * 
 * @author Abhinav kumar mishra
 */
public interface XMLWriter {

	/**
	 * Upload xml document.
	 *
	 * @param host the host
	 * @param port the port
	 * @param userName the user name
	 * @param password the password
	 * @param docStream the doc stream
	 * @param collectionName the collection name
	 * @param docUri the doc uri
	 * @return true, if successful
	 */
	boolean uploadXmlDocument(final String host, final int port, final String userName,
			final String password, final InputStream docStream, final String collectionName, final String docUri);
}
