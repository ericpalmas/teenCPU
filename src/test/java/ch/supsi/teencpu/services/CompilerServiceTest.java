package ch.supsi.teencpu.services;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CompilerServiceTest {

    @Test
    public void compilerServiceTest() {

        CompilerService compilerService = CompilerService.getInstance();
        // is singleton?
        assertEquals(compilerService, CompilerService.getInstance());
    }


}
