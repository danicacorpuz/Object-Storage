/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Danica
 */

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class TestObjectStorage {
    private ObjectStorageClient connector;
    
    @Before
    public void initializeObjectStorage() {
        connector = new ObjectStorageClient();
    }
    
    @Test
    public void connectorShouldAddAContainer() {
        
    }
    
    @Test
    public void connectorShouldDeleteAContainer() {
        
    }
    
    @Test
    public void connectorShouldUploadAFileToContainer() {
        
    }
    
    @Test
    public void connectorShouldDownloadAFileToContainer() {
        
    }
}
