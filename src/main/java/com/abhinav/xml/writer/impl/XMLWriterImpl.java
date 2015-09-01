/*
 * Created By: Abhinav Kumar Mishra
 * Copyright &copy; 2015. Abhinav Kumar Mishra. 
 * All rights reserved.
 */
package com.abhinav.xml.writer.impl;

import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.abhinav.xml.writer.XMLWriter;
import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.DatabaseClientFactory.Authentication;
import com.marklogic.client.FailedRequestException;
import com.marklogic.client.ForbiddenUserException;
import com.marklogic.client.ResourceNotFoundException;
import com.marklogic.client.document.XMLDocumentManager;
import com.marklogic.client.io.DocumentMetadataHandle;
import com.marklogic.client.io.InputStreamHandle;

/**
 * The Class XMLWriterImpl.
 * 
 * @author Abhinav kumar mishra
 */
public class XMLWriterImpl implements XMLWriter {
	
	/** The Constant LOG. */
	private static final Log LOG = LogFactory.getLog(XMLWriterImpl.class);
	
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
	@Override
	public boolean uploadXmlDocument(final String host, final int port, final String userName,
			final String password, final InputStream docStream, final String collectionName, final String docUri) {
		LOG.info("Started uploading document..");
		if (LOG.isDebugEnabled()) {
			LOG.debug("DocUri: "+docUri+" and Collection: "+collectionName);
		}
		boolean isUploaded = false;
		try {
			// create the client
			final DatabaseClient client = DatabaseClientFactory.newClient(host, port, userName, password,
					Authentication.DIGEST);
			// create a manager for XML documents
			final XMLDocumentManager xmlDocMgr = client.newXMLDocumentManager();
			// create a handle on the content
			final InputStreamHandle inStreamHandle = new InputStreamHandle(docStream);
			final DocumentMetadataHandle docMetadata = new DocumentMetadataHandle();
			docMetadata.getCollections().addAll(collectionName);
			// write the document content, this will always throw exception if write fails
			xmlDocMgr.write(docUri, docMetadata, inStreamHandle);
			LOG.info("Upload completed..");
			// release the client
			client.release();
			isUploaded = true; //Uploaded successfully
		} catch (ResourceNotFoundException | ForbiddenUserException | FailedRequestException excp) {
			LOG.error("Exception occurred while uploding xml document to marklogic: ",excp);
		}
		return isUploaded;
	}
}
