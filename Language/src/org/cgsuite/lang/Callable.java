/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.cgsuite.lang;

import java.util.List;
import java.util.Map;
import org.cgsuite.CgsuiteException;

/**
 *
 * @author asiegel
 */
public interface Callable
{
    public CgsuiteObject invoke(List<CgsuiteObject> arguments, Map<String,CgsuiteObject> optionalArguments)
        throws CgsuiteException;
}
