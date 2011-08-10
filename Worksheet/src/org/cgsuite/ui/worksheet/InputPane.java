/*
 * InputPane.java
 *
 * Created on December 11, 2002, 11:07 PM
 */

/* ****************************************************************************

    Combinatorial Game Suite - A program to analyze combinatorial games
    Copyright (C) 2003-06  Aaron Siegel (asiegel@users.sourceforge.net)
    http://cgsuite.sourceforge.net/

    Combinatorial Game Suite is free software; you can redistribute it
    and/or modify it under the terms of the GNU General Public License
    as published by the Free Software Foundation; either version 2 of the
    License, or (at your option) any later version.

    Combinatorial Game Suite is distributed in the hope that it will be
    useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Combinatorial Game Suite; if not, write to the Free Software
    Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110, USA

**************************************************************************** */

package org.cgsuite.ui.worksheet;

import java.awt.Font;
import javax.swing.JEditorPane;
import javax.swing.text.BadLocationException;
import org.cgsuite.filetype.cgscript.CgscriptEditorKit;
import org.netbeans.modules.editor.NbEditorDocument;
import org.netbeans.modules.editor.NbEditorKit;
import org.openide.text.CloneableEditorSupport;

public class InputPane extends JEditorPane
{
    public InputPane()
    {
    }
    
    public void activate()
    {
        try
        {
            NbEditorKit kit = new CgscriptEditorKit();
            NbEditorDocument doc = new NbEditorDocument("text/x-cgscript");

            setEditorKit(kit);
            setDocument(doc);
        }
        catch (Exception exc)
        {
            setEnabled(false);
        }
    }
    
    public void deactivate()
    {
        String text = getText();
        Font font = getFont();
        setEditorKit(CloneableEditorSupport.getEditorKit("text/plain"));
        setDocument(new NbEditorDocument("text/plain"));
        setText(text);
        setFont(font);
        setEditable(false);
    }

    public void insert(String str, int pos)
    {
        try
        {
            getDocument().insertString(pos, str, null);
        }
        catch (BadLocationException exc)
        {
        }
    }

    public int getCaretLine()
    {
        return getDocument().getDefaultRootElement().getElementIndex(getCaretPosition());
    }

    public int getLineCount()
    {
        return getDocument().getDefaultRootElement().getElementCount();
    }
}
