/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Danica
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.openstack4j.model.common.Payload;
import org.openstack4j.model.common.Payloads;
import org.openstack4j.model.storage.object.SwiftObject;

public class TestObjectStorage {
    private ObjectStorageClient connector;
    
    @Before
    public void initializeObjectStorage() {
        connector = new ObjectStorageClient();
    }
    
    @Test
    public void connectorShouldAddAContainer() {
        assertTrue("Connector should be able to add a container", connector.createContainer("testCont"));
    }
    
    @Test
    public void connectorShouldUploadFileToContainer() {
        String etag = "";
        try {
            File samplefile = new File("sample.txt");
            if(samplefile.createNewFile()) {
                FileWriter writer = new FileWriter(samplefile);
                writer.write("Sample File for JUnit testing");
                writer.flush();
                writer.close();
                Payload uploadfile = Payloads.create(samplefile);
                assertEquals("Connector should be able to upload a file", !etag.isEmpty(), connector.uploadFile("testCont", etag, uploadfile));
            }
        } catch (IOException ex) {
            assertEquals("Connector should be able to upload a file", "a", "b");
        }
    }
    
    @Test
    public void connectorShouldDownloadFileToContainer() {
        SwiftObject swiftobj = connector.getFile("testCont", "sample.txt");
        assertEquals("Connector should be able to download a file", "sample.txt", swiftobj.getName());
    }
    
    @Test
    public void connectorShouldDeleteFile() {
        assertTrue("Connector should be able to delete a file", connector.deleteFile("testCont", "sample.txt"));
    }
    
    @Test
    public void connectorShouldDeleteAContainer() {
        assertTrue("Connector should be able to delete a container", connector.deleteContainer("testCont"));
    }
}
