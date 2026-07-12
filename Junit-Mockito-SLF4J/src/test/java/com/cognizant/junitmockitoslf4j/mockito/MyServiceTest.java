package com.cognizant.junitmockitoslf4j.mockito;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MyServiceTest {

    @Test
    void testMockingAndStubbing() {

        ExternalApi api = mock(ExternalApi.class);

        when(api.getData()).thenReturn("Mock Data");

        MyService service = new MyService(api);

        String result = service.fetchData();

        assertEquals("Mock Data", result);
    }

    @Test
    void testVerifyInteraction() {

        ExternalApi api = mock(ExternalApi.class);

        when(api.getData()).thenReturn("Mock Data");

        MyService service = new MyService(api);

        service.fetchData();

        verify(api).getData();
    }
}