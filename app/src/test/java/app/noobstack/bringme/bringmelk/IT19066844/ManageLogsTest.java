package app.noobstack.bringme.bringmelk.IT19066844;

import com.google.android.gms.common.util.ScopeUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import app.noobstack.bringme.bringmelk.ManageLogs;

import static org.junit.Assert.*;

public class ManageLogsTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void createDate() {
        String  date = ManageLogs.createDate();
        assertNotNull(date);
    }

    @Test
    public void checkDate() {
        String  date = ManageLogs.createDate();
        assertEquals(15, date.length());
    }

    @Test
    public void uuidChecker(){
        assertNotNull(ManageLogs.generateUUID());
    }

    @Test
    public void uuidCheckerLength(){
        assertEquals(36, ManageLogs.generateUUID().length());
    }
}