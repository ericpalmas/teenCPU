package ch.supsi.teencpu.models.instruction;

import ch.supsi.teencpu.models.exceptions.InvalidParametersException;
import ch.supsi.teencpu.models.exceptions.NoParametersException;

public interface Parameterizable {

    public int getParameter();

    public void setParameter(int parameter);
}
