package helper;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(name = "ipara_test", locations = {"classpath:test-file-system-context.xml"})
public abstract class ServiceTestBase extends TestCase {

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }
}
