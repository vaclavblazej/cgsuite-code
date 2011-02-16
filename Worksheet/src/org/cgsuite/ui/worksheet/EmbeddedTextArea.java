/*
 * EmbeddedTextArea.java
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

import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.event.*;

class EmbeddedTextArea extends javax.swing.JTextArea
{
    private java.util.List<Object> embeddedObjects;
    private Object placeHolder;
    
    public EmbeddedTextArea()
    {
        setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        setLineWrap(true);
        setWrapStyleWord(true);
        setBackground(java.awt.Color.white);
        
        embeddedObjects = new java.util.ArrayList<Object>();
        placeHolder = new Object();

        addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent evt) { thisKeyTyped(evt); }
            public void keyPressed(KeyEvent evt) { thisKeyPressed(evt); }
        });
        
        addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent evt) { thisCaretUpdate(evt); }
        });
        
        getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent evt) {}
            public void removeUpdate(DocumentEvent evt) { thisTextRemoved(evt); }
            public void insertUpdate(DocumentEvent evt) { thisTextInserted(evt); }
        });
    }
    
    private boolean isBlocked(int pos)
    {
        return (pos >= 0 && pos < embeddedObjects.size() &&
            embeddedObjects.get(pos) == placeHolder);
    }
    
    private int findObjectStart(int pos)
    {
        int start;
        for (start = pos; embeddedObjects.get(start) == placeHolder; start--);
        return start;
    }
    
    private int findObjectEnd(int pos)
    {
        int end;
        for (end = pos;
             end < embeddedObjects.size() && embeddedObjects.get(end) == placeHolder;
             end++);
        return end;
    }
    
    private void thisKeyTyped(KeyEvent evt)
    {
        if (isBlocked(getCaretPosition()))
        {
            getToolkit().beep();
            evt.consume();
        }
        else if (evt.getKeyChar() == '\010' && getSelectedText() == null &&
            isBlocked(getCaretPosition()-1))
        {
            // Delete a whole embedded object.
            evt.consume();
            int endPos = getCaretPosition(), startPos;
            // Retreat to the first unblocked character.
            for (startPos = endPos - 1; isBlocked(startPos); startPos--);
            select(startPos, endPos);
            replaceSelection("");
        }
    }
    
    private void thisKeyPressed(KeyEvent evt)
    {
        if (evt.getKeyCode() == KeyEvent.VK_DELETE)
        {
            if (isBlocked(getCaretPosition()))
            {
                getToolkit().beep();
                evt.consume();
            }
            else if (getSelectedText() == null &&
                getCaretPosition() < embeddedObjects.size() &&
                embeddedObjects.get(getCaretPosition()) != null)
            {
                // Delete a whole embedded object.
                evt.consume();
                int startPos = getCaretPosition(), endPos;
                // Advance to the first unblocked character.
                for (endPos = startPos + 1;
                    endPos < embeddedObjects.size() && isBlocked(endPos);
                    endPos++);
                select(startPos, endPos);
                replaceSelection("");
            }
        }
    }

    private void thisCaretUpdate(CaretEvent evt)
    {
        if (evt.getDot() == evt.getMark())
        {
            return;
        }
        
        if (evt.getMark() < embeddedObjects.size() &&
            embeddedObjects.get(evt.getMark()) == placeHolder)
        {
            // The mark is inside an embedded object.
            select(
                Math.min(evt.getDot(), findObjectStart(evt.getMark())),
                Math.max(evt.getDot(), findObjectEnd(evt.getMark()))
                );
        }

        if (evt.getDot() < embeddedObjects.size() &&
            embeddedObjects.get(evt.getDot()) == placeHolder)
        {
            // The caret is inside an embedded object . . .
            if (evt.getMark() < evt.getDot())
            {
                // . . . and the mark is to its left.
                setSelectionEnd(findObjectEnd(evt.getDot()));
            }
            else if (evt.getMark() > evt.getDot())
            {
                // . . . and the mark is to its right.
                setSelectionStart(findObjectStart(evt.getDot()));
            }
        }
    }
    
    private void thisTextInserted(DocumentEvent evt)
    {
        int pos = evt.getOffset();
        for (int i = 0; i < evt.getLength(); i++)
        {
            embeddedObjects.add(i + evt.getOffset(), null);
        }
    }
    
    private void thisTextRemoved(DocumentEvent evt)
    {
        for (int i = 0; i < evt.getLength(); i++)
        {
            embeddedObjects.remove(evt.getOffset());
        }
    }
    
    public void embedObject(int pos, String id, Object object)
    {
        id = "\253" + id + "\273";
        insert(id, pos);
        embeddedObjects.set(pos, object);
        for (int i = 1; i  < id.length(); i++)
        {
            embeddedObjects.set(i + pos, placeHolder);
        }
    }
}
